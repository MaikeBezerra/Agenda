package com.example.agenda.ui.contato;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.data.ContatoListFirebase;
import com.example.agenda.mock.Telefones;
import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnContatoListEventListener;

import java.util.List;

public class ContatoListView implements OnContatoListEventListener {

    private ContatoListFirebase db;

    private Context context;
    private RecyclerView recyclerView;
    private ContatoListAdapter adapter;

    public ContatoListView( Context context ){
        this.context = context;
        this.db = new ContatoListFirebase( this);
    }

    public void inicilize( View view){
        recyclerView = view.findViewById(R.id.contatoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        db.find(Telefones.MY_NUMBER);
    }

//    public void readContatos(){
//        dbContato.findAll();
//    }

    @Override
    public void onSetList(List<Contato> contatos) {
        adapter = new ContatoListAdapter(context, contatos);
        recyclerView.setAdapter(adapter);
    }

}
