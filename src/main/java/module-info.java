module com.imageviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ImageViewer to javafx.fxml;
    exports com.ImageViewer;
}