package br.com.roberto.notas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.roberto.notas.DAO.NotasDAO;
import br.com.roberto.notas.DAO.SQLite;
import br.com.roberto.notas.MODEL.NotaAdapterList;
import br.com.roberto.notas.UTIL.NotaAdapter;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNota = (FloatingActionButton) findViewById(R.id.btnAddNota);
        addNota.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), NotaActivity.class);
                intent.putExtra("item_selected", "");
                startActivity(intent);
            }
        });

        try{
            StartDB();
            AtualizaTabela();
        }
        catch(Exception err){
                String error = err.getMessage();
        }
    }


    private void StartDB()
    {
        SQLite factory = new SQLite(getApplicationContext());
        if(base == null)
        {
            base = factory.getWritableDatabase();
        }
        factory.startDatabase(base);
    }

    public void AtualizaTabela()
    {
        NotasDAO notasDB = new NotasDAO(getApplicationContext());
        ArrayList<NotaAdapterList> notas = notasDB.ListaNotas();

        ListView lstNotas = (ListView) findViewById(R.id.lstNota);

        ArrayAdapter adapter = new NotaAdapter(this, notas);
        lstNotas.setAdapter(adapter);
    }
}
