module controllers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.google.gson;

    exports com.grupo5.fronttpajedrez.controllers;
    opens com.grupo5.fronttpajedrez.controllers to javafx.fxml;
    exports com.grupo5.fronttpajedrez;
    opens com.grupo5.fronttpajedrez to javafx.fxml;
}