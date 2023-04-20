package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private boolean hasWhiskers;

	private String name;

	private boolean evil;

	private int length;

	public Cat() {
		super();
	}

	public boolean isHasWhiskers() {
		return hasWhiskers;
	}

	public void setHasWhiskers(boolean hasWhiskers) {
		this.hasWhiskers = hasWhiskers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEvil() {
		return evil;
	}

	public void setEvil(boolean evil) {
		this.evil = evil;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
