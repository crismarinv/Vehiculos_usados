package dao;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dao.VehiculoDAO;

public class InterfazGraficaPrincipal extends JFrame {

    private VehiculoDAO vehiculoDAO;
    private JTextArea areaTexto;
    private JTextArea areaHistorial;
    

    public InterfazGraficaPrincipal() {
        vehiculoDAO = new VehiculoDAO();
        setTitle("Sistema de Venta de Vehículos Usados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inicializarComponentes();
        pack();
        setMinimumSize(new Dimension(800, 400));
        setVisible(true);
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(5, 2));

        // Cargar los íconos desde la carpeta resources/icons/
        JButton btnListarPlacas = crearBotonConIcono("Listar Placas", "/icons/car-2.png");
        JButton btnInfoVehiculo = crearBotonConIcono("Info Vehículo", "/icons/car.png");
        JButton btnOrdenarVehiculos = crearBotonConIcono("Ordenar Vehículos", "/icons/automatic.png");
        JButton btnBuscarPlacas = crearBotonConIcono("Buscar Placas", "/icons/magnifying-glass.png");
        JButton btnEliminarVehiculo = crearBotonConIcono("Eliminar Vehículo", "/icons/delete.png");
        JButton btnReducirPrecio = crearBotonConIcono("Reducir Precio", "/icons/price-tag.png");
        JButton btnVehiculoAntiguo = crearBotonConIcono("Vehículo Más Antiguo", "/icons/old-car.png");
        JButton btnVehiculoPotente = crearBotonConIcono("Vehículo Más Potente", "/icons/cars.png");  // Ícono correcto
        JButton btnVehiculoBarato = crearBotonConIcono("Vehículo Más Barato", "/icons/cheap.png");
        JButton btnAgregarVehiculo = crearBotonConIcono("Agregar Vehículo", "/icons/add.png");
        
        // Añadir botones al panel superior
        panelSuperior.add(btnListarPlacas);
        panelSuperior.add(btnInfoVehiculo);
        panelSuperior.add(btnAgregarVehiculo);
        panelSuperior.add(btnOrdenarVehiculos);
        panelSuperior.add(btnBuscarPlacas);
        panelSuperior.add(btnEliminarVehiculo);
        panelSuperior.add(btnReducirPrecio);
        panelSuperior.add(btnVehiculoAntiguo);
        panelSuperior.add(btnVehiculoPotente);  // Añadir el botón de "Vehículo Más Potente"
        panelSuperior.add(btnVehiculoBarato);

        // Área de texto central para mostrar información
        areaTexto = new JTextArea();
        JScrollPane scrollArea = new JScrollPane(areaTexto);

        // Añadir los componentes al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(scrollArea, BorderLayout.CENTER);

        add(panelPrincipal);

        // Asignar las acciones a los botones
        btnListarPlacas.addActionListener(e -> listarPlacas());
        btnInfoVehiculo.addActionListener(e -> obtenerInfoVehiculo());
        btnAgregarVehiculo.addActionListener(e -> agregarVehiculo());
        btnOrdenarVehiculos.addActionListener(e -> ordenarVehiculos());
        btnBuscarPlacas.addActionListener(e -> buscarPlacas());
        btnEliminarVehiculo.addActionListener(e -> eliminarVehiculo());
        btnReducirPrecio.addActionListener(e -> reducirPrecio());
        btnVehiculoAntiguo.addActionListener(e -> localizarVehiculoAntiguo());
        btnVehiculoPotente.addActionListener(e -> localizarVehiculoPotente());  // Acción del botón "Vehículo Más Potente"
        btnVehiculoBarato.addActionListener(e -> localizarVehiculoBarato());
    }

    private JButton crearBotonConIcono(String texto, String rutaIcono) {
        // Cargar el ícono desde los recursos
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaIcono));
        
        // Redimensionar el ícono al tamaño deseado (por ejemplo, 32x32 píxeles)
        Image imagenIcono = iconoOriginal.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        
        // Crear un nuevo ImageIcon con la imagen redimensionada
        ImageIcon iconoRedimensionado = new ImageIcon(imagenIcono);
        
        // Crear el botón con el ícono redimensionado
        JButton boton = new JButton(texto, iconoRedimensionado);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);  // Posición del texto
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);    // Posición del ícono
        
        return boton;
    }
    private void agregarAlHistorial(String mensaje) {
        areaHistorial.append(mensaje + "\n");  // Añadir el mensaje al historial
    }

    // Métodos de acciones
    private void listarPlacas() {
        List<String> placas = vehiculoDAO.obtenerTodasLasPlacas();
        areaTexto.setText("Placas de vehículos en venta:\n");
        for (String placa : placas) {
            areaTexto.append(placa + "\n");
        }
    }

    private void obtenerInfoVehiculo() {
        String placa = JOptionPane.showInputDialog(this, "Introduce la placa del vehículo:");
        if (placa != null && !placa.isEmpty()) {
            Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorPlaca(placa);
            if (vehiculo != null) {
                areaTexto.setText("Información del vehículo:\n" + vehiculo.toString());
            } else {
                areaTexto.setText("No se encontró el vehículo con la placa: " + placa);
            }
        }
    }

    private void agregarVehiculo() {
        try {
            String placa = JOptionPane.showInputDialog(this, "Introduce la placa:");
            String tipo = JOptionPane.showInputDialog(this, "Introduce el tipo:");
            String marca = JOptionPane.showInputDialog(this, "Introduce la marca:");
            String modelo = JOptionPane.showInputDialog(this, "Introduce el modelo:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce el año:"));
            int numeroEjes = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce el número de ejes:"));
            double cilindrada = Double.parseDouble(JOptionPane.showInputDialog(this, "Introduce la cilindrada:"));
            double valor = Double.parseDouble(JOptionPane.showInputDialog(this, "Introduce el valor:"));
            vehiculoDAO.agregarVehiculo(placa, tipo, marca, modelo, ano, numeroEjes, cilindrada, valor);
            areaTexto.setText("Vehículo agregado exitosamente.");
        } catch (Exception e) {
            areaTexto.setText("Error al agregar el vehículo: " + e.getMessage());
        }
    }

    private void ordenarVehiculos() {
        String criterio = JOptionPane.showInputDialog(this, "Ordenar por (modelo, marca, ano):");
        List<Vehiculo> vehiculos = vehiculoDAO.ordenarVehiculos(criterio);
        areaTexto.setText("Vehículos ordenados por " + criterio + ":\n");
        for (Vehiculo vehiculo : vehiculos) {
            areaTexto.append(vehiculo.toString() + "\n");
        }
    }

    private void buscarPlacas() {
        String modelo = JOptionPane.showInputDialog(this, "Introduce el modelo:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog(this, "Introduce el año:"));
        List<String> placas = vehiculoDAO.buscarPlacasPorModeloYAno(modelo, ano);
        areaTexto.setText("Placas encontradas:\n");
        for (String placa : placas) {
            areaTexto.append(placa + "\n");
        }
    }

    private void eliminarVehiculo() {
        String placa = JOptionPane.showInputDialog(this, "Introduce la placa del vehículo a eliminar:");
        if (placa != null && !placa.isEmpty()) {
            vehiculoDAO.eliminarVehiculoPorPlaca(placa);
            areaTexto.setText("Vehículo con placa " + placa + " eliminado.");
        }
    }

    private void reducirPrecio() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(this, "Introduce la cantidad límite:"));
        vehiculoDAO.reducirPrecio(cantidad);
        areaTexto.setText("Precios reducidos en un 10% para los vehículos con valor mayor a " + cantidad);
    }

    private void localizarVehiculoAntiguo() {
        Vehiculo vehiculo = vehiculoDAO.localizarVehiculoMasAntiguo();
        areaTexto.setText("Vehículo más antiguo:\n" + vehiculo.toString());
    }

    private void localizarVehiculoPotente() {
        Vehiculo vehiculo = vehiculoDAO.localizarVehiculoMasPotente();
        areaTexto.setText("Vehículo más potente:\n" + vehiculo.toString());
    }

    private void localizarVehiculoBarato() {
        Vehiculo vehiculo = vehiculoDAO.localizarVehiculoMasBarato();
        areaTexto.setText("Vehículo más barato:\n" + vehiculo.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGraficaPrincipal());
    }
}