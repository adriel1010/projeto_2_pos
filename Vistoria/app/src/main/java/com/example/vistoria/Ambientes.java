package com.example.vistoria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import com.example.adapter.ListAmbientesAdapter;
import com.example.model.AmbientesModel;
import com.example.model.ItensAmbienteModel;
import com.example.model.Locais;


public class Ambientes extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;
    private long id_local = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambientes);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("ID_LOCAL")) {
            Long id = bundle.getLong("ID_LOCAL");
            id_local = id;
            listView = (ListView) findViewById(R.id.listViewAmbientes);
            listView.setOnItemClickListener(this);
            listView.setOnItemLongClickListener(this);

            carregarLista(id);
        }
    }

    private void carregarLista(Long id_local) {
        List<AmbientesModel> amb = AmbientesModel.findWithQuery(AmbientesModel.class, "Select * from AMBIENTES_MODEL where local_Ambiente = ? ", String.valueOf(id_local));

        ListAmbientesAdapter adapter = new ListAmbientesAdapter(this, amb);
        listView.setAdapter(adapter);
    }

    public void novoAmbiente(View view) {
        Intent inttent = new Intent(this, NovoAmbiente.class);
        inttent.putExtra("ID_LOCAL", id_local);
        startActivity(inttent);
    }

    @Override
    protected void onStart() {
        carregarLista(id_local);
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AmbientesModel amb = (AmbientesModel) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(this, ItensAmbiente.class);
        intent.putExtra("ID_AMBIENTE", amb.getId());
        startActivity(intent);

    }

    public void chamarTelaEditar(long id) {
        Intent intent = new Intent(this, NovoAmbiente.class);
        intent.putExtra("ID_EDITAR", id);
        intent.putExtra("ID_LOCAL", id_local);
        startActivity(intent);
    }

    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja Editar Essa Ambiente?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AmbientesModel ambiente = (AmbientesModel) parent.getItemAtPosition(position);
                chamarTelaEditar(ambiente.getId());


            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
    }
}
