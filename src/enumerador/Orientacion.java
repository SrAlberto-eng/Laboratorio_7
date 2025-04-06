package enumerador;

public enum Orientacion {
    CERO(0),
    NOVENTA(90),
    CIENTO_OCHENTA(180),
    MENOS_NOVENTA(-90),
    MENOS_CIENTO_OCHENTA(-180);

    private final int valor;

    Orientacion(int valor) {
        this.valor = valor;
    }
    public int getValor() {
        return valor;
    }
    public static Orientacion getOrientacion(int valor) {
        for (Orientacion orientacion : Orientacion.values()) {
            if (orientacion.getValor() == valor) {
                return orientacion;
            }
        }
        return null;
    }
}
