package com.elorrieta.trivial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.elorrieta.trivial.model.bean.Pregunta;
import com.elorrieta.trivial.task.ClientTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CategoryActivity extends AppCompatActivity {
    private final int QUESTION_ACTIVITY = 1;

    private static final ArrayList<Integer> idPreguntasRespondidas = new ArrayList<>();

    private static final Random RANDOM = new Random();
    private int degree = 0;
    private int degreeOld = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ((Button) findViewById(R.id.btnSpinRoulette)).setOnClickListener( (v) -> {
            ((Button) v).setEnabled(false);
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
                    String categoryName = roulette.categories.get(categoriaSeleccionada).getNombre();


                    textCategoria.setVisibility(View.VISIBLE);
                    textCategoria.setText( categoryName );

                    Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                    intent.putExtra("categoria", categoryName);
                    intent.putIntegerArrayListExtra("idPreguntasRespondidas", idPreguntasRespondidas);
                    startActivityForResult(intent, QUESTION_ACTIVITY);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            roulette.startAnimation(rotate);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == QUESTION_ACTIVITY && resultCode == RESULT_OK) {
            resetActivity();

            if (data != null || idPreguntasRespondidas.size() == 4) {
                if (!data.getBooleanExtra("es_correcta", false)) {
                    finish();
                }
                idPreguntasRespondidas.add(data.getIntExtra("idPregunta", -1));

            }
        }
    }

    private void resetActivity() {
        ((Roulette) findViewById(R.id.roulette)).setRotation(0);
        ((Button) findViewById(R.id.btnSpinRoulette)).setEnabled(true);
        ((TextView) findViewById(R.id.lblCategory)).setVisibility(View.INVISIBLE);
    }
}