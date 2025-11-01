package ar.edu.unju.escmi.tp7.dominio;

public class Stock {
	private int cantidad;
	private Producto producto;

	public Stock() {
    }

    public Stock(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /* Metodos */
    public boolean validarStockDisponible (int cantidadSolicitada) {
        if (this.cantidad < cantidadSolicitada) {
            return false;
        }
        return true;
    }

    public void actualizarStock (int cantidadVendida) {
        if (cantidadVendida > this.cantidad) {
            System.out.println("No hay stock suficiente");
        }
        else {
            this.cantidad = this.cantidad - cantidadVendida;
        }
    }
}
