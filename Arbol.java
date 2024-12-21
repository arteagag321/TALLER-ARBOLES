import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Arbol {
    private Nodo raiz;
    private List<Nodo> nodos; // Lista de todos los nodos del árbol

    public Arbol() {
        this.raiz = null;
        this.nodos = new ArrayList<>();
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void anadirNodo(Nodo nuevoNodo, Nodo nodoPadre, String posicion) {
        if (nodoPadre == null) {
            raiz = nuevoNodo;
        } else {
            switch (posicion.toLowerCase()) {
                case "izquierda":
                    nodoPadre.setIzquierda(nuevoNodo);
                    break;
                case "centro":
                    nodoPadre.setCentro(nuevoNodo);
                    break;
                case "derecha":
                    nodoPadre.setDerecha(nuevoNodo);
                    break;
                default:
                    throw new IllegalArgumentException("Posición inválida: " + posicion);
            }
        }
        nodos.add(nuevoNodo); // Agregar el nodo a la lista de nodos
    }

    public void dibujarArbol(Graphics g, int x, int y, int xOffset, int yOffset) {
        dibujarNodo(g, raiz, x, y, xOffset, yOffset);
    }

    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int xOffset, int yOffset) {
        if (nodo == null) return;

        g.drawOval(x - 15, y - 15, 30, 30);
        g.drawString(nodo.getEtiqueta(), x - 10, y + 5);

        if (nodo.getIzquierda() != null) {
            int nuevoX = x - xOffset;
            int nuevoY = y + yOffset;
            g.drawLine(x, y, nuevoX, nuevoY);
            dibujarNodo(g, nodo.getIzquierda(), nuevoX, nuevoY, xOffset / 2, yOffset);
        }

        if (nodo.getCentro() != null) {
            int nuevoX = x;
            int nuevoY = y + yOffset;
            g.drawLine(x, y, nuevoX, nuevoY);
            dibujarNodo(g, nodo.getCentro(), nuevoX, nuevoY, xOffset / 2, yOffset);
        }

        if (nodo.getDerecha() != null) {
            int nuevoX = x + xOffset;
            int nuevoY = y + yOffset;
            g.drawLine(x, y, nuevoX, nuevoY);
            dibujarNodo(g, nodo.getDerecha(), nuevoX, nuevoY, xOffset / 2, yOffset);
        }
    }

    public String preorden() {
        StringBuilder resultado = new StringBuilder();
        preordenRecursivo(raiz, resultado);
        return resultado.toString();
    }

    private void preordenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo == null) return;
        resultado.append(nodo.getEtiqueta()).append(" ");
        preordenRecursivo(nodo.getIzquierda(), resultado);
        preordenRecursivo(nodo.getCentro(), resultado);
        preordenRecursivo(nodo.getDerecha(), resultado);
    }

    public String inorden() {
        StringBuilder resultado = new StringBuilder();
        inordenRecursivo(raiz, resultado);
        return resultado.toString();
    }

    private void inordenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo == null) return;
        inordenRecursivo(nodo.getIzquierda(), resultado);
        resultado.append(nodo.getEtiqueta()).append(" ");
        inordenRecursivo(nodo.getCentro(), resultado);
        inordenRecursivo(nodo.getDerecha(), resultado);
    }

    public String postorden() {
        StringBuilder resultado = new StringBuilder();
        postordenRecursivo(raiz, resultado);
        return resultado.toString();
    }

    private void postordenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo == null) return;
        postordenRecursivo(nodo.getIzquierda(), resultado);
        postordenRecursivo(nodo.getCentro(), resultado);
        postordenRecursivo(nodo.getDerecha(), resultado);
        resultado.append(nodo.getEtiqueta()).append(" ");
    }

    public int[][] generarMatrizAdyacencia() {
        int size = nodos.size();
        int[][] matriz = new int[size][size];

        for (int i = 0; i < size; i++) {
            Nodo nodo = nodos.get(i);

            if (nodo.getIzquierda() != null) {
                int j = nodos.indexOf(nodo.getIzquierda());
                matriz[i][j] = 1;
            }

            if (nodo.getCentro() != null) {
                int j = nodos.indexOf(nodo.getCentro());
                matriz[i][j] = 1;
            }

            if (nodo.getDerecha() != null) {
                int j = nodos.indexOf(nodo.getDerecha());
                matriz[i][j] = 1;
            }
        }

        return matriz;
    }
}
