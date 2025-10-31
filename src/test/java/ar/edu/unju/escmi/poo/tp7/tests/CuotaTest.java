package ar.edu.unju.escmi.poo.tp7.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp7.dominio.Cliente;
import ar.edu.unju.escmi.tp7.dominio.Credito;
import ar.edu.unju.escmi.tp7.dominio.Cuota;
import ar.edu.unju.escmi.tp7.dominio.Detalle;
import ar.edu.unju.escmi.tp7.dominio.Factura;
import ar.edu.unju.escmi.tp7.dominio.Producto;
import ar.edu.unju.escmi.tp7.dominio.TarjetaCredito;

public class CuotaTest {

    private Credito credito;
    private Cliente cliente;
    private TarjetaCredito tarjeta;
    private Factura factura;
    
    @BeforeEach
    void setUp() {
        // Preparar datos de prueba
        cliente = new Cliente(12345678, "Cliente Test", "Direccion Test", "1234567890");
        // Crear tarjeta con límite de 2000000 (usando la constante LIMITE_TARJETAS de TarjetaCredito)
        tarjeta = new TarjetaCredito(123456789L, LocalDate.of(2026, 12, 31), cliente, 2000000.0);
        
        // Crear un producto de prueba (TV con precio 900000)
        Producto producto = new Producto();
        producto.setCodigo(1L);
        producto.setDescripcion("TV 55 pulgadas");
        producto.setPrecioUnitario(900000);
        producto.setOrigenFabricacion("Nacional");
        
        // Crear una factura con monto total de 900000 (dentro del límite permitido)
        List<Detalle> detalles = new ArrayList<>();
        Detalle detalle = new Detalle();
        detalle.setCantidad(1);
        detalle.setProducto(producto);
        detalle.setImporte(producto.getPrecioUnitario() * detalle.getCantidad());
        detalles.add(detalle);
        
        // Crear la factura
        factura = new Factura();
        factura.setFecha(LocalDate.now());
        factura.setNroFactura(1L);
        factura.setCliente(cliente);
        factura.setDetalles(detalles);
    }

    @Test
    void testListaCuotasNoNulaAlGenerarCuotas() {
        // 1. Preparar
        credito = new Credito(tarjeta, factura, new ArrayList<>(), cliente);
        
        // 2. Ejecutar
        credito.generarCuotas();
        
        // 3. Verificar
        assertNotNull(credito.getCuotas(), "La lista de cuotas no debe ser nula después de generarCuotas()");
    }

    @Test
    void testGeneracionDe30Cuotas() {
        // 1. Preparar
        credito = new Credito(tarjeta, factura, new ArrayList<>(), cliente);
        
        // 2. Ejecutar
        credito.generarCuotas();
        
        // 3. Verificar
        assertEquals(30, credito.getCuotas().size(), "El crédito debe generar exactamente 30 cuotas");
        
        // Verificar que los montos sean correctos (900000 / 30 = 30000 por cuota)
        double montoEsperadoPorCuota = 30000.0;
        for (Cuota cuota : credito.getCuotas()) {
            assertEquals(montoEsperadoPorCuota, cuota.getMonto(), 0.01, 
                "Cada cuota debe tener el monto correcto (total/30)");
        }
    }

    @Test
    void testNoPermiteMasDe30Cuotas() {
        // 1. Preparar - Intentar crear un crédito con más de 30 cuotas
        credito = new Credito(tarjeta, factura, new ArrayList<>(), cliente);
        
        // 2. Ejecutar y verificar
        // El constructor ya llama a generarCuotas(), así que solo verificamos
        assertEquals(30, credito.getCuotas().size(), 
            "El crédito debe tener exactamente 30 cuotas");
            
        // Intentar modificar la lista no debería afectar al crédito
        List<Cuota> cuotasModificables = credito.getCuotas();
        cuotasModificables.add(new Cuota());
        
        // La lista interna del crédito debe mantenerse en 30
        assertEquals(30, credito.getCuotas().size(), 
            "El crédito debe mantener 30 cuotas aunque se modifique la lista devuelta");
    }

    //Test Extra
    @Test
    void testValidarFechasGeneracionYVencimiento() {
        // 1. Preparar
        credito = new Credito(tarjeta, factura, new ArrayList<>(), cliente);
        LocalDate fechaActual = LocalDate.now();
        
        // 2. Ejecutar
        credito.generarCuotas();
        
        // 3. Verificar fechas de generación y vencimiento
        List<Cuota> cuotas = credito.getCuotas();
        for (int i = 0; i < cuotas.size(); i++) {
            Cuota cuota = cuotas.get(i);
            assertEquals(fechaActual, cuota.getFechaGeneracion(), 
                "La fecha de generación debe ser la fecha actual");
            assertEquals(fechaActual.plusMonths(i + 1), cuota.getFechaVencimiento(), 
                "La fecha de vencimiento debe ser un mes después por cada cuota");
        }
    }

}
