//******************************************************
// Instituto Federal de São Paulo - Campus Sertãozinho
// Disciplina......: M4DADM
// Programação de Computadores e Dispositivos Móveis
// Aluno...........: Victor Rubens da Silva Santos
//******************************************************

package com.example.ifsp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ifsp.DBHelper.DBHelper;
import com.example.ifsp.R;
import com.example.ifsp.model.Contato;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private DBHelper dh;

    EditText nome, cpf, idade, tel, email;
    Button btnVoltar, btnSalvar, btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.txtNome);
        cpf = (EditText) findViewById(R.id.txtCPF);
        idade = (EditText) findViewById(R.id.txtIdade);
        tel = (EditText) findViewById(R.id.txtTel);
        email = (EditText) findViewById(R.id.txtEmail);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnListar = (Button) findViewById(R.id.btnListar);

        // intent no btn voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CadastroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // instancia o banco de dados
        this.dh = new DBHelper(this);

        // salva o contato com as informações que o usuario informou
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contato c = new Contato();

                c.setNome(nome.getText().toString());
                c.setCpf(cpf.getText().toString());
                c.setIdade(idade.getText().toString());
                c.setTelefone(tel.getText().toString());
                c.setEmail(email.getText().toString());
                try {
                    dh.insert(c);
                    showAlert("Sucesso", "Cadastro inserido com sucesso!");
                    clear();
                } catch (Exception e) {
                    showAlert("Erro", e.getMessage());
                    clear();
                }
            }
        });
        // mostra os contatos que estão no bd
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // busca no banco
                List<Contato> listaContatos = dh.queryGetAll();

                // se não houver contatos, mostra a mensagem
                if (listaContatos == null) {
                    showAlert("Sem Resultados", "Não há registros cadastrados!");
                    return;
                }

                // for que mostra as informações do contato detalhado
                int cont = 0;
                for (Contato c : listaContatos) {
                    cont++;
                    String msg = "Nome: " + c.getNome() +"\nCPF: " +c.getCpf() + "\nIdade: " + c.getIdade() +"\nTelefone: " + c.getTelefone() + "\nE-mail: " + c.getEmail();
                    showAlert("Registro " + cont, msg);
                }
            }
        });


    }

    // helper de mensagem na tela
    private void showAlert(String title, String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(title);
        alert.setMessage(msg);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    //limpa os campos
    private void clear (){
        nome.setText("");
        cpf.setText("");
        idade.setText("");
        tel.setText("");
        email.setText("");
    }
}
