package com.example.aula_ead_lais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterEstudante extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Estudante> estudantes;

    public MyAdapterEstudante(Context context, ArrayList<Estudante> estudantes) {
        this.inflater = LayoutInflater.from(context);
        this.estudantes = estudantes;
    }

    @Override
    public int getCount() {
        return estudantes.size();
    }

    @Override
    public Object getItem(int i) {
        return estudantes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Estudante item = estudantes.get(i);
        view = inflater.inflate(R.layout.list_estudantes, null);

        TextView estudanteNome = view.findViewById(R.id.estudanteNome);

        estudanteNome.setText(estudanteNome.getText().toString() + item.getNome());
        return view;
    }

    public void atualizarDados(ArrayList<Estudante> novosEstudantes) {
        this.estudantes.clear();
        this.estudantes.addAll(novosEstudantes);
        notifyDataSetChanged();
    }
}
