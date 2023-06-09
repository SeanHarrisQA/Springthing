package com.example.demo.domain;

import java.util.Objects;

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

	public Cat(String name, boolean hasWhiskers, boolean evil, int length) {
		super();
		this.hasWhiskers = hasWhiskers;
		this.name = name;
		this.evil = evil;
		this.length = length;
	}

	public Cat(long id, String name, boolean hasWhiskers, boolean evil, int length) {
		super();
		this.id = id;
		this.hasWhiskers = hasWhiskers;
		this.name = name;
		this.evil = evil;
		this.length = length;
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

	@Override
	public int hashCode() {
		return Objects.hash(evil, hasWhiskers, id, length, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		return evil == other.evil && hasWhiskers == other.hasWhiskers && id == other.id && length == other.length
				&& Objects.equals(name, other.name);
	}

}
