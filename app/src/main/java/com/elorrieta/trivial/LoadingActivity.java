package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoadingActivity extends AppCompatActivity {

    class LoadingActivityAsyncTask extends AsyncTask<Integer, Integer, String> {

        /**
         * Method called before task execution on UI thread. Disables the user
         * interaction with the screen. Blocks user touch events, so he cannot
         * do anything wrong.
         */
        @Override
        protected void onPreExecute() {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            );
        }

        /**
         * Method called just after execution of onPreExecute() in background thread.
         *
         * @param params The params
         * @return Empty string
         */
        @Override
        protected String doInBackground(Integer... params) {

            // TODO comprobación de usuarios en espera

            return "";
        }

        /**
         * Method is called on UI thread just after completion of doInBackground(Params...) and the
         * result is passed to onPostExecute(Result). Enables the user interaction with the screen.
         * Unblocks user touch events.
         *
         * We also make the textView visible...
         *
         * @param result the result
         */
        @Override
        protected void onPostExecute(String result) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading );

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor( Color.parseColor("#ffffff"));

        constraintLayout.setOnTouchListener((v, m) -> {
            Intent intent = new Intent( LoadingActivity.this, MainActivity.class );
            startActivity(intent);
            return true;
        });

        ImageView imageView = findViewById(R.id.imageLoading);
        Glide.with(this).load(R.raw.loading).into(imageView);

        new LoadingActivityAsyncTask().execute();
    }
}