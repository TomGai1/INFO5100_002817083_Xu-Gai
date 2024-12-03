package com.imagemanager.controller;

import com.imagemanager.model.ConversionTask;
import com.imagemanager.model.ImageFile;
import com.imagemanager.service.ConversionService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class ConversionController {
    private ConversionService conversionService;

    public ConversionController() {
        this.conversionService = new ConversionService();
    }

    public File convertImage(ImageFile source, String targetFormat) throws IOException {
        ConversionTask task = new ConversionTask(source, targetFormat);
        return conversionService.convertImage(task);
    }

    public List<File> convertImages(List<ImageFile> sources, String targetFormat) {
        List<File> convertedFiles = new ArrayList<>();
        for (ImageFile source : sources) {
            try {
                File converted = convertImage(source, targetFormat);
                convertedFiles.add(converted);
            } catch (IOException e) {
                // Handle error for individual conversion
                System.err.println("Error converting image: " + source.getFile().getName());
                e.printStackTrace();
            }
        }
        return convertedFiles;
    }
}
