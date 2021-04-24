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

import static com.example.listapersonagem.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //Criando variaveis staticas(Constantes)
    private static final String TITLE_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    private static final String TITLE_APPBAR_NOVO_PERSONAGEM = "Novo Personagem";

    //criando as variaveis EditText
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

    private final PersonagemDAO dao = new PersonagemDAO(); //criando um objeto dao(Banco de daodos do personagem) com referencia a PersonagemDAO.java
    private Personagem personagem; // Criando um objeto de tipo Personagem

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        //iniciando os campos de EditText
        inicializacaoCampos();
        //Configura o botao Salvar
        configuraBotao();
        carregaPersonagem();
    }

    private void carregaPersonagem() {
        //pegando o intent salvo e salvando ele na variavel dados
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            //Trocando o nome do titulo
            setTitle(TITLE_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            //setar o que esta salvo nos devidos EditText
            preencheCampos();
        } else {
            //Trocando o nome do titulo
            setTitle(TITLE_APPBAR_NOVO_PERSONAGEM);
            //cria um personagem caso nao exista
            personagem = new Personagem();
        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotao() {
        //Adicionando uma funcao ao botao de salvar e Pegando o botao
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Salva ou edita o formulario
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePersonagem();
        //se o id for valido
        if (personagem.IdValido()) {
            //traz as informacoes para editar
            dao.edita(personagem);
        } else {
            //Salvando a informacao do personagem no dao
            dao.salva(personagem);
        }
        //Finalizando o Activity
        finish();
    }

    private void inicializacaoCampos() {
        //Pegando as referencias do EditText no activity_formulario_personagem e adicionando a variavel
        campoNome = findViewById(R.id.editText_nome);
        campoAltura = findViewById(R.id.editText_altura);
        campoNascimento = findViewById(R.id.editText_nascimento);
    }

    private void preenchePersonagem() {
        //Setando o nome dos campos com as variaveis internas
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();
        //refenciando as strings com os respectivos itens
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
    }
}