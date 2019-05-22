package br.com.roberto.notas.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "notasR.db";
    public static final int VERSAO = 2;

    public SQLite(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        startDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void startDatabase(SQLiteDatabase db)
    {
        //String dropNotas = "DROP TABLE IF EXISTS NOTAS;";

        String sqlNotas = "CREATE TABLE IF NOT EXISTS NOTAS ("
                + "TITULO text,"
                + "CONTEUDO text"
                +")";


        //db.execSQL(dropNotas);
        db.execSQL(sqlNotas);

    }

    public void restartDatabase(SQLiteDatabase db)
    {
        String dropNotas = "DROP TABLE IF EXISTS NOTAS;";

        String sqlNotas = "CREATE TABLE IF NOT EXISTS NOTAS ("
                + "TITULO text,"
                + "CONTEUDO text"
                +")";


        db.execSQL(dropNotas);
        db.execSQL(sqlNotas);

    }
}