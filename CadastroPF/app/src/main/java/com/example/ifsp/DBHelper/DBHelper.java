//******************************************************
// Instituto Federal de São Paulo - Campus Sertãozinho
// Disciplina......: M4DADM
// Programação de Computadores e Dispositivos Móveis
// Aluno...........: Victor Rubens da Silva Santos
//******************************************************

package com.example.ifsp.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.ifsp.exception.ContatoException;
import com.example.ifsp.model.Contato;
import com.example.ifsp.validator.ContatoValidador;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    // declaração de variaveis
    private static final String DATABASE_NAME = "bancodedados.db";
    private static int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contato";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStnt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nome, cpf, idade, telefone, email) VALUES (?, ?, ?, ?, ?);";

    // Construtor
    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    // insere um contato no banco
    public void insert (Contato c) throws ContatoException {
        ContatoValidador.validador(c);
        try {
            this.insertStnt.bindString(1, c.getNome());
            this.insertStnt.bindString(2, c.getCpf());
            this.insertStnt.bindString(3, c.getIdade());
            this.insertStnt.bindString(4, c.getTelefone());
            this.insertStnt.bindString(5, c.getEmail());

            this.insertStnt.executeInsert();
        } finally {
        }
    }

    // consulta todos os contatos do banco
    public List<Contato> queryGetAll(){
        List<Contato> listaContatos = null;
        Cursor cursor = null;

        try {
            cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"}, null, null, null, null, null,null);
            int registros = cursor.getCount();

            if (registros != 0){
                while (cursor.moveToNext()){
                    if (listaContatos == null) {
                        listaContatos = new ArrayList<>();
                    }
                    Contato c = new Contato();
                    c.setNome(cursor.getString(0));
                    c.setCpf(cursor.getString(1));
                    c.setIdade(cursor.getString(2));
                    c.setTelefone(cursor.getString(3));
                    c.setEmail(cursor.getString(4));

                    listaContatos.add(c);
                }
            }

        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return listaContatos;
    }

    // cria a tabela de contatos, se não existir
    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper (Context contex){
            super (contex, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate (SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);

        }
    // deleta a tabela contatos
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
            String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sql);
        }
    }
}