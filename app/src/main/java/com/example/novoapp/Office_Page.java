package com.example.novoapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Office_Page extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_office);

        ActivityCompat.requestPermissions(Office_Page.this, new
                String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        ImageButton btTel, btWeb, btPolicia;
        Button btEditar, btAjuda;


        btTel = (ImageButton) findViewById(R.id.btTel);
        btWeb = (ImageButton) findViewById(R.id.btWeb);
        btEditar = (Button) findViewById(R.id.btEditar);
        btPolicia = (ImageButton) findViewById(R.id.btPolicia);
        btAjuda = (Button) findViewById(R.id.btAjuda);

        btTel.setOnClickListener(this);
        btWeb.setOnClickListener(this);
        btEditar.setOnClickListener(this);
        btPolicia.setOnClickListener(this);
        btAjuda.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btEditar) {
            Intent intent = new Intent(this, Editarbotao.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btTel) {
            Intent intent = new Intent(this, TelaNumeros.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btWeb) {
            String _url = "https://www.delegaciaeletronica.policiacivil.sp.gov.br/ssp-de-cidadao/pages/comunicar-ocorrencia";
            Uri _link = Uri.parse(_url);
            Intent intent = new Intent(Intent.ACTION_VIEW, _link);
            startActivity(intent);
        }

        if (view.getId() == R.id.btPolicia) {
            Intent intent = new Intent(this, Mapa.class);
            startActivity(intent);
        }
        if (view.getId() == R.id.btAjuda) {
            private void consultar() {
                int txtCodigo = Integer.parseInt(codigo.getText().toString()); /* para pegar o codigo digitado pelo usuario */

                BancoController bd = new BancoController(getBaseContext());  /* chamo o metodo bd para se conectar no banco de dados */

                Cursor dados = bd.carregaDadosPeloCodigo(txtCodigo) ;

                if(dados.moveToFirst()){
                    nome.setText( dados.getString(1) );
                    email.setText( dados.getString(2) );
                }else{
                    String msg= "Código não cadastrado";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    limpar();
                }

            }

        }
    }
}









