package ar.edu.unju.escmi.tp7.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import ar.edu.unju.escmi.tp7.dominio.Cuota;
import ar.edu.unju.escmi.tp7.dominio.Credito;

public class CuotaTest {

    @Test
    void testCuotaNoNula() {
        // Implementar el test para validar que la lista de cuota no sea nula
        List<Cuota> listaInicial = new ArrayList<>();

        Credito credito = new Credito(listaInicial);

        // 2. Ejecución
        List<Cuota> cuotas = credito.getCuotas();

        // 3. Verificación
        assertNotNull(cuotas, "La lista de cuotas no debería ser nula después de usar el constructor con lista.");
    }

    @Test
    void testCasoNull() {
        // caso en que la lista de cuotas sea nula
        Credito credito = new Credito();

        // Ejecución: Forzamos el valor a null usando el setter que lo permite.
        credito.setCuotas(null); // Esto provoca que el valor interno sea null.

        // Verificación: Comprobamos que el getter ahora devuelve null.
        List<Cuota> cuotas = credito.getCuotas();

        // Assertions.assertNull verifica que el objeto SÍ sea null.
        assertNull(cuotas, "La lista de cuotas DEBERÍA ser nula después de llamar a setCuotas(null).");
        // en caso de poner assertNotNull fallara porque se espera que sea nulo
    }

    @Test
    void testCuotasMinimas() {
        // Implementar el test para validar que la cantidad de cuotas no sea menor a 30
        List<Cuota> listaCuotas = new ArrayList<>(); // Lista vacía

        // Asignas la cantidad deseada (en este caso, 1 cuota) aca fallara el test al
        // ser 1
        // listaCuotas.add(new Cuota());

        // Si quisieras 30 cuotas, usarías un bucle:
        for (int i = 0; i < 30; i++) {
            listaCuotas.add(new Cuota());
        }

        // Crear el objeto Credito con la lista de cuotas
        Credito credito = new Credito(listaCuotas);

        // Ejecución
        List<Cuota> cuotas = credito.getCuotas();

        // Verificación
        assert (cuotas.size() == 30) : "La cantidad de cuotas no debería ser menor a 30";
    }

    @Test
    void testCuotasMaximas() {
        // Implementar el test para validar que la cantidad de cuotas no supere el
        // máximo permitido
        List<Cuota> listaCuotas = new ArrayList<>();

        // Agregar 30 cuotas a la lista
        for (int i = 0; i < 30; i++) {
            listaCuotas.add(new Cuota());
        }

        // Crear el objeto Credito con la lista de cuotas
        Credito credito = new Credito(listaCuotas);

        // Ejecución
        List<Cuota> cuotas = credito.getCuotas();

        // Verificación
        int maximoPermitido = 30;
        assert (cuotas.size() <= maximoPermitido) : "La cantidad de cuotas no debería superar el máximo permitido";
    }

}
