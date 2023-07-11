package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class GetActivity extends AppCompatActivity {

    private EditText editTextPhoneId;
    private TextView textViewResult;
    private PhoneApiService phoneApiService;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        editTextPhoneId = findViewById(R.id.edtPhoneId);
        textViewResult = findViewById(R.id.tvGetPhone);
        phoneApiService = RetrofitClient.getClient().create(PhoneApiService.class);

        Button btnGet = findViewById(R.id.btnGetPhone);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int phoneId = Integer.parseInt(editTextPhoneId.getText().toString());
                getPhoneById(phoneId);
            }
        });


    }

    private void getPhoneById(int id) {
        Call<Phone> call = phoneApiService.getPhoneById(id);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Phone phone = response.body();
                    if (phone != null) {
                        StringBuilder result = new StringBuilder();
                        result.append("ID: ").append(phone.getId())
                                .append("\nPhone Name: ").append(phone.getPhoneName())
                                .append("\nPrice: ").append(phone.getPrice());
                        textViewResult.setText(result.toString());
                    } else {
                        textViewResult.setText("Phone not found");
                    }
                } else {
                    textViewResult.setText("Failed to get phone. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                textViewResult.setText("Failed to get phone. Error message: " + t.getMessage());
            }
        });
    }


}