package interfaces;
import clases.Ficha;
import clases.Jugador;

public interface ManejarFichas {
    public void generarFichas();
    public void sortearFichas();
    public void repartirFichas(Jugador jugador);
    public Ficha getFicha();
    public boolean hayFichas();
    public boolean insertarFicha(Ficha ficha);
}
