package com.alekso.planner.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alekso.planner.R;
import com.alekso.planner.ui.accounts.AccountsFragment;
import com.alekso.planner.ui.timeline.TimeLineFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        showAccountsFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            showAccountsFragment();
        } else if (id == R.id.nav_tasks) {

        } else if (id == R.id.nav_timeline) {
            showTimeLineFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAccountsFragment() {
        AccountsFragment fragment = (AccountsFragment) getSupportFragmentManager().findFragmentByTag(AccountsFragment.TAG);
        if (fragment == null) {
            fragment = AccountsFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment, AccountsFragment.TAG).commit();
    }

    private void showTimeLineFragment() {
        TimeLineFragment fragment = (TimeLineFragment) getSupportFragmentManager().findFragmentByTag(TimeLineFragment.TAG);
        if (fragment == null) {
            fragment = TimeLineFragment.newInstance();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment, TimeLineFragment.TAG).commit();
    }
}
