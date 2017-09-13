package com.mani.cashkaro.cashkaro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mani.cashkaro.cashkaro.fragments.HomePagerAdapter;
import com.mani.cashkaro.cashkaro.fragments.Homemodel;
import com.mani.cashkaro.cashkaro.fragments.WebviewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Homemodel homemodel;
    ArrayList<Homemodel> arrHome = new ArrayList<>();
    private RecyclerView viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (RecyclerView) findViewById(R.id.viewpager);

        homemodel = new Homemodel();
        homemodel.setImage(R.drawable.flipkart);
        homemodel.setName("Flipkart Offers");
        homemodel.setDesc("Earn Upto 7.5% CashKaro Rewards on all orders at Flipkart");
        homemodel.setViewmore("7");
        arrHome.add(homemodel);

        homemodel = new Homemodel();
        homemodel.setImage(R.drawable.bigbasket);
        homemodel.setName("Bigbasket Offers");
        homemodel.setDesc("Earn Upto 5.25% CashKaro Cashback on top of all orders at Bigbasket");
        homemodel.setViewmore("7");
        arrHome.add(homemodel);

        homemodel = new Homemodel();
        homemodel.setImage(R.drawable.amazon);
        homemodel.setName("Amazon Offers");
        homemodel.setDesc("Earn Upto 6.5% CashKaro Rewards on top of Upto 90% Off Amazon Offers");
        homemodel.setViewmore("12");
        arrHome.add(homemodel);


        homemodel = new Homemodel();
        homemodel.setImage(R.drawable.jabong);
        homemodel.setName("Jabong Offers");
        homemodel.setDesc("Flat 25% Off Coupon + Rs 250 CashKaro Cashback on orders over Rs 500");
        homemodel.setViewmore("63");
        arrHome.add(homemodel);

        homemodel = new Homemodel();
        homemodel.setImage(R.drawable.makemytrip);
        homemodel.setName("Makmytrip Offers");
        homemodel.setDesc("Earn upto Rs 750 CashKaro Cashback on all Makemytrip bookings");
        homemodel.setViewmore("17");
        arrHome.add(homemodel);

        viewPager.setLayoutManager(new LinearLayoutManager(this));
        HomePagerAdapter adapter = new HomePagerAdapter(MainActivity.this, arrHome);

        viewPager.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            ActivityCompat.finishAffinity(this);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.nav_gallery) {
            Intent main = new Intent(this, WebviewActivity.class);
            main.putExtra("prName", "Top Offers");
            startActivity(main);
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(this, Profiles.class));
        } else if (id == R.id.nav_per) {
            startActivity(new Intent(this, Permissions.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
