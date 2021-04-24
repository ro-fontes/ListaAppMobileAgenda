package com.example.listapersonagem.ui.activities;

//Aula Gravada 26/03/21 00:36:22.

//Importando as referencias

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //criando as variaveis EditText
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private final PersonagemDAO dao = new PersonagemDAO(); //criando um objeto dao(Banco de daodos do personagem) com referencia a PersonagemDAO.java
    private Personagem Personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        //Trocando o nome do cabeçalho
        setTitle("Formulario de Personagem");
        //iniciando os campos de EditText
        inicializacaoCampos();
        //Configura o botao Salvar
        configuraBotao();


        //pegando o intent salvo e salvando ele na variavel dados
        Intent dados = getIntent();
        if(dados.hasExtra("personagem")) {
            Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
            campoNome.setText(personagem.getNome());
            campoAltura.setText(personagem.getAltura());
            campoNascimento.setText(personagem.getNascimento());
        }else {
            Personagem = new Personagem();
        }
    }

    private void configuraBotao() {
        //Adicionando uma funcao ao botao de salvar e Pegando o botao
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                //Salvando a informacao do personagemSalvo no dao
                dao.salva(personagemSalvo);
                //Finalizando o Activity
                finish();

                //traz as informacoes para editar
                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.edita(personagemSalvo);
            }
        });
    }

    private void inicializacaoCampos() {
        //Pegando as referencias do EditText no activity_formulario_personagem e adicionando a variavel
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }
}