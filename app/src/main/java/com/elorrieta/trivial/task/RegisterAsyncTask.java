package com.elorrieta.trivial.task;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.elorrieta.trivial.R;
import com.elorrieta.trivial.client.Client;
import com.elorrieta.trivial.response.UsuarioResponse;

import java.io.IOException;

public class RegisterAsyncTask extends AsyncTask<Void, Void, Object> {

    private Activity activity;
    private String cmd;

    public RegisterAsyncTask(Activity activity, String cmd) {
        this.activity = activity;
        this.cmd = cmd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Void... voids) {
        Client c = new Client();
        try {
            c.getStream().writeObject(cmd);

            return c.getStream().readObject();
        } catch (ClassNotFoundException e) {
            Log.e(this.getClass().toString(), "Error de casteo de I/O");
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
        Toast toast = Toast.makeText(activity, "", Toast.LENGTH_SHORT);

        try {
            switch ( (int) response ) {
                case UsuarioResponse.REGISTER_OK:
                    toast.setText(activity.getString(R.string.registerOk));

                    SharedPreferences sharedpreferences = activity.getSharedPreferences("session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString("username", cmd.split(" ")[1].split(",")[0])
                            .apply();

                    activity.setResult(Activity.RESULT_OK);
                    activity.finish();
                    break;
                case UsuarioResponse.REGISTER_ERROR:
                    toast.setText(activity.getString(R.string.errorRegister));
                    break;
                case UsuarioResponse.USER_ALREADY_EXISTS:
                    toast.setText(activity.getString(R.string.errorRegisterUserExists));
                    break;
            }

            toast.show();
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage());
            e.printStackTrace();
        }

    }
}