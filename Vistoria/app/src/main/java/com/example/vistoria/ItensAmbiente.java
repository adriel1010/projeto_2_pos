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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.ListAmbientesAdapter;
import com.example.adapter.ListItensAmbienteAdapter;
import com.example.model.AmbientesModel;
import com.example.model.ItensAmbienteModel;

import java.util.List;

public class ItensAmbiente extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private long id_ambiente = 0;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_ambiente);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("ID_AMBIENTE")) {
            Long id = bundle.getLong("ID_AMBIENTE");
            id_ambiente = id;
            listView = (ListView) findViewById(R.id.listViewItensAmbientes);
            listView.setOnItemLongClickListener(this);
            carregarLista(id);
        }
    }

    @Override
    protected void onStart() {
        carregarLista(id_ambiente);
        super.onStart();
    }

    private void carregarLista(Long id_local) {
        List<ItensAmbienteModel> amb = ItensAmbienteModel.findWithQuery(ItensAmbienteModel.class, "Select * from ITENS_AMBIENTE_MODEL where ambiente = ? ", String.valueOf(id_local));

        ListItensAmbienteAdapter adapter = new ListItensAmbienteAdapter(this, amb);
        listView.setAdapter(adapter);
    }

    public void novoItem(View view) {
        Intent inttent = new Intent(this, NovoItem.class);
        inttent.putExtra("ID_AMBIENTE", id_ambiente);
        startActivity(inttent);
    }

    public void chamarTelaEditar(long id) {
        Intent intent = new Intent(this, NovoItem.class);
        intent.putExtra("ID_EDITAR", id);
        intent.putExtra("ID_AMBIENTE", id_ambiente);
        startActivity(intent);
    }

    public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja Editar Essa Ambiente?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ItensAmbienteModel ambiente = (ItensAmbienteModel) parent.getItemAtPosition(position);
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
