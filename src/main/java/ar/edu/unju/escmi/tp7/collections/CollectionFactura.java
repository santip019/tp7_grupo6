package ar.edu.unju.escmi.tp7.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.dominio.Factura;

public class CollectionFactura {

	public static List<Factura> facturas = new ArrayList<Factura>();

	

	public static void agregarFactura(Factura factura) {
		
		try {
			facturas.add(factura);
		} catch (Exception e) {
			System.out.println("\nNO SE PUEDE GUARDAR LA FACTURA");
		}
		
	}

	public static Factura buscarFactura(long nroFactura) {
		Factura facturaEncontrada = null;
		
		try {
			if (facturas != null) {
				for (Factura fac : facturas) {
					if (fac.getNroFactura() == nroFactura) {
						facturaEncontrada = fac;
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		return facturaEncontrada;
	}

	public static void buscarFacturasPorDni(long dni) {

		System.out.println("=================================================");
		System.out.println("BUSCANDO FACTURAS PARA DNI: " + dni);
		System.out.println("=================================================");

		boolean encontrado = false;

		try {
			if (facturas != null && !facturas.isEmpty()) {
				for (Factura fac : facturas) {
					try {
						if (fac.getCliente() != null && fac.getCliente().getDni() == dni) {
							System.out.println(fac.toString());
							encontrado = true;
						}
					} catch (NullPointerException e) {
						System.out.println("Advertencia: factura incompleta encontrada y omitida.");
					}
				}
			}

			if (!encontrado) {
				System.out.println(" No se encontraron facturas asociadas al DNI " + dni + ".");
			}

		} catch (Exception e) {
			System.out.println("ERROR durante la búsqueda de facturas: " + e.getMessage());
		}
	}
}
