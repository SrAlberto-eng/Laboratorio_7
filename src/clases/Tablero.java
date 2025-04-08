package clases;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import interfaces.ManejarFichas;


public class Tablero implements ManejarFichas{
    private List<Ficha> fichas;
    private int filas;
    private int columnas;
    private Ficha[][] tablero;
    private int centroX;
    private int centroY;
    private int extremoIzquierdo;
    private int extremoDerecho;


    public Tablero(int filas, int columnas) {
        this.fichas = new ArrayList<>();
        this.columnas = columnas;
        this.filas = filas;
        centroX = filas / 2;
        centroY = columnas / 2;
        extremoIzquierdo = centroX;
        extremoDerecho = centroX;
        tablero = new Ficha[filas][columnas];
    }


    public void mostrarFichas(){
        for(Ficha ficha : fichas){
            System.out.println(ficha);
        }
    }



    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] != null)
                    System.out.print(tablero[i][j]);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }


    public boolean hayFichasDisponibles() {
        return !fichas.isEmpty();
    }

    private boolean dentroDeLimites(int fila, int col) {
        return fila >= 0 && fila < filas && col >= 0 && col < columnas;
    }



    //Manejar fichas
    @Override
    public void generarFichas(){
        for(int i = 0; i < 7; i++){
            for(int j = i; j < 7; j++){
                Ficha ficha = new Ficha(i, j);
                fichas.add(ficha);
            }
        }
    }


    @Override
    public void sortearFichas(){
        Collections.shuffle(fichas);
    }

    @Override
    public void repartirFichas(Jugador jugador){
        if (jugador == null) {
            throw new IllegalArgumentException("Jugador cannot be null");
        }

        if(fichas.size() < 7){
            throw new IllegalArgumentException("No hay suficientes fichas para repartir");
        }

        Collections.shuffle(fichas);

        jugador.setMano(new ArrayList<>(fichas.subList(0, 7)));
        fichas.subList(0, 7).clear();
    }

    public Ficha getFichaIzquierda(){
            if (!hayFichas())
                return null;

            if(tablero[centroX][extremoIzquierdo+1] != null)
                return tablero[centroX][extremoIzquierdo+1];
            else
                return null;
    }

    public Ficha getFichaDerecha(){
        if (!hayFichas())
            return null;

        if(tablero[centroX][extremoDerecho-1] != null)
            return tablero[centroX][extremoDerecho-1];
        else
            return null;
    }


    @Override
    public Ficha getFicha(){
        if (fichas.isEmpty()) {
            return null;
        }
        return fichas.remove(0);
    }


    @Override
    public boolean insertarFicha(Ficha ficha) {
        if (ficha == null) {
            throw new IllegalArgumentException("Ficha cannot be null");
        }

        if(dentroDeLimites(centroX, extremoIzquierdo) && dentroDeLimites(centroX, extremoDerecho)){

            if(!hayFichas()){

                tablero[centroX][centroY] = ficha;
                extremoIzquierdo--;
                extremoDerecho++;
                return true;

            }else{

                if(dentroDeLimites(centroX, extremoIzquierdo + 1) &&
                        tablero[centroX][extremoIzquierdo + 1] != null &&
                        ficha.embonaCon(tablero[centroX][extremoIzquierdo+1].getIzquierda())){

                    if(tablero[centroX][extremoIzquierdo+1].embonaConIzquierda(ficha.getDerecha())){
                        tablero[centroX][extremoIzquierdo] = ficha;
                        extremoIzquierdo--;
                        return true;
                    }
                    else{
                        ficha.invertirOrientacion();
                        return insertarFicha(ficha);
                    }

                }else if(dentroDeLimites(centroX, extremoDerecho - 1) &&
                        tablero[centroX][extremoDerecho - 1] != null &&
                        ficha.embonaCon(tablero[centroX][extremoDerecho-1].getDerecha())){

                    if(tablero[centroX][extremoDerecho-1].embonaConDerecha(ficha.getIzquierda())){
                        tablero[centroX][extremoDerecho] = ficha;
                        extremoDerecho++;
                        return true;
                    }else{
                        ficha.invertirOrientacion();
                        return insertarFicha(ficha);
                    }

                }else{
                    return false;
                }
            }
        }else
            return false;
    }


    @Override
    public boolean hayFichas(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                if(tablero[i][j] != null)
                    return true;
            }
        }
        return false;
    }


}
