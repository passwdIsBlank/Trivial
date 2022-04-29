package com.elorrieta.trivial.model.bean;

import java.io.Serializable;

public class Respuesta implements Serializable {

	private static final long serialVersionUID = 5622685802833838026L;
	private int id;
	private String descripcion;
	private boolean esCorrecta;
	private Pregunta pregunta;
	
	public Respuesta(String descripcion, boolean esCorrecta, Pregunta pregunta) {
		super();
		this.descripcion = descripcion;
		this.esCorrecta = esCorrecta;
		this.pregunta = pregunta;
	}

	public Respuesta() {
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

	public boolean isEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
}
