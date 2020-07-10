package com.example.galery.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.galery.MainActivity;
import com.example.galery.R;
import com.example.galery.ui.Photo.ArticleFragment;

import java.util.List;



import static android.content.Context.MODE_PRIVATE;

class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private LayoutInflater inflater;
//    private List<Picture> pictures;
      private List<PictureDb> pictures;

    private Context context;

    PictureAdapter(Context context, List<PictureDb> pictures) {
        this.context=context;
        this.pictures = pictures;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureAdapter.ViewHolder holder, int position) {
        final PictureDb picture = pictures.get(position);
        holder.imageView.setImageResource(picture.getImage());
//        holder.nameView.setText(picture.getName());
        holder.discView.setText(picture.getDisc());
//        holder.btn_detail_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context mContext;
//                ArticleFragment articleFragment=new ArticleFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("name",picture.getName());
//                articleFragment.setArguments(bundle);
//                FragmentManager fragmentManager =((AppCompatActivity)context).getSupportFragmentManager();
//                if (fragmentManager != null) {
//                    fragmentManager
//                            .beginTransaction()
//
//                            .add(R.id.nav_host_fragment, articleFragment)
//                            .addToBackStack(null)
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit();
//                }
//            }});
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView  discView;

//        final Button btn_detail_view;
        ViewHolder(View view){
            super(view);
            imageView = (ImageView)view.findViewById(R.id.photo);
//            nameView = (TextView) view.findViewById(R.id.name);
            discView = (TextView) view.findViewById(R.id.disc);
//            btn_detail_view= (Button)view.findViewById(R.id.btn_detail_view);
        }
    }
}