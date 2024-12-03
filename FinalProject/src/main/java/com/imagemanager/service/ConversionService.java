package com.imagemanager.service;

import com.imagemanager.model.ConversionTask;
import com.imagemanager.model.ImageFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConversionService {

    public File convertImage(ConversionTask task) throws IOException {
        try {
            BufferedImage originalImage = ImageIO.read(task.getSourceImage().getFile());

            String outputPath = generateOutputPath(
                    task.getSourceImage().getFile(),
                    task.getTargetFormat()
            );
            File outputFile = new File(outputPath);

            boolean success = ImageIO.write(originalImage, task.getTargetFormat(), outputFile);
            if (!success) {
                throw new IOException("Failed to convert image to " + task.getTargetFormat());
            }

            task.setOutputFile(outputFile);
            return outputFile;
        } catch (IOException e) {
            throw new IOException("Error converting image: " + e.getMessage(), e);
        }
    }

    private String generateOutputPath(File originalFile, String newFormat) {
        String originalPath = originalFile.getAbsolutePath();
        int dotIndex = originalPath.lastIndexOf('.');
        return originalPath.substring(0, dotIndex + 1) + newFormat;
    }
}
