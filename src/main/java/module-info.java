module com.example.hmi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.hmi to javafx.fxml;
    exports com.example.hmi;
}