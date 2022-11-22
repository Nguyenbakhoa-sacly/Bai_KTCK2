package com.example.bai_ktck_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Mota_Fish extends AppCompatActivity {

    ImageView img_back,img_mota;
    TextView tenkh_mota,tentg_mota,mausac_mota,dactinh_mota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mota_fish);
        anhxa();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mota_Fish.this, List_Fish.class);
                startActivity(intent);
            }
        });
        List_model list_model =(List_model) getIntent().getSerializableExtra("list_model");
        tenkh_mota.setText(list_model.getTenkh());
        tentg_mota.setText(list_model.getTentg());
        mausac_mota.setText(list_model.getMausac());
        dactinh_mota.setText(list_model.getDactinh());

    }

    private void anhxa() {
        img_back = findViewById(R.id.img_back);
        img_mota = findViewById(R.id.img_mota);
        tenkh_mota = findViewById(R.id.tenkh_mota);
        tentg_mota = findViewById(R.id.tentg_mota);
        mausac_mota = findViewById(R.id.mausac_mota);
        dactinh_mota = findViewById(R.id.dactinh_mota);
    }

}