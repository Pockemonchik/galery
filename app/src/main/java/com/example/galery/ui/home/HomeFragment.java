package com.example.galery.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galery.R;
import com.example.galery.ui.Photo.ArticleFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
//    private View view;
//    List<Picture>pictures=new ArrayList<>();
    List<PictureDb>pictures=new ArrayList<>();
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setInitialData();



        return root;
    }
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.list);
        Log.e("t","1");
        // создаем адаптер

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        PictureAdapter adapter = new PictureAdapter(getContext(), pictures);


        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

    }


    private void setInitialData(){

         pictures=PictureDb.listAll(PictureDb.class);
         try {
             for(PictureDb pic :pictures){
                 Log.e("all data",pic.getName());

             }
             pictures.get(0);
//             PictureDb.deleteAll(PictureDb.class);
         }
         catch (Exception e){
//             List<Point> points=new ArrayList<>();
//             for (int i =0;i <5;i++)
//             {
//
//                 points.add(new Point(0,0,"opisanie"));
//                 points.get(i).save();
////             }
             PictureDb pictureDb=new PictureDb ("ib", "", R.drawable.ib);
             pictureDb.save();
             pictures.add(pictureDb);
             PictureDb pictureDb2=new PictureDb ("krim", "", R.drawable.krim);
             pictureDb2.save();
             pictures.add(pictureDb2);
             PictureDb pictureDb3=new PictureDb ("oper", "", R.drawable.oper);
             pictureDb3.save();
             pictures.add(pictureDb3);
             PictureDb pictureDb4=new PictureDb ("psih", "", R.drawable.psih);
             pictureDb4.save();
             pictures.add(pictureDb4);
             PictureDb pictureDb5=new PictureDb ("isopr", "", R.drawable.isopr);
             pictureDb5.save();
             pictures.add(pictureDb5);



             PictureDb pictureDb6=new PictureDb ("oop", " ", R.drawable.oop1);
             pictureDb.save();
             pictures.add(pictureDb6);
             PictureDb pictureDb7=new PictureDb ("oop", "", R.drawable.oop2);
             pictureDb7.save();
             pictures.add(pictureDb7);
             PictureDb pictureDb8=new PictureDb ("oop", "", R.drawable.oop3);
             pictureDb8.save();
             pictures.add(pictureDb8);
             PictureDb pictureDb9=new PictureDb ("oop", "", R.drawable.oop4);
             pictureDb9.save();
             pictures.add(pictureDb9);
             PictureDb pictureDb10=new PictureDb ("oop", "", R.drawable.oop5);
             pictureDb10.save();
             pictures.add(pictureDb10);

             PictureDb pictureDb11=new PictureDb ("oop", "", R.drawable.gai);
             pictureDb11.save();
             pictures.add(pictureDb11);
             PictureDb pictureDb12=new PictureDb ("oop", "", R.drawable.gai1);
             pictureDb12.save();
             pictures.add(pictureDb12);
             PictureDb pictureDb13=new PictureDb ("oop", "", R.drawable.oop);
             pictureDb13.save();
             pictures.add(pictureDb13);
             PictureDb pictureDb14=new PictureDb ("oop", "", R.drawable.oper2);
             pictureDb14.save();
             pictures.add(pictureDb14);
             PictureDb pictureDb15=new PictureDb ("krim", "", R.drawable.krim1);
             pictureDb15.save();
             pictures.add(pictureDb15);

             PictureDb pictureDb16=new PictureDb ("krim", "", R.drawable.krim2);
             pictureDb16.save();
             pictures.add(pictureDb16);

             PictureDb pictureDb18=new PictureDb ("oper", "", R.drawable.oper1);
             pictureDb18.save();
             pictures.add(pictureDb18);
             PictureDb pictureDb19=new PictureDb ("mpf", "", R.drawable.mpf);
             pictureDb19.save();
             pictures.add(pictureDb19);

         }

    }

    }

