package ar.edu.unju.escmi.tp7.main;

import java.util.Scanner;

import ar.edu.unju.escmi.tp7.collections.CollectionCliente;
import ar.edu.unju.escmi.tp7.collections.CollectionProducto;
import ar.edu.unju.escmi.tp7.collections.CollectionStock;
import ar.edu.unju.escmi.tp7.collections.CollectionTarjetaCredito;
import ar.edu.unju.escmi.tp7.collections.CollectionCredito;
import ar.edu.unju.escmi.tp7.collections.CollectionFactura;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CollectionTarjetaCredito.precargarTarjetas();
        CollectionCliente.precargarClientes();
        CollectionProducto.precargarProductos();
        CollectionStock.precargarStocks();
        int opcion = 0;
        do {
            System.out.println("\n====== Menu Principal =====");
            System.out.println("1- Realizar una venta");
            System.out.println("2- Revisar compras realizadas por el cliente (debe ingresar el DNI del cliente)");
            System.out.println("3- Mostrar lista de los electrodomésticos");
            System.out.println("4- Consultar stock");
            System.out.println("5- Revisar creditos de un cliente (debe ingresar el DNI del cliente)");
            System.out.println("6- Salir");

            System.out.println("Ingrese su opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Lógica para realizar una venta
                    break;
                case 2:
                    System.out.println("Ingrese el DNI del cliente a buscar sus compras: ");
                    long dniBuscarFactura = scanner.nextLong();
                    // Mostrar facturas (ventas) del cliente
                    CollectionFactura.buscarFacturasPorDni(dniBuscarFactura);
                    break;
                case 3:
                    // Lógica para mostrar lista de productos incluidos en el ahora 30
                    CollectionProducto.mostrarProductosAhora30();
                    break;
                case 4:
                    // Lógica para consultar stock
                    break;
                case 5:
                    System.out.println("Ingrese el DNI del cliente a buscar sus créditos: ");
                    long dni = scanner.nextLong();
                    CollectionCredito.buscarCreditoPorDni(dni);
                    break;

                default:
                    break;
            }

        } while (opcion != 6);
        scanner.close();

    }

}
