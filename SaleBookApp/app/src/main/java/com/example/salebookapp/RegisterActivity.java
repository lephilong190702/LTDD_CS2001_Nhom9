package com.example.salebookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    //firebase auth
    private FirebaseAuth firebaseAuth;
    //progress dialog
    private ProgressDialog progressDialog;
    private String userName="",email="",passWord="";

    EditText edtTaiKhoan, edtEmail, edtMatKhau, edtXacNhanMatKhau;
    Button btnDangKy;
    TextView tvwCoTaiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init firebase auth
        firebaseAuth=FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("please wait ");
        progressDialog.setCanceledOnTouchOutside(false);

        edtTaiKhoan = findViewById(R.id.edt_RegUserName);
        edtEmail = findViewById(R.id.edt_RegEmail);
        edtMatKhau = findViewById(R.id.edt_RegPassWord);
        edtXacNhanMatKhau = findViewById(R.id.edt_ConfirmPassWord);
        btnDangKy = findViewById(R.id.btn_DangKy);
        tvwCoTaiKhoan = findViewById(R.id.tvw_XacNhanMatKhau);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 userName = edtTaiKhoan.getText().toString();
                 email = edtEmail.getText().toString();
                 passWord = edtMatKhau.getText().toString();
                String confirmPassWord = edtXacNhanMatKhau.getText().toString();
                if(userName.length() == 0 || email.length() == 0 || passWord.length() == 0 || confirmPassWord.length() == 0)
                    Toast.makeText(getApplicationContext(),"Bạn chưa điền đủ thông tin", Toast.LENGTH_SHORT).show();
                else if(!passWord.equals(confirmPassWord)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();}
                else {
                    createUserAccount();
                }
            }
        });

        tvwCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void createUserAccount() {
        progressDialog.setMessage("Creating account...");
        progressDialog.show();
        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email,passWord)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        updateUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //account creating failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void updateUserInfo() {
        progressDialog.setTitle("Saving user info...");

        //timestamp
        long timestamp=System.currentTimeMillis();
        //get current user uid, since user is registered so we can get now
        String uid= firebaseAuth.getUid();
        //setup data to add in db
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("password",passWord);
        hashMap.put("email",email);
        hashMap.put("name",userName);
        hashMap.put("profileImage","");
        hashMap.put("phone","");
        hashMap.put("gender","");
        hashMap.put("birthday","");
        hashMap.put("userType","user");
        hashMap.put("timestamp",timestamp);

        //set
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,"account created...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

}