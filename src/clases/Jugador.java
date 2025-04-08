package clases;
import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Ficha> mano;
    private int score;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<Ficha>();
        score = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getScore() {
        return score;
    }

    public void aumentarScore(int puntos){
        score += puntos;
    }

    public void comerFicha(Ficha ficha){
        mano.add(ficha);
    }

    public Ficha ponerFicha(int posicion){
        if(posicion < 1 || posicion > mano.size())
            return null;


       return mano.remove(posicion-1);
    }

    public void setMano(List<Ficha> mano){ this.mano = mano; }

    public List<Ficha> getMano(){ return mano; }

    public void mostrarMano(){
        int i = 0;
        for(Ficha ficha : mano){
            System.out.print("Ficha " + (++i) + ":");
            System.out.println(ficha);
        }
    }

    public boolean tengoFichas(){
        return !mano.isEmpty();
    }

    public boolean tengoEmbonable(Tablero tablero) {
        Ficha fichaIzquierda = tablero.getFichaIzquierda();
        Ficha fichaDerecha = tablero.getFichaDerecha();

        for (Ficha ficha : mano) {
            if(!tablero.hayFichas())
                return true;
            if ((fichaIzquierda != null && ficha.embonaCon(fichaIzquierda.getIzquierda())) ||
                    (fichaDerecha != null && ficha.embonaCon(fichaDerecha.getDerecha()))) {
                return true;
            }
        }
        return false;
    }

}
