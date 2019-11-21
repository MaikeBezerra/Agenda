package com.example.agenda.ui.contato;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.data.ContatoDAO;
import com.example.agenda.data.ContatoDAOFirebase;
import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnChangeContatoListener;

import java.util.List;

public class ContatoListViewModel implements OnChangeContatoListener {

    private ContatoDAO dbContato;

    private View view;
    private Context context;
    private RecyclerView recyclerView;
    private ContatoListAdapter adapter;

    public ContatoListViewModel(View view, Context context){
        this.view = view;
        this.context = context;
        startRecyclerView();

        this.dbContato = new ContatoDAOFirebase(this.context);
        dbContato.addObserver(this);
    }

    public void startRecyclerView(){
        this.recyclerView = view.findViewById(R.id.contatoList);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
    }

    public void readContatos(){
        dbContato.findAll();
    }

    @Override
    public void update(List<Contato> contatos) {
        adapter = new ContatoListAdapter(context, contatos);
        recyclerView.setAdapter(adapter);
    }
}
