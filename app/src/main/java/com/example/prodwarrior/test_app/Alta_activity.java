package com.example.prodwarrior.test_app;

import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prodwarrior.test_app.com.example.prodwarrior.test_app.sqlite_db.Users_DB;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prodwarrior on 01/09/2016.
 */
public class Alta_activity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView user_image;
    private String Image_File;
    /*Componentes de entrada para el registro de usuario*/
    private EditText names;
    private EditText lastName;
    private EditText phone;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry_layout);
        this.CreateDirs();

        user_image = (ImageView) findViewById(R.id.User_Picture);
        names = (EditText) findViewById(R.id.User_First_Names);
        lastName = (EditText) findViewById(R.id.User_last_Names);
        phone = (EditText) findViewById(R.id.User_Phone);
        email = (EditText) findViewById(R.id.User_Mail);
    }

    public void Take_Picture(View view){
        Intent take_picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = this.SavePhoto();
        if(take_picture.resolveActivity(getPackageManager()) != null){
            take_picture.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(take_picture, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(REQUEST_IMAGE_CAPTURE == requestCode && resultCode == RESULT_OK){
            user_image.setImageBitmap(BitmapFactory.decodeFile(this.Image_File));
        }
    }

    private void CreateDirs(){
        File file_path = Environment.getExternalStorageDirectory();
        File image_dir = new File(file_path.getAbsolutePath(), "User_Images");
        if (!image_dir.exists()){
            image_dir.mkdirs();
        }
    }

    private Uri SavePhoto(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "URS" + date;
        this.Image_File = Environment.getExternalStorageDirectory().getAbsolutePath() + "/User_Images/" + photoCode + ".jpg";

        File photo  = new File(this.Image_File);
        Uri uri = Uri.fromFile(photo);

        return uri;
    }

    public void SaveData(View view){
        String user_names = this.names.getText().toString();
        String user_last_name = this.lastName.getText().toString();
        String user_phone = this.phone.getText().toString();
        String user_mail = this.email.getText().toString();

        Users_DB dataBase = new Users_DB(this, "USUARIOS", null, 1);
        SQLiteDatabase writer = dataBase.getWritableDatabase();
        try {
            writer.execSQL("INSERT INTO USUARIOS (nombres, apellido, telefono, email, photo_path)" +
                    "VALUES (" + user_names + ", " + user_last_name + ", " + user_phone + ", " + user_mail + ", " +
                    Image_File + ");");
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(Alta_activity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(Alta_activity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
    }

}
