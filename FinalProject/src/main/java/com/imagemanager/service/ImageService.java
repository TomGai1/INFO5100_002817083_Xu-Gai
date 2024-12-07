package com.imagemanager.service;

import com.imagemanager.model.ImageFile;
import com.imagemanager.model.ImageProperties;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageService {

    public ImageFile loadImage(File file) throws IOException {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage == null) {
                throw new IOException("Unable to read image file: " + file.getName());
            }

            ImageFile imageFile = new ImageFile(file);
            ImageProperties properties = extractProperties(bufferedImage, file);
            imageFile.setProperties(properties);

            // Create thumbnail
            String thumbnailPath = createThumbnail(file);
            imageFile.setThumbnailPath(thumbnailPath);

            return imageFile;
        } catch (IOException e) {
            throw new IOException("Error loading image: " + e.getMessage(), e);
        }
    }

    private ImageProperties extractProperties(BufferedImage image, File file) {
        String format = getFileExtension(file);
        return new ImageProperties(
                image.getWidth(),
                image.getHeight(),
                format
        );
    }

    private String createThumbnail(File file) throws IOException {
        BufferedImage originalImage = ImageIO.read(file);
        BufferedImage thumbnail = createThumbnailImage(originalImage, 100, 100);

        String thumbnailPath = file.getParent() + "/thumb_" + file.getName();
        File thumbnailFile = new File(thumbnailPath);
        ImageIO.write(thumbnail, getFileExtension(file), thumbnailFile);

        return thumbnailPath;
    }

    private BufferedImage createThumbnailImage(BufferedImage original, int width, int height) {
        java.awt.Image scaled = original.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        thumbnail.getGraphics().drawImage(scaled, 0, 0, null);
        return thumbnail;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }
}

