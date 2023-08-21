package com.example.salebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.salebookapp.fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class EditProfile extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    Button btnSave;
    TextView tv_id;
    EditText et_ten, et_password, et_sdt, et_email, et_gioiTinh, et_ngaySinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        btnSave=findViewById(R.id.btn_saveEdit);

        et_ten=findViewById(R.id.et_ten);
        tv_id=findViewById(R.id.tv_id);
        et_password=findViewById(R.id.et_password);
        et_sdt=findViewById(R.id.et_sdt);
        et_email=findViewById(R.id.et_email);
        et_gioiTinh=findViewById(R.id.et_gioitinh);
        et_ngaySinh=findViewById(R.id.et_ns);

        //init firebase auth
        firebaseAuth=FirebaseAuth.getInstance();
        checkUser();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUser();
                startActivity(new Intent(EditProfile.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setUser() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            startActivity(new Intent(EditProfile.this, ProfileFragment.class));
        }
        else {
            DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid());
            String sdt= et_sdt.getText().toString();
            String render= et_gioiTinh.getText().toString();
            String bd= et_ngaySinh.getText().toString();
            String ten= et_ten.getText().toString();
            String email= et_email.getText().toString();
            String pw= et_password.getText().toString();

            userRef.child("phone").setValue(sdt);
            userRef.child("gender").setValue(render);
            userRef.child("birthday").setValue(bd);
            userRef.child("name").setValue(ten);
            userRef.child("email").setValue(email);
            userRef.child("password").setValue(pw);
        }
    }

    private void checkUser() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            startActivity(new Intent(EditProfile.this, LoginActivity.class));
        }
        else {
            DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot snapshot) {
                    //kiem tra dl có tồn tại
                    if(snapshot.exists()){
                        String name= snapshot.child("name").getValue(String.class);
                        String id= snapshot.child("uid").getValue(String.class);
                        String passWord=snapshot.child("password").getValue(String.class);
                        String email=snapshot.child("email").getValue(String.class);
                        String phone=snapshot.child("phone").getValue(String.class);
                        String gender=snapshot.child("gender").getValue(String.class);
                        String bd=snapshot.child("birthday").getValue(String.class);
                        //chèn
                        et_ten.setText(name);
                        tv_id.setText(id);
                        et_password.setText(passWord);
                        et_email.setText(email);
                        et_sdt.setText(phone);
                        et_gioiTinh.setText(gender);
                        et_ngaySinh.setText(bd);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }

    }
}