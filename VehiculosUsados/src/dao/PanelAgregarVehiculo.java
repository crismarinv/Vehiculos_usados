package dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.VehiculoDAO;

public class PanelAgregarVehiculo extends JPanel {

    private JTextField campoPlaca;
    private JTextField campoTipo;
    private JTextField campoMarca;
    private JTextField campoModelo;
    private JTextField campoAno;
    private JTextField campoNumeroEjes;
    private JTextField campoCilindrada;
    private JTextField campoValor;
    private VehiculoDAO vehiculoDAO;

    public PanelAgregarVehiculo(VehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
        setLayout(new GridLayout(9, 2, 10, 10));  // 9 filas, 2 columnas

        // Campos de texto y etiquetas
        add(new JLabel("Placa:"));
        campoPlaca = new JTextField(20);
        add(campoPlaca);

        add(new JLabel("Tipo:"));
        campoTipo = new JTextField(20);
        add(campoTipo);

        add(new JLabel("Marca:"));
        campoMarca = new JTextField(20);
        add(campoMarca);

        add(new JLabel("Modelo:"));
        campoModelo = new JTextField(20);
        add(campoModelo);

        add(new JLabel("Año:"));
        campoAno = new JTextField(20);
        add(campoAno);

        add(new JLabel("Número de ejes:"));
        campoNumeroEjes = new JTextField(20);
        add(campoNumeroEjes);

        add(new JLabel("Cilindrada:"));
        campoCilindrada = new JTextField(20);
        add(campoCilindrada);

        add(new JLabel("Valor:"));
        campoValor = new JTextField(20);
        add(campoValor);

        // Botón para agregar el vehículo
        JButton btnAgregar = new JButton("Agregar Vehículo");
        add(btnAgregar);

        // Acción del botón para agregar el vehículo
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });
    }

    private void agregarVehiculo() {
        // Obtener los datos de los campos
        String placa = campoPlaca.getText();
        String tipo = campoTipo.getText();
        String marca = campoMarca.getText();
        String modelo = campoModelo.getText();
        int ano = Integer.parseInt(campoAno.getText());
        int numeroEjes = Integer.parseInt(campoNumeroEjes.getText());
        double cilindrada = Double.parseDouble(campoCilindrada.getText());
        double valor = Double.parseDouble(campoValor.getText());

        // Agregar el vehículo usando VehiculoDAO
        try {
            vehiculoDAO.agregarVehiculo(placa, tipo, marca, modelo, ano, numeroEjes, cilindrada, valor);
            JOptionPane.showMessageDialog(this, "Vehículo agregado con éxito.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpiar los campos después de agregar un vehículo
    private void limpiarCampos() {
        campoPlaca.setText("");
        campoTipo.setText("");
        campoMarca.setText("");
        campoModelo.setText("");
        campoAno.setText("");
        campoNumeroEjes.setText("");
        campoCilindrada.setText("");
        campoValor.setText("");
    }
}