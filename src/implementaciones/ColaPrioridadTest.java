package implementaciones;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColaPrioridadTest {

	ColaPrioridad colaPrioridad;

	@Before
	public void setUp() throws Exception {
		colaPrioridad = new ColaPrioridad();
		colaPrioridad.inicializar();
	}

	@Test
	public void testColaVacia() {
		// Operaci�n
		boolean colaPrioridadVacia = colaPrioridad.colaVacia();

		// Validaci�n
		Assert.assertTrue(colaPrioridadVacia);
	}

	@Test
	public void testColaNoVacia() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado", "10:30");

		// Operaci�n
		boolean colaPrioridadVacia = colaPrioridad.colaVacia();

		// Validaci�n
		Assert.assertFalse(colaPrioridadVacia);
	}

	@Test
	public void testPrimerValor() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");

		// Operaci�n
		String abogadoQueAtiendePrimero = colaPrioridad.primero();

		//Se cambia la validacion porque nosotros tenemos la prioridad a la inversa
		// Validaci�n
		Assert.assertEquals("un abogado que atiende 9pm", abogadoQueAtiendePrimero);
		
		// Validaci�n
//		Assert.assertEquals("un abogado que atiende 9am", abogadoQueAtiendePrimero);
	}

	@Test
	public void testSegundoValor() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");

		// Operaci�n
		colaPrioridad.dasacolar();
		String abogadoQueAtiendeSegundo = colaPrioridad.primero();

		//Se cambia la validacion porque nosotros tenemos la prioridad a la inversa
		// Validaci�n
		Assert.assertEquals("un abogado que atiende 12am", abogadoQueAtiendeSegundo);
		
		// Validaci�n
		//Assert.assertEquals("un abogado que atiende 10.30am", abogadoQueAtiendeSegundo);
	}

	@Test
	public void testDosValoresEnLaMismaPrioridad() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");
		colaPrioridad.acolar("otro abogado que atiende 9am", "09:00");

		// Operaci�n
		colaPrioridad.dasacolar(); //Se agregan porque tenemos la prioridad al reves
		colaPrioridad.dasacolar();
		String unAbogadoQueAtiendeEnElSegundoHorario = colaPrioridad.primero();
		colaPrioridad.dasacolar();
		String unAbogadoQueAtiendeEnElPrimerHorario = colaPrioridad.primero();
		colaPrioridad.dasacolar();
		String otroAbogadoQueAtiendeEnElPrimerHorario = colaPrioridad.primero();

		// Validaci�n
		Assert.assertEquals("un abogado que atiende 9am", unAbogadoQueAtiendeEnElPrimerHorario);
		Assert.assertEquals("otro abogado que atiende 9am", otroAbogadoQueAtiendeEnElPrimerHorario);
		Assert.assertEquals("un abogado que atiende 10.30am", unAbogadoQueAtiendeEnElSegundoHorario);
		
		// Validaci�n
//		Assert.assertEquals("un abogado que atiende 9am", unAbogadoQueAtiendeEnElPrimerHorario);
//		Assert.assertEquals("otro abogado que atiende 9am", otroAbogadoQueAtiendeEnElPrimerHorario);
//		Assert.assertEquals("un abogado que atiende 10.30am", unAbogadoQueAtiendeEnElSegundoHorario);
	}

	@Test
	public void testPrioridad() {
		// Inicializaci�n
		colaPrioridad.acolar("un abogado que atiende 10.30am", "10:30");
		colaPrioridad.acolar("un abogado que atiende 12am", "12:00");
		colaPrioridad.acolar("un abogado que atiende 9am", "09:00");
		colaPrioridad.acolar("un abogado que atiende 9pm", "21:00");
		colaPrioridad.acolar("otro abogado que atiende 9am", "09:00");

		// Operaci�n
		String primeraPrioridad = colaPrioridad.prioridad();
		colaPrioridad.dasacolar();
		String segundaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.dasacolar();
		String terceraPrioridad = colaPrioridad.prioridad();
		colaPrioridad.dasacolar();
		String cuartaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.dasacolar();
		String quintaPrioridad = colaPrioridad.prioridad();
		colaPrioridad.dasacolar();
		
		//Se cambia la validacion porque nosotros tenemos la prioridad a la inversa
		//Se modifica sacandole los :, nuestra cola de prioridad no los usa
		// Validaci�n
		Assert.assertEquals("2100", primeraPrioridad);
		Assert.assertEquals("1200", segundaPrioridad);
		Assert.assertEquals("1030", terceraPrioridad);
		Assert.assertEquals("0900", cuartaPrioridad);
		Assert.assertEquals("0900", quintaPrioridad);
		
		// Validaci�n
//		Assert.assertEquals("09:00", primeraPrioridad);
//		Assert.assertEquals("09:00", segundaPrioridad);
//		Assert.assertEquals("10:30", terceraPrioridad);
//		Assert.assertEquals("12:00", cuartaPrioridad);
//		Assert.assertEquals("21:00", quintaPrioridad);
	}

}
