package interfaces;
import clases.Jugador;
public interface ManejarTurno {
    void primerTurno(Jugador jugador1, Jugador jugador2);
    void pasarTurno();
    void hacerJugada();
}
