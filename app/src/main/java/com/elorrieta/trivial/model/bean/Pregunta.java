package com.elorrieta.trivial.model.bean;

import java.io.Serializable;

public class Pregunta implements Serializable {

	static final long serialVersionUID = -3868709013227106364L;
	private int id;
	private String descripcion;
	private int nivel;
	private Categoria categoria;

	public Pregunta(String descripcion, int nivel, Categoria categoria) {
		super();
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.categoria = categoria;
	}

	public Pregunta() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
