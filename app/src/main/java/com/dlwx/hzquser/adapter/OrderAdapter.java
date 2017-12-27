package com.dlwx.hzquser.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 我的订单
 */

public class OrderAdapter extends FragmentPagerAdapter {
    List<String> tab_titles;
    List<Fragment> fragments;
    public OrderAdapter(FragmentManager fm,List<String> tab_titles,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.tab_titles = tab_titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tab_titles.get(position);
    }
}
