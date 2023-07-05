package com.grupo5.fronttpajedrez.controllers;

import com.grupo5.fronttpajedrez.models.ConexionHTTP;
import com.grupo5.fronttpajedrez.models.Partida;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class InicioController {

    @FXML
    private Label lblInfo;
    private ConexionHTTP conexionHTTP;
    private Gson gson;
    private ComandoController comandoController;

    public InicioController(){
        conexionHTTP = new ConexionHTTP();
        gson = new Gson();
    }

    public void setComandoController(ComandoController comandoController) {
        this.comandoController = comandoController;
    }

    @FXML
    void crearNuevaPartida(ActionEvent event) throws IOException {
        try{
            String response = conexionHTTP.get("partida/get-nueva?idB=1&idN=2&nombrePartida=Primera").toString();
            Partida partida = gson.fromJson(response, Partida.class);
            this.comandoController.cargarTablero(partida);
        } catch (Exception ex){
            lblInfo.setText("Debe guardar 2 jugadores en la base de datos con id = 1 e id = 2");
        }
    }

    @FXML
    void abrirViejaPartida(ActionEvent event) throws IOException {
        lblInfo.setText("Próximamente");
    }
}
