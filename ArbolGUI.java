import javax.swing.*;

public class ArbolGUI {
    private JPanel panel1; // Panel principal
    private JButton limpiarButton; // Botón para limpiar campos
    private JButton verGraficoButton; // Botón para ver gráfico
    private JTextField etiquetaNodoField; // Campo para la etiqueta del nodo
    private JTextField nodoPadreField; // Campo para el nodo padre
    private JComboBox<String> posicionComboBox; // ComboBox para la posición
    private JTextArea textArea1; // Área de texto para mensajes
    private JButton recorrerPreordenButton; // Botón para recorrer en preorden
    private JButton recorrerInordenButton; // Botón para recorrer en inorden
    private JButton recorrerPostordenButton; // Botón para recorrer en postorden
    private JButton salirButton; // Botón para salir
    private JButton agregarButton; // Botón para agregar nodo
    private Arbol arbol; // Objeto del árbol

    public ArbolGUI() {
        arbol = new Arbol();

        // Configurar el ComboBox con las posiciones posibles
        posicionComboBox.addItem("Izquierda");
        posicionComboBox.addItem("Centro");
        posicionComboBox.addItem("Derecha");

        // Listeners de botones
        limpiarButton.addActionListener(e -> limpiarCampos());
        verGraficoButton.addActionListener(e -> verGrafico());
        recorrerPreordenButton.addActionListener(e -> recorrerPreorden());
        recorrerInordenButton.addActionListener(e -> recorrerInorden());
        recorrerPostordenButton.addActionListener(e -> recorrerPostorden());
        salirButton.addActionListener(e -> salirAplicacion());
        agregarButton.addActionListener(e -> agregarNodo());
    }

    // Método para agregar un nodo
    private void agregarNodo() {
        try {
            String etiqueta = etiquetaNodoField.getText().trim();
            String etiquetaPadre = nodoPadreField.getText().trim();
            String posicion = (String) posicionComboBox.getSelectedItem(); // Obtener la posición seleccionada

            if (etiqueta.isEmpty()) {
                textArea1.append("La etiqueta del nodo no puede estar vacía.\n");
                return;
            }

            Nodo nuevoNodo = new Nodo(etiqueta);
            Nodo nodoPadre = null;

            // Buscar el nodo padre
            for (Nodo nodo : arbol.getNodos()) {
                if (nodo.getEtiqueta().equals(etiquetaPadre)) {
                    nodoPadre = nodo;
                    break;
                }
            }

            // Agregar el nodo al árbol
            arbol.anadirNodo(nuevoNodo, nodoPadre, posicion);
            textArea1.append("Nodo agregado correctamente.\n");
        } catch (Exception ex) {
            textArea1.append("Error al agregar nodo: " + ex.getMessage() + "\n");
        }
    }

    // Método para mostrar el gráfico del árbol
    private void verGrafico() {
        JFrame frame = new JFrame("Gráfico del Árbol");
        ArbolGraficoGUI grafico = new ArbolGraficoGUI(arbol);
        frame.setContentPane(grafico.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // Métodos para los recorridos
    private void recorrerPreorden() {
        textArea1.append("Recorrido en preorden: " + arbol.preorden() + "\n");
    }

    private void recorrerInorden() {
        textArea1.append("Recorrido en inorden: " + arbol.inorden() + "\n");
    }

    private void recorrerPostorden() {
        textArea1.append("Recorrido en postorden: " + arbol.postorden() + "\n");
    }

    // Método para limpiar campos
    private void limpiarCampos() {
        etiquetaNodoField.setText("");
        nodoPadreField.setText("");
        posicionComboBox.setSelectedIndex(0); // Reiniciar el ComboBox
        textArea1.setText("");
    }

    // Método para salir de la aplicación
    private void salirAplicacion() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
        if (frame != null) {
            frame.dispose();
        }
    }

    // Método para obtener el panel principal
    public JPanel getPanel() {
        return panel1;
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Árbol");
        frame.setContentPane(new ArbolGUI().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
