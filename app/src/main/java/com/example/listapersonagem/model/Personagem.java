package com.example.listapersonagem.model;

import java.io.Serializable;

public class Personagem implements Serializable {
    //setando as variaveis
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {
        //Adicioando o nome que foi adicionado, com o nome que esta no local
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }

    public Personagem(){

    }

    //Metodos Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    //Sobreescrevendo o metodo toString para retornar o nome
    @Override
    public String toString() {
        return nome;
    }
    //Localizacao na lista pelo id
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
