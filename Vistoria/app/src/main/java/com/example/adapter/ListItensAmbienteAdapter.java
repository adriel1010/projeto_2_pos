package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.ItensAmbienteModel;
import com.example.model.Locais;
import com.example.vistoria.R;

import java.util.List;

public class ListItensAmbienteAdapter extends BaseAdapter {

    Context context;
    List<ItensAmbienteModel> itens;

    public ListItensAmbienteAdapter(Context context, List<ItensAmbienteModel> amb) {
        this.context = context;
        this.itens = amb;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return itens.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View viewLinha = LayoutInflater.from(context).inflate(R.layout.linha_itens_ambientes, parent, false);

        ItensAmbienteModel ambient = itens.get(i);
        TextView textView = viewLinha.findViewById(R.id.textLinhaItensAmbiente);
        textView.setText(ambient.getNomeItem());


        return viewLinha;
    }
}
