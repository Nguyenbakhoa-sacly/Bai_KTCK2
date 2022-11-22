package com.example.bai_ktck_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_item extends AppCompatActivity {
    Button btn_save,btn_back;
    EditText edt_tenkh,edt_tentg,edt_mausac,edt_dactinh,edt_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        anhxa();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserData();
                clearAll();
            }
        });

        //thoat
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
                Intent intent= new Intent(Add_item.this, List_Fish.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa() {
        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);

        edt_tenkh = findViewById(R.id.edt_tenkh);
        edt_mausac = findViewById(R.id.edt_mausac);
        edt_tentg = findViewById(R.id.edt_tentg);
        edt_dactinh = findViewById(R.id.edt_dactinh);
        edt_link = findViewById(R.id.edt_link);

    }
    private void inserData(){

        Map<String,Object> map = new HashMap<>();
        map.put("tenkh",edt_tenkh.getText().toString());
        map.put("mausac",edt_mausac.getText().toString());
        map.put("tentg",edt_tentg.getText().toString());
        map.put("dactinh",edt_dactinh.getText().toString());
        map.put("img",edt_link.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Tree").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Add_item.this,"Thêm móm ăn thành công!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_item.this,"Thêm món ăn thất bại!",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private  void clearAll(){
        edt_tentg.setText("");
        edt_dactinh.setText("");
        edt_link.setText("");
        edt_tenkh.setText("");
        edt_mausac.setText("");
    }

}