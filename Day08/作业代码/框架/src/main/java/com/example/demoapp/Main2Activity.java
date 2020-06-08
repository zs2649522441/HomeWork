package com.example.demoapp;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import fragment.KeChengFragment;
import fragment.PersonFragment;
import fragment.YueYeFragment;
import fragment.Grfragment;

public class Main2Activity extends AppCompatActivity {

    private FrameLayout mFram;
    private TabLayout mTab;
    private KeChengFragment classFragment;
    private PersonFragment personFragment;
    private YueYeFragment shouYeFragment;
    private Grfragment yueKeClassFragment;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mFram = (FrameLayout) findViewById(R.id.fram);
        mTab = (TabLayout) findViewById(R.id.tab);

        classFragment = new KeChengFragment();
        personFragment = new PersonFragment();
        shouYeFragment = new YueYeFragment();
        yueKeClassFragment = new Grfragment();
        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.fram,classFragment).add(R.id.fram,personFragment).add(R.id.fram,shouYeFragment).add(R.id.fram,yueKeClassFragment).hide(personFragment).hide(personFragment).hide(shouYeFragment).hide(yueKeClassFragment).commit();

        mTab.addTab(mTab.newTab().setText("首页"));
        mTab.addTab(mTab.newTab().setText("课程"));
        mTab.addTab(mTab.newTab().setText("约课记录"));
        mTab.addTab(mTab.newTab().setText("个人"));


        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        manager.beginTransaction().show(classFragment).hide(personFragment).hide(shouYeFragment).hide(yueKeClassFragment).commit();
                        break;
                    case 1:
                        manager.beginTransaction().show(personFragment).hide(classFragment).hide(shouYeFragment).hide(yueKeClassFragment).commit();
                        break;
                    case 2:
                        manager.beginTransaction().show(shouYeFragment).hide(personFragment).hide(classFragment).hide(yueKeClassFragment).commit();
                        break;
                    case 3:
                        manager.beginTransaction().show(yueKeClassFragment).hide(personFragment).hide(shouYeFragment).hide(classFragment).commit();
                        break;

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
