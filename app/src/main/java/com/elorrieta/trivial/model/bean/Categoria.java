package com.elorrieta.trivial.model.bean;

import java.io.Serializable;

public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 6982430975898612430L;
	private int id;
	private String nombre;

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Categoria() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
