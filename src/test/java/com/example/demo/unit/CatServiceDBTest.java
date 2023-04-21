package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.domain.Cat;
import com.example.demo.repo.CatRepo;
import com.example.demo.service.CatService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CatServiceDBTest {

	@Autowired
	private CatService service;

	@MockBean
	private CatRepo repo;

	@Test
	void testUpdate() {
		long id = 1L;
		Cat existing = new Cat(1L, "Manny", true, true, 13);
		Cat updated = new Cat(1L, "Manny", false, false, 42);
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

		assertEquals(updated, this.service.updateCat(id, updated.getName(), updated.isHasWhiskers(), updated.isEvil(),
				updated.getLength()));

		Mockito.verify(repo, Mockito.times(1)).findById(id);
		Mockito.verify(repo, Mockito.times(1)).save(updated);

	}

	@Test
	void testCreate() {
		long id = 1L;
		Cat xC = new Cat(1L, "Manny", true, true, 13);

		Mockito.when(this.repo.save(xC)).thenReturn(xC);

		assertEquals(xC, this.service.createCat(xC));

		Mockito.verify(repo, Mockito.times(1)).save(xC);

	}

	@Test
	void testGetAllCats() {
		List<Cat> xC = new ArrayList<>();
		Cat expectedCat = new Cat(1L, "Manny", true, true, 13);
		xC.add(expectedCat);

		Mockito.when(this.repo.findAll()).thenReturn(xC);

		assertEquals(xC, this.service.getAllCats());

		Mockito.verify(repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDeleteCat() {
		long id = 1L;
		Cat xC = new Cat(1L, "Manny", true, true, 13);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(xC));

		assertEquals(xC, this.service.deleteCat(id));

		Mockito.verify(repo, Mockito.times(1)).findById(id);
	}

	@Test
	void testGetCat() {
		long id = 1L;
		Cat xC = new Cat(1L, "Manny", true, true, 13);

		Mockito.when(repo.findById(id)).thenReturn(Optional.of(xC));

		assertEquals(xC, this.service.getCat(id));

		Mockito.verify(repo, Mockito.times(1)).findById(id);
	}
}
