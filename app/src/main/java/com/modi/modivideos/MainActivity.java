package com.modi.modivideos;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.modi.modivideos.adapters.FragmentAdapter;
import com.modi.modivideos.callback.BaseInterface;
import com.modi.modivideos.fragments.LatestVideoFragment;
import com.modi.modivideos.fragments.ManKiBaatFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseInterface {

    private ViewPager pager;
    private FragmentAdapter fragment_adapter;

    private ActionBar actionBar;
    private TabLayout tabLayout;

    private List<Fragment> fragment_list;
    private List<String> fragment_title_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initValues();
        initUI();
        initUIActions();
    }

    public void initValues() {
        fragment_list = new ArrayList<>();
        fragment_title_list = new ArrayList<>();
        fragment_list.add(new LatestVideoFragment());
        fragment_list.add(new ManKiBaatFragment());
        fragment_title_list.add("Videos");
        fragment_title_list.add("Mann Ki Baat");
        actionBar = getSupportActionBar();
    }

    public void initUI() {
        pager = (ViewPager)findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.tabs);
    }

    public void initUIActions() {
        fragment_adapter = new FragmentAdapter(getSupportFragmentManager() , fragment_list , fragment_title_list);
        pager.setAdapter(fragment_adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onEveryThingReady() {

    }
}
