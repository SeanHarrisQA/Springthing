package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Cat;

public interface CatService {

	Cat createCat(Cat c);

	List<Cat> getAllCats();

	Cat getCat(long index);

	Cat deleteCat(long id);

	Cat updateCat(long id, String name, Boolean hasWhiskers, Boolean evil, Integer length);

}
