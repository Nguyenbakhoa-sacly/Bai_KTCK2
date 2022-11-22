package com.example.bai_ktck_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In extends AppCompatActivity  {
EditText edt_email,edt_pass;
TextView txt_signup;
Button btn_signin;
    CheckBox checkbox;
    SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        edt_email= findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_pass);
        txt_signup=findViewById(R.id.txt_signup);
        btn_signin= findViewById(R.id.btn_signin);
        checkbox = findViewById(R.id.checkbox);

        sharedPreferences = getSharedPreferences("SignIn",MODE_PRIVATE);
        edt_email.setText(sharedPreferences.getString("taikhoan",""));
        edt_pass.setText(sharedPreferences.getString("matkhau",""));
        checkbox.setChecked(sharedPreferences.getBoolean("checked",false));
        txt_signup.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent(Sign_In.this, Sign_Up.class);
                startActivity(intent);
            }
        });
        btn_signin.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                userSignIn();
            }
        });
    }


    private void userSignIn() {
        String email = edt_email.getText().toString().trim();
        String pass = edt_pass.getText().toString().trim();
        if(email.isEmpty()){
            edt_email.setError("Nhập Email");
            edt_email.requestFocus();
            return;

        } else if (pass.isEmpty()){
            edt_pass.setError("Nhập Password");
            edt_pass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(checkbox.isChecked()){
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("taikhoan",email);
                                editor.putString("matkhau",pass);
                                editor.putBoolean("checked",true);
                                editor.commit();
                            }else{
                                SharedPreferences.Editor  editor = sharedPreferences.edit();
                                editor.remove("taikhoan");
                                editor.remove("matkhau");
                                editor.remove("checked");
                                editor.commit();
                            }
                            startActivity(new Intent(Sign_In.this, List_Fish.class));
                        }else {
                            Toast.makeText(Sign_In.this,"Đăng nhập không thành công, vui lòng đăng nhập lại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}