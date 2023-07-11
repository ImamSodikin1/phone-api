package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.api.api.RetrofitClient;
import com.example.api.model.Phone;
import com.example.api.service.PhoneApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePhoneActivity extends AppCompatActivity {

    private EditText edtPhoneName;
    private EditText edtPrice;
    private PhoneApiService phoneApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_phone);

        edtPhoneName = findViewById(R.id.edtPhoneName);
        edtPrice = findViewById(R.id.edtPhonePrice);

        phoneApiService = RetrofitClient.getClient().create(PhoneApiService.class);

       Button btnPost = findViewById(R.id.btnCreate);
       btnPost.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               createPhone();
           }
       });
    }

    private void createPhone() {
        String phoneName = edtPhoneName.getText().toString();
        int phonePrice = Integer.parseInt(edtPrice.getText().toString());

        Phone phone = new Phone();
        phone.setPhoneName(phoneName);
        phone.setPrice(phonePrice);

        Call<Phone> call = phoneApiService.createPhone(phone);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreatePhoneActivity.this, "Phone created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreatePhoneActivity.this, "Failed to create phone. Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(CreatePhoneActivity.this, "Failed to create phone. Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}