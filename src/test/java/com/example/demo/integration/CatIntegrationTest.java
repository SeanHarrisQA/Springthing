package com.example.demo.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.domain.Cat;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:cat-schema.sql", "classpath:cat-data.sql" })
public class CatIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Cat newCat = new Cat("Tom", true, true, 12);
		String newCatAsJson = this.mapper.writeValueAsString(newCat);
		// Post method doesn't exist in this class but MockMvcRequestBuilders.post has
		// been imported statically
		RequestBuilder req = post("/create").content(newCatAsJson).contentType(MediaType.APPLICATION_JSON);

		ResultMatcher checkStatus = status().isCreated();
		Cat created = new Cat(2L, "Tom", true, true, 12);
		String createdAsJson = this.mapper.writeValueAsString(created);
		ResultMatcher checkBody = content().json(createdAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetAll() throws Exception {
		List<Cat> xC = new ArrayList<>();
		Cat expectedCat = new Cat(1L, "Manny", true, true, 13);
		xC.add(expectedCat);
		String xCAsJson = this.mapper.writeValueAsString(xC);

		RequestBuilder req = get("/getAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(xCAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testGetCat() throws Exception {
		long id = 1L;
		Cat xC = new Cat(id, "Manny", true, true, 13);
		String xCAsJson = this.mapper.writeValueAsString(xC);

		RequestBuilder req = get("/get/" + id);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(xCAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDeleteCat() throws Exception {
		long id = 1L;
		Cat xC = new Cat(id, "Manny", true, true, 13);
		String xCAsJson = this.mapper.writeValueAsString(xC);

		RequestBuilder req = delete("/remove/" + id);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(xCAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdateCat() throws Exception {
		long id = 1L;
		Cat xC = new Cat(id, "Moriarty", false, false, 10);
		String xCAsJson = this.mapper.writeValueAsString(xC);
		RequestBuilder req = patch("/update/" + id).queryParam("name", "Moriarty").queryParam("hasWhiskers", "false")
				.queryParam("evil", "false").queryParam("length", "10");
		// This is a scuffed way to do it, req is better than reqScuffed
		@SuppressWarnings("unused")
		RequestBuilder reqScuffed = patch("/update/" + id + "?name=Moriarty&evil=false&hasWhiskers=false&length=10");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(xCAsJson);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

}
