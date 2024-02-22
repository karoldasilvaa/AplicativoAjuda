package com.example.novoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {
    Button btCadastrarUsuario;
    EditText editNome, editCpf, editTelefone, editEmail, editSenha, editConfirmeSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btCadastrarUsuario = (Button) findViewById(R.id.btCadastrarUsuario);
        editNome = (EditText) findViewById(R.id.editNome);
        editCpf = (EditText) findViewById(R.id.editCpf);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenhaCadastro);
        editConfirmeSenha = (EditText) findViewById(R.id.ediConfirmeSenha);

        btCadastrarUsuario.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nome = editNome.getText().toString();
        String cpf = editCpf.getText().toString();
        String telefone = editTelefone.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        String ConfSenha = editConfirmeSenha.getText().toString();
        String msg = "";

        if (nome.length() == 0) {
            msg = "O campo Nome deve ser preenchido corretamente!";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } else {
            if (cpf.length() == 0) {
                msg = "O Cpf deve ser preenchido corretamente!";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            } else {
                if (telefone.length() == 0) {
                    msg = "O campo Telefone deve ser preenchido corretamente!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                } else {
                    if (email.length() == 0) {
                        msg = "O campo Email deve ser preenchido corretamente!";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else {
                        if (senha.length() == 0 || !senha.equals(ConfSenha)) {
                            msg = "Os campos Senha e Confirmar Senha devem ser preenchidos corretamente e iguais!";
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                        } else {
                            BancoController bd = new BancoController(getBaseContext());
                            msg = bd.insereDadosUsuarios(nome, cpf, telefone, email, senha);

                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                            /* limpar os dados digitados pelo usuario */
                            editNome.setText("");
                            editCpf.setText("");
                            editTelefone.setText("");
                            editEmail.setText("");
                            editSenha.setText("");
                            editConfirmeSenha.setText("");

                        }
                    }
                }
            }
        }
    }
}