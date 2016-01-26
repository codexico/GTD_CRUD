package com.example.fk.gtd_crud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private final String GTD_PREFS = "GTD_PREFS";
    private final String KEEPSIGNEDIN = "KEEPSIGNEDIN";

    @Bind(R.id.etName)
    EditText etName;

    @Bind(R.id.etPassword)
    EditText etPassword;

    @Bind(R.id.cbStay_signed_in)
    CheckBox cbStay_signed_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        if (isSignedIn()) {
            goToMainList();
        }
    }

    @OnClick(R.id.btSignIn)
    public void login() {
        if (isValidUser()) {
            staySignedIn();
            goToMainList();
        }
    }

    private void goToMainList() {
        Intent i = new Intent(LoginActivity.this, MainListActivity.class);
        startActivity(i);
        finish();
    }

    private boolean isValidUser() {
        boolean valid = false;

        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        if (name.equals("admin") && password.equals("admin")) {
            valid = true;
        } else {
            Toast.makeText(LoginActivity.this, R.string.Login_incorrect, Toast.LENGTH_LONG).show();
        }

        return valid;
    }

    private void staySignedIn() {
        SharedPreferences settings = getSharedPreferences(GTD_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEEPSIGNEDIN, cbStay_signed_in.isChecked());
        editor.apply();
    }

    private boolean isSignedIn() {
        SharedPreferences settings = getSharedPreferences(GTD_PREFS, MODE_PRIVATE);

        return settings.getBoolean(KEEPSIGNEDIN, false);
    }


}
