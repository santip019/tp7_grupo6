package ar.edu.unju.escmi.tp7.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.dominio.Producto;

public class CollectionProducto {
	 public static List<Producto> productos = new ArrayList<Producto>();

	 public static void precargarProductos() {
	        if (productos.isEmpty()) {
	            productos = new ArrayList<Producto>();
	            productos.add(new Producto(1111, "Aire Acondicionado Split On/Off 2750W FC Hisense", 220000, "Argentina"));
	            productos.add(new Producto(1112, "Aire Acondicionado Split On/Off 3400W FC Hisense", 180000, "China"));
	            productos.add(new Producto(1113, "Aire Acondicionado Split On/Off 3400W FC Philco", 180000, "Argentina"));
	            productos.add(new Producto(1114, "Aire Acondicionado AA Inverter 3000 FC BGH", 250000, "Argentina"));
	            productos.add(new Producto(2111, "Celular Galaxi A33 Samsung", 150000, "Argentina"));
	            productos.add(new Producto(2112, "Celular L7 + Primer Black - RVA TCL", 110000, "Argentina"));
	            productos.add(new Producto(2113, "Celular PANTALLA 5\" QUAD CORE 1RAM 32GB Alcatel", 90000, "Argentina"));
	            productos.add(new Producto(2114, "Celular QTEST NEGRO Quantum", 75000, "Argentina"));
	            productos.add(new Producto(3111, "Heladera Heladera con Freezer 317 lts blanca Columbia", 130000, "Argentina"));
	            productos.add(new Producto(3112, "Heladera Heladera Ciclica Gafa", 160000, "Argentina"));
	            productos.add(new Producto(3113, "Heladera Heladera No Frost Electrolux", 155000, "Argentina"));
	            productos.add(new Producto(3114, "Heladera Heladera Ciclica R600 Drean", 280000, "Argentina"));
	            productos.add(new Producto(4111, "Lavarropas Semi 7 Kgs Columbia", 210000, "Argentina"));
	            productos.add(new Producto(4112, "Lavarropas Next 10.12 Pg Eco Drean", 190000, "Argentina"));
	            productos.add(new Producto(4113, "Lavarropa 8514 Aurora", 200000, "Argentina"));
	            productos.add(new Producto(4114, "Lavarropas Silver 8.5 Kg LG", 225000, "Argentina"));
	            productos.add(new Producto(5111, "Televisor 50\" - ULTRA HD Smart LG", 165000, "Argentina"));
	            productos.add(new Producto(5112, "Televisor Smart TV 4K 50´ Sist Operativo Vidaa Noblex", 280000, "Argentina"));
	            productos.add(new Producto(5113, "Televisor Smart TV de 50\" Ultra HD borderless Android Philips", 300000,
	                    "Argentina"));
	            productos.add(new Producto(5114, "Televisor UHD 4K Smart TV Samsung", 195000, "Argentina"));
	        }
	    }

	   public static void agregarProducto(Producto producto) {
	        
	    	try {
	    		if (productos.isEmpty()) {
	                productos.add(producto);
	            } else {
	                long controlCodigo = producto.getCodigo();
	                boolean band = true;

	                for (Producto pro : productos) {
	                    if (band) {
	                        if (pro.getCodigo() == controlCodigo) {
	                            System.out.println("\nYa existe un producto con ese código");
	                            band = false;
	                        }
	                    }
	                }
	                if (band) {
	                    productos.add(producto);
	                }

	            }
			} catch (Exception e) {
				System.out.println("\nNO SE PUEDE GUARDAR EL PRODUCTO");
			}
	    	
	    }

	    public static Producto buscarProducto(long codigo) {
	        Producto productoEncontrado = null;
	        
	        try {
	        	if (!productos.isEmpty()) {
	                for (Producto pro : productos) {
	                    if (pro.getCodigo() == codigo) {
	                        productoEncontrado = pro;
	                    }
	                }
	            }
			} catch (Exception e) {
				return null;
			}
	        
	        return productoEncontrado;
	    }

		public static List<Producto> obtenerProductosAhora30() {
			List<Producto> productosAhora30 = new ArrayList<>();
			
			for (Producto p : productos) {
				if (p.getOrigenFabricacion().equals("Argentina")) {
					String descripcion = p.getDescripcion().toLowerCase();
					boolean califica = false;

					// Verificar celulares (límite $800000)
					if (descripcion.contains("celular") && p.getPrecioUnitario() <= 800000) {
						califica = true;
					}
					// Verificar electrodomésticos (límite $1500000)
					else if (p.getPrecioUnitario() <= 1500000 && 
							(descripcion.contains("aire") || 
							descripcion.contains("heladera") || 
							descripcion.contains("lavarropas") || 
							descripcion.contains("tv") || 
							descripcion.contains("televisor"))) {
						califica = true;
					}

					if (califica) {
						productosAhora30.add(p);
					}
				}
			}
			
			return productosAhora30;
		}

		public static boolean buscarProductoAhora30 (long codigo) {
			List<Producto> productosAhora30 = obtenerProductosAhora30();
			
			for (Producto p : productosAhora30) {
				if (p.getCodigo() == codigo) {
					return true;
				}
			}
			
			return false;
		}

		public static void mostrarProductosAhora30() {
			System.out.println("\n====== Lista de productos disponibles - Ahora 30 ======");
			CollectionStock.mostrarStockAhora30(obtenerProductosAhora30());
		}
}
