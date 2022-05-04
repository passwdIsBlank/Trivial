package com.elorrieta.trivial.task;

import android.util.Log;

import com.elorrieta.trivial.client.Client;

import java.io.IOException;

public class ClientTask extends Thread {

    private Object response;
    private String cmd;

    public ClientTask(String cmd) {
        this.cmd = cmd;
        this.start();
    }

    @Override
    public void run() {
        Client c = new Client();
        try {
            c.getStream().writeObject(cmd);
            response =  c.getStream().readObject();
        } catch (ClassNotFoundException e) {
            Log.e(this.getClass().toString(), "Error de casteo de I/O");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            c.getStream().close();
        }
    }

    public Object getResponse() {
        return response;
    }
}