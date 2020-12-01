package com.example.supervendas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;



    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private String uid;
    private String usuario;

    private ArrayList<String>seguindo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        b1 = findViewById( R.id.btn1 );
        b2 = findViewById( R.id.btn2 );
        b3 = findViewById( R.id.btn3 );
        b4 = findViewById( R.id.btn4 );
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        seguindo = new ArrayList<>();

        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ProdutoView.class);
                startActivity( i );
            }
        } );


        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this , VendasActivity.class );
                startActivity( i );
            }
        } );
        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Produtos.class);
                startActivity( i );
            }
        } );


    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null)finish();


        getUserinfo();
    }

    private void getUserinfo(){
         uid = mAuth.getCurrentUser().getUid();

        DatabaseReference userRef = database.getReference("users/" + uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                usuario = datasnapshot.child("usuario").getValue(String.class);

                seguindo.clear();
                for (DataSnapshot s : datasnapshot.child("seguindo").getChildren()) {
                    seguindo.add(s.getValue(String.class));
                }
                Log.d("usuario",usuario);
                Log.d("lista",seguindo.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    }
