package com.elorrieta.trivial.model.bean;

import java.io.Serializable;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 631143809863479729L;
	private String username;
	private String password;
	private int aciertos;
	private int fallos;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String username, String password, int aciertos, int fallos) {
		super();
		this.username = username;
		this.password = password;
		this.aciertos = aciertos;
		this.fallos = fallos;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public int getFallos() {
		return fallos;
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;
	}
}
