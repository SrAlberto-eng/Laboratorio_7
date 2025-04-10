package clases;
import enumerador.Orientacion;
import interfaces.Embonable;

public class Ficha implements Embonable{
    private int valorIzquierda;
    private int valorDerecha;
    private Orientacion orientacion;


    public Ficha(int valorIzquierda, int valorDerecha){
        this.valorIzquierda = valorIzquierda;
        this.valorDerecha = valorDerecha;
        this.orientacion = Orientacion.HORIZONTAL;
    }

    public int getIzquierda() {
        return valorIzquierda;
    }

    public int getDerecha() {
        return valorDerecha;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public boolean esMula(){
        return valorIzquierda == valorDerecha;
    }


    public String toString() {
        return switch (orientacion) {
            case HORIZONTAL ->"[" + valorIzquierda + " | " + valorDerecha + "]";
            case HORIZONTAL_INVERTIDO ->"[" + valorIzquierda + " | " + valorDerecha + "]";
        };
    }


    @Override
    public boolean embonaCon(int valor) {
        return valorIzquierda == valor || valorDerecha == valor;
    }

    @Override
    public boolean embonaConIzquierda(int valor){
        return valorIzquierda == valor;
    }

    @Override
    public boolean embonaConDerecha(int valor){
        return valorDerecha == valor;
    }

    @Override
    public void invertirOrientacion() {
        if (orientacion == Orientacion.HORIZONTAL) {
            orientacion = Orientacion.HORIZONTAL_INVERTIDO;
        } else {
            orientacion = Orientacion.HORIZONTAL;
        }
        int temp = valorIzquierda;
        valorIzquierda = valorDerecha;
        valorDerecha = temp;
    }


}

