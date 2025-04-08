package clases;
import interfaces.ManejarPuntaje;
import interfaces.ManejarTurno;
import clases.Jugador;
import clases.Ficha;
import clases.Tablero;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class JuegoDomino implements ManejarTurno, ManejarPuntaje{

    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    
    JuegoDomino(Tablero tablero, Jugador jugador1, Jugador jugador2){
        this.tablero = tablero;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public String toString(){
        return String.format("%s Score: %d\n%s Score: %d",jugador1.getNombre(), jugador1.getScore(), jugador2.getNombre(), jugador2.getScore());
    }

    //ADMINISTRADOR DE PUNTAJE
    @Override
    public void aumentarPuntaje(){}

    @Override
    public void disminuirPuntaje(){}




    //ADMINISTRADOR DE TURNOS
    @Override
    public void iniciarTurno(){}

    @Override
    public void siguienteJugador(){}

    @Override
    public void iniciarTiempo(int segundos){}

    @Override
    public void terminarTiempo(){}

    @Override
    public void terminarJuego(){}

    @Override
    public void terminarTurno(){}
}
