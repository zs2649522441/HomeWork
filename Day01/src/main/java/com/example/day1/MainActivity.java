package com.example.day1;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import adapter.ViewAdapter;
import fragment.Dongtaifragment;
import fragment.Lianxirenfragment;
import fragment.Xiaoxifragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpager;
    private TabLayout mTab;
    private NavigationView mNv;
    private DrawerLayout mDraw;
    private LinearLayout mLin;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTab = (TabLayout) findViewById(R.id.tab);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDraw = (DrawerLayout) findViewById(R.id.draw);
        mLin = (LinearLayout) findViewById(R.id.lin);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDraw,mToolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.syncState();

        mDraw.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = mNv.getRight();
                mLin.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Xiaoxifragment());
        fragments.add(new Lianxirenfragment());
        fragments.add(new Dongtaifragment());

        ViewAdapter adapter = new ViewAdapter(getSupportFragmentManager(), fragments);
        mViewpager.setAdapter(adapter);

        mTab.setupWithViewPager(mViewpager);

        mTab.getTabAt(0).setText("消息");
        mTab.getTabAt(1).setText("联系人");
        mTab.getTabAt(2).setText("动态");

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==0){
                    mToolbar.setTitle("消息");
                }else {

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
