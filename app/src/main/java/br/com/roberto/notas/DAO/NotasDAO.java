package br.com.roberto.notas.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.roberto.notas.MODEL.Nota;
import br.com.roberto.notas.MODEL.NotaAdapterList;

public class NotasDAO {

    private SQLiteDatabase db;
    private SQLite banco;

    public NotasDAO(Context context){
        banco = new SQLite(context);
    }

    public String insereNota(String TITULO, String CONTEUDO)
    {
        try {
            db = banco.getWritableDatabase();
            String sqlString = "INSERT INTO NOTAS (TITULO, CONTEUDO) VALUES ('";
            sqlString += "" + TITULO + "','" + CONTEUDO + "');";

            db.execSQL(sqlString);
            db.close();
            return "Registro Inserido com sucesso";
        }
        catch (Exception err)
        {
            return "Erro: "+err.getMessage();
        }
    }

    public Nota BuscaNota(String TITULO)
    {
        Cursor cursor;
        db = banco.getReadableDatabase();
        String StrSQL = "SELECT TITULO, CONTEUDO FROM NOTAS WHERE TITULO = '"+ TITULO +"'";
        cursor = db.rawQuery(StrSQL, null);
        Nota nota = new Nota();

        if(cursor!=null){
            try {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    nota.TITULO = cursor.getString(0);
                    nota.CONTEUDO = cursor.getString(1);
                }
            }
            catch (Exception err)
            {
            }
        }
        db.close();
        return nota;
    }

    public String alteraNota(String TITULO, String CONTEUDO)
    {
        try {
            db = banco.getWritableDatabase();
            String sqlString = "UPDATE NOTAS SET  CONTEUDO = '"+ CONTEUDO +"' WHERE TITULO = '"+ TITULO +"'";
            db.execSQL(sqlString);
            db.close();
            return "Registro alterado com sucesso";
        }
        catch (Exception err)
        {
            return "Erro: "+err.getMessage();
        }
    }

    public String deletaNota(String TITULO)
    {
        try {
            db = banco.getWritableDatabase();
            String sqlString = "DELETE FROM NOTAS WHERE TITULO = '"+ TITULO +"'";
            db.execSQL(sqlString);
            db.close();
            return "Registro alterado com sucesso";
        }
        catch (Exception err)
        {
            return "Erro: "+err.getMessage();
        }
    }

    public ArrayList<NotaAdapterList> ListaNotas()
    {
        Cursor cursor;
        db = banco.getReadableDatabase();
        String StrSQL = "SELECT TITULO FROM NOTAS ";
        cursor = db.rawQuery(StrSQL, null);
        ArrayList<NotaAdapterList> notas = new ArrayList<NotaAdapterList>();

        if(cursor!=null){
            try {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    NotaAdapterList adp = new NotaAdapterList();
                    adp.TITULO = cursor.getString(0);
                    notas.add(adp);
                }
            }
            catch (Exception err)
            {
            }
        }
        db.close();
        return notas;
    }
}
