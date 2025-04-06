package clases;
import java.util.ArrayList;
import java.util.List;
import interfaces.DibujarFicha;
import interfaces.ManejarFichas;


public class Tablero {
    private List<Ficha> fichas;
    private List<Ficha> fichasDibujadas;
    private int filas;
    private int columnas;

    Tablero(int filas, int columnas) {
        this.fichas = new ArrayList<>();
        this.fichasDibujadas = new ArrayList<>();
        this.columnas = columnas;
        this.filas = filas;
    }
}
