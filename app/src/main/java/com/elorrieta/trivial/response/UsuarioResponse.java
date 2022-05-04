package com.elorrieta.trivial.response;

public class UsuarioResponse {

	public final static int LOGIN_OK = 0;
	public final static int PASSWORD_MISMATCH = 1;
	public final static int USER_NOT_FOUND = -1;
	
	public final static int REGISTER_OK = 0;
	public final static int USER_ALREADY_EXISTS = 1;
	public final static int REGISTER_ERROR = -1;
	
	public final static int USER_STATS_UPDATE_OK = 0;
	public final static int USER_STATS_UPDATE_ERROR = 1;
	
	public final static int USER_DELETION_OK = 0;
	public final static int USER_DELETION_ERROR = 1;

}
