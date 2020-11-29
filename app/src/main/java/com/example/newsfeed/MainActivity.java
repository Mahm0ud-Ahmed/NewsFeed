package com.example.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager_conteiner);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getBaseContext());
        viewPager.setAdapter(adapter);
        tabLayout = findViewById(R.id.tab_conteiner);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcon();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void setupTabIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.world);
        tabLayout.getTabAt(1).setIcon(R.drawable.news);
        tabLayout.getTabAt(2).setIcon(R.drawable.running);
        tabLayout.getTabAt(3).setIcon(R.drawable.technology);
        tabLayout.getTabAt(4).setIcon(R.drawable.heartbeat);
    }

}