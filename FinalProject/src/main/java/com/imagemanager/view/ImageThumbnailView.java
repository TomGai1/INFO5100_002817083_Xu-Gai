package com.imagemanager.view;

import com.imagemanager.model.ImageFile;
import com.imagemanager.model.ImageProperties;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageThumbnailView extends VBox {
    private ImageFile imageFile;

    public ImageThumbnailView(ImageFile imageFile) {
        this.imageFile = imageFile;
        setupUI();
    }

    private void setupUI() {
        try {

            Image thumbnail = new Image(new FileInputStream(imageFile.getThumbnailPath()));
            ImageView imageView = new ImageView(thumbnail);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            VBox propertiesBox = createPropertiesBox();

            this.setSpacing(5);
            this.getChildren().addAll(imageView, propertiesBox);
            this.getStyleClass().add("thumbnail-view");

        } catch (IOException e) {
            e.printStackTrace();
            Label errorLabel = new Label("Error loading image");
            this.getChildren().add(errorLabel);
        }
    }

    private VBox createPropertiesBox() {
        VBox propertiesBox = new VBox(5);
        ImageProperties props = imageFile.getProperties();

        Label nameLabel = new Label("Name: " + imageFile.getFile().getName());
        Label sizeLabel = new Label(String.format("Size: %dx%d", props.getWidth(), props.getHeight()));
        Label formatLabel = new Label("Format: " + props.getFormat());

        propertiesBox.getChildren().addAll(nameLabel, sizeLabel, formatLabel);

        if (props.getCameraModel() != null) {
            Label cameraLabel = new Label("Camera: " + props.getCameraModel());
            propertiesBox.getChildren().add(cameraLabel);
        }

        if (props.getLocation() != null) {
            Label locationLabel = new Label("Location: " + props.getLocation());
            propertiesBox.getChildren().add(locationLabel);
        }

        return propertiesBox;
    }
}
