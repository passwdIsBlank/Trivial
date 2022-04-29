package com.elorrieta.trivial.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.elorrieta.trivial.R;
import com.elorrieta.trivial.adapter.RankingAdapter;
import com.elorrieta.trivial.client.Client;
import com.elorrieta.trivial.model.bean.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class UserInformationAsyncTask extends AsyncTask<Void, Void, Object> {

    private Activity activity;
    private String cmd;

    public UserInformationAsyncTask(Activity activity, String cmd) {
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
        Usuario usuario = (Usuario) response;
        Log.println(Log.INFO, "RESPONSE", usuario.getUsername());

        try {
            ((TextView) activity.findViewById(R.id.lblNumAciertosCount)).setText(String.valueOf(usuario.getAciertos()));
            ((TextView) activity.findViewById(R.id.lblNumFallosCount)).setText(String.valueOf(usuario.getFallos()));
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage());
            e.printStackTrace();
        }

    }
}