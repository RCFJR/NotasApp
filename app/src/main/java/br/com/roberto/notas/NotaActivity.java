package br.com.roberto.notas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.roberto.notas.DAO.NotasDAO;
import br.com.roberto.notas.MODEL.Nota;

public class NotaActivity extends AppCompatActivity {

    private String tituloSelecionado;
    private String Contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        tituloSelecionado = getIntent().getStringExtra("item_selected").replace("'","*") ;
        TextInputLayout txtTitulo = (TextInputLayout) findViewById(R.id.txtTitulo);
        TextInputLayout txtNota = (TextInputLayout) findViewById(R.id.txtNota);
        final FloatingActionButton salvar = (FloatingActionButton) findViewById(R.id.btnSalvar);
        final FloatingActionButton deletar = (FloatingActionButton) findViewById(R.id.btnDelete);

        if(tituloSelecionado.equals("")){
            Contexto = "novo";
            txtTitulo.getEditText().setText("");
            txtNota.getEditText().setText("");
        } else
        {
            Contexto = "existente";
            txtTitulo.getEditText().setEnabled(false);
            deletar.setVisibility(View.VISIBLE);
            Nota nota = new NotasDAO(getApplicationContext()).BuscaNota(tituloSelecionado);
            txtTitulo.getEditText().setText(nota.TITULO.replace("*","'"));
            txtNota.getEditText().setText(nota.CONTEUDO.replace("*","'"));
        }

        salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                TextInputLayout txtTituloAtu = (TextInputLayout) findViewById(R.id.txtTitulo);
                TextInputLayout txtNotaAtu = (TextInputLayout) findViewById(R.id.txtNota);

                String titulo = txtTituloAtu.getEditText().getText().toString().replace("'","*") ;
                String nota = txtNotaAtu.getEditText().getText().toString().replace("'","*") ;

                if(titulo.equals("") || nota.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Preenchimento inválido!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    NotasDAO notasDB = new NotasDAO(getApplicationContext());

                    if(Contexto.equals("novo"))
                    {
                        notasDB.insereNota(titulo,nota);
                    }
                    else
                    {
                        notasDB.alteraNota(titulo,nota);
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        deletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextInputLayout txtTituloAtu = (TextInputLayout) findViewById(R.id.txtTitulo);
                final String titulo = txtTituloAtu.getEditText().getText().toString();
                Deletar(titulo).show();
            }
        });
    }

    private Dialog Deletar(String titulo)
    {
        final String idT = titulo.replace("'","*");
        final AlertDialog.Builder builder = new AlertDialog.Builder(NotaActivity.this);
        builder.setMessage("Você tem certeza que deseja excluir?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NotasDAO notasDB = new NotasDAO(getApplicationContext());
                        notasDB.deletaNota(idT);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
