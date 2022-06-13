package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Log.i(TAG, "onClick login button");
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            loginUser(username, password);
        });
    }

    private void loginUser(String username, String password){
        Log.i(TAG, "Trying to Login user: " + username);
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if(e != null){
                Log.e(TAG, "Issue with Login: ", e);
                Toast.makeText(LoginActivity.this, "Issue with Login", Toast.LENGTH_SHORT).show();
                return;
            }

            goMainActivity();
            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}