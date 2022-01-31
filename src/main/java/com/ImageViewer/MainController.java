package com.ImageViewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MainController {
    private static String name, height, width, size;
    private static Image image, baseImage;
    @FXML public ImageView imageView;

    @FXML
    protected void file() {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilter);
            File file = fileChooser.showOpenDialog(null);
            Utilities.resetAdjustments();
            if (Utilities.validImageFile(file)) {
                image = baseImage = new Image(file.toURI().toString());
                imageView.setImage(image);
                name = file.getName();
                height = String.valueOf((int) image.getHeight());
                width = String.valueOf((int) image.getWidth());
                size = Utilities.fileSizeMB(file);
            }
        } catch (Exception e) {
            System.out.println("error loading file");
        }
    }

    @FXML
    protected void details() throws IOException {
        VBox detailsVB = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("detail-view.fxml")));
        Scene details = new Scene(detailsVB, 200, 100);
        Stage stage = new Stage();
        stage.setTitle("file details");
        stage.setScene(details);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void grayscale() {
        if (image != null) {
            try {
                WritableImage grayImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
                for (int i = 0; i < (int) image.getHeight(); i++) {
                    for (int j = 0; j < (int) image.getWidth(); j++) {
                        Color c = image.getPixelReader().getColor(j, i);
                        double r = (c.getRed() * 0.299);
                        double g = (c.getGreen() * 0.587);
                        double b = (c.getBlue() * 0.114);
                        Color gray = new Color(r + g + b, r + g + b, r + g + b, 1.0);
                        grayImage.getPixelWriter().setColor(j, i, gray);
                    }
                }
                image = grayImage;
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("error converting image to grayscale");
            }
        }
    }

    @FXML
    protected void colours() throws IOException {
        VBox channelsVB = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("colour-adjustment-view.fxml")));
        Scene colours = new Scene(channelsVB, 200, 200);
        Stage stage = new Stage();
        stage.setTitle("adjust colours");
        stage.setScene(colours);
        stage.setResizable(false);
        stage.show();
        if (imageView != null) imageView.setEffect(AdjustmentController.getColorAdjust());
    }

    @FXML
    protected void channels() throws IOException {
        VBox channelsVB = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("colour-channel-view.fxml")));
        Scene channels = new Scene(channelsVB, 400, 300);
        Stage stage = new Stage();
        stage.setTitle("colour channels");
        stage.setScene(channels);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    protected void undo() {
        if (image != null) {
            image = baseImage;
            imageView.setImage(image);
        }
        Utilities.resetAdjustments();
    }

    @FXML
    protected void close() {
        if (imageView != null) {
            image = null;
            imageView.setImage(null);
            Utilities.resetAdjustments();
        }
    }

    @FXML
    protected void exit() {
        System.exit(0);
    }

    public static Image getImage() {
        return image;
    }

    public static String getName() {
        return name;
    }

    public static String getHeight() {
        return height;
    }

    public static String getWidth() {
        return width;
    }

    public static String getSize() {
        return size;
    }
}