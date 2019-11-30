package com.example.agenda.ui.contato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.agenda.R;

public class ContatoListFragment extends Fragment {

    private ContatoListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato_list, container, false);

        listView = new ContatoListView(getContext());
        listView.inicilize( view );

        return view;
    }

}
