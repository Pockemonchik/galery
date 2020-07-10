package com.example.galery.ui.Photo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PrepareImage {
    public static Bitmap prepareImageForClassification(Bitmap bitmap) {




       Paint paint = new Paint();

        Bitmap bmpGrayscale = Bitmap.createScaledBitmap(
                bitmap,
                NeyroModelConfig.INPUT_IMG_SIZE_WIDTH,
                NeyroModelConfig.INPUT_IMG_SIZE_HEIGHT,
                false);
        Canvas canvas = new Canvas(bmpGrayscale);
        canvas.drawBitmap(bmpGrayscale, 0, 0, paint);
        return bmpGrayscale;
    }
}
