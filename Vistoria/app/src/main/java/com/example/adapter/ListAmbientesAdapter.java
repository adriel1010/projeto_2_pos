package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.AmbientesModel;
import com.example.vistoria.R;

import java.util.List;

public class ListAmbientesAdapter extends BaseAdapter {

    Context context;
    List<AmbientesModel> ambientes;

    public ListAmbientesAdapter(Context context, List<AmbientesModel> amb) {
        this.context = context;
        this.ambientes = amb;
    }

    @Override
    public int getCount() {
        return ambientes.size();
    }

    @Override
    public Object getItem(int i) {
        return ambientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ambientes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View viewLinha = LayoutInflater.from(context).inflate(R.layout.linha_ambientes, parent, false);

        AmbientesModel ambient = ambientes.get(i);
        TextView textView = viewLinha.findViewById(R.id.textLinhaAmbi);
        textView.setText(ambient.getNomeAmbiente());

        return viewLinha;
    }
}
