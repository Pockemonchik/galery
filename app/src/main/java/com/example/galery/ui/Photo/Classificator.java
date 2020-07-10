package com.example.galery.ui.Photo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.galery.ui.home.Picture;


import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import static com.example.galery.ui.Photo.NeyroModelConfig.CLASSIFICATION_THRESHOLD;
import static com.example.galery.ui.Photo.NeyroModelConfig.MAX_CLASSIFICATION_RESULTS;

public class Classificator {

    private final Interpreter interpreter;

    private Classificator(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public static Classificator classifier(AssetManager assetManager, String modelPath) throws IOException {
        Log.e("filename",modelPath);
        ByteBuffer byteBuffer = loadModelFile(assetManager, modelPath);
        Interpreter interpreter = new Interpreter(byteBuffer);
        return new Classificator(interpreter);
    }

    private static ByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public List<Classification> recognizeImage(Bitmap bitmap) {
        ByteBuffer byteBuffer = convertBitmapToByteBuffer(bitmap);
        float[][] result = new float[1][NeyroModelConfig.OUTPUT_LABELS.size()];
        interpreter.run(byteBuffer, result);
        Log.e("res",result[0].toString());
        return getSortedResult(result);
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(NeyroModelConfig.MODEL_INPUT_SIZE);
        byteBuffer.order(ByteOrder.nativeOrder());
        byteBuffer.rewind();

        int[] pixels = new int[NeyroModelConfig.INPUT_IMG_SIZE_WIDTH * NeyroModelConfig.INPUT_IMG_SIZE_HEIGHT];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
//        for (int pixel : pixels) {
//            float rChannel = (pixel >> 16) & 0xFF;
//            byteBuffer.putFloat(rChannel);
//            float gChannel = (pixel >> 8) & 0xFF;
//            byteBuffer.putFloat(gChannel);
//            float bChannel = (pixel) & 0xFF;
//            byteBuffer.putFloat(bChannel);
//
//
//        }
        int pixel = 0;
        for (int i = 0; i < NeyroModelConfig.INPUT_IMG_SIZE_HEIGHT; ++i) {
            for (int j = 0; j < NeyroModelConfig.INPUT_IMG_SIZE_HEIGHT; ++j) {
                final int val = pixels[pixel++];


                byteBuffer.putFloat( ((val >> 16) & 0xFF)* (1.f/255.f));
                byteBuffer.putFloat( ((val >> 8) & 0xFF)* (1.f/255.f));
                byteBuffer.putFloat( (val & 0xFF)* (1.f/255.f));
            }
        }

        return byteBuffer;
    }

    private List<Classification> getSortedResult(float[][] resultsArray) {
        PriorityQueue<Classification> sortedResults = new PriorityQueue<>(
                MAX_CLASSIFICATION_RESULTS,
                (lhs, rhs) -> Float.compare(rhs.confidence, lhs.confidence)
        );

        for (int i = 0; i < NeyroModelConfig.OUTPUT_LABELS.size(); ++i) {
            float confidence = resultsArray[0][i];
            Log.e("arritem", Float.toString(confidence));
            if (confidence > CLASSIFICATION_THRESHOLD) {
                NeyroModelConfig.OUTPUT_LABELS.size();
                sortedResults.add(new Classification(NeyroModelConfig.OUTPUT_LABELS.get(i), confidence));
            }
        }

        return new ArrayList<>(sortedResults);
    }
}
