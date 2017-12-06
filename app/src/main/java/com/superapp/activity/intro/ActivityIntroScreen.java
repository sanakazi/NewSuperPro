package com.superapp.activity.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.superapp.R;
import com.superapp.activity.ActivityLogin;
import com.superapp.activity.registration.ActivityRegistration;
import com.superapp.custom.AutoScrollViewPager;
import com.superapp.custom.CirclePageIndicator;


public class ActivityIntroScreen extends AppCompatActivity {
    Button register;
    TextView login;
    private AutoScrollViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;
    private ScreenSlidePagerAdapter mPagerAdapter;
    public static final String TAG = ActivityIntroScreen.class.getSimpleName();
    String alreadyRegisterString;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_screen);
        intialize();
        setUpAsAction();

    }

    private void intialize()
    {
        register=findViewById(R.id.register);
        viewPager = findViewById(R.id.view_pager);
        circlePageIndicator =  findViewById(R.id.indicator);
        login=findViewById(R.id.login);
        handler=new Handler();
    }
    private void setUpAsAction()
    {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        nextActivityNotFullScreen();
                       startActivity(new Intent(ActivityIntroScreen.this,ActivityRegistration.class));
                    }
                });
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        nextActivityNotFullScreen();
                        startActivity(new Intent(ActivityIntroScreen.this,ActivityLogin.class));
                    }
                });
            }
        });

        mPagerAdapter = new ScreenSlidePagerAdapter(ActivityIntroScreen.this);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

            @Override
            public void transformPage(View page, float position) {
                // TODO Auto-generated method stub
            }

        });
        circlePageIndicator.setViewPager(viewPager);
        viewPager.setInterval(5000);
        viewPager.startAutoScroll();
    }
    private void nextActivityNotFullScreen()
    {
        ActivityIntroScreen.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        viewPager.stopAutoScroll();
    }
    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        viewPager.startAutoScroll();

    }

}
