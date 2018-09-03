//******************************************************
// Instituto Federal de São Paulo - Campus Sertãozinho
// Disciplina......: M4DADM
// Programação de Computadores e Dispositivos Móveis
// Aluno...........: Victor Rubens da Silva Santos
//******************************************************

package com.example.ifsp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ifsp.DBHelper.DBHelper;
import com.example.ifsp.R;

public class MainActivity extends AppCompatActivity {

    Button btnTelaCadstro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTelaCadstro = (Button) findViewById(R.id.btnTelaCadastro);

        // intent que vai para a tela de cadastro
        btnTelaCadstro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
