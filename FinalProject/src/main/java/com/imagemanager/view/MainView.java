package com.imagemanager.view;

import com.imagemanager.controller.ImageController;
import com.imagemanager.controller.ConversionController;  // 添加导入
import com.imagemanager.model.ImageFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainView extends Application {
    private ImageController imageController;
    private ConversionController conversionController;  // 添加成员变量
    private FlowPane imageContainer;

    @Override
    public void start(Stage primaryStage) {
        imageController = new ImageController();
        conversionController = new ConversionController();  // 初始化

        BorderPane root = new BorderPane();

        // Top toolbar
        ToolBar toolbar = createToolbar();
        root.setTop(toolbar);

        // Center content
        ScrollPane scrollPane = new ScrollPane();
        imageContainer = new FlowPane();
        imageContainer.setHgap(10);
        imageContainer.setVgap(10);
        imageContainer.setPadding(new Insets(10));
        scrollPane.setContent(imageContainer);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Image Management Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ToolBar createToolbar() {
        Button uploadButton = new Button("Upload Images");
        uploadButton.setOnAction(e -> handleImageUpload());

        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("jpg", "png", "gif", "bmp");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> handleConversion(formatComboBox.getValue()));

        return new ToolBar(uploadButton, new Separator(), formatComboBox, convertButton);
    }

    private void handleImageUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp")
        );

        List<File> files = fileChooser.showOpenMultipleDialog(null);
        if (files != null) {
            try {
                imageController.loadImages(files);
                updateImageDisplay();
            } catch (IOException e) {
                showErrorAlert("Error Loading Image", e.getMessage());
            }
        }
    }

    private void handleConversion(String format) {
        if (format == null || format.isEmpty()) {
            showErrorAlert("Error", "Please select a target format");
            return;
        }

        List<ImageFile> selectedImages = imageController.getLoadedImages();
        if (selectedImages.isEmpty()) {
            showErrorAlert("Error", "No images selected for conversion");
            return;
        }

        try {
            List<File> convertedFiles = conversionController.convertImages(selectedImages, format);
            showSuccessAlert("Successfully converted " + convertedFiles.size() + " images to " + format);
        } catch (IOException e) {
            showErrorAlert("Conversion Error", e.getMessage());
        }
    }

    private void updateImageDisplay() {
        imageContainer.getChildren().clear();
        for (ImageFile imageFile : imageController.getLoadedImages()) {
            ImageThumbnailView thumbnailView = new ImageThumbnailView(imageFile);
            imageContainer.getChildren().add(thumbnailView);
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccessAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}