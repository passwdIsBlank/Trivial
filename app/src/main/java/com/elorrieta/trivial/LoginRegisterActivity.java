package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.elorrieta.trivial.task.LoginAsyncTask;
import com.elorrieta.trivial.task.RegisterAsyncTask;

import java.util.Objects;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        int colorInactive = getColor(R.color.colorInactive);
        int colorActive = getColor(R.color.colorActive);

        Objects.requireNonNull(getSupportActionBar()).hide();

        ((Button) findViewById(R.id.btnLoginRegister)).setOnClickListener( (v) -> {
                EditText textUsername = findViewById(R.id.textUsername);
                EditText textPassword = findViewById(R.id.textPassword);
                EditText textPasswordCheck = findViewById(R.id.textPasswordCheck);

                if (textPasswordCheck.getVisibility() == View.VISIBLE)
                    if (textPassword.getText().toString().equals(textPasswordCheck.getText().toString()))
                        new RegisterAsyncTask(LoginRegisterActivity.this, "REGISTER " + textUsername.getText().toString() + "," + textPassword.getText().toString()).execute();
                    else {
                        Toast toast = Toast.makeText(LoginRegisterActivity.this, "", Toast.LENGTH_SHORT);
                        toast.setText(getText(R.string.errorPasswordMismatch));
                        toast.show();
                    }
                else
                    new LoginAsyncTask(LoginRegisterActivity.this, "LOGIN " + textUsername.getText().toString() + "," + textPassword.getText().toString()).execute();
        });

        ((TextView) findViewById(R.id.lblLogin)).setOnClickListener( (v) -> {
            ((TextView) findViewById(R.id.lblRegister)).setTextColor(colorInactive);
            ((TextView) findViewById(R.id.lblLogin)).setTextColor(colorActive);

            ((TextView) findViewById(R.id.lblPasswordCheck)).setVisibility(View.INVISIBLE);

            ((EditText) findViewById(R.id.textPasswordCheck)).setVisibility(View.INVISIBLE);
            ((EditText) findViewById(R.id.textPasswordCheck)).setText("");

            ((Button) findViewById(R.id.btnLoginRegister)).setText(getString(R.string.btnLogin));
        });

        ((TextView) findViewById(R.id.lblRegister)).setOnClickListener( (v) -> {
            ((TextView) findViewById(R.id.lblRegister)).setTextColor(colorActive);
            ((TextView) findViewById(R.id.lblLogin)).setTextColor(colorInactive);

            ((TextView) findViewById(R.id.lblPasswordCheck)).setVisibility(View.VISIBLE);

            ((EditText) findViewById(R.id.textPasswordCheck)).setVisibility(View.VISIBLE);
            ((EditText) findViewById(R.id.textPasswordCheck)).setText("");

            ((Button) findViewById(R.id.btnLoginRegister)).setText(getString(R.string.btnRegister));
        });

    }
}