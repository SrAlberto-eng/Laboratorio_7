package interfaces;
import clases.Jugador;
public interface ManejarTurno {
    public void primerTurno(Jugador jugador1, Jugador jugador2);
    public void pasarTurno();
    public void hacerJugada();
}
