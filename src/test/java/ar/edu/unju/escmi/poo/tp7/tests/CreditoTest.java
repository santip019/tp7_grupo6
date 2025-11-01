package ar.edu.unju.escmi.poo.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.TarjetaCredito;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Credito;
import ar.edu.unju.escmi.tp7.dominio.Detalle;
import ar.edu.unju.escmi.tp7.dominio.Producto;

class CreditoTest {
    private Cliente cliente;
    private TarjetaCredito tarjeta;
    private Factura factura;
    private Credito credito;
    private List<Detalle> detalles;

    @BeforeEach
    void setUp() {
        // Cliente
        cliente = new Cliente(12345678L, "Cliente Test", "Calle Test", "123456789");

        // Tarjeta con saldo suficiente para probar límites
        tarjeta = new TarjetaCredito(11111L, LocalDate.of(2030, 1, 1), cliente, 1600000);

		//se crean 2 productos y detalles 
        Producto p1 = new Producto(1L, "TV 55 pulgadas", 500000, "Argentina");
        Producto p2 = new Producto(2L, "Heladera No Frost", 400000, "Argentina");

        detalles = new ArrayList<>();
        // marcar los detalles como NO-Ahora30 para usar calcularTotal()
        detalles.add(new Detalle(1, 500000, p1, false));
        detalles.add(new Detalle(1, 400000, p2, false));

        factura = new Factura(LocalDate.now(), cliente);
        factura.setDetalles(detalles);

        // Crear crédito con monto igual al total de la factura
        double monto = factura.calcularTotal();
        credito = new Credito(tarjeta, factura, cliente, monto);
    }

    @Test
    void testMontoCreditoValido() {
        // verificar que el monto del crédito no supere 1.500.000
        double montoCredito = credito.getMontoCredito();
        assertEquals(true, montoCredito <= 1500000,
            "El monto del crédito no debe superar $1.500.000");
    }

    @Test
    void testSumaImportesDetallesIgualTotalFactura() {
        // calcular la suma de los importes de los detalles
        double sumaDetalles = 0;
        for (Detalle detalle : detalles) {
            sumaDetalles += detalle.getImporte();
        }
        
        // verificar que la suma de los detalles sea igual al total de la factura
        assertEquals(sumaDetalles, factura.calcularTotal(),
            "La suma de los importes de los detalles debe ser igual al total de la factura");
    }

    @Test
    void testMontoCompraNoSuperaLimiteYSaldoTarjeta() {
        double totalCompra = factura.calcularTotal();
        
        // Verificar que el total de la compra no supere $1.500.000
        assertEquals(true, totalCompra <= 1500000,
            "El total de la compra no debe superar $1.500.000");
            
        // Verificar que el total no supere el saldo disponible en la tarjeta
        assertEquals(true, tarjeta.tieneSaldoSuficiente(totalCompra),
            "El total de la compra no debe superar el saldo disponible en la tarjeta");
    }

}
