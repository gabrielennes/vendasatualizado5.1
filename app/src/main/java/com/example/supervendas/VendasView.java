package com.example.supervendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class VendasView extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_vendas_view );

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("vendas", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from vendas", null);
        int id = c.getColumnIndex("id");
        int produto = c.getColumnIndex("produto" );
        int prodesc = c.getColumnIndex("prodesc");
        int produtopreco = c.getColumnIndex( "produtopreco" );

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<ven> vend = new ArrayList<ven>();

        if(c.moveToNext()){
            do {
                ven ve = new ven();
                ve.id =  c.getString( id );
                ve.produto = c.getString( produto );
                ve.des = c.getString( prodesc );
                ve.produtopreco = c .getString( produtopreco );
                vend.add(ve);

                titles.add(c.getString(id)+" \t"  +c.getString(produto)+" \t"  + c.getString(prodesc)+" \t"+ c.getString(produtopreco)+" \t");

            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                ven ve = vend.get((position));
                Intent i = new Intent(getApplicationContext(), VendasEditar.class);
                i.putExtra("id",ve.id);
                i.putExtra("produto",ve.produto);
                i.putExtra("prodesc",ve.des);
                i.putExtra( "produtopreco",ve.produtopreco );


                startActivity(i);
            }
        } );
    }
}