package com.imagemanager.model;

public class ImageProperties {
    private int width;
    private int height;
    private String format;
    private String cameraModel;
    private String location;

    public ImageProperties(int width, int height, String format) {
        this.width = width;
        this.height = height;
        this.format = format;
    }

    // Getters and setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFormat() {
        return format;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
