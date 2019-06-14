package algoritmos;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import tdas.AgendaCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ConjuntoTDA;

public interface IAlgoritmo {

	/**
	 * Determinar si el abogado tiene tiempo disponible para una cita en un d�a y horario determinado.
	 * */
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora);
	
	/**
	 * Determinar el/los abogados que tienen m�s citas en un per�odo de tiempo. 
	 * 
	 * Las fecha tienen el mismo formato que el utilizado en la agenda.
	 * */
	public ConjuntoTDA masCitas(AgendaCitasTDA agenda, String fechaDesde, String fechaHasta);
	
	/**
	 * Cual es el abogado que tuvo la �ltima cita con un cliente determinado pasado como par�metro
	 * */
	public String abogadoUltimaVez(AgendaCitasTDA agenda, String cliente);
	
	/**
	 * Obtener todas las citas de un abogado determinado durante una semana determinada.
	 * 
	 * Se pasa la fecha correspondiente al Lunes de la mensionada semana.
	 * 
	 * Debe devolver un arreglo conteniendo el nombre del d�a, la hora y el cliente ordenado por
	 * d�a de la semana y horario.  
	 */
	public String[][] obtenerCitas(AgendaCitasTDA agenda, String abogado, String fecha);
	
	/**
	 * Obtener para un cliente determinado, la lista de todos los abogados con que se reun�o junto con la fecha y la hora.
	 * 
	 * Debe devolver un arreglo conteniendo el nombre del abogado, la fecha y la hora y el cliente ordenado por
	 * d�a de la semana y horario.  
	 * */
	public String[][] conQuienSeReunio(AgendaCitasTDA agenda, String cliente);
	
	/**
	 * Obtener todas los horarios libres de todos los abogados para una semana determinada.
	 * 
	 * Se pasa la fecha correspondiente al Lunes de la mensionada semana.
	 * 
	 * Devolver una cola con prioridad que tenga como valor el nombre del abogado y como prioridad el horario
	 * */
	public ColaPrioridadTDA libresTotal(AgendaCitasTDA agenda, String fecha);
	
}

