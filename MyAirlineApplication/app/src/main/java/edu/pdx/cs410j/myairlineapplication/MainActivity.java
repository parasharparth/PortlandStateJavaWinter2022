package edu.pdx.cs410j.parth2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.pdx.cs410j.myairlineapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchreadme(View view) {
        Intent intent = new Intent(this, readmeactivity.class);
        startActivity(intent);
    }

    public void launchaddflight(View view) {
        Intent intent = new Intent(this, AddFlightActivity.class);
        startActivity(intent);
    }

    public void launchsearch(View view) {
        Intent intent = new Intent(this, LaunchSearchActivity.class);
        startActivity(intent);
    }

    public void launchdelete(View view) {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }
}