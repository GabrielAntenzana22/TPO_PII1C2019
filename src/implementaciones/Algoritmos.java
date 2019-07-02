package implementaciones;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import algoritmos.IAlgoritmo;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Algoritmos implements IAlgoritmo {

	@Override
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora) {
		
		ConjuntoTDA fechas = agenda.fechas(abogado);
		
		if(!fechas.pertenece(fecha) || agenda.existeCita(abogado, fecha, hora)) 
			return false;
		else
			return true;
	}

	@Override
	public ConjuntoTDA masCitas(AgendaCitasTDA agenda, String fechaDesde, String fechaHasta) {

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
			if(Integer.valueOf(resultadoEnCola.prioridad())!=0) {
			    resultado.agregar(resultadoEnCola.primero());
			}
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
		String [] horas =  {"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30",
	            "05:00","05:30","06:00","06:30","07:00","07:30","08:00", "08:30","09:00","09:30",
	            "10:00","10:30","11:00","11:30","12:30","13:00","13:30","14:00","14:30","15:00",
	            "15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00",
	            "20:30","21:00","21:30","22:00","22:30","23:00","23:30"};
		String resultado="", abogado, ultimaFecha="0000/00/00", ultimoHorario="00:00";
		ConjuntoTDA auxAbogados = agenda.abogados();
		
		//Recorro todos los abogados
		while(!auxAbogados.conjuntoVacio()) {
			abogado = auxAbogados.elegir();
			ConjuntoTDA auxFechas = agenda.fechas(abogado);
			
			//Recorro las fechas
			while(!auxFechas.conjuntoVacio()) {
				String fecha = auxFechas.elegir();

				//Recorro los horarios
				for (String hora: horas) {
					
					//Para cada turno que tuvo el cliente recorro y guardo cual fue el ultimo
					if(agenda.existeCita(abogado, fecha, hora) 
							&& agenda.clienteEnCita(abogado, fecha, hora).equalsIgnoreCase(cliente)
							&& fechaPosterior(ultimaFecha,fecha) 
							&& horaMasTardia(ultimoHorario, hora)) {
						resultado = abogado;
						ultimaFecha = fecha;
						ultimoHorario = hora;
					}
				}
				auxFechas.sacar(fecha);
			}
			auxAbogados.sacar(abogado);
		}
		return resultado;
	}
	
	//Devuelve true si hora2 es mas temprano que hora1
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
	
	//Devuelve true si hora2 es mas tarde que hora1
	private boolean horaMasTardia(String hora1, String hora2) {
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
		
		String [][] citas =new String[0][0];
		String [] horas =  {"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30",
	            "05:00","05:30","06:00","06:30","07:00","07:30","08:00", "08:30","09:00","09:30",
	            "10:00","10:30","11:00","11:30","12:30","13:00","13:30","14:00","14:30","15:00",
	            "15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00",
	            "20:30","21:00","21:30","22:00","22:30","23:00","23:30"};
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Locale locale = Locale.forLanguageTag("es-ES");
		//Seteo las fechas principio y fin donde voy a buscar los turnos
		Calendar auxCal = Calendar.getInstance();
		auxCal.setTime(sdf.parse(fecha, new ParsePosition(0)));
		
		Calendar fechaSigLunes = getFechaAUnaSemana(fecha);
		
		//Sale del while cuando las fechas son iguales
		while (auxCal.compareTo(fechaSigLunes)!=0) {
		
				//Recorro el array con las horas
				for(String hora : horas) {
					
					String auxFecha = sdf.format(auxCal.getTime());
					//Si el abogado tiene cita en esta fecha y hora busco al cliente y lo agrego					
					if(agenda.existeCita(abogado, auxFecha, hora)){
						String auxDia = auxCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
						//Necesito el nombre del dia asi que tengo que buscarlo										
						String [] elem = new String [] {auxDia,hora,agenda.clienteEnCita(abogado, auxFecha, hora)};
						citas = agregarElementoCita(citas, elem);				

					}

				}
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
		
		String [][] reuniones =new String[0][0];
		String [] horas =  {"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30",
	            "05:00","05:30","06:00","06:30","07:00","07:30","08:00", "08:30","09:00","09:30",
	            "10:00","10:30","11:00","11:30","12:30","13:00","13:30","14:00","14:30","15:00",
	            "15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00",
	            "20:30","21:00","21:30","22:00","22:30","23:00","23:30"};
		
		ConjuntoTDA abogados = agenda.abogados();
		
		//Recorro todos los abogados
		while (!abogados.conjuntoVacio()) {
			
			List <String> fechasOrdenadas = new ArrayList<String> ();
			//Elijo un abogado
 			String auxAbogado = abogados.elegir();
			abogados.sacar(auxAbogado);			
			ConjuntoTDA fechas = agenda.fechas(auxAbogado);
			
			while (!fechas.conjuntoVacio()){
				String auxFecha = fechas.elegir();
				fechas.sacar(auxFecha);
				fechasOrdenadas.add(auxFecha);
				
			}
			
			Collections.sort(fechasOrdenadas);
			
			//Desacolo una fecha
			for (String fecha:fechasOrdenadas){
				
				//Recorro el array con las horas
				for(String hora : horas) {
					
					//Si el abogado tiene cita en esta fecha y hora y es con el cliente que busco lo agrego
					if(agenda.existeCita(auxAbogado, fecha, hora) 
							&& agenda.clienteEnCita(auxAbogado, fecha, hora).equalsIgnoreCase(cliente)) {
						
						String [] elem = new String [] {auxAbogado,fecha,hora};
						reuniones = agregarElementoReunion(reuniones, elem, 1, 2);				

					}

				}
			}

		}
		
		return reuniones;
	}

	@Override
	public ColaPrioridadTDA libresTotal(AgendaCitasTDA agenda, String fecha) {
		
		ConjuntoTDA listaAbogados = agenda.abogados();
		ColaPrioridadTDA horariosLibresAbogados = new ColaPrioridad();
		horariosLibresAbogados.inicializar();
		String [] horas =  {"00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30",
	            "05:00","05:30","06:00","06:30","07:00","07:30","08:00", "08:30","09:00","09:30",
	            "10:00","10:30","11:00","11:30","12:30","13:00","13:30","14:00","14:30","15:00",
	            "15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00",
	            "20:30","21:00","21:30","22:00","22:30","23:00","23:30"};

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		//Recorro todos los abogados
		while(!listaAbogados.conjuntoVacio()) {
			
			//Elijo un abogado
 			String auxAbogado = listaAbogados.elegir();
 			listaAbogados.sacar(auxAbogado);
 			
 			//Obtengo todas las fechas
 			ConjuntoTDA fechas = agenda.fechas(auxAbogado);
 			
			
			//Seteo las fechas principio y fin donde voy a buscar los turnos
			Calendar auxCal = Calendar.getInstance();
			auxCal.setTime(sdf.parse(fecha, new ParsePosition(0)));
			
			Calendar fechaSigLunes = getFechaAUnaSemana(fecha);
			
			//Sale del while cuando las fechas son iguales
			while (auxCal.compareTo(fechaSigLunes)!=0) {
				
				//Recorro el array con las horas
				for(String hora : horas) {
					
					//Si no hay cita en esta fecha y hora agrego el turno 
					String auxFecha = sdf.format(auxCal.getTime());
					if(fechas.pertenece(auxFecha) && !agenda.existeCita(auxAbogado, auxFecha, hora)) {

						horariosLibresAbogados.acolar(auxAbogado, hora);

					}
				
				}
				//Avanzo al siguiente dia de la semana
				auxCal.add(Calendar.DAY_OF_MONTH, 1);
				
			}
		
		}
		return horariosLibresAbogados;
	}

	private String [][] agregarElementoCita (String [][] arrayOriginal, String [] elem){
		
		String [][] nuevoArray = new String[(arrayOriginal.length+1)][elem.length];
        
        for (int i = 0; i <= arrayOriginal.length; i++) {
        	
        	//Cuando llego al final agrego el elemento
        	if (i==arrayOriginal.length){
        		nuevoArray[i]=elem;
        	
        	//Copio los elementos del original
        	} else {
        		nuevoArray[i]=arrayOriginal[i];
        	}
        	
        }        
        return nuevoArray;
  
	}
	
	private String [][] agregarElementoReunion (String [][] arrayOriginal, String [] elem, int posFecha, int posHora){
		
        String [][] nuevoArray = new String[(arrayOriginal.length+1)][elem.length];
        boolean agregado = false;
        
        for (int i = 0; i <= arrayOriginal.length; i++) {
        	//Si no lo agregue lo agrego cuando llego al final
        	if (i==arrayOriginal.length && !agregado){
        		nuevoArray[i]=elem;
        	
        	//Si la fecha y hora del elemento nuevo es menor a alguno que ya esta lo inserto y corro todo 
        	}else if (fechaPosterior (elem[posFecha],arrayOriginal[i][posFecha])
        			|| (elem[posFecha].equalsIgnoreCase(arrayOriginal[i][posFecha]) && horaMasTardia(elem[posHora],arrayOriginal[i][posHora]))) {
        		
                String [] arrayAux = arrayOriginal[i];
                nuevoArray[i]=elem;
                nuevoArray[i+1]=arrayAux;
                
                //Uso un flag para saber que ya lo agregue
                agregado = true;
                i++;
        	//Si no se cumple ninguna de las anteriores solamente copio
        	} else { 
        		nuevoArray[i]=arrayOriginal[i];
        	}

        }        
        return nuevoArray;
  
	}
}
