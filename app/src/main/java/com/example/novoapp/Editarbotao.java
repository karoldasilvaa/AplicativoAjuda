package com.example.novoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Editarbotao extends AppCompatActivity implements View.OnClickListener{

    Button btSalvarContato;

    EditText txt_edit_msg, nomeContato, numeroContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarbotao);

        btSalvarContato = (Button) findViewById(R.id.btSalvarContato);
        txt_edit_msg = (EditText) findViewById(R.id.txt_edit_msg) ;
        nomeContato = (EditText) findViewById(R.id.nomeContato) ;
        numeroContato = (EditText) findViewById(R.id.numeroContato) ;
        btSalvarContato.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String mensagem = txt_edit_msg.getText().toString();
        String nome = nomeContato.getText().toString();
        String telefone = numeroContato.getText().toString();
        String msgEditar = "";

        if (nome.length() == 0) {
            msgEditar = "Adicione pelo menos um contato!";
            Toast.makeText(getApplicationContext(), msgEditar, Toast.LENGTH_LONG).show();
        } else {
            if (numeroContato.length() == 0) {
                msgEditar = "Adicione pelo menos um número!";
                Toast.makeText(getApplicationContext(), msgEditar, Toast.LENGTH_LONG).show();
            } else {
                if (mensagem.length() == 0) {
                    msgEditar = "Mensagem não digitada!";
                    Toast.makeText(getApplicationContext(), msgEditar, Toast.LENGTH_LONG).show();
                } else {
                    BancoController bd = new BancoController(getBaseContext());
                    msgEditar = bd.adicionarContato(mensagem, nome, telefone);

                    Toast.makeText(getApplicationContext(), msgEditar, Toast.LENGTH_LONG).show();
                    /* limpar os dados digitados pelo usuario */
                    txt_edit_msg.setText("");
                    nomeContato.setText("");
                    numeroContato.setText("");


                }
            }

        }
    }
}