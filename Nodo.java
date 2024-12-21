public class Nodo {
    private String etiqueta;
    private Nodo izquierda;
    private Nodo centro;
    private Nodo derecha;

    public Nodo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.izquierda = null;
        this.centro = null;
        this.derecha = null;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getCentro() {
        return centro;
    }

    public void setCentro(Nodo centro) {
        this.centro = centro;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}
