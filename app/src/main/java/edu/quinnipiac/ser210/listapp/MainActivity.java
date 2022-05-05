package edu.quinnipiac.ser210.listapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavController navController;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private NewListFragment test;
    private ShareActionProvider shareActionProvider;
    private Button buttonNew,buttonAdd,buttonView,
     addButtonNewView,createButtonNewView,editFragNewView;
    private ConstraintLayout headerlayout;
    int color = 2;
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.nav_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to share a list?");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonNew = findViewById(R.id.newbutton);
        buttonAdd = findViewById(R.id.editbutton);
        buttonView = findViewById(R.id.allviewbutton);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //need to use intent to pass if color is blue oncreate frag change color

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        headerlayout = findViewById(R.id.header_layout);
        int id = item.getItemId();
        if (id == R.id.backgroundblue) {
        color = 2;

        toolbar.setBackgroundColor(getResources().getColor(R.color.light_blue));
        headerlayout.setBackgroundColor(getResources().getColor(R.color.light_blue));
            findViewById(R.id.newbutton).setBackgroundColor(getResources().getColor(R.color.light_blue));
            findViewById(R.id.editbutton).setBackgroundColor(getResources().getColor(R.color.light_blue));
            buttonView.setBackgroundColor(getResources().getColor(R.color.light_blue));

        } else if (id == R.id.backgroundred) {

            color = 1;

            toolbar.setBackgroundColor(getResources().getColor(R.color.maroon));
            headerlayout.setBackgroundColor(getResources().getColor(R.color.maroon));
            buttonNew.setBackgroundColor(getResources().getColor(R.color.maroon));
            buttonAdd.setBackgroundColor(getResources().getColor(R.color.maroon));
            findViewById(R.id.allviewbutton).setBackgroundColor(getResources().getColor(R.color.maroon));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public int backgroundColor() {
           return color;
    }



}