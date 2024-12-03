package com.imagemanager.controller;

import com.imagemanager.model.ImageFile;
import com.imagemanager.service.ImageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageController {
    private ImageService imageService;
    private ObservableList<ImageFile> loadedImages;

    public ImageController() {
        this.imageService = new ImageService();
        this.loadedImages = FXCollections.observableArrayList();
    }

    public ObservableList<ImageFile> getLoadedImages() {
        return loadedImages;
    }

    public void loadImage(File file) throws IOException {
        ImageFile imageFile = imageService.loadImage(file);
        loadedImages.add(imageFile);
    }

    public void loadImages(List<File> files) {
        for (File file : files) {
            try {
                loadImage(file);
            } catch (IOException e) {
                // Handle error for individual file
                System.err.println("Error loading image: " + file.getName());
                e.printStackTrace();
            }
        }
    }
}
