package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
public EditText mailid, password;
public Button btnSignUp;
public TextView tvSignUp;
public FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        mailid  = findViewById(R.id.mail1);
        password = findViewById(R.id.password1);
        btnSignUp = findViewById(R.id.button1);
        tvSignUp = findViewById(R.id.textView1);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailid.getText().toString();
                String pass =password.getText().toString();
                if(email.isEmpty()) { mailid.setError("Введите почту!");
                mailid.requestFocus();
                }
              else if(pass.isEmpty()){
                    password.setError("Введите пароль!");
                    password.requestFocus();
                }

                else if(!(pass.isEmpty()&&email.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Не получилось зарегистрироваться, попробуйте еще раз!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this,MainActivity3.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Ошибка регистрации!",Toast.LENGTH_SHORT).show();
                }
            }
        });
tvSignUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(i);

    }
});
    }
}