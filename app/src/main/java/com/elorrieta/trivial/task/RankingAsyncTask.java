package com.elorrieta.trivial.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.elorrieta.trivial.R;
import com.elorrieta.trivial.adapter.RankingAdapter;
import com.elorrieta.trivial.model.bean.Usuario;
import com.elorrieta.trivial.client.Client;

import java.io.IOException;
import java.util.ArrayList;

public class RankingAsyncTask extends AsyncTask<Void, Void, Object> {

    private Activity activity;
    private String cmd;

    public RankingAsyncTask(Activity activity, String cmd) {
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
        ArrayList<Usuario> ranking = (ArrayList<Usuario>) response;
        Log.println(Log.INFO, "RESPONSE", String.valueOf(ranking.size()));

        try {
            ListView listRanking = activity.findViewById(R.id.listRanking);
            RankingAdapter adapter = new RankingAdapter(activity, ranking);

            listRanking.setAdapter(adapter);
        } catch (Exception e) {
            Log.e(this.getClass().toString(), e.getMessage());
            e.printStackTrace();
        }

    }
}