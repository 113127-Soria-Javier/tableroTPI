package models;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int[][] tablero = new int[5][5];
    private List<List<Object>> lala = new ArrayList<>();

    public Tablero() {
        iniciarEscaques();
        rellenerEscaques();
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }
    public void borrarPosibles(List<int[]> movimientosPosibles){
        for(int[] movimientoPosible : movimientosPosibles){
            tablero[movimientoPosible[1]][movimientoPosible[0]] -= 100;
        }
    }
    public int getContenidoEscaque(int[] posicion){
        return getTablero()[posicion[1]][posicion[0]];
    }
    public void setContenidoEscaque(int[] posicion, int contenido){
        getTablero()[posicion[1]][posicion[0]] = contenido;
    }
    public void movimiento(int[] posicion1, int[] posicion2){
        int contenido = getContenidoEscaque(posicion1);
        setContenidoEscaque(posicion2, contenido);
        setContenidoEscaque(posicion1, 0);
    }
    private void iniciarEscaques(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i == 0 || j == 0 || i == 4 || j == 4){
                    tablero[i][j] = 1;
                }
            }
        }
    }

    private void rellenerEscaques(){
        tablero[1][2] = 24;
        tablero[3][1] = 34;
        tablero[1][3] = 24;
        tablero[2][2] = 34;
    }

}

