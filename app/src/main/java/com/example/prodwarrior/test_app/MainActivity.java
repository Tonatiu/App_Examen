package com.example.prodwarrior.test_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prodwarrior.test_app.com.example.prodwarrior.test_app.sqlite_db.Users_DB;

public class MainActivity extends AppCompatActivity {

    /*Métodos de la clase*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Go_Alta_Activity(View view){
        Intent intent = new Intent(this, Alta_activity.class);
        startActivity(intent);
    }

    public void Go_User_List_Activity(View view){
        Intent intent = new Intent(this, User_List_Activity.class);
        startActivity(intent);
    }
}
