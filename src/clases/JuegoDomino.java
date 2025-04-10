package clases;
import interfaces.ManejarPuntaje;
import interfaces.ManejarTurno;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class JuegoDomino implements ManejarTurno, ManejarPuntaje {
    private final Tablero tablero;
    private final List<Jugador> jugadores;
    private Jugador jugadorActivo;
    private int index;
    private final Scanner sc = new Scanner(System.in);

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
    @Override
    public Jugador determinarVencedor(){
        int score1 = jugadores.get(0).getScore();
        int score2 = jugadores.get(1).getScore();

        if(score1 == score2){
            return null;
        }
        else if(score1 > score2)
            return jugadores.get(0);
        else
            return jugadores.get(1);
    }

    @Override
    public int calcularPuntaje(long tiempoInicio){
        long tiempoFinal = System.currentTimeMillis();
        int tiempoTardado = (int) ((tiempoFinal - tiempoInicio) / 1000);

        System.out.println("Tardaste " + tiempoTardado + " segundos.");

        if(tiempoTardado <= 10)
            return 20;
        else if(tiempoTardado <= 20)
            return 10;
        else if(tiempoTardado <= 30)
            return 5;
        else
            return 0;
    }

    //ADMINISTRADOR DE TURNOS//
    @Override
    public void primerTurno(Jugador jugador1, Jugador jugador2) {

		System.out.println("Inicia quien tenga la ficha doble mas grande.");
		
        int mulaJugador1 = jugador1.getMulaMasGrande();
        int mulaJugador2 = jugador2.getMulaMasGrande();

        if (mulaJugador1 > mulaJugador2) {
            jugadorActivo = jugador1;
        } else if (mulaJugador2 > mulaJugador1) {
            jugadorActivo = jugador2;
        } else {
            jugadorActivo = jugadores.get(0);
        }

		System.out.println("Inicia el "+jugadorActivo.getNombre());
         index = jugadores.indexOf(jugadorActivo);
    }


    @Override
    public void pasarTurno() {
        index = (index + 1) % jugadores.size();
        jugadorActivo = jugadores.get(index);

    }


    @Override
    public void hacerJugada() {

        if (!tablero.hayFichasDisponibles() && !jugadorActivo.tengoEmbonable(tablero)) {
            System.out.println("No hay fichas disponibles ni movimientos válidos. Se termina el juego.");
            return;
        }



        if (jugadorActivo.tengoEmbonable(tablero)) {
            tablero.imprimirTablero();

            System.out.println(jugadorActivo.getNombre());
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

            if (!tablero.hayFichasDisponibles()) {
                System.out.println("No tienes fichas embonables.");
                System.out.println("Ya no hay fichas para comer, se pasa turno");
            }else {
                System.out.println("No tienes fichas embonables.");
                System.out.println("Come 1 ficha y pasa turno.");

                Ficha ficha = tablero.getFicha();
                jugadorActivo.comerFicha(ficha);
            }
        }
    }
}
