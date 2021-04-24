package com.example.listapersonagem.ui.activities;

//Importando as referencias

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagem.R;
import com.example.listapersonagem.dao.PersonagemDAO;
import com.example.listapersonagem.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);

        //Trocando o nome do cabeçalho
        setTitle("Lista de Personagem");

        dao.salva(new Personagem("Ken","1.80","03082000"));
        dao.salva(new Personagem("Ryu","1.91","01071999"));

        botaoFAB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        //Pegando a referencia do dao.todos para o personagem poder acessar as informacoes
        List<Personagem> personagens = dao.todos();
        //Pegando os personagens e adicionando a lista
        listaDePersonagem(listaDePersonagens, personagens);
        //ao clicar em um item na lista
        configuraItemPerClique(listaDePersonagens);
    }

    private void configuraItemPerClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Sobreescrevendo o metodo para realizar a selecao do personagem
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Adicionando a posicao da lista aonde o player clicou
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(position);
                //Criando uma Intent para trocar para a activity ao clicar em um item da lista
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                //trazendo as informacoes pelo putExtra do personagem
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                //Trocando para a activity FormularioPersonagemActivity
                startActivity(vaiParaFormulario);
            }
        });
    }

    private void listaDePersonagem(ListView listaDePersonagens, List<Personagem> personagens) {
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }

    private void botaoFAB() {
        //Pegando o FloatingActionButton(FAB)
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_novo_personagem);
        //Ao clicar no botao FAB, realiza uma funcao
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Troca para o activity FormularioPersonagemActivity
                abreFormularioSalvar();
            }
        });
    }

    private void abreFormularioSalvar() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }
}
