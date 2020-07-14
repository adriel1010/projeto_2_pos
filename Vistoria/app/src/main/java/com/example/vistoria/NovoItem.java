package com.example.vistoria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.model.AmbientesModel;
import com.example.model.ItensAmbienteModel;
import com.example.model.Locais;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NovoItem extends AppCompatActivity {

    private long id_ambiente = 0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    private int foto = 0;
    private static final int REQUEST_TAKE_PHOTO = 105;

    ItensAmbienteModel item = new ItensAmbienteModel();
    private   String currentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_item);

        Bundle bundle = getIntent().getExtras();

        imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView2 = (ImageView) findViewById(R.id.imageView3);
        imageView3 = (ImageView) findViewById(R.id.imageView4);


        if (bundle != null && bundle.containsKey("ID_AMBIENTE")) {
            Long id = bundle.getLong("ID_AMBIENTE");
            id_ambiente = id;
           // listView = (ListView) findViewById(R.id.listViewItensAmbientes);
           // carregarLista(id);
        }

        if (bundle != null && bundle.containsKey("ID_EDITAR")) {
            Long id = bundle.getLong("ID_EDITAR");


            item =  ItensAmbienteModel.findById(ItensAmbienteModel.class, id);

            EditText campoNomeLocal = (EditText) findViewById(R.id.input_nome_item_ambiente);
            EditText  campoDescricao = (EditText) findViewById(R.id.input_descricao_item_ambiente);


            campoNomeLocal.setText(item.getNomeItem());
            campoDescricao.setText(item.getDescricao());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            if (item.getImagem1() != null && item.getImagem1().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(item.getImagem1(), options);
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
                imageView1.setBackground(null);
                imageView1.setImageBitmap(bitmap);
            }
            if (item.getImagem2() != null && item.getImagem2().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(item.getImagem2(), options);
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
                imageView1.setBackground(null);
                imageView1.setImageBitmap(bitmap);
            }
            if (item.getImagem3() != null && item.getImagem3().length() > 2) {
                Bitmap bitmap = BitmapFactory.decodeFile(item.getImagem3(), options);
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView2);
                imageView1.setBackground(null);
                imageView1.setImageBitmap(bitmap);
            }

        }

        imageView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                imageView1.setImageBitmap(null);
                imageView1.setBackground(getResources().getDrawable(R.drawable.ic_add_a_photo));
                item.setImagem1("");
                return true;
            }
        });

        imageView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                imageView2.setImageBitmap(null);
                imageView2.setBackground(getResources().getDrawable(R.drawable.ic_add_a_photo));
                item.setImagem2("");
                return true;
            }
        });

        imageView3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                imageView3.setImageBitmap(null);
                imageView3.setBackground(getResources().getDrawable(R.drawable.ic_add_a_photo));
                item.setImagem3("");
                return true;
            }
        });
    }



    public void cadastrarItemAmbiente(View view) {
        EditText campoNomeLocal = (EditText) findViewById(R.id.input_nome_item_ambiente);
        EditText  campoDescricao = (EditText) findViewById(R.id.input_descricao_item_ambiente);


        AmbientesModel a = new AmbientesModel();
        a = Locais.findById(AmbientesModel.class, id_ambiente);
        item.setAmbiente(a);


        item.setNomeItem(campoNomeLocal.getText().toString());
        item.setDescricao(campoDescricao.getText().toString());

        item.save();

        Toast.makeText(this, "Cadastro Realizado Com Sucesso !!!", Toast.LENGTH_SHORT).show();
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // método executado sempre que cai nessa tela
        switch (requestCode) {
            case 105: {
                if (resultCode == RESULT_OK) {
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 3;
                        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, options);

                        // converter o bitmap em base64  'q é ideal para enviar para o servidor'
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                        byte[] binario = outputStream.toByteArray();
                        String fotoString = Base64.encodeToString(binario, Base64.DEFAULT);

                        if (foto == 1) {
                            imageView1.setImageBitmap(bitmap);
                            imageView1.setBackground(null);
                            item.setImagem1(currentPhotoPath);
                        } else if (foto == 2) {


                            imageView2.setImageBitmap(bitmap);
                            imageView2.setBackground(null);
                            item.setImagem1(currentPhotoPath);
                        } else if (foto == 3) {
                            imageView3.setImageBitmap(bitmap);
                            imageView3.setBackground(null);
                            item.setImagem1(currentPhotoPath);
                        }

                    } catch (Exception i) {
                        Log.e("Item", "Deu erro");
                    }
                }
                break;
            }
        }
    }

    public void chamarCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    public void tirarFoto1(View vire) {
        foto = 1;
        chamarCamera();
    }

    public void tirarFoto2(View vire) {
        foto = 2;
        chamarCamera();
    }

    public void tirarFoto3(View vire) {
        foto = 3;
        chamarCamera();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


}
