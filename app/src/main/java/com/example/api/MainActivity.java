package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.api.api.RetrofitClient;
import com.example.api.model.Phone;
import com.example.api.service.PhoneApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnGet;
    private Button btnCreate;
    private Button btnUpdate;
    private Button btndelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btnGetPhoneMain);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent get = new Intent(MainActivity.this, GetActivity.class);
                startActivity(get);
                finish();
            }
        });

        btnCreate = findViewById(R.id.btnPostPhone);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(MainActivity.this, CreatePhoneActivity.class);
                startActivity(create);
                finish();
            }
        });

        btnUpdate = findViewById(R.id.btnUpdateMain);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(update);
                finish();
            }
        });

        btndelete = findViewById(R.id.btnDeleteMain);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(delete);
                finish();
            }
        });


    }


}
