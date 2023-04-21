package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cat;

@Service
public class CatServiceList implements CatService {

	List<Cat> cats = new ArrayList<>();

	@Override
	public Cat createCat(Cat c) {
		this.cats.add(c);
		Cat created = this.cats.get(cats.size() - 1);
		return created;
	}

	@Override
	public List<Cat> getAllCats() {
		return this.cats;
	}

	@Override
	public Cat getCat(long index) {
		return this.cats.get((int) index);
	}

//	@Override
//	public Cat deleteCat(long id) {
//		return this.cats.remove(id);
//	}

	@Override
	public Cat updateCat(long id, String name, Boolean hasWhiskers, Boolean evil, Integer length) {
		Cat catToChange = this.cats.get((int) id);
		if (name != null)
			catToChange.setName(name);
		if (hasWhiskers != null)
			catToChange.setHasWhiskers(hasWhiskers);
		if (evil != null)
			catToChange.setEvil(evil);
		if (length != null)
			catToChange.setLength(length);

		return this.cats.get((int) id);
	}

	@Override
	public Cat deleteCat(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
