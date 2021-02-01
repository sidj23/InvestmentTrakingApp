package com.sid.investmenttrakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sid.investmenttrakingapp.R;
import com.sid.investmenttrakingapp.ui.common.CommonFragment;
import com.sid.investmenttrakingapp.ui.goals.GoalsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.am_bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        navigation.setSelectedItemId(R.id.nav_goals);

        RelativeLayout chatLayout = findViewById(R.id.am_chat_rl);
        chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchChatActivity();
            }
        });
    }

    private void launchChatActivity() {
        startActivity(new Intent(this, ChatActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                fragment = CommonFragment.newInstance("dashboard");
                break;

            case R.id.nav_goals:
                fragment = GoalsFragment.newInstance();
                break;

            case R.id.nav_profile:
                fragment = CommonFragment.newInstance("profile");
                break;
        }
        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.am_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}