package clases;
import interfaces.Embonable;
import enumerador.Orientacion;

public class Ficha implements Embonable{
    private int valorIzquierda;
    private int valorDerecha;
    private Orientacion orientacion;

    public Ficha(int valorIzquierda, int valorDerecha, Orientacion inicial){
        this.valorIzquierda = valorIzquierda;
        this.valorDerecha = valorDerecha;
        orientacion = inicial;
    }

    @Override
    public void rotarFicha(int anguloRotacion) {
        // Calcular la nueva orientación
        int nuevaOrientacion = (orientacion.getValor() + anguloRotacion) % 360;

        // Ajustar para valores negativos
        if (nuevaOrientacion < 0) {
            nuevaOrientacion += 360;
        }

        // Actualizar la orientación según el enumerador
        for (Orientacion o : Orientacion.values()) {
            if (o.getValor() == nuevaOrientacion) {
                this.orientacion = o;
                break;
            }
        }
    }

    @Override
    public boolean validarEmbonable(Ficha ficha){
        return true;
    }
}
