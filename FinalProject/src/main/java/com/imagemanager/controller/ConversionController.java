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
        if (source == null || targetFormat == null || targetFormat.isEmpty()) {
            throw new IllegalArgumentException("Source file and target format must not be null");
        }

        ConversionTask task = new ConversionTask(source, targetFormat);
        return conversionService.convertImage(task);
    }

    public List<File> convertImages(List<ImageFile> sources, String targetFormat) throws IOException {
        if (sources == null || sources.isEmpty()) {
            throw new IllegalArgumentException("Source files list must not be empty");
        }
        if (targetFormat == null || targetFormat.isEmpty()) {
            throw new IllegalArgumentException("Target format must not be empty");
        }

        List<File> convertedFiles = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (ImageFile source : sources) {
            try {
                File converted = convertImage(source, targetFormat);
                convertedFiles.add(converted);
            } catch (IOException e) {
                // 收集错误信息而不是立即终止
                String errorMsg = "Error converting " + source.getFile().getName() + ": " + e.getMessage();
                errors.add(errorMsg);
            }
        }

        // 如果有错误但也有成功的转换，继续返回成功的部分
        if (!errors.isEmpty() && !convertedFiles.isEmpty()) {
            // 将错误信息合并并抛出
            throw new IOException("Some files failed to convert:\n" + String.join("\n", errors));
        }
        // 如果全部失败，抛出异常
        else if (convertedFiles.isEmpty()) {
            throw new IOException("All conversions failed:\n" + String.join("\n", errors));
        }

        return convertedFiles;
    }
}