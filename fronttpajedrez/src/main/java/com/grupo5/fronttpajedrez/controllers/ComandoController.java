package com.grupo5.fronttpajedrez.controllers;

import com.grupo5.fronttpajedrez.models.Partida;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ComandoController {
    private Stage stage;
    private BorderPane borderPane;
    public ComandoController(Stage stage){
        this.stage = stage;
        cargarBase();
        cargarInicio();
    }

    public void cargarBase(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/grupo5/fronttpajedrez/views/base-view.fxml"));
            borderPane = loader.load();
            Scene scene = new Scene(borderPane);
            stage.setTitle("Super Ajedrez");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarInicio(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ComandoController.class.getResource("/com/grupo5/fronttpajedrez/views/inicio-view.fxml"));
            Pane pane = loader.load();
            borderPane.setCenter(pane);
            InicioController inicioController = loader.getController();
            inicioController.setComandoController(this);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void cargarTablero(Partida partida){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ComandoController.class.getResource("/com/grupo5/fronttpajedrez/views/tablero-view.fxml"));
            Pane pane = loader.load();
            borderPane.setCenter(pane);
            TableroController tableroController = loader.getController();
            tableroController.setComandoController(this);
            tableroController.initilize(partida);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
