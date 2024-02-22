package com.example.novoapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {

        banco = new CriaBanco(context);

    }

    // tela cadastro
    public String insereDadosUsuarios(String txtNome, String txtCpf, String txtTelefone, String txtEmail, String txtSenha) {
        /* valores é um objeto, que vem de uma classe ContentValues é uma classe que
        vai armazenar um conjunto de dados, que são o nome e email */
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        /* receber o nome, email e senha */
        valores = new ContentValues();
        valores.put("nome", txtNome);
        valores.put("cpf", txtCpf);
        valores.put("telefone", txtTelefone);
        valores.put("email", txtEmail);
        valores.put("senha", txtSenha);

        /* gravar na tabela */
        resultado = db.insert("usuario", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir o registro do usuário";
        else
            return "Registro do usuário inserido com sucesso";
    }


    public Cursor carregaDadosPeloLogin(String email, String senha) {
        Cursor cursor;
        String[] campos = {"codigo", "nome", "cpf", "telefone", "email", "senha"};
        String filtro = "email= '" + email + "' and senha = '" + senha + "'";

        db = banco.getReadableDatabase();
        cursor = db.query("usuario", campos, filtro, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
    public String adicionarContato(String _mensagem, String _nome, String _telefone) {
        /* valores é um objeto, que vem de uma classe ContentValues é uma classe que
        vai armazenar um conjunto de dados, que são o nome e email */
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        /* receber o nome, email e senha */
        valores = new ContentValues();
        valores.put("mensagem", _mensagem);
        valores.put("nomeCont", _nome);
        valores.put("telefoneCont", _telefone);



        /* gravar na tabela */
        resultado = db.insert("contato_emergencia", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir contato";
        else
            return "Contato inserido com sucesso";

    }

    public Cursor mandarSMS(String mensagem, String numeroContato) {
        Cursor cursor;
        String[] campos = {"mensagem", "nomeContato", "numeroContato"};
        String filtro = "mensagem= '" + mensagem +  "' numeroContato = '" + numeroContato + "'";

        db = banco.getReadableDatabase();
        cursor = db.query("contato_emergencia", campos, filtro, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}