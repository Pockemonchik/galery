package com.example.galery.ui.Photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.galery.R;
import com.example.galery.ui.home.PictureDb;

import java.util.List;


public class FactFragment extends Fragment {
        private ImageView imageDisplay;
        private TextView textBody;
        private View root;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_fact, container, false);
            try {
                Bundle bundle=this.getArguments();
                String namePic=bundle.getString("name");
                List<PictureDb> pictureDblist=PictureDb.find(PictureDb.class,"name = ?",namePic);

                imageDisplay= root.findViewById(R.id.imageDisplayFact);
                textBody=root.findViewById(R.id.textBodyFact);
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), pictureDblist.get(0).getImage());
                bmp = bmp.copy(bmp.getConfig(), true);
                Paint paint = new Paint();
                paint.setStrokeWidth(10);
//paint.setAntiAlias(true);
                paint.setColor(Color.BLUE);

                Canvas canvas = new Canvas(bmp);
                canvas.drawCircle(50, 50, 20, paint);
                imageDisplay.setImageBitmap(bmp);
//                imageDisplay.setImageResource(pictureDblist.get(0).getImage());


                imageDisplay.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        float[] pts = {
                                event.getX(), event.getY()
                        };

                        Log.e("x",":"+Math.floor(pts[0]));
                        Log.e("y",":"+Math.floor(pts[1]));
                        return false;

                    }

                });


            }
            catch (Exception e){

                imageDisplay= root.findViewById(R.id.imageDisplayFact);
                textBody=root.findViewById(R.id.textBodyFact);
                imageDisplay.setImageResource(R.drawable.ic_launcher_background);

                textBody.setText("facts with circle");

                }



            return root;
        }





    }

