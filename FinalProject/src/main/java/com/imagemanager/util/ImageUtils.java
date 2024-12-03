package com.imagemanager.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImageUtils {
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp"
    );

    public static boolean isSupported(File file) {
        String extension = getFileExtension(file);
        return SUPPORTED_FORMATS.contains(extension.toLowerCase());
    }

    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1).toLowerCase();
    }

    public static String generateUniqueFileName(String originalName, String newExtension) {
        String baseName = originalName.substring(0, originalName.lastIndexOf('.'));
        return String.format("%s_%d.%s", baseName, System.currentTimeMillis(), newExtension);
    }
}
