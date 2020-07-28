package com.example.togethersujung2020.ui.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
private ArrayList<Fragment> items;
private ArrayList<String> itext = new ArrayList<String>();
    public VPAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new profile_comment());
        items.add(new profile_content());
        items.add(new profile_scrap());

        itext.add("작성한 글");
        itext.add("댓글 단 글");
        itext.add("스크랩 한 글");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
