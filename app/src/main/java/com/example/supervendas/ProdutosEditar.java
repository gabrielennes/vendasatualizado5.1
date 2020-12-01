package com.example.supervendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProdutosEditar extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_produtos_editar );
        ed1 = findViewById(R.id.prodid);
        ed2 = findViewById(R.id.produto);
        ed3 = findViewById(R.id.produtodesc);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra( "id" ).toString();
        String produto = i.getStringExtra( "produto" ).toString();
        String des = i.getStringExtra( "prodesc" ).toString();

        ed1.setText(id);
        ed2.setText(produto);
        ed3.setText(des);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletar();
            }
        } );

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        } );

    }
    public void Editar(){
        try{
            String prodid = ed1.getText().toString();
            String produtonome = ed2.getText().toString();
            String produtodesc = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("vendas", Context.MODE_PRIVATE, null);

            String sql = "update produto set produto = ?, prodesc = ? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,produtonome);
            statement.bindString(2,produtodesc);
            statement.bindString(3,prodid);
            statement.execute();
            Toast.makeText(this,"Produto Editado com sucesso!!!",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }catch(Exception ex){
            Toast.makeText(this,"Produto deu error!!!",Toast.LENGTH_SHORT).show();
        }
    }
    public void Deletar (){
        try {
            String prodid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("vendas", Context.MODE_PRIVATE,null);
            String sql = "delete from produto where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,prodid);
            statement.execute();
            Toast.makeText(this, "Produto Deletado Com Sucesso!!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this, "Produto deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
}



