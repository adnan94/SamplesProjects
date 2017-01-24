package com.example.adnan.demotablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by adnan on 1/24/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    String i[];

    public Pager(FragmentManager fm, String[] array) {
        super(fm);
        this.i = array;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new BlankFragment();
        } else if (position == 1) {
            fragment = new BlankFragmnet2();
        } else if (position == 2) {
            fragment = new BlankFragment();

        }
        return fragment;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = i[position];
                break;
            case 1:
                title = i[position];
                break;
            case 2:
                title = i[position];
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return i.length;
    }
}
