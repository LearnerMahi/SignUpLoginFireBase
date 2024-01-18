package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    EditText signupName,signupEmail,signupPassword,signupUsername;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupName=findViewById(R.id.signup_name);
        signupEmail=findViewById(R.id.signup_email);
        signupPassword=findViewById(R.id.signup_password);
        signupUsername=findViewById(R.id.signup_username);
        signupButton=findViewById(R.id.signup_button);
        loginRedirectText=findViewById(R.id.loginRedirectText);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("Users");
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(SignUpActivity.this, "signed up", Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpActivity.this, LoginAcitivity.class));
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginAcitivity.class));
            }
        });
    }
}
