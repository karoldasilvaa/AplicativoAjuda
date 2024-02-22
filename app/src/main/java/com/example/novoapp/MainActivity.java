package com.example.novoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btEntrar;
    TextView textTelaCadastrar;
    EditText edit_email, edit_senha;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEntrar = (Button) findViewById(R.id.btEntrar);
        textTelaCadastrar = (TextView) findViewById(R.id.textTelaCadastrar);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_senha = (EditText) findViewById(R.id.edit_senha);
        btEntrar.setOnClickListener(this);
        textTelaCadastrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btEntrar) {
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            BancoController bd = new BancoController(getBaseContext());

            Cursor dados = bd.carregaDadosPeloLogin(email, senha);

            if (dados.moveToFirst()) {
                int codigo = dados.getInt(0);
                String nome = dados.getString(1);
                Intent intencao = new Intent(this, Office_Page.class);
                Bundle parametros = new Bundle();

                parametros.putString("nome", nome);
                parametros.putString("email", email);
                parametros.putString("senha", senha);
                parametros.putInt("codigo", codigo);
                intencao.putExtras(parametros);
                startActivity(intencao);

            } else {
                String msg = "Usuario ou senha digitada não consta na base de dados!!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        }
        if (view.getId() == R.id.textTelaCadastrar) {
            Intent intent = new Intent(this, Cadastro.class);
            startActivity(intent);
        }
    }
}

