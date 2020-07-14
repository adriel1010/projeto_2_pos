package com.example.vistoria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.ListLocaisVistoriaAdapter;
import com.example.model.AmbientesModel;
import com.example.model.Locais;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,  AdapterView.OnItemLongClickListener {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewRegistrosLocais);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        carregarLista();
    }

    public void chamarTelaNovaVistoria(View view) {
        Intent itt = new Intent(this, LocalVistoria.class);
        startActivity(itt);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Locais local = (Locais) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, Ambientes.class);
        intent.putExtra("ID_LOCAL", local.getId());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        carregarLista();
        super.onStart();
    }

    public void chamarTelaEditar(long id) {
        Intent intent = new Intent(this, LocalVistoria.class);
        intent.putExtra("ID_LOCAL_EDITAR", id);
        startActivity(intent);
    }

    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja Editar Essa Localidade?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Locais local = (Locais) parent.getItemAtPosition(position);
               chamarTelaEditar(local.getId());


            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
    }

    private void carregarLista() {
        List<Locais> listaLocais = Locais.find(Locais.class, "id > 0");

        ListLocaisVistoriaAdapter adapter = new ListLocaisVistoriaAdapter(this, listaLocais);
        listView.setAdapter(adapter);

    }
}
