package com.elorrieta.trivial.task;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elorrieta.trivial.R;
import com.elorrieta.trivial.client.Client;
import com.elorrieta.trivial.model.bean.Usuario;
import com.elorrieta.trivial.response.UsuarioResponse;

import java.io.IOException;

public class TimerAsyncTask extends AsyncTask<Void, Void, Object> {

    private Activity activity;

    public TimerAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ProgressBar pBar = activity.findViewById(R.id.timerPregunta);
        do {
            try {
                Thread.sleep(100);
                pBar.setProgress(pBar.getProgress() + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (pBar.getProgress() < pBar.getMax());
        return null;
    }

    protected void onPostExecute(Object response) {
        SharedPreferences sharedPref = activity.getSharedPreferences("session", Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "");

        ClientTask task = new ClientTask("UPDATE_USER_STATS " + username + ",0,1");

        try {
            task.join();

            switch ((int) task.getResponse()) {
                case UsuarioResponse.USER_STATS_UPDATE_OK:
                    Log.println(Log.INFO, "RESPONSE", "Estado del jugador actualizado");

                    break;
                case UsuarioResponse.USER_STATS_UPDATE_ERROR:
                    Log.println(Log.ERROR, "RESPONSE", "Error al actualizar estado del jugador");
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Intent intent = activity.getIntent();
            intent.putExtra("es_correcta", false);

            activity.setResult(Activity.RESULT_OK, intent);
            activity.finish();
        }
    }
}