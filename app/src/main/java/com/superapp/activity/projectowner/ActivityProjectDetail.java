package com.superapp.activity.projectowner;

import android.media.Image;
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

import com.superapp.R;
import com.superapp.fragment.projectowner.projectdetail.milestone.FragmentProjectDetailMilestone;
import com.superapp.fragment.projectowner.projectdetail.overview.FragmentProjectDetailOverview;
import com.superapp.fragment.projectowner.projectdetail.team.FragmentProjectDetailTeam;

import java.util.ArrayList;
import java.util.List;

public class ActivityProjectDetail extends AppCompatActivity {
    private static final String TAG = ActivityProjectDetail.class.getSimpleName();
    Toolbar toolbar;
    public FloatingActionButton fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
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

        fab =findViewById(R.id.fab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setAsAction() {


     //   fab.setRippleColor(getResources().getColor(R.color.floatingButtonColor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (getLiveFragment() != null)
                getLiveFragment().onFloatingButtonClick();*/
                Log.w(TAG,"You clicked from" +    viewPager.getCurrentItem() );

            }
        });


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
