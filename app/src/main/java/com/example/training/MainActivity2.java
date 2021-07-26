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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    public EditText mailid, password;
    public Button btnSignUp;
    public TextView tvSignUp;
    public FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        mailid  = findViewById(R.id.mail1);
        password = findViewById(R.id.password1);
        btnSignUp = findViewById(R.id.button1);
        tvSignUp = findViewById(R.id.textView12);


        auth = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(MainActivity2.this, "Вы авторизовались!",Toast.LENGTH_SHORT).show();
                    Intent i  = new Intent(MainActivity2.this,MainActivity3.class);
                    startActivity(i);
                }
else  {Toast.makeText(MainActivity2.this, "Пожалуйста, авторизуйтесь!",Toast.LENGTH_SHORT).show(); }


            }
        };
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailid.getText().toString();
                String pass =password.getText().toString();
                if(email.isEmpty()) { mailid.setError("Введите почту!");
                    mailid.requestFocus();
                }
                if(pass.isEmpty()){
                    password.setError("Введите пароль!");
                    password.requestFocus();
                }
                else if(!(pass.isEmpty()&&email.isEmpty())){
                 firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity2.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful()){
                             Toast.makeText(MainActivity2.this,"Ошибка авторизации!",Toast.LENGTH_LONG).show();
                         }
                         else{

                             Intent k  = new Intent(MainActivity2.this,MainActivity3.class);
                             startActivity(k);

                         }
                     }
                 });
                }
                else{
                    Toast.makeText(MainActivity2.this,"Ошибка авторизации!",Toast.LENGTH_LONG).show();
                }

            }


        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ik = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(ik);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(auth);
    }
    
}
