package com.elorrieta.trivial.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.elorrieta.trivial.LoadingActivity;
import com.elorrieta.trivial.client.Client;
import com.elorrieta.trivial.response.UsuarioResponse;

import java.io.IOException;

public class LoginAsyncTask extends AsyncTask<String, Void, Object> {

    private Context context;

    public LoginAsyncTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(String... cmd) {
        Client c = new Client();
        try {
            // Ignora el resto de cmds :D
            c.getStream().writeObject(cmd[0]);

            return c.getStream().readObject();
        } catch (ClassNotFoundException e) {
            Log.e("LoginAsyncTask", "Error de casteo de I/O");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            c.getStream().close();
        }
    }

    protected void onPostExecute(Object response) {
        Log.println(Log.INFO, "RESPONSE", response.toString());
        Toast t = Toast.makeText(this.context, "", Toast.LENGTH_SHORT);

        try {
            switch ( (int) response ) {
                case UsuarioResponse.LOGIN_OK:
                    t.setText("Logeado Correctamente");
                    Intent intent = new Intent(this.context, LoadingActivity.class);
                    this.context.startActivity(intent);
                    ((Activity)this.context).finish();
                    break;
                case UsuarioResponse.USER_NOT_FOUND:
                    t.setText("Usuario no encontrado");
                    break;
                case UsuarioResponse.PASSWORD_MISMATCH:
                    t.setText("ContraseÃ±a incorrecta");
                    break;
            }

            t.show();
        } catch (Exception e) {
            Log.e("LoginAsyncTask", e.getMessage());
            e.printStackTrace();
        }

    }
}