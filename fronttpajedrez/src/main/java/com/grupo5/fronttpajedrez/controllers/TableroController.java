package com.grupo5.fronttpajedrez.controllers;

import com.grupo5.fronttpajedrez.models.ConexionHTTP;
import com.grupo5.fronttpajedrez.models.Partida;
import com.grupo5.fronttpajedrez.models.Posicion;
import com.grupo5.fronttpajedrez.models.Touch;
import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class TableroController {

    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Button btn13;
    @FXML
    private Button btn14;
    @FXML
    private Button btn15;
    @FXML
    private Button btn16;
    @FXML
    private Button btn17;
    @FXML
    private Button btn18;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Button btn23;
    @FXML
    private Button btn24;
    @FXML
    private Button btn25;
    @FXML
    private Button btn26;
    @FXML
    private Button btn27;
    @FXML
    private Button btn28;
    @FXML
    private Button btn31;
    @FXML
    private Button btn32;
    @FXML
    private Button btn33;
    @FXML
    private Button btn34;
    @FXML
    private Button btn35;
    @FXML
    private Button btn36;
    @FXML
    private Button btn37;
    @FXML
    private Button btn38;
    @FXML
    private Button btn41;
    @FXML
    private Button btn42;
    @FXML
    private Button btn43;
    @FXML
    private Button btn44;
    @FXML
    private Button btn45;
    @FXML
    private Button btn46;
    @FXML
    private Button btn47;
    @FXML
    private Button btn48;
    @FXML
    private Button btn51;
    @FXML
    private Button btn52;
    @FXML
    private Button btn53;
    @FXML
    private Button btn54;
    @FXML
    private Button btn55;
    @FXML
    private Button btn56;
    @FXML
    private Button btn57;
    @FXML
    private Button btn58;
    @FXML
    private Button btn61;
    @FXML
    private Button btn62;
    @FXML
    private Button btn63;
    @FXML
    private Button btn64;
    @FXML
    private Button btn65;
    @FXML
    private Button btn66;
    @FXML
    private Button btn67;
    @FXML
    private Button btn68;
    @FXML
    private Button btn71;
    @FXML
    private Button btn72;
    @FXML
    private Button btn73;
    @FXML
    private Button btn74;
    @FXML
    private Button btn75;
    @FXML
    private Button btn76;
    @FXML
    private Button btn77;
    @FXML
    private Button btn78;
    @FXML
    private Button btn81;
    @FXML
    private Button btn82;
    @FXML
    private Button btn83;
    @FXML
    private Button btn84;
    @FXML
    private Button btn85;
    @FXML
    private Button btn86;
    @FXML
    private Button btn87;
    @FXML
    private Button btn88;

    @FXML
    private Button btnImgTurno;
    @FXML
    private Label lblPiezaSeleccionada;
    @FXML
    private Label lblAccion;
    @FXML
    private Label lblEstado;
    @FXML
    private TextArea txtMovBlancas;
    @FXML
    private TextArea txtMovNegras;

    private Partida partida;
    private ConexionHTTP conexionHTTP;
    private Gson gson;
    private ImageView imageView;
    private Touch touch;
    private String posicionAnterior;

    private ComandoController comandoController;
    public void setComandoController(ComandoController comandoController) {
        this.comandoController = comandoController;
    }

    public void initilize(Partida partida){
        this.partida = partida;
        conexionHTTP = new ConexionHTTP();
        gson = new Gson();
        ponerImagenes();
        cambiarImagenTurno(partida.getTurno());
        lblEstado.setText("10 Juego en curso");
        lblAccion.setText("Seleccione una pieza");
    }

    @FXML
    private void onClickBtn(MouseEvent mouseEvent) throws IOException {
        String col = mouseEvent.getSource().toString().substring(14, 15);
        String row = mouseEvent.getSource().toString().substring(13, 14);
        Posicion posicion = new Posicion(Integer.parseInt(col), Integer.parseInt(row));

        String response = conexionHTTP.get("juego/get-touch/" + posicion.getNotAlgebraica()).toString();
        touch = gson.fromJson(response, Touch.class);

        switch (touch.getAccion()){
            case 0: lblAccion.setText(touch.getAccion() + " Ninguna acción");
                break;
            case 1:
                posicionAnterior = row + col;
                lblPiezaSeleccionada.setText(touch.getNotAlgebraica());
                lblAccion.setText(touch.getAccion() + " Pieza seleccionada");
                break;
            case 2:
                cambiarImagen(posicionAnterior, 1);
                cambiarImagen(row + col, 2);
                cambiarImagenTurno(touch.getTurno());
                escribirNotAlge(touch);
                lblPiezaSeleccionada.setText("");
                lblAccion.setText(touch.getAccion() + " Movimiento permitido");
                break;
            case 3: lblAccion.setText(touch.getAccion() + " Movimiento prohibido");
                break;
            case 6: lblAccion.setText(touch.getAccion() + " Casillero vacío");
                break;
            case 7: lblAccion.setText(touch.getAccion() + " Pieza del turno contrario");
                break;
        }

        switch (touch.getEstado()){
            case 10: lblEstado.setText(touch.getEstado() + " Juego en curso");
                break;
            case 11: lblEstado.setText(touch.getEstado() + " Empate");
                break;
            case 40: lblEstado.setText(touch.getEstado() + " JAQUE");
                break;
            case 50: lblEstado.setText(touch.getEstado() + " JAQUE MATE");
                break;
            case 90: lblEstado.setText(touch.getEstado() + " Juego detenido");
                break;
            case 99: lblEstado.setText(touch.getEstado() + " Juego Finalizado");
                break;
        }
    }

    private void escribirNotAlge(Touch touch){
        if(touch.getTurno() == 3)
            txtMovBlancas.appendText(touch.getNotAlgebraica() + "\n");
        else
            txtMovNegras.appendText(touch.getNotAlgebraica() + "\n");
    }

    @FXML
    private void nuevaPartida() throws IOException {
        String response = conexionHTTP.get("partida/get-nueva?idB=1&idN=2&nombrePartida=Nueva").toString();
        Partida partida = gson.fromJson(response, Partida.class);
        initilize(partida);
        lblEstado.setText("10 Juego en curso");
        lblAccion.setText("Seleccione una pieza");
        lblPiezaSeleccionada.setText("");
        txtMovBlancas.setText("");
        txtMovNegras.setText("");
    }

    private void ponerImagenes() {
        this.eliminarImagenesBotones();
        String url = "";
        Image imagen;
        String posicion;
        for(int i = 1; i <= 8; ++i) {
            for(int j = 1; j <= 8; ++j) {
                url = "";
                switch (this.partida.getTablero()[i][j] / 10) {
                    case 2:
                        switch (this.partida.getTablero()[i][j] % 10) {
                            case 4: url = "/com/grupo5/fronttpajedrez/imgs/peon-blanco.png";
                                break;
                            case 5: url = "/com/grupo5/fronttpajedrez/imgs/torre-blanca.png";
                                break;
                            case 6: url = "/com/grupo5/fronttpajedrez/imgs/caballo-blanco.png";
                                break;
                            case 7: url = "/com/grupo5/fronttpajedrez/imgs/alfil-blanco.png";
                                break;
                            case 8: url = "/com/grupo5/fronttpajedrez/imgs/reina-blanca.png";
                                break;
                            case 9: url = "/com/grupo5/fronttpajedrez/imgs/rey-blanco.png";
                                break;
                            default:
                                continue;
                        }
                        break;
                    case 3:
                        switch (this.partida.getTablero()[i][j] % 10) {
                            case 4: url = "/com/grupo5/fronttpajedrez/imgs/peon-negro.png";
                                break;
                            case 5: url = "/com/grupo5/fronttpajedrez/imgs/torre-negra.png";
                                break;
                            case 6: url = "/com/grupo5/fronttpajedrez/imgs/caballo-negro.png";
                                break;
                            case 7: url = "/com/grupo5/fronttpajedrez/imgs/alfil-negro.png";
                                break;
                            case 8: url = "/com/grupo5/fronttpajedrez/imgs/reina-negra.png";
                                break;
                            case 9: url = "/com/grupo5/fronttpajedrez/imgs/rey-negro.png";
                                break;
                            default:
                                continue;
                        }
                        break;
                }
                if(!url.isEmpty()){
                    String path = getClass().getResource(url).getPath();
                    imagen = new Image(path.substring(1));
                    this.imageView = new ImageView(imagen);
                    posicion = Integer.toString(j) + i;
                    this.cambiarImagen(posicion, 2);
                }
            }
        }
    }

    private void cambiarImagenTurno(int turno){
        ImageView imageViewTurno;

        String pathBlancas = getClass().getResource("/com/grupo5/fronttpajedrez/imgs/peon-blanco.png").getPath();
        Image imagenBlancas = new Image(pathBlancas.substring(1));
        String pathNegras = getClass().getResource("/com/grupo5/fronttpajedrez/imgs/peon-negro.png").getPath();
        Image imagenNegras = new Image(pathNegras.substring(1));

        if(turno == 2){
            imageViewTurno = new ImageView(imagenBlancas);
            btnImgTurno.setGraphic(imageViewTurno);
        } else {
            imageViewTurno = new ImageView(imagenNegras);
            btnImgTurno.setGraphic(imageViewTurno);
        }
    }

    private void eliminarImagenesBotones(){
        btn11.setGraphic(null);
        btn12.setGraphic(null);
        btn13.setGraphic(null);
        btn14.setGraphic(null);
        btn15.setGraphic(null);
        btn16.setGraphic(null);
        btn17.setGraphic(null);
        btn18.setGraphic(null);
        btn21.setGraphic(null);
        btn22.setGraphic(null);
        btn23.setGraphic(null);
        btn24.setGraphic(null);
        btn25.setGraphic(null);
        btn26.setGraphic(null);
        btn27.setGraphic(null);
        btn28.setGraphic(null);
        btn31.setGraphic(null);
        btn32.setGraphic(null);
        btn33.setGraphic(null);
        btn34.setGraphic(null);
        btn35.setGraphic(null);
        btn36.setGraphic(null);
        btn37.setGraphic(null);
        btn38.setGraphic(null);
        btn41.setGraphic(null);
        btn42.setGraphic(null);
        btn43.setGraphic(null);
        btn44.setGraphic(null);
        btn45.setGraphic(null);
        btn46.setGraphic(null);
        btn47.setGraphic(null);
        btn48.setGraphic(null);
        btn51.setGraphic(null);
        btn52.setGraphic(null);
        btn53.setGraphic(null);
        btn54.setGraphic(null);
        btn55.setGraphic(null);
        btn56.setGraphic(null);
        btn57.setGraphic(null);
        btn58.setGraphic(null);
        btn61.setGraphic(null);
        btn62.setGraphic(null);
        btn63.setGraphic(null);
        btn64.setGraphic(null);
        btn65.setGraphic(null);
        btn66.setGraphic(null);
        btn67.setGraphic(null);
        btn68.setGraphic(null);
        btn71.setGraphic(null);
        btn72.setGraphic(null);
        btn73.setGraphic(null);
        btn74.setGraphic(null);
        btn75.setGraphic(null);
        btn76.setGraphic(null);
        btn77.setGraphic(null);
        btn78.setGraphic(null);
        btn81.setGraphic(null);
        btn82.setGraphic(null);
        btn83.setGraphic(null);
        btn84.setGraphic(null);
        btn85.setGraphic(null);
        btn86.setGraphic(null);
        btn87.setGraphic(null);
        btn88.setGraphic(null);
    }

    private void cambiarImagen(String posicionBoton, int touch) {
        switch (posicionBoton) {
            case "11":
                if (touch == 1) {
                    imageView = (ImageView) btn11.getGraphic();
                    btn11.setGraphic(null);
                } else if (touch == 2) {
                    btn11.setGraphic(imageView);
                }
                break;
            case "12":
                if (touch == 1) {
                    imageView = (ImageView) btn12.getGraphic();
                    btn12.setGraphic(null);
                } else if (touch == 2) {
                    btn12.setGraphic(imageView);
                }
                break;
            case "13":
                if (touch == 1) {
                    imageView = (ImageView) btn13.getGraphic();
                    btn13.setGraphic(null);
                } else if (touch == 2) {
                    btn13.setGraphic(imageView);
                }
                break;
            case "14":
                if (touch == 1) {
                    imageView = (ImageView) btn14.getGraphic();
                    btn14.setGraphic(null);
                } else if (touch == 2) {
                    btn14.setGraphic(imageView);
                }
                break;
            case "15":
                if (touch == 1) {
                    imageView = (ImageView) btn15.getGraphic();
                    btn15.setGraphic(null);
                } else if (touch == 2) {
                    btn15.setGraphic(imageView);
                }
                break;
            case "16":
                if (touch == 1) {
                    imageView = (ImageView) btn16.getGraphic();
                    btn16.setGraphic(null);
                } else if (touch == 2) {
                    btn16.setGraphic(imageView);
                }
                break;
            case "17":
                if (touch == 1) {
                    imageView = (ImageView) btn17.getGraphic();
                    btn17.setGraphic(null);
                } else if (touch == 2) {
                    btn17.setGraphic(imageView);
                }
                break;
            case "18":
                if (touch == 1) {
                    imageView = (ImageView) btn18.getGraphic();
                    btn18.setGraphic(null);
                } else if (touch == 2) {
                    btn18.setGraphic(imageView);
                }
                break;
            case "21":
                if (touch == 1) {
                    imageView = (ImageView) btn21.getGraphic();
                    btn21.setGraphic(null);
                } else if (touch == 2) {
                    btn21.setGraphic(imageView);
                }
                break;
            case "22":
                if (touch == 1) {
                    imageView = (ImageView) btn22.getGraphic();
                    btn22.setGraphic(null);
                } else if (touch == 2) {
                    btn22.setGraphic(imageView);
                }
                break;
            case "23":
                if (touch == 1) {
                    imageView = (ImageView) btn23.getGraphic();
                    btn23.setGraphic(null);
                } else if (touch == 2) {
                    btn23.setGraphic(imageView);
                }
                break;
            case "24":
                if (touch == 1) {
                    imageView = (ImageView) btn24.getGraphic();
                    btn24.setGraphic(null);
                } else if (touch == 2) {
                    btn24.setGraphic(imageView);
                }
                break;
            case "25":
                if (touch == 1) {
                    imageView = (ImageView) btn25.getGraphic();
                    btn25.setGraphic(null);
                } else if (touch == 2) {
                    btn25.setGraphic(imageView);
                }
                break;
            case "26":
                if (touch == 1) {
                    imageView = (ImageView) btn26.getGraphic();
                    btn26.setGraphic(null);
                } else if (touch == 2) {
                    btn26.setGraphic(imageView);
                }
                break;
            case "27":
                if (touch == 1) {
                    imageView = (ImageView) btn27.getGraphic();
                    btn27.setGraphic(null);
                } else if (touch == 2) {
                    btn27.setGraphic(imageView);
                }
                break;
            case "28":
                if (touch == 1) {
                    imageView = (ImageView) btn28.getGraphic();
                    btn28.setGraphic(null);
                } else if (touch == 2) {
                    btn28.setGraphic(imageView);
                }
                break;
            case "31":
                if (touch == 1) {
                    imageView = (ImageView) btn31.getGraphic();
                    btn31.setGraphic(null);
                } else if (touch == 2) {
                    btn31.setGraphic(imageView);
                }
                break;
            case "32":
                if (touch == 1) {
                    imageView = (ImageView) btn32.getGraphic();
                    btn32.setGraphic(null);
                } else if (touch == 2) {
                    btn32.setGraphic(imageView);
                }
                break;
            case "33":
                if (touch == 1) {
                    imageView = (ImageView) btn33.getGraphic();
                    btn33.setGraphic(null);
                } else if (touch == 2) {
                    btn33.setGraphic(imageView);
                }
                break;
            case "34":
                if (touch == 1) {
                    imageView = (ImageView) btn34.getGraphic();
                    btn34.setGraphic(null);
                } else if (touch == 2) {
                    btn34.setGraphic(imageView);
                }
                break;
            case "35":
                if (touch == 1) {
                    imageView = (ImageView) btn35.getGraphic();
                    btn35.setGraphic(null);
                } else if (touch == 2) {
                    btn35.setGraphic(imageView);
                }
                break;
            case "36":
                if (touch == 1) {
                    imageView = (ImageView) btn36.getGraphic();
                    btn36.setGraphic(null);
                } else if (touch == 2) {
                    btn36.setGraphic(imageView);
                }
                break;
            case "37":
                if (touch == 1) {
                    imageView = (ImageView) btn37.getGraphic();
                    btn37.setGraphic(null);
                } else if (touch == 2) {
                    btn37.setGraphic(imageView);
                }
                break;
            case "38":
                if (touch == 1) {
                    imageView = (ImageView) btn38.getGraphic();
                    btn38.setGraphic(null);
                } else if (touch == 2) {
                    btn38.setGraphic(imageView);
                }
                break;
            case "41":
                if (touch == 1) {
                    imageView = (ImageView) btn41.getGraphic();
                    btn41.setGraphic(null);
                } else if (touch == 2) {
                    btn41.setGraphic(imageView);
                }
                break;
            case "42":
                if (touch == 1) {
                    imageView = (ImageView) btn42.getGraphic();
                    btn42.setGraphic(null);
                } else if (touch == 2) {
                    btn42.setGraphic(imageView);
                }
                break;
            case "43":
                if (touch == 1) {
                    imageView = (ImageView) btn43.getGraphic();
                    btn43.setGraphic(null);
                } else if (touch == 2) {
                    btn43.setGraphic(imageView);
                }
                break;
            case "44":
                if (touch == 1) {
                    imageView = (ImageView) btn44.getGraphic();
                    btn44.setGraphic(null);
                } else if (touch == 2) {
                    btn44.setGraphic(imageView);
                }
                break;
            case "45":
                if (touch == 1) {
                    imageView = (ImageView) btn45.getGraphic();
                    btn45.setGraphic(null);
                } else if (touch == 2) {
                    btn45.setGraphic(imageView);
                }
                break;
            case "46":
                if (touch == 1) {
                    imageView = (ImageView) btn46.getGraphic();
                    btn46.setGraphic(null);
                } else if (touch == 2) {
                    btn46.setGraphic(imageView);
                }
                break;
            case "47":
                if (touch == 1) {
                    imageView = (ImageView) btn47.getGraphic();
                    btn47.setGraphic(null);
                } else if (touch == 2) {
                    btn47.setGraphic(imageView);
                }
                break;
            case "48":
                if (touch == 1) {
                    imageView = (ImageView) btn48.getGraphic();
                    btn48.setGraphic(null);
                } else if (touch == 2) {
                    btn48.setGraphic(imageView);
                }
                break;
            case "51":
                if (touch == 1) {
                    imageView = (ImageView) btn51.getGraphic();
                    btn51.setGraphic(null);
                } else if (touch == 2) {
                    btn51.setGraphic(imageView);
                }
                break;
            case "52":
                if (touch == 1) {
                    imageView = (ImageView) btn52.getGraphic();
                    btn52.setGraphic(null);
                } else if (touch == 2) {
                    btn52.setGraphic(imageView);
                }
                break;
            case "53":
                if (touch == 1) {
                    imageView = (ImageView) btn53.getGraphic();
                    btn53.setGraphic(null);
                } else if (touch == 2) {
                    btn53.setGraphic(imageView);
                }
                break;
            case "54":
                if (touch == 1) {
                    imageView = (ImageView) btn54.getGraphic();
                    btn54.setGraphic(null);
                } else if (touch == 2) {
                    btn54.setGraphic(imageView);
                }
                break;
            case "55":
                if (touch == 1) {
                    imageView = (ImageView) btn55.getGraphic();
                    btn55.setGraphic(null);
                } else if (touch == 2) {
                    btn55.setGraphic(imageView);
                }
                break;
            case "56":
                if (touch == 1) {
                    imageView = (ImageView) btn56.getGraphic();
                    btn56.setGraphic(null);
                } else if (touch == 2) {
                    btn56.setGraphic(imageView);
                }
                break;
            case "57":
                if (touch == 1) {
                    imageView = (ImageView) btn57.getGraphic();
                    btn57.setGraphic(null);
                } else if (touch == 2) {
                    btn57.setGraphic(imageView);
                }
                break;
            case "58":
                if (touch == 1) {
                    imageView = (ImageView) btn58.getGraphic();
                    btn58.setGraphic(null);
                } else if (touch == 2) {
                    btn58.setGraphic(imageView);
                }
                break;
            case "61":
                if (touch == 1) {
                    imageView = (ImageView) btn61.getGraphic();
                    btn61.setGraphic(null);
                } else if (touch == 2) {
                    btn61.setGraphic(imageView);
                }
                break;
            case "62":
                if (touch == 1) {
                    imageView = (ImageView) btn62.getGraphic();
                    btn62.setGraphic(null);
                } else if (touch == 2) {
                    btn62.setGraphic(imageView);
                }
                break;
            case "63":
                if (touch == 1) {
                    imageView = (ImageView) btn63.getGraphic();
                    btn63.setGraphic(null);
                } else if (touch == 2) {
                    btn63.setGraphic(imageView);
                }
                break;
            case "64":
                if (touch == 1) {
                    imageView = (ImageView) btn64.getGraphic();
                    btn64.setGraphic(null);
                } else if (touch == 2) {
                    btn64.setGraphic(imageView);
                }
                break;
            case "65":
                if (touch == 1) {
                    imageView = (ImageView) btn65.getGraphic();
                    btn65.setGraphic(null);
                } else if (touch == 2) {
                    btn65.setGraphic(imageView);
                }
                break;
            case "66":
                if (touch == 1) {
                    imageView = (ImageView) btn66.getGraphic();
                    btn66.setGraphic(null);
                } else if (touch == 2) {
                    btn66.setGraphic(imageView);
                }
                break;
            case "67":
                if (touch == 1) {
                    imageView = (ImageView) btn67.getGraphic();
                    btn67.setGraphic(null);
                } else if (touch == 2) {
                    btn67.setGraphic(imageView);
                }
                break;
            case "68":
                if (touch == 1) {
                    imageView = (ImageView) btn68.getGraphic();
                    btn68.setGraphic(null);
                } else if (touch == 2) {
                    btn68.setGraphic(imageView);
                }
                break;
            case "71":
                if (touch == 1) {
                    imageView = (ImageView) btn71.getGraphic();
                    btn71.setGraphic(null);
                } else if (touch == 2) {
                    btn71.setGraphic(imageView);
                }
                break;
            case "72":
                if (touch == 1) {
                    imageView = (ImageView) btn72.getGraphic();
                    btn72.setGraphic(null);
                } else if (touch == 2) {
                    btn72.setGraphic(imageView);
                }
                break;
            case "73":
                if (touch == 1) {
                    imageView = (ImageView) btn73.getGraphic();
                    btn73.setGraphic(null);
                } else if (touch == 2) {
                    btn73.setGraphic(imageView);
                }
                break;
            case "74":
                if (touch == 1) {
                    imageView = (ImageView) btn74.getGraphic();
                    btn74.setGraphic(null);
                } else if (touch == 2) {
                    btn74.setGraphic(imageView);
                }
                break;
            case "75":
                if (touch == 1) {
                    imageView = (ImageView) btn75.getGraphic();
                    btn75.setGraphic(null);
                } else if (touch == 2) {
                    btn75.setGraphic(imageView);
                }
                break;
            case "76":
                if (touch == 1) {
                    imageView = (ImageView) btn76.getGraphic();
                    btn76.setGraphic(null);
                } else if (touch == 2) {
                    btn76.setGraphic(imageView);
                }
                break;
            case "77":
                if (touch == 1) {
                    imageView = (ImageView) btn77.getGraphic();
                    btn77.setGraphic(null);
                } else if (touch == 2) {
                    btn77.setGraphic(imageView);
                }
                break;
            case "78":
                if (touch == 1) {
                    imageView = (ImageView) btn78.getGraphic();
                    btn78.setGraphic(null);
                } else if (touch == 2) {
                    btn78.setGraphic(imageView);
                }
                break;
            case "81":
                if (touch == 1) {
                    imageView = (ImageView) btn81.getGraphic();
                    btn81.setGraphic(null);
                } else if (touch == 2) {
                    btn81.setGraphic(imageView);
                }
                break;
            case "82":
                if (touch == 1) {
                    imageView = (ImageView) btn82.getGraphic();
                    btn82.setGraphic(null);
                } else if (touch == 2) {
                    btn82.setGraphic(imageView);
                }
                break;
            case "83":
                if (touch == 1) {
                    imageView = (ImageView) btn83.getGraphic();
                    btn83.setGraphic(null);
                } else if (touch == 2) {
                    btn83.setGraphic(imageView);
                }
                break;
            case "84":
                if (touch == 1) {
                    imageView = (ImageView) btn84.getGraphic();
                    btn84.setGraphic(null);
                } else if (touch == 2) {
                    btn84.setGraphic(imageView);
                }
                break;
            case "85":
                if (touch == 1) {
                    imageView = (ImageView) btn85.getGraphic();
                    btn85.setGraphic(null);
                } else if (touch == 2) {
                    btn85.setGraphic(imageView);
                }
                break;
            case "86":
                if (touch == 1) {
                    imageView = (ImageView) btn86.getGraphic();
                    btn86.setGraphic(null);
                } else if (touch == 2) {
                    btn86.setGraphic(imageView);
                }
                break;
            case "87":
                if (touch == 1) {
                    imageView = (ImageView) btn87.getGraphic();
                    btn87.setGraphic(null);
                } else if (touch == 2) {
                    btn87.setGraphic(imageView);
                }
                break;
            case "88":
                if (touch == 1) {
                    imageView = (ImageView) btn88.getGraphic();
                    btn88.setGraphic(null);
                } else if (touch == 2) {
                    btn88.setGraphic(imageView);
                }
                break;
        }
    }
}
