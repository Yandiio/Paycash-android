package com.nawasena.dev.paycash;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.nawasena.dev.paycash.Fragment.history.HistoryFragment;
import com.nawasena.dev.paycash.Fragment.home.HomeFragment;
import com.nawasena.dev.paycash.Fragment.inbox.InboxFragment;
import com.nawasena.dev.paycash.Fragment.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nawasena.dev.paycash.Fragment.history.HistoryFragment;
import com.nawasena.dev.paycash.Fragment.home.HomeFragment;
import com.nawasena.dev.paycash.Fragment.inbox.InboxFragment;
import com.nawasena.dev.paycash.Fragment.profile.ProfileFragment;

//import com.example.paycash.Fragment.R;

public class MainFragment extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment= null;
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_history:
                fragment = new HistoryFragment();
                break;
            case R.id.navigation_report:
                fragment = new InboxFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;

        }
        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.nav_host_view, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}




