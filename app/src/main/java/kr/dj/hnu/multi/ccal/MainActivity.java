package kr.dj.hnu.multi.ccal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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

        SharedPreferences pref = getSharedPreferences("saveView", MODE_PRIVATE);

        int id = pref.getInt("id", R.id.ic_monthly);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (id){
            case R.id.ic_monthly:
                fragmentTransaction.replace(R.id.main_container, new MonthCalendar());
                fragmentTransaction.commit();
                break;
            case R.id.ic_weekly:
                fragmentTransaction.replace(R.id.main_container, new WeekCalendar());
                fragmentTransaction.commit();
                break;
            case R.id.ic_daily:
                fragmentTransaction.replace(R.id.main_container, new DailyCalendar());
                fragmentTransaction.commit();
                break;
            default:
                fragmentTransaction.replace(R.id.main_container, new MonthCalendar());
                fragmentTransaction.commit();

        }



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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.ic_monthly) {
            getSupportActionBar().setTitle("월");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new MonthCalendar());
            fragmentTransaction.commit();
            savePref(id);
        } else if (id == R.id.ic_weekly) {
            getSupportActionBar().setTitle("주");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new WeekCalendar());
            fragmentTransaction.commit();
            savePref(id);
        } else if (id == R.id.ic_daily) {
            getSupportActionBar().setTitle("일");
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new DailyCalendar());
            fragmentTransaction.commit();
            savePref(id);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void savePref(int id){
        SharedPreferences pref = getSharedPreferences("saveView", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("id", id);
        edit.commit();
    }
}
