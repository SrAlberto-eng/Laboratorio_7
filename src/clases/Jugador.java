package clases;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Ficha> mano;

    Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<Ficha>();
    }

    public void agregarFicha(Ficha ficha){
        mano.add(ficha);
    }

    public void ponerFicha(Ficha ficha){
        mano.remove(ficha);
    }

}
