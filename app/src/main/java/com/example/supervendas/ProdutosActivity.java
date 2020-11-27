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

public class ProdutosActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        ed1 = findViewById(R.id.edaddprod);
        ed2 = findViewById(R.id.edaddquantidade);

        b2 = findViewById(R.id.btnadicionar);
        b1 = findViewById(R.id.btncancelar1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProdutosActivity.this,MainActivity.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
                finish();
            }
        });
    }

    public void insert(){
        try {
            String edaddprod = ed1.getText().toString();
            String edaddquantidade = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS produtos(id INTEGER PRIMARY KEY AUTOINCREMENT,edaddprod VARCHAR,edaddquantidade VARCHAR)");

            String sql = "insert into produtos(edaddprod, edaddquantidade)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,edaddprod);
            statement.bindString(2,edaddquantidade);
            statement.execute();
            Toast.makeText(this, "Produto adicionado com sucesso!!", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this, "Produto deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
}