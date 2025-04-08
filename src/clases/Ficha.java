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
        if(valorIzquierda == valorDerecha)
            return true;
        return false;
    }

    private static final char[] UNICODE_DADOS = {
            '\u2680',
            '\u2681',
            '\u2682',
            '\u2683',
            '\u2684',
            '\u2685'};


    private String getUnicode(int value) {
        if (value >= 1 && value <= 6) {
            return String.valueOf(UNICODE_DADOS[value - 1]);
        } else {
            return "\u25A1";
        }
    }

    public String toString() {
        return switch (orientacion) {
            case HORIZONTAL -> "["+getUnicode(valorIzquierda) + "|" + getUnicode(valorDerecha)+"]";
            //case VERTICAL -> getUnicode(valorIzquierda) + "\n" + getUnicode(valorDerecha);
            case HORIZONTAL_INVERTIDO -> "["+getUnicode(valorIzquierda) + "|" + getUnicode(valorDerecha)+"]";
            //case VERTICAL_INVERTIDO -> getUnicode(valorDerecha) + "\n" + getUnicode(valorIzquierda);
            default -> String.format("[ %d | %d ]", valorIzquierda, valorDerecha);
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

