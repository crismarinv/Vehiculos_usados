package dao;

import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import config.Conexion;

public class Main {
    public static void main(String[] args) {
        VehiculoDAO dao = new VehiculoDAO();
        Scanner scanner = new Scanner(System.in);

        // Establecer conexión a la base de datos
        Connection conexion = Conexion.conectar();

        // Verificar si la conexión fue exitosa
        if (conexion != null) {
            System.out.println("La conexión fue establecida con éxito.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        System.out.println("Bienvenido al sistema de venta de vehículos usados.");

        while (true) {
            System.out.println("Elige una opción:");
            System.out.println("1. Obtener lista de placas");
            System.out.println("2. Agregar vehículo");
            System.out.println("3. Eliminar vehículo por placa");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            try {
                switch (opcion) {
                    case 1:
                        List<String> placas = dao.obtenerTodasLasPlacas();
                        System.out.println("Placas en venta:");
                        for (String placa : placas) {
                            System.out.println(placa);
                        }
                        break;

                    case 2:
                        // Aquí puedes pedir detalles del vehículo e invocar agregarVehiculo()
                        break;

                    case 3:
                        System.out.println("Introduce la placa a eliminar:");
                        String placa = scanner.nextLine();
                        dao.eliminarVehiculoPorPlaca(placa);
                        System.out.println("Vehículo eliminado.");
                        break;

                    case 4:
                        System.out.println("Saliendo...");
                        return;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                // Capturar cualquier excepción que ocurra en el bloque de operaciones
                e.printStackTrace();
            }
        }
    }
}