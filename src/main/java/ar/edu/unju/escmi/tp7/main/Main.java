package ar.edu.unju.escmi.tp7.main;

import java.time.LocalDate;
import java.util.Scanner;

import ar.edu.unju.escmi.tp7.collections.CollectionCliente;
import ar.edu.unju.escmi.tp7.collections.CollectionProducto;
import ar.edu.unju.escmi.tp7.collections.CollectionStock;
import ar.edu.unju.escmi.tp7.collections.CollectionTarjetaCredito;
import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.Detalle;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Producto;
import ar.edu.unju.escmi.tp7.dominio.Stock;
import ar.edu.unju.escmi.tp7.dominio.TarjetaCredito;
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
                    /* Se identifica al cliente */
                    System.out.println("Ingrese el DNI del cliente: ");
                    long dniCliente = scanner.nextLong();
                    Cliente cliente = CollectionCliente.buscarCliente(dniCliente);
                    if (cliente == null) {
                        System.out.println("Cliente no encontrado, ¿desea registrarlo? (SI/NO)");
                        String op = scanner.nextLine();
                        if (op.equalsIgnoreCase("SI")) {

                            System.out.println("Ingrese nombre: ");
                            String nombreCliente = scanner.nextLine();
                            
                            System.out.println("Ingrese direcion: ");
                            String direccionCliente = scanner.nextLine();
                            
                            System.out.println("Ingrese telefono: ");
                            String telefonoCliente = scanner.nextLine();
                            
                            cliente = new Cliente(dniCliente, nombreCliente, direccionCliente, telefonoCliente);
                            CollectionCliente.agregarCliente(cliente);
                            System.out.println("Cliente registrado con exito, vuelva a intentar la compra");
                        }
                        else {
                            System.out.println("No se puede continuar con la venta sin un cliente registrado");
                        }
                    }
                    else {
                        /* Empieza la compra */
                        LocalDate fecha = LocalDate.now();
                        Factura factura = new Factura(fecha, cliente);
                        boolean seguirComprando = true;
                        while (seguirComprando) {
                            /* Se seleccionan los productos */
                            System.out.print("Ingrese el código del producto: ");
                            int codigoProductoVenta = scanner.nextInt();

                            Producto producto = CollectionProducto.buscarProducto(codigoProductoVenta);
                            if (producto == null) {
                                System.out.println("Producto no encontrado.");
                                continue;
                            }

                            System.out.print("Ingrese la cantidad: ");
                            int cantidadProducto = scanner.nextInt();
                            scanner.nextLine(); // Para limpiar el buffer del scanner

                            Stock stock = CollectionStock.buscarStock(producto);

                            if (stock.validarStockDisponible(cantidadProducto) == false) {
                                System.out.println("Stock insuficiente. Stock disponible: " + stock.getCantidad());
                            }
                            else {
                                
                                double precioTotal = producto.getPrecioUnitario() * cantidadProducto;
                                
                                if (CollectionProducto.buscarProductoAhora30(codigoProductoVenta)) {
                                    System.out.println("El producto es elegible para el programa 'Ahora 30'");
                                    System.out.println("Ingrese numero de tarjeta de credito: ");
                                    long numeroTarjeta = scanner.nextLong();
                                    TarjetaCredito tarjetaCredito = CollectionTarjetaCredito.buscarTarjetaCredito(numeroTarjeta);
                                    if (tarjetaCredito == null){
                                        System.out.println("Tarjeta de credito no encontrada, ¿Desea agregar agregar la tarjeta de credito? (SI/NO)");
                                        String op = scanner.nextLine();
                                        if (op.equalsIgnoreCase("SI")) {
                                            System.out.println("Ingrese fecha de caducacion: (AAAA-MM-DD)");
                                            String fechaCaducacion = scanner.nextLine();
                                            System.out.println("Ingrese el limite de compra: ");
                                            double limiteCompra = scanner.nextDouble();
                                            tarjetaCredito = new TarjetaCredito(numeroTarjeta, LocalDate.parse(fechaCaducacion), cliente, limiteCompra);
                                            CollectionTarjetaCredito.agregarTarjetaCredito(tarjetaCredito);
                                            System.out.println("Tarjeta de credito agregada con exito, vuelva a intentar la compra");
                                        }
                                        else {
                                            System.out.println("No se puede continuar con la venta del producto sin una tarjeta de credito registrada");
                                        }
                                    }
                                    else {
                                        if (tarjetaCredito.tieneSaldoSuficiente(precioTotal)){
                                            System.out.println("La compra sigue con los requisitos del programa 'Ahora 30'");
                                            // Crear detalle
                                            Detalle detalle = new Detalle(cantidadProducto, precioTotal, producto);
                                            factura.agregarDetalle(detalle);
                                            System.out.println("Detalle agregado con exito ");
                                            // Actualizar stock
                                            stock.actualizarStock(cantidadProducto);

                                        }
                                        else {
                                            System.out.println("Saldo insuficiente en la tarjeta, no cumple con los requisitos para el programa 'Ahora 30'");
                                        }
                                    }
                                    
                                }

                                /* Preguntar si sigue comprando */
                                System.out.print("¿Desea agregar otro producto? (s/n): ");
                                String op = scanner.nextLine();
                                seguirComprando = op.equalsIgnoreCase("s");
                        }
                        break;
                    }
                }
                    
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
