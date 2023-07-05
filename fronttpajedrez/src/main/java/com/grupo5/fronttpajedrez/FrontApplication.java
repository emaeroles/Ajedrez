package com.grupo5.fronttpajedrez;

import com.grupo5.fronttpajedrez.controllers.ComandoController;
import javafx.application.Application;
import javafx.stage.Stage;

public class FrontApplication extends Application {
    @Override
    public void start(Stage stage) {
        new ComandoController(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
