package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cat;
import com.example.demo.exception.CatNotFoundException;
import com.example.demo.repo.CatRepo;

@Primary
@Service
public class CatServiceDB implements CatService {

	private CatRepo repo;

	public CatServiceDB(CatRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Cat createCat(Cat c) {
		return this.repo.save(c);
	}

	@Override
	public List<Cat> getAllCats() {
		return this.repo.findAll();
	}

	@Override
	public Cat getCat(long id) {
		// Find by id returns an optional, will either be null or a cat, the get()
		// forces out the value to be a Cat
		return this.repo.findById((long) id).orElseThrow(CatNotFoundException::new);
	}

	@Override
	public Cat deleteCat(long id) {
		Cat removed = this.getCat(id);
		this.repo.deleteById(id);
		return removed;
	}

	@Override
	public Cat updateCat(long id, String name, Boolean hasWhiskers, Boolean evil, Integer length) {
		Cat toUpdate = this.getCat(id);
		if (name != null)
			toUpdate.setName(name);
		if (hasWhiskers != null)
			toUpdate.setHasWhiskers(hasWhiskers);
		if (evil != null)
			toUpdate.setEvil(evil);
		if (length != null)
			toUpdate.setLength(length);
		return this.repo.save(toUpdate);
	}

}
