package dao;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import config.Conexion;

public class PanelListaVehiculos extends JPanel {

    public PanelListaVehiculos() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Lista de Vehículos en Venta", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaVehiculos = new JList<>(modeloLista);

        cargarVehiculos(modeloLista); // Llamada al método para cargar los vehículos desde la base de datos

        JScrollPane scrollLista = new JScrollPane(listaVehiculos);
        add(scrollLista, BorderLayout.CENTER);
    }

    // Método para cargar vehículos desde la base de datos
    private void cargarVehiculos(DefaultListModel<String> modeloLista) {
        Connection conexion = Conexion.conectar();  // Conexión a la base de datos
        if (conexion != null) {
            try {
                Statement stmt = conexion.createStatement();
                String sql = "SELECT marca, modelo, ano FROM vehiculo";  // Ajusta esta consulta a la estructura de tu base de datos
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    int ano = rs.getInt("ano");

                    // Agregar el vehículo a la lista
                    modeloLista.addElement(marca + " " + modelo + " (" + ano + ")");
                }

                rs.close();
                stmt.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}