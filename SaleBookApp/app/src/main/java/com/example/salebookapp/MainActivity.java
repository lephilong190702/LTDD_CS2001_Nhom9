package com.example.salebookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.salebookapp.fragment.CartFragment;
import com.example.salebookapp.fragment.CategoryFragment;
import com.example.salebookapp.fragment.HomeFragment;
import com.example.salebookapp.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CategoryFragment categoryFragment = new CategoryFragment();

    ProfileFragment profileFragment = new ProfileFragment();
    CartFragment cartFragment = new CartFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item)
    {

        if (item.getItemId() == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        }else if (item.getItemId() == R.id.category) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, categoryFragment)
                    .commit();
            return true;
        }else if (item.getItemId() == R.id.shoppingcart) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, cartFragment)
                    .commit();
            return true;
        }else if (item.getItemId() == R.id.profile) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, profileFragment)
                    .commit();
            return true;
        }
        return false;
    }

}