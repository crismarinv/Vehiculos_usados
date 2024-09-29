package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;

public class VehiculoDAO {

    // 1. Obtener todas las placas de los vehículos a la venta
    public List<String> obtenerTodasLasPlacas() {
        List<String> placas = new ArrayList<>();
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT placa FROM vehiculo";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return placas;
    }

    // 2. Obtener la información detallada de un vehículo dada la placa
    public Vehiculo obtenerVehiculoPorPlaca(String placa) {
        Vehiculo vehiculo = null;
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT * FROM vehiculo WHERE placa = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, placa);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                vehiculo = new Vehiculo(rs.getString("placa"), rs.getString("tipo"), 
                                        rs.getString("marca"), rs.getString("modelo"), 
                                        rs.getInt("ano"), rs.getInt("numero_ejes"), 
                                        rs.getDouble("cilindrada"), rs.getDouble("valor"));
            }

            rs.close();
            pstmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

   

    // 3. Ordenar la lista de vehículos por modelo, marca o año
    public List<Vehiculo> ordenarVehiculos(String criterio) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT * FROM vehiculo ORDER BY " + criterio;
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(rs.getString("placa"), rs.getString("tipo"), 
                                                 rs.getString("marca"), rs.getString("modelo"), 
                                                 rs.getInt("ano"), rs.getInt("numero_ejes"), 
                                                 rs.getDouble("cilindrada"), rs.getDouble("valor"));
                vehiculos.add(vehiculo);
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    // 4. Búsqueda de placas usando el modelo y el año
    public List<String> buscarPlacasPorModeloYAno(String modelo, int ano) {
        List<String> placas = new ArrayList<>();
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT placa FROM vehiculo WHERE modelo = ? AND ano = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, modelo);
            pstmt.setInt(2, ano);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                placas.add(rs.getString("placa"));
            }

            rs.close();
            pstmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return placas;
    }

    // 5. Eliminar vehículo por placa (Comprar un vehículo)
    public void eliminarVehiculoPorPlaca(String placa) {
        try {
            Connection conexion = Conexion.conectar();
            String sql = "DELETE FROM vehiculo WHERE placa = ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, placa);
            pstmt.executeUpdate();

            pstmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 6. Disminuir en un 10% el precio de los vehículos que tienen un valor mayor a una cantidad dada
    public void reducirPrecio(double cantidad) {
        try {
            Connection conexion = Conexion.conectar();
            String sql = "UPDATE vehiculo SET valor = valor * 0.9 WHERE valor > ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setDouble(1, cantidad);
            pstmt.executeUpdate();

            pstmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 7. Localizar el vehículo más antiguo
    public Vehiculo localizarVehiculoMasAntiguo() {
        Vehiculo vehiculo = null;
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT * FROM vehiculo ORDER BY ano ASC LIMIT 1";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                vehiculo = new Vehiculo(rs.getString("placa"), rs.getString("tipo"), 
                                        rs.getString("marca"), rs.getString("modelo"), 
                                        rs.getInt("ano"), rs.getInt("numero_ejes"), 
                                        rs.getDouble("cilindrada"), rs.getDouble("valor"));
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    // 8. Localizar el vehículo más potente (el de más cilindrada)
    public Vehiculo localizarVehiculoMasPotente() {
        Vehiculo vehiculo = null;
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT * FROM vehiculo ORDER BY cilindrada DESC LIMIT 1";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                vehiculo = new Vehiculo(rs.getString("placa"), rs.getString("tipo"), 
                                        rs.getString("marca"), rs.getString("modelo"), 
                                        rs.getInt("ano"), rs.getInt("numero_ejes"), 
                                        rs.getDouble("cilindrada"), rs.getDouble("valor"));
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    // 9. Localizar el vehículo más barato (el de menor precio)
    public Vehiculo localizarVehiculoMasBarato() {
        Vehiculo vehiculo = null;
        try {
            Connection conexion = Conexion.conectar();
            String sql = "SELECT * FROM vehiculo ORDER BY valor ASC LIMIT 1";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                vehiculo = new Vehiculo(rs.getString("placa"), rs.getString("tipo"), 
                                        rs.getString("marca"), rs.getString("modelo"), 
                                        rs.getInt("ano"), rs.getInt("numero_ejes"), 
                                        rs.getDouble("cilindrada"), rs.getDouble("valor"));
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehiculo;
    }
    public void agregarVehiculo(String placa, String tipo, String marca, String modelo, int ano, int numeroEjes, double cilindrada, double valor) throws SQLException {
        Connection conexion = Conexion.conectar();  // Conexión a la base de datos
        String sql = "INSERT INTO vehiculo (placa, tipo, marca, modelo, ano, numero_ejes, cilindrada, valor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            // Establecer los valores de cada parámetro
            pstmt.setString(1, placa);
            pstmt.setString(2, tipo);
            pstmt.setString(3, marca);
            pstmt.setString(4, modelo);
            pstmt.setInt(5, ano);
            pstmt.setInt(6, numeroEjes);
            pstmt.setDouble(7, cilindrada);
            pstmt.setDouble(8, valor);

            // Ejecutar la consulta de inserción
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al insertar el vehículo", e);
        } finally {
            // Cerrar la conexión a la base de datos
            if (conexion != null) {
                conexion.close();
            }
        }
    }
}