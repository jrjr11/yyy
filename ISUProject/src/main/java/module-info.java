module com.example.isuproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.isuproject to javafx.fxml;
    exports com.example.isuproject;
}