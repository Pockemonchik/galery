package com.example.galery.ui.Photo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.galery.R;

public class ViewPageFragment extends Fragment {


    Context context;
    private String name="";
    private ViewPager pager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_viewpage, container, false);
        pager = (ViewPager)root.findViewById(R.id.viewpager);

        pager.setAdapter(new ViewPageAdapter(getParentFragmentManager(),getContext(),this.name));
        return root;
    }
    public void setNameRefresh(String name){
        this.name=name;
        pager.setAdapter(new ViewPageAdapter(getParentFragmentManager(),getContext(),this.name));
        pager.getAdapter().notifyDataSetChanged();
    }
}
