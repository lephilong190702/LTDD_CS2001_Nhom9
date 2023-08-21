package com.example.salebookapp;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String userName="",passWord="";

    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap;
    TextView tvwDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init firebase auth
        firebaseAuth=FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("please wait ");
        progressDialog.setCanceledOnTouchOutside(false);

        edtTaiKhoan= findViewById(R.id.edt_UserName);
        edtMatKhau = findViewById(R.id.edt_PassWord);
        btnDangNhap = findViewById(R.id.btn_DangNhap);
        tvwDangKy = findViewById(R.id.tvw_DangKy);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtTaiKhoan.getText().toString();
                passWord = edtMatKhau.getText().toString();
                if(userName.length() == 0 || passWord.length() == 0)
                    Toast.makeText(getApplicationContext(),"Bạn chưa điền đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    loginUser();
                }
            }
        });

        tvwDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginUser() {
        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        //login user
        firebaseAuth.signInWithEmailAndPassword(userName,passWord)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        checkUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void checkUser() {
        progressDialog.setMessage("checking User...");
        //check if use is user or admin from realtime database
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        //get user type
                        String userType=""+snapshot.child("userType").getValue();
                        //check user tupe
                        if(userType.equals("user")) {
                            //this is simple user, open user db
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                    }
                });
    }
}