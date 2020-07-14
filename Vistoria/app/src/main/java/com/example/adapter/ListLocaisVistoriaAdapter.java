package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.Locais;
import com.example.vistoria.R;

import java.util.List;

public class ListLocaisVistoriaAdapter extends BaseAdapter {

    Context context;
    List<Locais> locais;


    public ListLocaisVistoriaAdapter(Context context, List<Locais> locais) {
        this.context = context;
        this.locais = locais;
    }

    @Override
    public int getCount() {
        return locais.size();
    }

    @Override
    public Object getItem(int i) {
        return locais.get(i);
    }

    @Override
    public long getItemId(int i) {
        return locais.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View viewLinha = LayoutInflater.from(context).inflate(R.layout.linha_locais, parent, false);

        Locais local = locais.get(position);
        TextView textView = viewLinha.findViewById(R.id.textLinhaLocais);
        textView.setText(local.getNome_local());

        return viewLinha;
    }
}
