import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArbolGraficoGUI {
    private JPanel panel1; // Panel principal
    private JButton matrizDeAdyacenciaButton; // Botón para generar matriz
    private JTable table1; // Tabla para mostrar matriz de adyacencia
    private JButton graficarElÁrbolButton; // Botón para graficar el árbol
    private JButton volverALaGUIButton; // Botón para volver a la GUI principal
    private JPanel panelGrafico; // Panel para el gráfico del árbol
    private Arbol arbol; // Objeto del árbol

    public ArbolGraficoGUI(Arbol arbol) {
        this.arbol = arbol;

        // Acción para generar la matriz de adyacencia
        matrizDeAdyacenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarMatrizAdyacencia();
            }
        });

        // Acción para graficar el árbol
        graficarElÁrbolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graficarArbol();
            }
        });

        // Acción para volver a la GUI principal
        volverALaGUIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverALaGUI();
            }
        });
    }

    // Método para generar la matriz de adyacencia
    private void generarMatrizAdyacencia() {
        if (arbol == null || arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(panel1, "El árbol está vacío. Por favor, agrega nodos.");
            return;
        }

        int[][] matriz = arbol.generarMatrizAdyacencia();
        String[] columnas = new String[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            columnas[i] = "Nodo " + i;
        }

        Object[][] datos = new Object[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                datos[i][j] = matriz[i][j];
            }
        }

        table1.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    // Método para graficar el árbol
    private void graficarArbol() {
        if (arbol == null || arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(panel1, "No hay nodos en el árbol para graficar.");
            return;
        }

        JPanel dibujoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (arbol.getRaiz() != null) {
                    arbol.dibujarArbol(g, getWidth() / 2, 50, getWidth() / 4, 50);
                }
            }
        };

        dibujoPanel.setPreferredSize(new Dimension(600, 400));
        panelGrafico.removeAll();
        panelGrafico.setLayout(new BorderLayout());
        panelGrafico.add(dibujoPanel, BorderLayout.CENTER);
        panelGrafico.revalidate();
        panelGrafico.repaint();
    }

    // Método para volver a la GUI principal
    private void volverALaGUI() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
        if (frame != null) {
            frame.dispose();
        }
    }

    // Método para obtener el panel principal
    public JPanel getPanel() {
        return panel1;
    }

    // Método principal para probar esta clase
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gráfico del Árbol");
        Arbol arbol = new Arbol(); // Crea un árbol para pruebas
        ArbolGraficoGUI gui = new ArbolGraficoGUI(arbol);

        frame.setContentPane(gui.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
