package com.example.vistoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.AmbientesModel;
import com.example.model.Locais;

public class NovoAmbiente extends AppCompatActivity {

    private long id_local = 0;
    AmbientesModel ambiente = new AmbientesModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_ambiente);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("ID_LOCAL")) {
            Long id = bundle.getLong("ID_LOCAL");
            id_local = id;
        }
        if (bundle != null && bundle.containsKey("ID_EDITAR")) {
            Long id = bundle.getLong("ID_EDITAR");


            ambiente =  AmbientesModel.findById(AmbientesModel.class, id);

            EditText editTextNomeAmbiente = (EditText) findViewById(R.id.input_nome_ambiente);
            EditText editTextDescricaoAmbiente = (EditText) findViewById(R.id.input_descricao_ambiente);

            editTextNomeAmbiente.setText(ambiente.getNomeAmbiente());
            editTextDescricaoAmbiente.setText(ambiente.getDescricao());

        }


    }

    public void cadastrarAmbiente(View view) {

        EditText editTextNomeAmbiente = (EditText) findViewById(R.id.input_nome_ambiente);
        EditText editTextDescricaoAmbiente = (EditText) findViewById(R.id.input_descricao_ambiente);


        ambiente.setDescricao(editTextDescricaoAmbiente.getText().toString());
        ambiente.setNomeAmbiente(editTextNomeAmbiente.getText().toString());

        Locais local = new Locais();
        local = Locais.findById(Locais.class, id_local);
        ambiente.setLocalAmbiente(local);

        ambiente.save();

        Toast.makeText(this, "Ambiente Adicionado", Toast.LENGTH_SHORT).show();
        finish();

    }

}
