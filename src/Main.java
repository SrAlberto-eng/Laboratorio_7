import clases.JuegoDomino;
import clases.Jugador;
import clases.Tablero;

public class Main {

    public static void main(String[] args) {

        Tablero tablero = new Tablero(40, 40);
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");
        JuegoDomino juego = new JuegoDomino(tablero, jugador1, jugador2);

        //SE GENERAN LAS FICHAS DE DOMINO
        tablero.generarFichas();

        //SE MEZCLAN ALEATORIAMENRTE LAS FICHAS
        tablero.sortearFichas();

        //EL MANEJADOR DE FICHAS DEL TABLERO REPARTE LAS FICHAS A CADA JUGADOR
        tablero.repartirFichas(jugador1);
        tablero.repartirFichas(jugador2);

        //SE ELIGE QUIEN COMIENZA EN BASE A QUIEN TIENE LA FICHA DOBLE MAS GRANDE.
        juego.primerTurno(jugador1, jugador2);

        while(true){

            System.out.println("\n\n" +juego+"\n");
            System.out.println("Turno de "+ juego.getActivo().getNombre());
            juego.hacerJugada();

            if(!tablero.hayFichasDisponibles() && !juego.getActivo().tengoEmbonable(tablero)){
                System.out.println("Ya no hay fichas ni movimientos posibles.");
                Jugador winner = juego.determinarVencedor();
                System.out.println(winner.getNombre()+ "gana por puntos!");
                break;
            } else if(juego.manoLimpia()){
                Jugador winner = juego.determinarVencedor();
                if(winner != null) {
                    System.out.println("\n" + winner.getNombre() + " Gana!");
                    System.out.println(juego);
                }else{
					System.out.println("\n\n" +juego+"\n");
                    System.out.println("Empate!");
                }
                break;
            }

            juego.pasarTurno();
        }
    }
}
