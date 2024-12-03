package com.imagemanager.model;

import java.io.File;

public class ConversionTask {
    private ImageFile sourceImage;
    private String targetFormat;
    private File outputFile;

    public ConversionTask(ImageFile sourceImage, String targetFormat) {
        this.sourceImage = sourceImage;
        this.targetFormat = targetFormat;
    }

    public ImageFile getSourceImage() {
        return sourceImage;
    }

    public String getTargetFormat() {
        return targetFormat;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
}
