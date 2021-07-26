package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    public Button btnLogOut;
    FirebaseAuth firebaseAuth;
    private TextView zametka;
    private Button btnzametka;
    private EditText editzametka;
    private EditText edittxt;
    private  FirebaseAuth.AuthStateListener auth;
    private  FirebaseAuth aut;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private ListView listView;
    private ImageButton delbtn0;
    private ImageButton delbtn1;
    private ImageButton delbtn2;
    private ImageButton delbtn3;
    private ImageButton delbtn4;
    private ImageButton delbtn5;
    private ImageButton delbtn6;
    private ImageButton delbtn7;
    private ImageButton delbtn8;
    private ImageButton delbtn9;
    private ImageButton delbtn10;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);
        btnLogOut = findViewById(R.id.button55);

        btnzametka = findViewById(R.id.buttonzamet);
        editzametka = findViewById(R.id.editTextTextEmailAddresszamet);
        edittxt = findViewById(R.id.editTextTextEmailAddresszamet2);

        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listView = findViewById(R.id.list_view);



        ArrayList<note> str  = new ArrayList<>();


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference usersRef = rootRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    str.add(new note(ds.getKey(),ds.getValue().toString()));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        usersRef.addListenerForSingleValueEvent(eventListener);
        noteListAdapter adapter = new noteListAdapter(this, R.layout.adapter_design,str);
        listView.setAdapter(adapter);




        btnzametka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittxt.getText().toString().isEmpty() || editzametka.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity3.this,"Создайте заголовок и текст заметки!",Toast.LENGTH_LONG).show();
                }
            else{   str.add(new note(editzametka.getText().toString(),edittxt.getText().toString()));
                    listView.setAdapter(adapter);
                    Map<String,Object> childUpdates = new HashMap<String, Object>();
                    childUpdates.put("Users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/"+editzametka.getText().toString(),edittxt.getText().toString());
                    mDatabase.updateChildren(childUpdates);
            }



            }
        });





        btnLogOut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            Intent out  = new Intent(MainActivity3.this,MainActivity.class);
            startActivity(out);
        }
    });

    }

}
