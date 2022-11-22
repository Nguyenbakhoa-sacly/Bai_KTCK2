package com.example.bai_ktck_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Sign_Up extends AppCompatActivity {
    EditText edt_nameup, edt_emailup,edt_passup;
    Button btn_signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edt_emailup = findViewById(R.id.edt_emailup);
        edt_passup = findViewById(R.id.edt_passup);
        edt_nameup = findViewById(R.id.edt_nameup);
        btn_signup = findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp_user();
            }
        });
    }
    private void SignUp_user() {
        String email = edt_emailup.getText().toString().trim();
        String name = edt_nameup.getText().toString().trim();
        String pass = edt_passup.getText().toString().trim();
        if(email.isEmpty()){
            edt_emailup.setError("Nhập Email");
            edt_emailup.findFocus();
            return;
        }else if(name.isEmpty()){
            edt_nameup.setError("Nhập Name");
            edt_nameup.requestFocus();
            return;
        }else if (pass.isEmpty()){
            edt_passup.setError("Nhập Password");
            edt_passup.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name, email,pass);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(Sign_Up.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(Sign_Up.this,"Đăng ký không thành công, Đăng ký lại!",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(Sign_Up.this,"Đăng ký không thành công, Đăng ký lại!",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}