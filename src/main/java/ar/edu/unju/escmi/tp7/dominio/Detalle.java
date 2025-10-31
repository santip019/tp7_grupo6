package ar.edu.unju.escmi.tp7.dominio;

public class Detalle {

	private int cantidad;
    private double importe;
    private Producto producto;
    private boolean estadoAhora30; /* true = el detalle es de un producto 'Ahora 30' y false = el detalle no es de un producto 'Ahora 30' */

    public Detalle() {

    }

    public Detalle(int cantidad, double importe, Producto producto, boolean estadoAhora30) {
        this.cantidad = cantidad;
        this.importe = importe;
        this.producto = producto;
        this.estadoAhora30 = estadoAhora30;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isEstadoAhora30() {
        return estadoAhora30;
    }

    public void setEstadoAhora30(boolean estadoAhora30) {
        this.estadoAhora30 = estadoAhora30;
    }

    private void calcularImporte() {
        this.setImporte(this.cantidad * this.producto.getPrecioUnitario());
    }

    @Override
    public String toString() {
        return "PRODUCTO: " + producto + "\nCANTIDAD: " + cantidad + " | IMPORTE: " + importe + "\n";
    }

    
}
