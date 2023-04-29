package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import models.Peon;
import models.Tablero;
import models.TableroDao;

import java.util.List;

public class TableroController {
    @FXML
    public MenuItem nuevo;
    @FXML
    public Button btna1;
    @FXML
    public Button btna2;
    @FXML
    public Button btna3;
    @FXML
    public Button btnb1;
    @FXML
    public Button btnb2;
    @FXML
    public Button btnb3;
    @FXML
    public Button btnc1;
    @FXML
    public Button btnc2;
    @FXML
    public Button btnc3;
    Tablero tablero;
    int turno = 0;
    boolean segundoTouch = false;
    int[] posicionVieja;
    List<int[]> movimientosPosibles;
    ImageView imageView;

    public TableroController() {


    }
    @FXML
    public void nuevoJuego(){
        turno = 2;
        tablero = new Tablero();
        ponerImagenes();
    }
    public void guardar(){
        TableroDao dao = new TableroDao();
        dao.guardar(tablero.getTablero());
    }
    private void cambiarTurno(){
        if(turno == 2){
            turno = 3;
        } else {
            turno = 2;
        }
    }
    Peon peon = new Peon();
    private void movimiento(int[] posicionBoton) {
        int contenidoEscaque = tablero.getContenidoEscaque(posicionBoton); // no
        if (!segundoTouch && contenidoEscaque / 10 == turno) {
            movimientosPosibles = peon.movPosibles(this.tablero, posicionBoton); // no
            cambiarImagen(posicionBoton, segundoTouch); // si
            posicionVieja = posicionBoton; // no
            segundoTouch = true; // no
        } else if (segundoTouch && contenidoEscaque >= 100) {
            tablero.borrarPosibles(movimientosPosibles); // no
            cambiarImagen(posicionBoton, segundoTouch); // si
            tablero.movimiento(posicionVieja, posicionBoton); // no
            segundoTouch = false; // no
            cambiarTurno(); // no
        }
    }
    /*
    private void ponerImagenes(){
        eliminarImagenesBotones();
        segundoTouch = true;
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                switch(tablero.getTablero()[i][j] / 10){
                    case 2:
                        switch(tablero.getTablero()[i][j] % 10){
                            case 4:
                                Image peonBlancoImage = new Image("imgs/peonBlanco.png");
                                imageView = new ImageView(peonBlancoImage);
                                int[] cuadro = new int[] {j,i};
                                cambiarImagen(cuadro,segundoTouch);
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 9:
                                break;
                        }
                        break;
                    case 3:
                        switch(tablero.getTablero()[i][j] % 10){
                            case 4:
                                Image peonNegroImage = new Image("imgs/peonNegro.png");
                                imageView = new ImageView(peonNegroImage);
                                int[] cuadro = new int[] {j,i};
                                cambiarImagen(cuadro,segundoTouch);
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            case 7:
                                break;
                            case 8:
                                break;
                            case 9:
                                break;
                        }
                        break;
                }
            }
        }
        segundoTouch = false;
    }
*/

    private void ponerImagenes() {
        this.eliminarImagenesBotones();
        this.segundoTouch = true;
        String url = "";

        for(int i = 1; i <= 3; ++i) {
            for(int j = 1; j <= 3; ++j) {
                Image image;
                int[] cuadro;
                url = "";
                switch (this.tablero.getTablero()[i][j] / 10) {
                    case 2:
                        switch (this.tablero.getTablero()[i][j] % 10) {
                            case 4: url = "imgs/peonNegro.png";
                                break;
                            case 5: url = "imgs/torreNegro.png";
                                break;
                            case 6: url = "imgs/reinaNegro.png";
                                break;
                            case 7: url = "imgs/alfilNegro.png";
                                break;
                            case 8: url = "imgs/caballoNegro.png";
                                break;
                            case 9: url = "imgs/reyNegro.png";
                                break;
                            default:
                                continue;
                        }
                        break;
                    case 3:
                        switch (this.tablero.getTablero()[i][j] % 10) {
                            case 4: url = "imgs/peonBlanco.png";
                                break;
                            case 5: url = "imgs/torreBlanco.png";
                                break;
                            case 6: url = "imgs/reinaBlanco.png";
                                break;
                            case 7: url = "imgs/alfilBlanco.png";
                                break;
                            case 8: url = "imgs/caballoBlanco.png";
                                break;
                            case 9: url = "imgs/reyBlanco.png";
                                break;
                            default:
                                continue;
                        }
                        break;
                }
                if(!url.isEmpty()){
                    image = new Image(url);
                    this.imageView = new ImageView(image);
                    cuadro = new int[]{j, i};
                    this.cambiarImagen(cuadro, this.segundoTouch);
                }
            }
        }

        this.segundoTouch = false;
    }

    private void eliminarImagenesBotones(){
        btna1.setGraphic(null);
        btna2.setGraphic(null);
        btna3.setGraphic(null);
        btnb1.setGraphic(null);
        btnb2.setGraphic(null);
        btnb3.setGraphic(null);
        btnc1.setGraphic(null);
        btnc2.setGraphic(null);
        btnc3.setGraphic(null);
    }
    private void cambiarImagen(int[] posicionBoton, boolean touch) {
        String strPosicionBoton = Integer.toString(posicionBoton[0]) + Integer.toString(posicionBoton[1]);
        switch (strPosicionBoton) {
            case "11":
                if (!touch) {
                    imageView = (ImageView) btna1.getGraphic();
                } else {
                    btna1.setGraphic(imageView);
                }
                break;
            case "12":
                if (!touch) {
                    imageView = (ImageView) btna2.getGraphic();
                } else {
                    btna2.setGraphic(imageView);
                }
                break;
            case "13":
                if (!touch) {
                    imageView = (ImageView) btna3.getGraphic();
                } else {
                    btna3.setGraphic(imageView);
                }
                break;
            case "21":
                if (!touch) {
                    imageView = (ImageView) btnb1.getGraphic();
                } else {
                    btnb1.setGraphic(imageView);
                }
                break;
            case "22":
                if (!touch) {
                    imageView = (ImageView) btnb2.getGraphic();
                } else {
                    btnb2.setGraphic(imageView);
                }
                break;
            case "23":
                if (!touch) {
                    imageView = (ImageView) btnb3.getGraphic();
                } else {
                    btnb3.setGraphic(imageView);
                }
                break;
            case "31":
                if (!touch) {
                    imageView = (ImageView) btnc1.getGraphic();
                } else {
                    btnc1.setGraphic(imageView);
                }
                break;
            case "32":
                if (!touch) {
                    imageView = (ImageView) btnc2.getGraphic();
                } else {
                    btnc2.setGraphic(imageView);
                }
                break;
            case "33":
                if (!touch) {
                    imageView = (ImageView) btnc3.getGraphic();
                } else {
                    btnc3.setGraphic(imageView);
                }
                break;
        }
    }
    public void fun(ActionEvent actionEvent) {
    }
    /* Eventos botones ************************************************************************/
    @FXML
    public void clickA1(MouseEvent mouseEvent) {
        int[] a1 = {1, 1};
        movimiento(a1);
    }
    @FXML
    public void clickA2(MouseEvent mouseEvent) {
        int[] a2 = {1, 2};
        movimiento(a2);
    }
    @FXML
    public void clickA3(MouseEvent mouseEvent) {
        int[] a3 = {1, 3};
        movimiento(a3);
    }
    @FXML
    public void clickB1(MouseEvent mouseEvent) {
        int[] b1 = {2, 1};
        movimiento(b1);
    }
    @FXML
    public void clickB2(MouseEvent mouseEvent) {
        int[] b2 = {2, 2};
        movimiento(b2);
    }
    @FXML
    public void clickB3(MouseEvent mouseEvent) {
        int[] b3 = {2, 3};
        movimiento(b3);
    }
    @FXML
    public void clickC1(MouseEvent mouseEvent) {
        int[] c1 = {3, 1};
        movimiento(c1);
    }
    @FXML
    public void clickC2(MouseEvent mouseEvent) {
        int[] c2 = {3, 2};
        movimiento(c2);
    }
    @FXML
    public void clickC3(MouseEvent mouseEvent) {
        int[] c2 = {3, 2};
        movimiento(c2);
    }
}
