package com.sheeloh.alhusban.sheelohapp;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

class CountryAp {
    public String ID;
    public String name;
    public String name_s;

    @Override
    public String toString() {
        return this.name;            // What to display in the Spinner list.
    }
}

public class Register extends SuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolBar();


        String[] items = getResources().getStringArray(R.array.country);
        String[] itemsID = getResources().getStringArray(R.array.countryID);
        List<CountryAp> countryAps = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            CountryAp countryAp = new CountryAp();
            countryAp.name = items[i];
            countryAp.ID = itemsID[i];
            countryAps.add(countryAp);
        }
        Log.e("count", countryAps.size() + "");
        Spinner userSpinner = (Spinner) findViewById(R.id.spinner);
        if (userSpinner != null) {
            userSpinner.setAdapter(new ArrayAdapter(this, R.layout.spinner, countryAps));
        }

        (findViewById(R.id.submit_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
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
}
