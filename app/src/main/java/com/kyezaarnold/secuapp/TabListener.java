package com.kyezaarnold.secuapp;

/**
 * Created by Kyeza Arnold on 7/15/2015.
 */

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;

public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    public TabListener(Activity activity, String tag, Class<T> clz){
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Check whether the fragment is instantiated if not instantiate it
//        Otherwise just attach it to the activity
        if (mFragment == null){
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(mFragment, mTag);
        }else{
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Check whether the fragment is still instantiated, if so detach it
        if(mFragment != null){
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//        Usually do nothing, it should return on saved state
    }
}
