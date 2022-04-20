package edu.quinnipiac.ser210.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.listapp.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

}

    public void onNewListClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NewListFragment.newInstance())
                .commit();
    }
    public void onEditListClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, EditListFragment.newInstance())
                .commit();
    }
    public void onAllListsClick(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AllListsFragment.newInstance())
                .commit();
    }
}