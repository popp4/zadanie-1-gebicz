package com.example.lekcja_1_gebicz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputLastName;
    private EditText inputPassword;
    private EditText inputEmail;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputName = findViewById(R.id.Name_input);
        inputLastName = findViewById(R.id.Last_name_input);
        inputPassword = findViewById(R.id.Password_input);
        inputEmail = findViewById(R.id.Email_input);
        registerButton = findViewById(R.id.Register);

        registerButton.setOnClickListener(v -> {
            String imie = inputName.getText().toString().trim();
            String nazwisko = inputLastName.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String haslo = inputPassword.getText().toString();

            if (imie.isEmpty() || nazwisko.isEmpty()
                    || email.isEmpty() || haslo.isEmpty()) {
                pokazKomunikat("Wypelnij wszystkie pola");
                return;
            }

            if (!czyEmailPoprawny(email)) {
                pokazKomunikat("Email musi zawierac nazwe i znak @");
                return;
            }

            if (haslo.length() < 16) {
                pokazKomunikat("Haslo musi miec co najmniej 16 znakow");
                return;
            }

            if (!czyHasloPoprawne(haslo)) {
                pokazKomunikat(
                        "Haslo musi zawierac mala litere, duza litere, cyfre i znak specjalny"
                );
                return;
            }

            pokazKomunikat("Zarejestrowano");
        });
    }

    private boolean czyEmailPoprawny(String email) {
        int pozycjaMalpy = email.indexOf('@');

        return pozycjaMalpy > 0
                && pozycjaMalpy == email.lastIndexOf('@')
                && pozycjaMalpy < email.length() - 1;
    }

    private boolean czyHasloPoprawne(String haslo) {
        boolean mala = false;
        boolean duza = false;
        boolean cyfra = false;
        boolean specjalny = false;

        for (char znak : haslo.toCharArray()) {
            if (Character.isLowerCase(znak)) {
                mala = true;
            } else if (Character.isUpperCase(znak)) {
                duza = true;
            } else if (Character.isDigit(znak)) {
                cyfra = true;
            } else if (!Character.isWhitespace(znak)) {
                specjalny = true;
            }
        }

        return mala && duza && cyfra && specjalny;
    }

    private void pokazKomunikat(String tekst) {
        Toast.makeText(
                MainActivity.this,
                tekst,
                Toast.LENGTH_SHORT
        ).show();
    }
}