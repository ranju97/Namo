package com.modi.modivideos.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Avilash on 30-09-2017.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragment_list;
    private List<String> fragment_title_list;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragment_list, List<String> fragment_title_list) {
        super(fm);
        this.fragment_list = fragment_list;
        this.fragment_title_list = fragment_title_list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragment_list.get(i);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragment_title_list.get(position);
    }
}
