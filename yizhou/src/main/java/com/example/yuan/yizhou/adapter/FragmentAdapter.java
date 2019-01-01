package com.example.yuan.yizhou.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuan.yizhou.fragment.One;
import com.example.yuan.yizhou.fragment.Three;
import com.example.yuan.yizhou.fragment.Two;

public class FragmentAdapter extends FragmentPagerAdapter {
    private String[] str=new String[]{"详情","评论","我的"};
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new One();
            case 1:
                return new Two();
            case 2:
                return new Three();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }

    @Override
    public int getCount() {
        return str.length;
    }
}
