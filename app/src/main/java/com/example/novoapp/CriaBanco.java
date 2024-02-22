package com.example.novoapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {
    /* FINAL=não muda ela não é variavel é uma constante, o valor não muda, não pode ser modificado */
    private static final String NOME_BANCO = "banco_CRUD.db"; /* se trocar o nome do banco de dados, apaga todos os dados */
    private static final int VERSAO = 2;   /* se mudar o número da versão apaga todos os dados */
    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario ("
                + "codigo integer primary key autoincrement,"
                + "nome text,"
                + "cpf text,"
                + "telefone text,"
                + "email text,"
                + "senha text)";
        db.execSQL(sql);

        /* para criar mais tabelas */
       String sql1 = "CREATE TABLE contato_emergencia ("
                + "id_contato integer primary key autoincrement,"
                + "mensagem text,"
                + "nomeCont text,"
                + "telefoneCont text)";
        db.execSQL(sql1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS contato_emergencia");

        /* db.execSQL("DROP TABLE IF EXISTS alunos"); */
        onCreate(db);
    }
}