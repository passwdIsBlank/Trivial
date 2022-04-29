package com.elorrieta.trivial.model.bean;

import java.io.Serializable;

public class Sala implements Serializable {

	private static final long serialVersionUID = 2252766511103713740L;
	private String room_name;
	private String player_name;
	private boolean is_turn;
	
	public Sala() {
		super();
	}

	public Sala(String room_name, String player_name, boolean is_turn) {
		super();
		this.room_name = room_name;
		this.player_name = player_name;
		this.is_turn = is_turn;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public boolean isIs_turn() {
		return is_turn;
	}

	public void setIs_turn(boolean is_turn) {
		this.is_turn = is_turn;
	}

}
