package com.example.vistoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.Locais;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocalVistoria extends AppCompatActivity {

    EditText campoNomeLocal, enderecoLocal, nomeProprietario;
    Locais local = new Locais();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_vistoria);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("ID_LOCAL_EDITAR")) {
            Long id = bundle.getLong("ID_LOCAL_EDITAR");
            local =  Locais.findById(Locais.class, id);

            EditText campoNomeLocal = (EditText) findViewById(R.id.input_nome_local);
            EditText  enderecoLocal = (EditText) findViewById(R.id.input_endereco_local);
            EditText nomeProprietario = (EditText) findViewById(R.id.input_nome_proprietario);

            campoNomeLocal.setText(local.getNome_local());
            enderecoLocal.setText(local.getEndereco_local());
            nomeProprietario.setText(local.getNome_proprietario());

        }

    }

    public void cadastrarLocal(View view) {
        EditText campoNomeLocal = (EditText) findViewById(R.id.input_nome_local);
        EditText  enderecoLocal = (EditText) findViewById(R.id.input_endereco_local);
        EditText nomeProprietario = (EditText) findViewById(R.id.input_nome_proprietario);


        local.setNome_local(campoNomeLocal.getText().toString());
        local.setEndereco_local(enderecoLocal.getText().toString());
        local.setNome_proprietario(nomeProprietario.getText().toString());
        local.save();

        Toast.makeText(this, "Cadastro Realizado Com Sucesso !!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
