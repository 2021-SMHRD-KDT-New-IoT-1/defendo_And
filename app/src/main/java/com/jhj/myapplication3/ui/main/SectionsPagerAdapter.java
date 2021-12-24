package com.jhj.myapplication3.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jhj.myapplication3.AllWorkerFragment;
import com.jhj.myapplication3.LocationFragment;
import com.jhj.myapplication3.MachineFragment;
import com.jhj.myapplication3.R;


public class SectionsPagerAdapter extends FragmentPagerAdapter {
    MemberVO vo;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm,MemberVO vo) {
        super(fm);
        mContext = context;
        this.vo=vo;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0:
                String dept = vo.getWorker_dept();
                fragment = new AllWorkerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("dept",dept);
                fragment.setArguments(bundle);
                break;

            case 1:
                fragment = new LocationFragment();
                break;
            case 2:
                fragment = new MachineFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}