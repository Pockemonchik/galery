package com.example.galery.ui.Photo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.galery.R;
import com.example.galery.ui.home.Picture;
import com.example.galery.ui.home.PictureDb;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ArticleFragment extends Fragment {
    private ImageView imageDisplay;
    private TextView textBody;
    private VideoView videoPlayer;
    private View root;
    int videoPosition = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            videoPosition = savedInstanceState.getInt("Position");
        }

        View root = inflater.inflate(R.layout.fragment_article, container, false);
        BottomNavigationView navView = getActivity().findViewById(R.id.nav_view);
        navView.setVisibility(View.GONE);
//        Menu nav_Menu = navView.getMenu();
//        nav_Menu.findItem(R.id.navigation_home).setVisible(View.GONE);
//        nav_Menu.findItem(R.id.navigation_dashboard).setVisible(false);
        try {
            Bundle bundle=this.getArguments();

            String namePic=bundle.getString("name");

            List<PictureDb> pictureDblist=PictureDb.find(PictureDb.class,"name = ?",namePic);

//            imageDisplay= root.findViewById(R.id.imageDisplay);
//            textBody=root.findViewById(R.id.textBody);
            if(namePic!="") {
                videoPlayer=root.findViewById(R.id.videoPlayer);
                videoPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.e("video", "setOnErrorListener "); return true;
                    }
                });
                Uri videoUri= Uri.parse("http://78.24.220.66:9000/static/" + namePic.replaceAll("\\d", "")+".mp4");

                videoPlayer.setVideoURI(videoUri);
                MediaController mediaController = new MediaController(getContext());
                videoPlayer.setMediaController(mediaController);
                mediaController.setMediaPlayer(videoPlayer);
                videoPlayer.seekTo(videoPosition);

                videoPlayer.start();
            }
            else  {
                videoPlayer=root.findViewById(R.id.videoPlayer);
                ((ViewGroup)videoPlayer.getParent()).removeView(videoPlayer);
            }
//

            imageDisplay.setImageResource(pictureDblist.get(0).getImage());

            textBody.setText(pictureDblist.get(0).getDisc());



        }
        catch (Exception e){

//            imageDisplay= root.findViewById(R.id.imageDisplay);
//            textBody=root.findViewById(R.id.textBody);

        }

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        videoPosition = videoPlayer.getCurrentPosition();
        state.putInt("Position", videoPosition);
        Log.e("My_Log", "view destroy" + videoPosition);
        Log.e("My_Log", "onSaveInstanceState = " + videoPosition);

    }





}

