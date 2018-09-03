//******************************************************
// Instituto Federal de São Paulo - Campus Sertãozinho
// Disciplina......: M4DADM
// Programação de Computadores e Dispositivos Móveis
// Aluno...........: Victor Rubens da Silva Santos
//******************************************************

package com.example.ifsp.validator;

import com.example.ifsp.exception.ContatoException;
import com.example.ifsp.model.Contato;

public class ContatoValidador  {
    // valida o contato
    public static void validador(Contato c) throws ContatoException{
        if (c == null) {
            throw new ContatoException("Contato vazio");
        }
        if (c.getNome() == null || c.getNome().trim().equals("")){
            throw new ContatoException("Informe o nome");
        }
        if (c.getCpf() == null || c.getCpf().trim().equals("")){
            throw new ContatoException("Informe o CPF");
        }
        if (c.getIdade() == null || c.getIdade().trim().equals("")){
            throw new ContatoException("Informe a idade");
        }
        if (c.getTelefone() == null || c.getTelefone().trim().equals("")) {
            throw new ContatoException("Informe um telefone");
        }
        if (c.getEmail() == null || c.getEmail().trim().equals("")){
            throw new ContatoException("Informe o e-mail");
        }

    }
}
