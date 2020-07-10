package com.example.galery.ui.Photo;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.galery.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PhotoFragment extends Fragment implements SurfaceHolder.Callback,View.OnClickListener{

    private PhotoViewModel photoViewModel;
    private SurfaceView sv;
    private SurfaceHolder holder;
    private Button btnCaptureImage;
    private ImageView imageDisplay;
    private  Camera camera;
    private  Camera.PictureCallback pictureCallback;
    private ImageView resImg;
    private static final int CAMERA_REQUEST_CODE = 100;

    final int CAMERA_ID = 0;
    final boolean FULL_SCREEN = true;
    private Classificator classificator;
    private String name;
    private ArticleFragment articleFragment;


    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                ViewModelProviders.of(this).get(PhotoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_photo, container, false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        }

        sv=root.findViewById(R.id.surfaceView);
        holder=sv.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        loadMnistClassifier();

        btnCaptureImage =(Button) root.findViewById(R.id.btn_capture);


        pictureCallback=new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                Log.e("log","takepiccalbach");
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                Bitmap cbmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),null,true);
                try (FileOutputStream out = new FileOutputStream(new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + "/Filename.xml")
                        .getPath())) {
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
                onImageCaptured(bytes);

            }
        };
        btnCaptureImage.setOnClickListener(this);
        return root;
    }

    private void loadMnistClassifier() {
        try {
           classificator = Classificator.classifier(getActivity().getAssets(), NeyroModelConfig.MODEL_FILENAME);
        } catch (IOException e) {
            Toast.makeText(getContext(), "MNIST model couldn't be loaded. Check logs for details.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("LOG", ""+requestCode+ " "+resultCode);
//        if (requestCode == 1888 && resultCode == Activity.RESULT_OK) {
//////
//////        }
        Bitmap photo = (Bitmap) data.getExtras().get("data");
          imageDisplay.setImageBitmap(photo);


        super.onActivityResult(requestCode, resultCode, data);
    }



    private void onImageCaptured(byte[] picture) {
        Log.e("res","startimg capt");
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
//        resImg.setImageResource(R.drawable.tst2);
//        Bitmap bitmap_orig = ((BitmapDrawable)resImg.getDrawable()).getBitmap();

        Bitmap preprocessedImage = PrepareImage.prepareImageForClassification(bitmap);
        Log.e("res","bitmap reaaaaady");

        List<Classification> recognitions = classificator.recognizeImage(preprocessedImage);
        Log.e("reslast",recognitions.get(0).toString());
        articleFragment=new ArticleFragment();
        Bundle arg=new Bundle();
        name=recognitions.get(0).toString();
        arg.putString("name",name);
        Log.e("regoggggggggg ---------",name);
        articleFragment.setArguments(arg);


        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction
                .add(R.id.article_frame, articleFragment);//R.id.content_frame is the layout you want to replace
        fragmentTransaction.addToBackStack(null);
        Log.e("ft","ft");
        btnCaptureImage.setVisibility(View.GONE);
        fragmentTransaction.commit();
        progressDialog.hide();



    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera =Camera.open();

        }
        catch (Exception e){

        }
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        float aspect = (float) previewSize.width / previewSize.height;

        int previewSurfaceWidth = sv.getWidth();
        int previewSurfaceHeight = sv.getHeight();

        ViewGroup.LayoutParams lp = sv.getLayoutParams();

        Camera.Parameters parameters;
        parameters=camera.getParameters();



        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
        {
            // портретный вид
            camera.setDisplayOrientation(90);
            lp.height = previewSurfaceHeight;
            lp.width = (int) (previewSurfaceHeight / aspect);
            ;
        }
        else
        {
            // ландшафтный
            camera.setDisplayOrientation(0);
            lp.width = previewSurfaceWidth;
            lp.height = (int) (previewSurfaceWidth / aspect);
        }

        sv.setLayoutParams(lp);
        camera.setParameters(parameters);
        camera.startPreview();


        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }



    @Override
    public void onClick(View v) {

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Загрузка....");
            progressDialog.show();
            camera.takePicture(null, null,null , pictureCallback);

        }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        float aspect = (float) previewSize.width / previewSize.height;

        int previewSurfaceWidth = sv.getWidth();
        int previewSurfaceHeight = sv.getHeight();

        ViewGroup.LayoutParams lp = sv.getLayoutParams();

        Camera.Parameters parameters;
        parameters=camera.getParameters();



        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE)
        {
            // портретный вид
            camera.setDisplayOrientation(90);
            lp.height = previewSurfaceHeight;
            lp.width = (int) (previewSurfaceHeight / aspect);
            ;
        }
        else
        {
            // ландшафтный
            camera.setDisplayOrientation(0);
            lp.width = previewSurfaceWidth;
            lp.height = (int) (previewSurfaceWidth / aspect);
        }

        sv.setLayoutParams(lp);
        camera.setParameters(parameters);
        camera.startPreview();
    }

}







