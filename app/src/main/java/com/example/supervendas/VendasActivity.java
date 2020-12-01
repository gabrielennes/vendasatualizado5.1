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

public class VendasActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);
        ed1 = findViewById(R.id.edproduto);
        ed2 = findViewById(R.id.edquantidade);
        b1 = findViewById(R.id.btncomprar);
        b2 = findViewById(R.id.btncancelar);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VendasActivity.this,MainActivity.class);
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
            String edproduto = ed1.getText().toString();
            String edquantidade = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Vendas(id INTEGER PRIMARY KEY AUTOINCREMENT,edproduto VARCHAR,edquantidade VARCHAR)");

            String sql = "insert into Vendas(edproduto, edquantidade)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,edproduto);
            statement.bindString(2,edquantidade);
            statement.execute();
            Toast.makeText(this, "Venda concluida com sucesso!!", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this, "Venda deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
}
