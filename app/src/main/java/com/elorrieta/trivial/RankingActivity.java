package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.elorrieta.trivial.task.RankingAsyncTask;
import com.elorrieta.trivial.task.UserInformationAsyncTask;

import java.util.Objects;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Objects.requireNonNull(getSupportActionBar()).hide();
        SharedPreferences sharedpreferences = this.getSharedPreferences("session", Context.MODE_PRIVATE);

        new RankingAsyncTask(this, "RANKING").execute();
        new UserInformationAsyncTask(this, "USER_INFO " + sharedpreferences.getString("username", "")).execute();
    }
}