package com.example.galery.ui.Photo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NeyroModelConfig {
    public static String MODEL_FILENAME = "MNv2.tflite";

    public static final int INPUT_IMG_SIZE_WIDTH = 224;
    public static final int INPUT_IMG_SIZE_HEIGHT = 224;
    public static final int FLOAT_TYPE_SIZE = 4;
    public static final int PIXEL_SIZE = 3;
    public static final int MODEL_INPUT_SIZE = FLOAT_TYPE_SIZE * INPUT_IMG_SIZE_WIDTH * INPUT_IMG_SIZE_HEIGHT * PIXEL_SIZE;

    public static final List<String> OUTPUT_LABELS = Collections.unmodifiableList(
            Arrays.asList("gai", "gai1","ib", "krim","krim1", "krim2","mpf", "oop","oop1", "oop2","oop3", "oop4","oop5", "oper","oper1", "oper2","parad", "psih","isopr", "unik"));

    public static final int MAX_CLASSIFICATION_RESULTS = 3;
    public static final float CLASSIFICATION_THRESHOLD = 0.1f;
}
