package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api.api.RetrofitClient;
import com.example.api.service.PhoneApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private EditText edtDeleteId;
    private Button btnDelete;
    private PhoneApiService phoneApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        edtDeleteId = findViewById(R.id.edtDeleteId);

        phoneApiService = RetrofitClient.getClient().create(PhoneApiService.class);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePhone();
            }
        });

    }

    private void deletePhone() {
        int phoneId = Integer.parseInt(edtDeleteId.getText().toString());

        Call<Void> call = phoneApiService.deletePhone(phoneId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteActivity.this, "Delete Phone Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteActivity.this, "Failed to delete Phone, Error Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteActivity.this, "Failed to delete Phone , Error Message : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}