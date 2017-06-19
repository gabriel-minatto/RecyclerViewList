package com.example.root.minhanavegacoes;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListaRecycler extends AppCompatActivity implements ClickRecycler{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    MyAdapter adapter;
    private List<Pessoa> listaPessoas = new ArrayList<>();
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for(int i=0; i<5; i++)
            listaPessoas.add(Pessoa.carrega());
        adapter = new MyAdapter(this, listaPessoas, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            // Cria uma nova pessoa - adiciona a pessoa e avisa o adapter que o conteúdo da lista foi alterado
                listaPessoas.add(Pessoa.carrega());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCustomClick(Object object) {
        Pessoa p = (Pessoa) object;
        System.out.println("Pessoa = " + p.toString());
    }
}
