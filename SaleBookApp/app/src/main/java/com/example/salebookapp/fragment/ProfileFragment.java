package com.example.salebookapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebookapp.AddCartActivity;
import com.example.salebookapp.EditProfile;
import com.example.salebookapp.LoginActivity;
import com.example.salebookapp.R;
import com.example.salebookapp.adapter.ProfileAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnEdit,btnLogOut;
    TextView txt_thanhtoan,tv_ten;
    RecyclerView recyclerView;
    String[] items = {"Thanh toán khi nhận hàng", "Momo",
            "Tài khoản ngân hàng"};
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnEdit= view.findViewById(R.id.btn_Edit);
        txt_thanhtoan=view.findViewById(R.id.txt_thanhtoan);

        tv_ten= view.findViewById(R.id.tv_ten);
        btnLogOut=view.findViewById(R.id.btn_logOut);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ProfileAdapter adapter = new ProfileAdapter(items);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String text) {
                txt_thanhtoan.setText(text);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileFragment.this.getContext(), EditProfile.class);
                startActivity(intent);
            }
        });

        //init firebase auth
        firebaseAuth=FirebaseAuth.getInstance();
        checkUser();
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });

        return view;
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Xác nhận đăng xuất")
                .setMessage("Bạn có muốn đăng xuất khỏi ứng dụng không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        checkUser();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }


    private void checkUser() {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser==null){
            startActivity(new Intent(ProfileFragment.this.getContext(), LoginActivity.class));
        }
        else {
            DatabaseReference userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot snapshot) {
                    //kiem tra dl có tồn tại
                    if(snapshot.exists()){
                        String name= snapshot.child("name").getValue(String.class);
                        //chèn
                        tv_ten.setText(name);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
    }
}