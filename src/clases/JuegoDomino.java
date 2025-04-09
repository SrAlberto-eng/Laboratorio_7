package clases;

import interfaces.ManejarPuntaje;
import interfaces.ManejarTurno;
import clases.Jugador;
import clases.Ficha;
import clases.Tablero;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.List;

public class JuegoDomino implements ManejarTurno, ManejarPuntaje {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private Jugador jugadorActivo;
    private int index;
    private Scanner sc = new Scanner(System.in);

    public JuegoDomino(Tablero tablero, Jugador jugador1, Jugador jugador2) {
        this.tablero = tablero;
        this.jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        index = 0;
    }

    public String toString() {
        return String.format("%s Score: %d\n%s Score: %d", jugadores.get(0).getNombre(), jugadores.get(0).getScore(), jugadores.get(1).getNombre(), jugadores.get(1).getScore());
    }

    public Jugador getActivo() {
        return jugadorActivo;
    }

    public boolean manoLimpia() {
        return !jugadorActivo.tengoFichas();
    }

    //ADMINISTRADOR DE PUNTAJE

    public Jugador determinarVencedor(){
        int score1 = jugadores.get(0).getScore();
        int score2 = jugadores.get(1).getScore();

        if(score1 > score2)
            return jugadores.get(0);
        else
            return jugadores.get(1);
    }

	public int calcularPuntaje(long tiempoInicio){
		long tiempoFinal = System.currentTimeMillis();
		int tiempoTardado = (int) ((tiempoInicio - tiempoFinal) / 1000);

        if(tiempoTardado > 30)
            return 0;

		if(tiempoTardado > 20)
			return 20;
		else if(tiempoTardado > 10 && tiempoTardado <= 20)
			return 10;
		else
			return 5;
	}
	
	
    @Override
    public void aumentarPuntaje(int puntaje) {
		jugadorActivo.aumentarScore(puntaje);											
	}

    @Override
    public void disminuirPuntaje(int puntaje) {
		jugadorActivo.reducirScore(puntaje);
	}

    //ADMINISTRADOR DE TURNOS//

    @Override
    public void primerTurno(Jugador jugador1, Jugador jugador2) {

        int mulaJugador1 = jugador1.getMulaMasGrande();
        int mulaJugador2 = jugador2.getMulaMasGrande();

        if (mulaJugador1 > mulaJugador2) {
            jugadorActivo = jugador1;
        } else if (mulaJugador2 > mulaJugador1) {
            jugadorActivo = jugador2;
        } else {
            jugadorActivo = jugadores.get(0);
        }
    }


    @Override
    public void pasarTurno() {
        index = (index + 1) % jugadores.size();
        jugadorActivo = jugadores.get(index);

    }


    @Override
    public void hacerJugada() {


        if (!tablero.hayFichasDisponibles()) {
            System.out.println("Ya no hay fichas disponibles, se pasa turno");
            this.pasarTurno();
            return;
        }


        if (jugadorActivo.tengoEmbonable(tablero)) {
            tablero.imprimirTablero();

            System.out.println("" + jugadorActivo.getNombre());
            jugadorActivo.mostrarMano();
			
		long tiempoInicio = System.currentTimeMillis();

            boolean fichaInsertada = false;
            while (!fichaInsertada) {
                try {
                    System.out.print("Elegir ficha -> ");
                    int posicion = sc.nextInt();

                    Ficha ficha = jugadorActivo.ponerFicha(posicion);

                    if (tablero.insertarFicha(ficha)) {
                        fichaInsertada = true;

                        int puntos = calcularPuntaje(tiempoInicio);

                        if(puntos > 0) {
                            jugadorActivo.getMano().remove(posicion - 1);
                            jugadorActivo.aumentarScore(puntos);

                            System.out.println("Ficha insertada. Sumaste "+ puntos +" puntos");
                        }else{
                            System.out.println("Tardaste mas de 30 segundos! Se te restan 5 puntos.");
                            jugadorActivo.reducirScore(5);
                        }
                    } else {
                        System.out.println("La ficha no embona, intenta con otra ficha.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Ingresa un número válido.");
                    sc.nextLine();
                }
            }
        } else {
            System.out.println("No tienes fichas embonables.");
            System.out.println("Come 1 ficha y pasa turno.");

            Ficha ficha = tablero.getFicha();
            jugadorActivo.comerFicha(ficha);
        }

        this.pasarTurno();
    }
}
