package com.example.galery.ui.Photo;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.galery.R;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private String[] headerTitles;
    private String name;
    private ArticleFragment articleFragment;

    ViewPageAdapter(FragmentManager fm, Context context,String name) {
        super(fm);
        Resources resources=context.getResources();
        headerTitles=resources.getStringArray(R.array.headtitles);
        this.name=name;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        PhotoFragment photoFragment=new PhotoFragment();

        articleFragment=new ArticleFragment();
        Bundle arg=new Bundle();
        arg.putString("name",name);
        articleFragment.setArguments(arg);
        FactFragment factFragment=new FactFragment();
        factFragment.setArguments(arg);
        switch(position) {

            case 0:
                return photoFragment;
            case 1:
                return articleFragment;

            default: return photoFragment;

        }

    }
    public void setName(String name){
        this.name=name;
    }
    @Override
    public int getCount() {

        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return headerTitles[position];
    }
    @Override
    public int getItemPosition(Object object){
        Bundle arg=new Bundle();
        arg.putString("name",name);
        Log.e("refreshad",name);
        articleFragment.setArguments(arg);
        return PagerAdapter.POSITION_NONE;
    }
}