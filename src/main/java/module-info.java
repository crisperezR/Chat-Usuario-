module org.example.proyecto_usuario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens org.example.proyecto_usuario to javafx.fxml;
    exports org.example.proyecto_usuario;
}