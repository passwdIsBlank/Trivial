package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elorrieta.trivial.model.bean.Categoria;
import com.elorrieta.trivial.task.ClientTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CategoryActivity extends AppCompatActivity {
    private static final Random RANDOM = new Random();
    private int degree = 0;
    private int degreeOld = 0;
    //private ArrayList<Categoria> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Objects.requireNonNull(getSupportActionBar()).hide();

        //getCategories();

        ((Button) findViewById(R.id.btnSpinRoulette)).setOnClickListener( (v) -> {
            ((Button) v).setEnabled(false);
            //ImageView roulette = findViewById(R.id.imgRoulette);
            Roulette roulette = findViewById(R.id.roulette);
            degreeOld = degree % 360;

            degree = RANDOM.nextInt(360) + 720;

            RotateAnimation rotate = new RotateAnimation(degreeOld, degree,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(3600);
            rotate.setFillAfter(true);
            rotate.setInterpolator(new DecelerateInterpolator());
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    TextView textCategoria = findViewById(R.id.lblCategory);
                    int nVueltas = degree / 360;
                    float gradosRecorridos = ( ((float)degree / 360) - nVueltas ) * 360;
                    int categoriaSeleccionada = (int)(360 - gradosRecorridos) / ( 360 / 5 );

                    textCategoria.setVisibility(View.VISIBLE);
                    textCategoria.setText( roulette.categories.get(categoriaSeleccionada).getNombre() );
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            roulette.startAnimation(rotate);

        });
    }

    /*private void getCategories() {
        ClientTask task = new ClientTask("CATEGORIES");

        try {
            task.join();
            categories = (ArrayList<Categoria>) task.getResponse();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}