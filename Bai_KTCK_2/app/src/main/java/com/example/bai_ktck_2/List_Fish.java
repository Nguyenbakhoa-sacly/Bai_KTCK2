package com.example.bai_ktck_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class List_Fish extends AppCompatActivity {

    RecyclerView rcyview;
    List_adapter list_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
        rcyview = findViewById(R.id.rcyview);
        rcyview.setLayoutManager(new LinearLayoutManager(this));
   rcyview.setItemAnimator(null);
        FirebaseRecyclerOptions<List_model> options=
                new FirebaseRecyclerOptions.Builder<List_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Fish"),List_model.class)
                        .build();

        list_adapter = new List_adapter(options);
        rcyview.setAdapter(list_adapter);

        //buttom add
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List_Fish.this,Add_item.class)  ;
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        list_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        list_adapter.stopListening();
    }
}