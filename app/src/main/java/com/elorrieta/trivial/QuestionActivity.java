package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.elorrieta.trivial.model.bean.Pregunta;
import com.elorrieta.trivial.response.UsuarioResponse;
import com.elorrieta.trivial.task.ClientTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    private Pregunta pregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        Intent intent = getIntent();

        ArrayList<Integer> idPreguntasRespondidas = intent.getIntegerArrayListExtra("idPreguntasRespondidas");
        String categoria = intent.getStringExtra("categoria");
        int numPregunta = 1;//idPreguntasRespondidas.size() + 1;

        ClientTask task = new ClientTask("QUESTION_PER_CATEGORY_LEVEL " + categoria + "," + numPregunta);

        try {
            task.join();
            preguntas = (ArrayList<Pregunta>) task.getResponse();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        do {
            pregunta = preguntas.get( ThreadLocalRandom.current().nextInt(0, preguntas.size()));
        } while (idPreguntasRespondidas.contains(pregunta.getId()));

        //No voy a negar que está feo, pero ahora no me apetece hacer los botones con un bucle. Otro día será
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);

        ((TextView) findViewById(R.id.lblPregunta)).setText(pregunta.getDescripcion());
        ((TextView) findViewById(R.id.lblNumPregunta)).setText(String.valueOf(numPregunta));

        btnA.setText(pregunta.getRespuestas().get(0).getDescripcion());
        btnA.setTag(pregunta.getRespuestas().get(0).isEsCorrecta());

        btnB.setText(pregunta.getRespuestas().get(1).getDescripcion());
        btnB.setTag(pregunta.getRespuestas().get(1).isEsCorrecta());

        btnC.setText(pregunta.getRespuestas().get(2).getDescripcion());
        btnC.setTag(pregunta.getRespuestas().get(2).isEsCorrecta());

        btnD.setText(pregunta.getRespuestas().get(3).getDescripcion());
        btnD.setTag(pregunta.getRespuestas().get(3).isEsCorrecta());

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        Boolean isCorrect = Boolean.parseBoolean(btn.getTag().toString());
        int acierto = 0;
        int fallo = 0;
        String username;
        SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
        username = sharedPref.getString("username", "");

        if (!isCorrect) {
            btn.setBackgroundColor(getColor(R.color.incorrect));
            fallo++;
        } else {
            btn.setBackgroundColor(getColor(R.color.correct));
            acierto++;
        }

        ClientTask task = new ClientTask("UPDATE_USER_STATS " +  username + "," + acierto + "," + fallo);

        try {
            task.join();

            switch ( (int) task.getResponse() ) {
                case UsuarioResponse.USER_STATS_UPDATE_OK:
                    Log.println(Log.INFO, "RESPONSE", "Estado del jugador actualizado");

                    break;
                case UsuarioResponse.USER_STATS_UPDATE_ERROR:
                    Log.println(Log.ERROR, "RESPONSE", "Error al actualizar estado del jugador");
                    break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        retrieveAnswer(isCorrect);
    }

    private void retrieveAnswer(boolean isCorrect) {
        Intent intent = new Intent();
        intent.putExtra("es_correcta", isCorrect);
        intent.putExtra("idPregunta", pregunta.getId());

        setResult(RESULT_OK, intent);
        finish();
    }
}