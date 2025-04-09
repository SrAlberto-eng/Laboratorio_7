import clases.Ficha;
import clases.JuegoDomino;
import clases.Jugador;
import clases.Tablero;
import clases.JuegoDomino;

public class Main {

    public static void main(String[] args) {

        Tablero tablero = new Tablero(30, 30);
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");
        JuegoDomino juego = new JuegoDomino(tablero, jugador1, jugador2);

        //SE GENERAN LAS FICHAS DE DOMINO
        tablero.generarFichas();

        //SE MEZCLAN ALEATORIAMENRTE LAS FICHAS
        tablero.sortearFichas();

        //tablero.mostrarFichas();

        //EL MANEJADOR DE FICHAS DEL TABLERO REPARTE LAS FICHAS A CADA JUGADOR
        tablero.repartirFichas(jugador1);
        tablero.repartirFichas(jugador2);

        //SE ELIGE QUIEN COMIENZA EN BASE A QUIEN TIENE LA FICHA DOBLE MAS GRANDE.
        juego.primerTurno(jugador1, jugador2);

        while(true){

            if(!tablero.hayFichasDisponibles() && juego.manoLimpia()){
                System.out.println("Ya no hay fichas ni movimientos posibles.");
                Jugador winner = juego.determinarVencedor();
                System.out.println(winner.getNombre()+ "gana por puntos!");
                break;
            }
            //SE DETERMINA SI HAY GANADOR SI UN JUGADOR SE QUEDO SIN FICHAS.
            else if(juego.manoLimpia()){
                Jugador winner = juego.determinarVencedor();
                System.out.println("\n"+winner.getNombre()+" Gana!");
                System.out.println(juego);
                break;
            }
            System.out.println("\n\n" +juego+"\n");
            System.out.println("Turno de "+ juego.getActivo().getNombre());
            juego.hacerJugada();
        }
    }
}
