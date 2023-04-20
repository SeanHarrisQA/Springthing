package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Cat;

public interface CatService {

	Cat createCat(Cat c);

	List<Cat> getAllCats();

	Cat getCat(int index);

	Cat deleteCat(int id);

	Cat updateCat(int id, String name, Boolean hasWhiskers, Boolean evil, Integer length);

}
