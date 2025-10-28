package ar.edu.unju.escmi.tp7.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp7.dominio.Credito;

public class CollectionCredito {

	public static List<Credito> creditos = new ArrayList<Credito>();

	public static void agregarCredito(Credito credito) {

		try {
			creditos.add(credito);
		} catch (Exception e) {
			System.out.println("\nNO SE PUEDE GUARDAR EL CREDITO");
		}

	}

	public static void buscarCreditoPorDni(long dni) {

		System.out.println("=================================================");
		System.out.println("BUSCANDO CRÉDITOS PARA DNI: " + dni);
		System.out.println("=================================================");

		boolean encontrado = false; // Bandera para saber si se encontró

		try {
			if (creditos != null && !creditos.isEmpty()) { // Verifica que la lista no sea nula ni esté vacía

				for (Credito credito : creditos) { // Recorre cada crédito en la lista

					try {
						// ¡Lógica Simplificada! Acceso directo al Cliente y al DNI
						if (credito.getCliente() != null && credito.getCliente().getDni() == dni) {

							// El primero verifica que cliente no sea nulo (es el único punto de falla)
							// El segundo verifica que el DNI coincida con el buscado

							System.out.println("\n--- Crédito Encontrado ---");
							// Llamamos al método que tienes en la clase Credito para mostrar su detalle
							credito.mostarCredito();
							encontrado = true;
						}
					} catch (NullPointerException e) {
						// Captura si, por alguna razón, getCliente() devolviera null (aunque ya lo
						// verificamos)
						System.out.println(
								"ERROR: Se encontró un crédito incompleto (Cliente nulo). Se omite y se continúa la búsqueda.");
					}
				}
			}

			if (!encontrado) {
				System.out.println(" No se encontraron créditos asociados al DNI " + dni + ".");
			}

		} catch (Exception e) {
			// Para cualquier otro error inesperado fuera del NullPointerException
			System.out.println("ERROR durante la búsqueda de créditos: " + e.getMessage());
		}
	}
}
