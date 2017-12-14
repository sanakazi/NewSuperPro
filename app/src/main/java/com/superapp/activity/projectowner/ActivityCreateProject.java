package com.superapp.activity.projectowner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.superapp.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityCreateProject extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = ActivityCreateProject.class.getSimpleName();
    Toolbar toolbar;
    ImageView btn_add_client;
    Spinner spinner_profession,spinner_startdate,spinner_handover_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        init();
        setAsAction();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Project");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_profession = findViewById(R.id.spinner_profession);
        spinner_profession.setOnItemSelectedListener(this);

        spinner_startdate = findViewById(R.id.spinner_startdate);
        spinner_startdate.setOnItemSelectedListener(this);

        spinner_handover_date = findViewById(R.id.spinner_handover_date);
        spinner_handover_date.setOnItemSelectedListener(this);

        btn_add_client = findViewById(R.id.btn_add_client);

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
        spinner_profession.setAdapter(dataAdapter);
        spinner_startdate.setAdapter(dataAdapter);
        spinner_handover_date.setAdapter(dataAdapter);
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


    private void setAsAction() {

        btn_add_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCreateProject.this,ActivityAddClient.class);
                intent.putExtra("from","Client");
                startActivity(intent);
            }
        });
    }

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
