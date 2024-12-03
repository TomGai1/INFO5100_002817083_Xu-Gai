package com.imagemanager.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import com.imagemanager.controller.ImageController;
import com.imagemanager.controller.ConversionController;
import com.imagemanager.model.ImageFile;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.List;

public class MainView extends Application {
    private ImageController imageController;
    private ConversionController conversionController;
    private FlowPane imageContainer;

    @Override
    public void start(Stage primaryStage) {
        imageController = new ImageController();
        conversionController = new ConversionController();

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
            imageController.loadImages(files);
            updateImageDisplay();
        }
    }

    private void handleConversion(String format) {
        if (format == null || format.isEmpty()) {
            showAlert("Error", "Please select a target format");
            return;
        }

        List<ImageFile> selectedImages = imageController.getLoadedImages();
        if (selectedImages.isEmpty()) {
            showAlert("Error", "No images selected for conversion");
            return;
        }

        List<File> convertedFiles = conversionController.convertImages(selectedImages, format);
        showAlert("Success", "Converted " + convertedFiles.size() + " images successfully");
    }

    private void updateImageDisplay() {
        imageContainer.getChildren().clear();
        for (ImageFile imageFile : imageController.getLoadedImages()) {
            ImageThumbnailView thumbnailView = new ImageThumbnailView(imageFile);
            imageContainer.getChildren().add(thumbnailView);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
