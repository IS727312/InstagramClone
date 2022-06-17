package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SingUpActivity";
    private EditText etSignUpPassword;
    private EditText etSignUpConPassword;
    private EditText etSignUpUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignUpPassword = findViewById(R.id.etSignUpPassword);
        etSignUpConPassword = findViewById(R.id.etSignUpConPassword);
        etSignUpUsername = findViewById(R.id.etSignUpUsername);
        Button btnFinalSignUp = findViewById(R.id.btnFinalSignup);

        btnFinalSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = etSignUpPassword.getText().toString();
                String conPass = etSignUpConPassword.getText().toString();
                String user = etSignUpUsername.getText().toString();
                if(pass.isEmpty() || user.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Some inputs are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!conPass.equals(pass)){
                    Toast.makeText(SignUpActivity.this,"Passwords are different", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseUser newUser = new ParseUser();
                newUser.setUsername(user);
                newUser.setPassword(pass);
                newUser.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Log.e(TAG, "Failed to Sign Up " + e);
                        }
                    }
                });
            }
        });
    }
}