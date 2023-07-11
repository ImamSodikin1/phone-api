package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

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
import retrofit2.http.Body;
import retrofit2.http.Path;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtPhoneName;
    private EditText edtPrice;
    private PhoneApiService phoneApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtId = findViewById(R.id.edtUpdateId);
        edtPhoneName = findViewById(R.id.edtUpdatePhoneName);
        edtPrice = findViewById(R.id.edtUpdatePrice);
        phoneApiService = RetrofitClient.getClient().create(PhoneApiService.class);

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePhone();
            }
        });
    }

    private void updatePhone() {
        int phoneId = Integer.parseInt(edtId.getText().toString());
        String phoneName = edtPhoneName.getText().toString();
        int phonePrice = Integer.parseInt(edtPrice.getText().toString());

        Phone phone = new Phone();
        phone.setPhoneName(phoneName);
        phone.setPrice(phonePrice);
        Call<Phone> call = phoneApiService.updatePhone(phoneId, phone);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Phone Update Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed To Update Phone, Error Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Failed To Update Phone, Error Message : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}