package com.elorrieta.trivial.task;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.elorrieta.trivial.client.Client;
import com.elorrieta.trivial.response.UsuarioResponse;

import java.io.IOException;

public class LoginAsyncTask extends AsyncTask<String, Void, Object> {

    private Exception exception;
    private Context context;

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
                c = null;
            }
    }

    protected void onPostExecute(Object response) {
        Log.println(Log.INFO, "RESPONSE", response.toString());
        Toast t = Toast.makeText(this.context, "", Toast.LENGTH_SHORT);

        // TODO Acciones post-login
        try {
            switch ( (int) response ) {
                case UsuarioResponse.LOGIN_OK:
                    t.setText("Logeado Correctamente");
                    break;
                case UsuarioResponse.USER_NOT_FOUND:
                    t.setText("Usuario no encontrado");
                    break;
                case UsuarioResponse.PASSWORD_MISMATCH:
                    t.setText("Contraseña incorrecta");
                    break;
            }

            t.show();
        } catch (Exception e) {
            Log.e("LoginAsyncTask", e.getMessage());
            e.printStackTrace();
        }

    }

    public void setContext(Context context) {
        this.context = context;
    }
}
