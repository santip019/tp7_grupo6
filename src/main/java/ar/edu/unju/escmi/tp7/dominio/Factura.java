package ar.edu.unju.escmi.tp7.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Factura {

	private static int contador = 1;
    private LocalDate fecha;
    private long nroFactura;
    private Cliente cliente;
    private List<Detalle> detalles = new ArrayList<Detalle>();

    public Factura() {

    }

    public Factura(LocalDate fecha, Cliente cliente) {
        this.fecha = fecha;
        this.nroFactura = contador ++;
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public long getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(long nroFactura) {
        this.nroFactura = nroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public void agregarDetalle(Detalle detalle) {
        detalles.add(detalle);
    }

    public double calcularTotalAhora30() {
        double totalAhora30 = 0;
        for (Detalle detalle : detalles) {
            if (detalle.isEstadoAhora30()) {
                totalAhora30 += detalle.getImporte();
            }
        }
        return totalAhora30;
    }

    public double calcularTotal() {
        double total = 0;
        for (Detalle detalle : detalles) {
            if (!detalle.isEstadoAhora30()) {
                total += detalle.getImporte();
            }
        }
        return total;
    }

    public boolean esFacturaAhora30() {
        for (Detalle detalle : detalles) {
            if (detalle.isEstadoAhora30()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return  "\n\n******************** Factura ********************"
                + "\nFecha: " + fecha + "\nN° de Factura: " + nroFactura
                + "\nCliente: " + cliente.getNombre() + "\nDNI: " + cliente.getDni()
                + "\n************ Detalles de la Factura *************"
                + "\n" + detalles.toString().replaceAll("\\[|\\]", "").replaceAll(", ", "") + "\n"
                + (esFacturaAhora30() ? "\nTotal Ahora 30: $" + calcularTotalAhora30() 
                + "\nMonto de cada couta fija: " + calcularTotalAhora30()/30 : "Subtotal: $" + calcularTotal())
                + "\nMonto total: $" + (calcularTotal() + calcularTotalAhora30());

    }
}
