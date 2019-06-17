package implementaciones;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import algoritmos.IAlgoritmo;
import tdas.AgendaCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Algoritmos implements IAlgoritmo {

	@Override
	public boolean disponible(AgendaCitasTDA agenda, String abogado, String fecha, String hora) {
		// TODO Auto-generated method stub
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
		String abogado;
		while(!aux.conjuntoVacio()) {
			abogado=aux.elegir();
			ColaTDA auxFechas = agenda.fechasDelAbogado(abogado);
			while(!auxFechas.colaVacia()) {
				if((fechaPosterior(fechaDesde,auxFechas.primero()) && fechaPosterior(auxFechas.primero(),fechaHasta)) || (auxFechas.primero()==fechaDesde || auxFechas.primero()==fechaHasta)){
					if(!agenda.turnos(abogado, auxFechas.primero()).colaVacia()) {
					   resultado.agregar(abogado);
					}
				}
				auxFechas.desacolar();
			}
			aux.sacar(abogado);
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
			ColaTDA auxFechas = agenda.fechasDelAbogado(abogado);
			while(!auxFechas.colaVacia()) {
				ColaTDA auxHorarios = agenda.turnos(abogado, auxFechas.primero());
				while(!auxHorarios.colaVacia()) {
					if(agenda.clienteEnCita(abogado, auxFechas.primero(), auxHorarios.primero())==cliente && fechaPosterior(ultimaFecha,auxFechas.primero()) && horaMasTemprana(ultimoHorario, auxHorarios.primero())) {
						resultado = abogado;
						ultimaFecha = auxFechas.primero();
						ultimoHorario = auxHorarios.primero();
					}
					auxHorarios.desacolar();
				}
				auxFechas.desacolar();
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

	@Override
	public String[][] obtenerCitas(AgendaCitasTDA agenda, String abogado, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] conQuienSeReunio(AgendaCitasTDA agenda, String cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColaPrioridadTDA libresTotal(AgendaCitasTDA agenda, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}


}
