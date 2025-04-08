import clases.Ficha;
import clases.Jugador;
import clases.Tablero;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero(30, 30);
        Ficha ficha;
        Jugador jugador1 = new Jugador("Jugador 1");
        //Jugador jugador2 = new Jugador("Jugador 2");

        tablero.generarFichas();
        tablero.sortearFichas();
        //tablero.mostrarFichas();

        tablero.repartirFichas(jugador1);
        // tablero.repartirFichas(jugador2);

        while (jugador1.getMano().size() > 0) {

            if (!tablero.hayFichasDisponibles())
                break;

            if (jugador1.tengoEmbonable(tablero)) {
                System.out.println("Jugador 1");
                jugador1.mostrarMano();

                System.out.print("Ingrese la posicion de la ficha a repartir: ");
                int posicion = sc.nextInt();

                ficha = jugador1.ponerFicha(posicion);

                if (!tablero.insertarFicha(ficha)) {
                    System.out.println("No se puede poner esta ficha");
                    jugador1.comerFicha(ficha);
                }
            } else {
                System.out.println("Jugador 1 no puede poner fichas, come 1 ficha");
                ficha = tablero.getFicha();
                jugador1.comerFicha(ficha);
            }

            if (!jugador1.tengoFichas()) {
                System.out.println("Jugador 1 ya no tiene fichas");
                break;
            }

            tablero.imprimirTablero();
        }
    }
}
