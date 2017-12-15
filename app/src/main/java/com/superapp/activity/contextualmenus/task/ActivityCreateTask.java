package com.superapp.activity.contextualmenus.task;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.superapp.R;
import com.superapp.activity.contextualmenus.ActivitySelectTeam;
import com.superapp.custom.HorizontalSpacingDecoration;
/**
 * Created by Sana Kazi
 */
public class ActivityCreateTask extends AppCompatActivity {
    Toolbar toolbar;
    protected RecyclerView recycler_view_list;
    CreateTaskImageUploadAdapter createTaskImageUploadAdapter;
    private LinearLayout background_layout, upload_popover_layout;
    private ScrollView scrollView;
    private ImageView select_upload,select_team;
    private TextInputLayout layout_task_name,layout_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        init();
        setAsAction();
    }


    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycler_view_list = findViewById(R.id.recycler_view_list);

        background_layout = findViewById(R.id.background_layout);
        upload_popover_layout = findViewById(R.id.upload_popover_layout);
        scrollView = findViewById(R.id.scrollView);
        select_upload = findViewById(R.id.select_upload);
        select_team= findViewById(R.id.select_team);
        layout_task_name= findViewById(R.id.layout_task_name);
        layout_location= findViewById(R.id.layout_location);
    }

    private void setAsAction() {

        recycler_view_list.setHasFixedSize(true);
        recycler_view_list.setLayoutManager(new LinearLayoutManager(ActivityCreateTask.this, LinearLayoutManager.HORIZONTAL, false));
        recycler_view_list.addItemDecoration(new HorizontalSpacingDecoration(10));
        recycler_view_list.setNestedScrollingEnabled(false);
        createTaskImageUploadAdapter = new CreateTaskImageUploadAdapter(ActivityCreateTask.this, "abc");
        recycler_view_list.setAdapter(createTaskImageUploadAdapter);
        select_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleViews(true);
            }
        });
        background_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleViews(false);
            }
        });
        select_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCreateTask.this, ActivitySelectTeam.class);
                startActivity(intent);
            }
        });
    }

    private void handleViews(boolean enable) {
        if(enable) {
            layout_task_name.setEnabled(false);
            layout_location.setEnabled(false);
            background_layout.setVisibility(View.VISIBLE);
            upload_popover_layout.setVisibility(View.VISIBLE);

        }
        else {
            layout_task_name.setEnabled(true);
            layout_location.setEnabled(true);
            background_layout.setVisibility(View.GONE);
            upload_popover_layout.setVisibility(View.GONE);
        }
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
