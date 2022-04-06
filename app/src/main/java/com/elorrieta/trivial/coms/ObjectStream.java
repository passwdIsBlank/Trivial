package com.elorrieta.trivial.coms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectStream {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ObjectStream(Socket s) {
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			System.out.println("Error loading communications");
		}
	}
	
	public Object readObject() throws ClassNotFoundException, IOException {
		return this.in.readObject();
	}

	public void writeObject(Object obj) throws ClassNotFoundException, IOException {
		this.out.writeObject(obj);
	}
	
	public void close() {
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar la conexi�n con el cliente");
		}
	}
}
