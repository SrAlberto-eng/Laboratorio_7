package interfaces;
import clases.Jugador;

public interface ManejarPuntaje {
    int calcularPuntaje(long tiempoInicio);
    Jugador determinarVencedor();
}
