package com.homework.cat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SearchFragment searchFragment = new SearchFragment();
        final FavoriteFragment favoriteFragment = new FavoriteFragment();
        BottomNavigationView bottomnavigationview = findViewById(R.id.bottomnavigationview);
        bottomnavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              if (menuItem.getItemId()==R.id.tab1) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.fl_slot, searchFragment).commit();
                  return true;
              }else if (menuItem.getItemId()==R.id.tab2) {
                  getSupportFragmentManager().beginTransaction().replace(R.id.fl_slot,favoriteFragment).commit();
                    return true;
              }
                return false;
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_slot, searchFragment);
        fragmentTransaction.commit();

    }




}
