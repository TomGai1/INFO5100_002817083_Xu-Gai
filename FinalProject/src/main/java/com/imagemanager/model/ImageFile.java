package com.imagemanager.model;

import java.io.File;
import java.io.Serializable;

public class ImageFile implements Serializable {
    private File file;
    private ImageProperties properties;
    private String thumbnailPath;

    public ImageFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setProperties(ImageProperties properties) {
        this.properties = properties;
    }

    public ImageProperties getProperties() {
        return properties;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
