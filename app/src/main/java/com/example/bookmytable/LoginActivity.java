package com.example.bookmytable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText username , password;
    TextView registerHereText;
    Button login , register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
        registerHereText = findViewById(R.id.registerTextView);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    //TODO add more checks like if its a valid email or not etc etc
                    // TODO call firebase function "signInWithEmailAndPassword(email,password)

                }
            }
        });


    }

    public void register(View view) {
        // if "New User? Click Here to Register" or Register Button Clicked
        // GO TO register activity


    }
}
