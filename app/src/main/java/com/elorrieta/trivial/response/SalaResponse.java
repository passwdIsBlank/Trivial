package com.elorrieta.trivial.response;

public class SalaResponse {

	public final static int CREATION_OK = 0;
	public final static int CREATION_ERROR = 1;
	public final static int ROOM_ALREADY_CREATED = -1;

	public final static int JOIN_OK = 0;
	public final static int JOIN_ERROR = 1;
	public final static int ALREADY_JOINED = -1;
	
	public final static int DELETE_OK = 0;
	public final static int ROOM_DOES_NOT_EXIST = 1;
	
	public final static int DISCONNECT_OK = 0;
	public final static int NOT_CONNECTED_TO_ANY_ROOM = 1;
}
