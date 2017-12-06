package com.superapp.activity.projectowner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superapp.R;
import com.superapp.activity.base.BaseAppCompatActivity;
import com.superapp.fragment.projectowner.dashboard.clients.FragmentDashboardClient;
import com.superapp.fragment.projectowner.dashboard.projects.FragmentDashboardProject;
import com.superapp.fragment.projectowner.dashboard.team.FragmentDashboardTeam;

import java.util.ArrayList;
import java.util.List;

public class ActivityMainOwner extends BaseAppCompatActivity {
    private static final String TAG = ActivityMainOwner.class.getSimpleName();
    ActionBarDrawerToggle toggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    DrawerLayout drawer;
    public int row_index;
    Toolbar toolbar;
    public FloatingActionButton fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static int expanded_item = 0;
    final String TITLES[] = {"All Projects", "Settings", "Coach marks", "Share/Invite friends", "Rate us on Playstore", "Help & Support", "Feedback", "Legal", "Logout"};
    final int IMAGES[] = {R.mipmap.all_projects_sidemenu, R.mipmap.settings_sidemenu, R.mipmap.coach_marks_sidemenu, R.mipmap.share_sidemenu, R.mipmap.rate_sidemenu, R.mipmap.help_sidemenu, R.mipmap.feedback_sidemenu, R.mipmap.legal_sidemenu, R.mipmap.logout_sidemenu};
    private int[] tabIcons = {
            R.mipmap.clients_tabbar,
            R.mipmap.projects_tabbar,
            R.mipmap.team_tabbar
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setAsAction();
        setupViewPager(viewPager);
        setupTabIcons();

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SuperApp");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.nav_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
       /* mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));*/
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setAsAction() {

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        // drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                expanded_item = 0;

              /*  if (row_index == 2 || row_index == 3) {
                    row_index = 1;
                    getSupportActionBar().setTitle("Home");
                    mAdapter.notifyDataSetChanged();
                } else {
                    if (row_index == 5) {
                        row_index = 1;
                        getSupportActionBar().setTitle("Home");
                        mAdapter.notifyDataSetChanged();
                    }
                }*/
            }

            @Override
            public void onDrawerStateChanged(int newState) {


            }
        });


        mAdapter = new DrawerAdapter(ActivityMainOwner.this, TITLES, IMAGES, "NAME", "EMAIL", 1);
        mRecyclerView.setAdapter(mAdapter);


        fab.setRippleColor(getResources().getColor(R.color.floatingButtonColor));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (getLiveFragment() != null)
                getLiveFragment().onFloatingButtonClick();*/
                Log.w(TAG, "You clicked from" + viewPager.getCurrentItem());
                Intent intent;
                switch (viewPager.getCurrentItem()) {

                    case 0:
                        intent  = new Intent(ActivityMainOwner.this, ActivityAddClient.class);
                        intent.putExtra("from","Client");
                        startActivity(intent);
                        break;
                    case 1:
                        intent  = new Intent(ActivityMainOwner.this, ActivityCreateProject.class);
                        startActivity(intent);

                        break;
                    case 2:
                        intent  = new Intent(ActivityMainOwner.this, ActivityAddClient.class);
                        intent.putExtra("from","Team");
                        startActivity(intent);
                        break;
                }

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentDashboardClient(), "CLIENTS");
        adapter.addFrag(new FragmentDashboardProject(), "PROJECTS");
        adapter.addFrag(new FragmentDashboardTeam(), "TEAM");
        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
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


    public void onNavigationItemClick(int pos) {


        //region switchcase
        switch (pos) {
            case 1:
                enableViews(false);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
             /*   HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);
                replaceFragment(homeFragment, HOME_FRAGMENT, true);*/

                break;

            case 2:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                    expanded_item = 0;
                }
                break;
            case 3:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case 4:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;
            case 5:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                break;

        }
// endregion
    }

    private void enableViews(boolean enable) {
        System.out.println("VIEEWWWWWS" + enable);
        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Remove hamburger
            toggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        getSupportActionBar().setTitle("Home");
                        row_index = 1;
                        mAdapter.notifyDataSetChanged();
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }

    public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
        private Context context;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        private static final int TYPE_EXP_ITEM = 2;
        private String mNavTitles[];
        private int mNavImages[];


        public class ViewHolder extends RecyclerView.ViewHolder {
            int Holderid;

            TextView rowText, tvName, txt_main;
            public LinearLayout nav_item, nav_item1, main_l, ll_2, l1, l2, l3;
            ImageView img, ivGallery;
            //  CircularNetworkImageView ivProfileImage;

            public ViewHolder(View itemView, int ViewType) {
                super(itemView);

                if (ViewType == TYPE_ITEM) {
                    rowText = (TextView) itemView.findViewById(R.id.rowText);
                    nav_item = (LinearLayout) itemView.findViewById(R.id.nav_item);
                    img = itemView.findViewById(R.id.img);
                    Holderid = 1;
                } else if (ViewType == TYPE_EXP_ITEM) {
                    nav_item1 = itemView.findViewById(R.id.nav_item1);
                    txt_main = (TextView) itemView.findViewById(R.id.txt_main);
                    main_l = itemView.findViewById(R.id.main_l);
                    ll_2 = itemView.findViewById(R.id.ll_2);
                    l1 = itemView.findViewById(R.id.l1);
                    l2 = itemView.findViewById(R.id.l2);
                    l3 = itemView.findViewById(R.id.l3);
                    Holderid = 2;
                } else {

                    tvName = (TextView) itemView.findViewById(R.id.tvName);

                    Holderid = 0;
                }
            }


        }


        public DrawerAdapter(Context context, String Titles[], int Images[], String Name, String Email, int default_select) {
            this.context = context;
            mNavTitles = Titles;
            mNavImages = Images;
            row_index = default_select;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == TYPE_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item_row, parent, false);

                ViewHolder vhItem = new ViewHolder(v, viewType);

                return vhItem;

            } else if (viewType == TYPE_EXP_ITEM) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_iexp_item_row, parent, false);

                ViewHolder vhExpItem = new ViewHolder(v, viewType);

                return vhExpItem;

            } else if (viewType == TYPE_HEADER) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header_home, parent, false);

                ViewHolder vhHeader = new ViewHolder(v, viewType);
                return vhHeader;


            }
            return null;

        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            if (holder.Holderid == 1) {
                holder.img.setImageResource(mNavImages[position - 1]);
                holder.rowText.setText(mNavTitles[position - 1]);
                holder.nav_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        row_index = position;
                        notifyDataSetChanged();
                        onNavigationItemClick(position);

                    }
                });

                if (row_index == position) {
                    holder.nav_item.setBackgroundColor(Color.TRANSPARENT);
                    holder.rowText.setTextColor(Color.parseColor("#5a819e"));
                } else {
                    holder.nav_item.setBackgroundColor(Color.TRANSPARENT);
                    holder.rowText.setTextColor(Color.WHITE);
                }
            } else if (holder.Holderid == 2) {
                holder.txt_main.setText(mNavTitles[position - 1]);

                Log.w(TAG, "expanded_item is " + expanded_item);
                holder.main_l.setVisibility(View.GONE);

                holder.nav_item1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        row_index = position;
                        if (expanded_item == 0) {
                            holder.main_l.setVisibility(View.VISIBLE);
                            expanded_item = 1;

                            holder.l1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    notifyDataSetChanged();
                                    if (row_index == position) {
                                        // holder.l1.setBackgroundColor(Color.parseColor("#894b87"));
                                        holder.l2.setBackgroundColor(Color.TRANSPARENT);
                                    } else {
                                        holder.l1.setBackgroundColor(Color.TRANSPARENT);
                                    }

                                    onNavigationItemClick(position);
                                }
                            });


                            holder.l2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    notifyDataSetChanged();
                                    if (row_index == position) {
                                        holder.l2.setBackgroundColor(Color.TRANSPARENT);
                                    } else {
                                        holder.l2.setBackgroundColor(Color.TRANSPARENT);
                                    }

                                    onNavigationItemClick(position);
                                }
                            });


                            holder.l3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    notifyDataSetChanged();
                                    if (row_index == position) {
                                        holder.l3.setBackgroundColor(Color.TRANSPARENT);
                                    } else {
                                        holder.l3.setBackgroundColor(Color.TRANSPARENT);
                                    }

                                    onNavigationItemClick(position);
                                }
                            });

                        } else if (expanded_item == 1) {
                            holder.main_l.setVisibility(View.GONE);
                            expanded_item = 0;
                            notifyDataSetChanged();
                        }

                        //  onNavigationItemClick(position);
                    }
                });


            }
        }

        @Override
        public int getItemCount() {
            return mNavTitles.length + 1; // the number of items in the list will be +1 the titles including the header view.
        }


        @Override
        public int getItemViewType(int position) {
            /*if (isPositionHeader(position))
                return TYPE_HEADER;
            else if (position == 2) {
                return TYPE_EXP_ITEM;
            } else return TYPE_ITEM;*/
            switch (position) {
                case 0:
                    return TYPE_HEADER;
                case 2:
                    return TYPE_EXP_ITEM;
            }
            return 1;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

    }


}

//-----------------------commented by sana-------------------------------------------
//---------------------------------------------------------

/* private void setupTabIcons() {

        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabone_text = (TextView)tabOne.findViewById(R.id.tab_text) ;
        tabone_text.setText("CLIENTS");
        tabLayout.getTabAt(0).setCustomView(tabOne);

     //   TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        LinearLayout tabTwo = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabtwo_text = (TextView)tabTwo.findViewById(R.id.tab_text) ;
        tabtwo_text.setText("PROJECT OWNER");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tabThree_text = (TextView)tabThree.findViewById(R.id.tab_text) ;
        tabThree_text.setText("TEAM");
      //  tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.user, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }*/