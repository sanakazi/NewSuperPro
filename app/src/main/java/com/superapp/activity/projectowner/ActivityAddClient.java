package com.superapp.activity.projectowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.superapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddClient  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = ActivityAddClient.class.getSimpleName();
    Toolbar toolbar;
    Spinner spinner_country;
    Intent intent;
    private static String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        intent = getIntent();
        from = intent.getStringExtra("from");
        init();
        setAsAction();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if(from.equals("Client"))
        getSupportActionBar().setTitle("Add Client");
        else if(from.equals("Team"))
            getSupportActionBar().setTitle("Add Team");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_country = findViewById(R.id.spinner_country);
        spinner_country.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //   dataAdapter.setDropDownViewResource(R.layout.createproject_spinner_item);

        // attaching data adapter to spinner
        spinner_country.setAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    private void setAsAction() {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                onBackPressed();
                //  overridePendingTransition(0, R.anim.push_right);
                break;
        }
        return true;
    }
}
