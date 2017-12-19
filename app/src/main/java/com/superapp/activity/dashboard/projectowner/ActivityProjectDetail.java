package com.superapp.activity.dashboard.projectowner;
/**
 * Created by Sana Kazi
 */

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.superapp.R;
import com.superapp.activity.contextualmenus.appointment.ActivityAppointment;
import com.superapp.activity.contextualmenus.clientprofile.ActivityClientProfile;
import com.superapp.activity.contextualmenus.communication.ActivityCommunication;
import com.superapp.activity.contextualmenus.notepad.ActivityNotepadLandingScreen;
import com.superapp.activity.contextualmenus.task.ActivityCreateTask;
import com.superapp.activity.contextualmenus.transaction.ActivityTransaction;
import com.superapp.fragment.projectowner.projectdetail.milestone.FragmentProjectDetailMilestone;
import com.superapp.fragment.projectowner.projectdetail.overview.FragmentProjectDetailOverview;
import com.superapp.fragment.projectowner.projectdetail.team.FragmentProjectDetailTeam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ActivityProjectDetail extends AppCompatActivity {
    private static final String TAG = ActivityProjectDetail.class.getSimpleName();
    Toolbar toolbar;
    public FloatingActionButton fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView context_menu_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        init();
        setAsAction();
        setupViewPager(viewPager);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SuperApp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        context_menu_project = findViewById(R.id.context_menu_project);


    }


    private void setAsAction() {


        //   fab.setRippleColor(getResources().getColor(R.color.floatingButtonColor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (getLiveFragment() != null)
                getLiveFragment().onFloatingButtonClick();*/
                Log.w(TAG, "You clicked from" + viewPager.getCurrentItem());
                Intent intent;
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        intent = new Intent(ActivityProjectDetail.this, ActivityCreateTask.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ActivityProjectDetail.this, ActivityCreateTask.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ActivityProjectDetail.this, ActivityAddClient.class);
                        intent.putExtra("from", "Team");
                        startActivity(intent);
                        break;
                }

            }
        });
        context_menu_project.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ActivityProjectDetail.this, context_menu_project);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.dashboard_project_owner_menu, popup.getMenu());


                // region Force icons to show
                Object menuHelper;
                Class[] argTypes;
                try {
                    Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                    fMenuHelper.setAccessible(true);
                    menuHelper = fMenuHelper.get(popup);
                    argTypes = new Class[]{boolean.class};
                    menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
                } catch (Exception e) {
                    // Possible exceptions are NoSuchMethodError and NoSuchFieldError
                    //
                    // In either case, an exception indicates something is wrong with the reflection code, or the
                    // structure of the PopupMenu class or its dependencies has changed.
                    //
                    // These exceptions should never happen since we're shipping the AppCompat library in our own apk,
                    // but in the case that they do, we simply can't force icons to display, so log the error and
                    // show the menu normally.

                    Log.w(TAG, "error forcing menu icons to show", e);
                    return;
                }
                //endregion


                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent;


                        switch (item.getItemId()) {
                            case R.id.menu_add_task:
                                intent = new Intent(ActivityProjectDetail.this, ActivityCreateTask.class);
                                startActivity(intent);

                                break;
                            case R.id.menu_edit_project:
                                intent = new Intent(ActivityProjectDetail.this, ActivityCreateProject.class);
                                startActivity(intent);

                                break;
                            case R.id.menu_communication:
                                intent = new Intent(ActivityProjectDetail.this, ActivityCommunication.class);
                                startActivity(intent);
                                break;

                            case R.id.menu_appointment:
                                intent = new Intent(ActivityProjectDetail.this, ActivityAppointment.class);
                                startActivity(intent);

                                break;
                            case R.id.menu_transaction:
                                intent = new Intent(ActivityProjectDetail.this, ActivityTransaction.class);
                                startActivity(intent);

                                break;
                            case R.id.menu_client_profile:
                                intent = new Intent(ActivityProjectDetail.this, ActivityClientProfile.class);
                                startActivity(intent);

                                break;

                            case R.id.menu_notepad:
                                intent = new Intent(ActivityProjectDetail.this, ActivityNotepadLandingScreen.class);
                                startActivity(intent);
                                break;
                            case R.id.menu_reminder:

                                break;
                            case R.id.menu_supervisor:

                                break;

                            case R.id.menu_delete:
                                break;
                        }


                        return false;
                    }
                });
                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentProjectDetailOverview(), "OVERVIEW");
        adapter.addFrag(new FragmentProjectDetailMilestone(), "MILESTONE");
        adapter.addFrag(new FragmentProjectDetailTeam(), "TEAM");
        viewPager.setAdapter(adapter);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
