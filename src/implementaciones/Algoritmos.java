package implementaciones;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import algoritmos.IAlgoritmo;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Algoritmos implements IAlgoritmo {

	@Override
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora) {
		
		if(agenda.existeCita(abogado, fecha, hora)) 
			return false;
		else
			return true;
	}

	@Override
	public ConjuntoTDA masCitas(AgendaCitasTDA agenda, String fechaDesde, String fechaHasta) {
		// TODO Auto-generated method stub
		ConjuntoTDA aux = agenda.abogados();
		ConjuntoTDA resultado = new Conjunto();
		resultado.inicializar();
		String abogado, fecha;
		ColaPrioridadTDA resultadoEnCola = new ColaPrioridad();
		resultadoEnCola.inicializar();
		while(!aux.conjuntoVacio()) {
			int citasTotales = 0;
			abogado=aux.elegir();
			ConjuntoTDA auxFechas = agenda.fechas(abogado);
			while(!auxFechas.conjuntoVacio()) {
				fecha = auxFechas.elegir();
				if((fechaPosterior(fechaDesde,fecha) && fechaPosterior(fecha,fechaHasta)) || (fecha ==fechaDesde || fecha==fechaHasta)){
					if(!agenda.turnos(abogado, fecha).colaVacia()) {
					   citasTotales = citasTotales + cantidadDeCitasDeLaFecha(agenda, abogado, fecha);
					}
				}
				auxFechas.sacar(fecha);
			}
			if(!resultadoEnCola.colaVacia()) {
				if(citasTotales>Integer.valueOf(resultadoEnCola.prioridad())) {
					resultadoEnCola.inicializar();
					resultadoEnCola.acolar(abogado, String.valueOf(citasTotales));
				}else if(citasTotales==Integer.valueOf(resultadoEnCola.prioridad())) {
					resultadoEnCola.acolar(abogado, String.valueOf(citasTotales));
				}
			}else {
				resultadoEnCola.acolar(abogado, String.valueOf(citasTotales));
			}
			aux.sacar(abogado);
		}
		while(!resultadoEnCola.colaVacia()) {
			resultado.agregar(resultadoEnCola.primero());
			resultadoEnCola.dasacolar();
		}
		return resultado;
	}
	private int cantidadDeCitasDeLaFecha(AgendaCitasTDA agenda, String abogado, String fecha) {
		int resultado = 0;
		ColaTDA auxTurnos = agenda.turnos(abogado,fecha);
		while(!auxTurnos.colaVacia()) {
			resultado++;
			auxTurnos.desacolar();
		}
		return resultado;
	}
	private boolean fechaPosterior(String fecha1, String fecha2){     // si fecha 2 es posterior a fecha 1 return true
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = sdf.parse(fecha1, new ParsePosition(0));
		Date date2 = sdf.parse(fecha2, new ParsePosition(0));
		if(date2.after(date1)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String abogadoUltimaVez(AgendaCitasTDA agenda, String cliente) {
		// TODO Auto-generated method stub
		String resultado=null, abogado, ultimaFecha="0000/00/00", ultimoHorario="00:00";
		ConjuntoTDA auxAbogados = agenda.abogados();
		while(!auxAbogados.conjuntoVacio()) {
			abogado = auxAbogados.elegir();
			ConjuntoTDA auxFechas = agenda.fechas(abogado);
			while(!auxFechas.conjuntoVacio()) {
				String fecha = auxFechas.elegir();
				ColaTDA auxHorarios = agenda.turnos(abogado, fecha);
				while(!auxHorarios.colaVacia()) {
					if(agenda.clienteEnCita(abogado, fecha, auxHorarios.primero())==cliente && fechaPosterior(ultimaFecha,fecha) && horaMasTemprana(ultimoHorario, auxHorarios.primero())) {
						resultado = abogado;
						ultimaFecha = fecha;
						ultimoHorario = auxHorarios.primero();
					}
					auxHorarios.desacolar();
				}
				auxFechas.sacar(fecha);
			}
			auxAbogados.sacar(abogado);
		}
		return resultado;
	}
	private boolean horaMasTemprana(String hora1, String hora2) {
		if((int)hora1.charAt(0)<(int)hora2.charAt(0)) {
			return true;
		}else {
			if((int)hora1.charAt(0)==(int)hora2.charAt(0)&&(int)hora1.charAt(1)<(int)hora2.charAt(1)) {
				return true;
			}else {
				if((int)hora1.charAt(0)==(int)hora2.charAt(0)&&(int)hora1.charAt(1)==(int)hora2.charAt(1)&&(int)hora1.charAt(3)<(int)hora2.charAt(3)) {
				   return true;
				}else {
					return false;
				}
			}
		}
		
	}

	//Devuelve las citas ordenadas por fecha y hora de menor a mayor
	@Override
	public String[][] obtenerCitas(AgendaCitasTDA agenda, String abogado, String fecha) {
		
		String [][] citas =new String[100][100];
		int indiceFecha = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					
		//Seteo las fechas principio y fin donde voy a buscar los turnos
		Calendar auxCal = Calendar.getInstance();
		auxCal.setTime(sdf.parse(fecha, new ParsePosition(0)));
		
		Calendar fechaSigLunes = getFechaAUnaSemana(fecha);
		
		//Sale del while cuando las fechas son iguales
		while (auxCal.compareTo(fechaSigLunes)!=0) {
		
			//Obtengo los turnos para la fecha en la que estoy
			String auxFecha = sdf.format(auxCal.getTime());
			ColaTDA turnos = agenda.turnos(abogado, auxFecha);

			int indiceHora = 0;
			while (!turnos.colaVacia()) {
				String auxTurno = turnos.primero();
				turnos.desacolar();
				
				//Desacola en orden asi que los meto tal cual en el array
				citas[indiceFecha][indiceHora] = "DIA: "+auxFecha+" - HORA: "+auxTurno+" - CLIENTE: "
						+agenda.clienteEnCita(abogado,auxFecha,auxTurno);
				indiceHora++;
			}
			indiceFecha++;
			auxCal.add(Calendar.DAY_OF_MONTH, 1);
			
		}		

		return citas;
	}

	//Devuelve la fecha en Date sumandole 7 dias
	private Calendar getFechaAUnaSemana (String fecha) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(fecha, new ParsePosition(0)));
		c.add(Calendar.DATE, 7); // Adding 5 days
		return c;
	}
	
	//Devuelve las reuniones ordenadas primero por abogado y luego por fecha y hora de menor a mayor
	@Override
	public String[][] conQuienSeReunio(AgendaCitasTDA agenda, String cliente) {
		
		String [][] reuniones =new String[100][100];
		int indiceAbogado = 0;
		
		ConjuntoTDA abogados = agenda.abogados();
		
		//Recorro todos los abogados
		while (!abogados.conjuntoVacio()) {
			
			//Elijo un abogado
 			String auxAbogado = abogados.elegir();
			abogados.sacar(auxAbogado);			
			ColaTDA fechas = agenda.fechasConCola(auxAbogado);
			
			int indiceTurno = 0;
			//Desacolo una fecha
			while (!fechas.colaVacia()) {
				String auxFecha = fechas.primero();
				fechas.desacolar();
				
				ColaTDA turnos = agenda.turnos(auxAbogado, auxFecha);			

				while (!turnos.colaVacia()) {
					//Desacolo un turno
					String auxTurno = turnos.primero();
					turnos.desacolar();
					
					//Solo lo meto en el array si corresponde al mismo cliente
					if (agenda.clienteEnCita(auxAbogado,auxFecha,auxTurno).equalsIgnoreCase(cliente)) {
						//Desacola en orden asi que los meto tal cual en el array
						reuniones[indiceAbogado][indiceTurno] = "CLIENTE: "+cliente+" - ABOGADO: "+auxAbogado+
								" - DIA: "+auxFecha+" - HORA: "+auxTurno;						
						indiceTurno++;
					}
				}
			}
			indiceAbogado++;

		}
		
		return reuniones;
	}

	@Override
	public ColaPrioridadTDA libresTotal(AgendaCitasTDA agenda, String fecha) {
		
		ConjuntoTDA listaAbogados = agenda.abogados();
		ColaPrioridadTDA horariosLibresAbogados = new ColaPrioridad();
		horariosLibresAbogados.inicializar();
		String [] horas =  {"08:00", "08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00"}; 
		while(!listaAbogados.conjuntoVacio()) {
			String abogado = listaAbogados.elegir();
			for(int i=0; i<horas.length;i++) {
				if(!agenda.existeCita(abogado, fecha, horas[i])) {
					horariosLibresAbogados.acolar(listaAbogados.elegir(), horas[i]);
				}
			}
			listaAbogados.sacar(abogado);
		}
		// TODO Auto-generated method stub
		return horariosLibresAbogados;
	}


}
