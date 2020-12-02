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

public class VendasComprar extends AppCompatActivity {

    EditText ed1, ed2, ed3,ed4;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vendas_comprar );
        ed1 = findViewById( R.id.prodid );
        ed2 = findViewById( R.id.produto );
        ed3 = findViewById( R.id.produtodesc );
        ed4 = findViewById( R.id.produtopreco );

        b1 = findViewById( R.id.btn1 );
        b2 = findViewById( R.id.btn2 );
        b3 = findViewById( R.id.btn3 );

        Intent i = getIntent();

        String id = i.getStringExtra( "id" ).toString();
        String produto = i.getStringExtra( "produto" ).toString();
        String des = i.getStringExtra( "prodesc" ).toString();

        ed1.setText( id );
        ed2.setText( produto );
        ed3.setText( des );

        b2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CarrinhoActivity.class);
                startActivity( i );
            }
        } );

        b3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( getApplicationContext() , MainActivity.class );
                startActivity( i );
            }
        } );

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comprar();
            }
        } );

    }

    public void Comprar() {
        try {
            String prodid = ed1.getText().toString();
            String produtonome = ed2.getText().toString();
            String produtodesc = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase( "vendas" , Context.MODE_PRIVATE , null );

            String sql = "update produto set produto = ?, prodesc = ? where id=?";
            SQLiteStatement statement = db.compileStatement( sql );
            statement.bindString( 1 , produtonome );
            statement.bindString( 2 , produtodesc );
            statement.bindString( 3 , prodid );
            statement.execute();
            Toast.makeText( this , "Compra Efetuada com sucesso!!!" , Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( getApplicationContext() , CarrinhoActivity.class );
            startActivity( i );
        } catch (Exception ex) {
            Toast.makeText( this , "Compra deu error!!!" , Toast.LENGTH_SHORT ).show();
        }
    }
}



