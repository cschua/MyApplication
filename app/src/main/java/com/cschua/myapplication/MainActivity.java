package com.cschua.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import com.cschua.myapplication.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private static final String SNACKBAR_IS_SHOWN = "SNACKBAR_IS_SHOWN";
    private AppBarConfiguration mAppBarConfiguration;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpNavigation();
        setUpFab();
    }

    @Override
    protected void onResume() {
        super.onResume();

        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menuNav = navigationView.getMenu();
        MenuItem item = menuNav.findItem(R.id.nav_share);
        item.setVisible(NetworkUtils.isConnectedToInternet(this));
    }

    private void setUpFab() {
        FloatingActionButton fab = findViewById(R.id.fab);

        if (snackbar == null) {
            snackbar = Snackbar.make(fab, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (snackbar.isShown()) {
                    snackbar.dismiss();
                } else {
                    snackbar.show();
                }
            }
        });
    }

    private void setUpNavigation() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
            R.id.nav_tools, R.id.nav_share, R.id.nav_send)
            .setDrawerLayout(drawer)
            .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
            || super.onSupportNavigateUp();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.getBoolean(SNACKBAR_IS_SHOWN) && snackbar != null) {
            snackbar.show();
        }
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SNACKBAR_IS_SHOWN, snackbar == null ? false : snackbar.isShown());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }
}
