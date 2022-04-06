package com.elorrieta.trivial.client;

import android.util.Log;

import com.elorrieta.trivial.coms.ObjectStream;
import com.elorrieta.trivial.configuration.ServerConnectionParams;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private Socket client;
	private ObjectStream stream;
	
	public Client() {
		try {
			client = new Socket(ServerConnectionParams.IP, ServerConnectionParams.PORT);
			stream = new ObjectStream(client);
		} catch (UnknownHostException e) {
			System.out.println("Host remoto desconocido");
			Log.println(Log.ERROR, "Error", "hola");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al conectar con el host remoto");
		}
	}
	
	public ObjectStream getStream() {
		return this.stream;
	}
}
