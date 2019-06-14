package implementaciones;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.text.SimpleDateFormat;

import tdas.AgendaCitasTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitas implements AgendaCitasTDA {
	
	NodoAgenda primero;

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		primero = null;
	}

	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {
		// TODO Auto-generated method stub
		if(primero==null) {
			NodoAgenda auxAgenda = new NodoAgenda();
			auxAgenda.abogado=abogado;
			auxAgenda.sigMedico=null;
			NodoDia auxDia = new NodoDia();
			auxDia.dia=dia;
			auxDia.fecha=fecha;
			auxDia.siguienteFecha=null;
			auxDia.turnos=null;
			primero=auxAgenda;
			primero.primeraFecha=auxDia;
		}else{
			NodoDia auxDia = new NodoDia();
			auxDia.dia=dia;
			auxDia.fecha=fecha;
			auxDia.siguienteFecha=null;
			auxDia.turnos=null;
			NodoAgenda aux = primero;
			while(aux!=null) {    //Buscar Posicion de abogado
				if(aux.abogado!=abogado){
					if(aux.sigMedico==null) {
						   NodoAgenda auxAgenda = new NodoAgenda();
						   auxAgenda.abogado=abogado;
					       auxAgenda.primeraFecha=auxDia;
						   auxAgenda.sigMedico=null;
						   aux.sigMedico=auxAgenda;
						   break;
					   }
				aux = aux.sigMedico;
				}else {
					NodoDia auxPost = aux.primeraFecha;   //Buscar posicion para el nodoDia creado con los valores
					NodoDia auxAnt = aux.primeraFecha;
					while(auxPost!=null) {
						if(!fechaPosterior(auxPost.fecha,fecha) && auxPost.fecha!=fecha){
							auxDia.siguienteFecha=auxPost;
							if(auxPost!=auxAnt) {
							   auxAnt.siguienteFecha=auxDia;
							   break;
							}else {
								aux.primeraFecha=auxDia;
								break;
							}
						}else {
							if(auxPost.fecha==fecha) {
								break;
							}
							auxAnt=auxPost;
							auxPost=auxPost.siguienteFecha;
						}
					}
					if(fechaPosterior(auxAnt.fecha,fecha) && auxPost==null){
						auxAnt.siguienteFecha=auxDia;
						auxDia.siguienteFecha=null;
						break;
					}
					break;
				}
			}
		}
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
	public void agregarNuevaCita(String abogado, String fecha, String hora, String cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarAbogado(String abogado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarFecha(String abogado, String fecha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarCita(String abogado, String fecha, String hora, String cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existeCita(String abogado, String fecha, String hora) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String clienteEnCita(String abogado, String fecha, String hora) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConjuntoTDA abogados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColaTDA turnos(String abogado, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void mostrarAgendas() {
		NodoAgenda aux = primero;
		while(aux!=null) {
			System.out.println("Abogado : "+aux.abogado);
			NodoDia auxd = aux.primeraFecha;
			while(auxd!=null) {
			   System.out.println(auxd.dia+" "+auxd.fecha);
			   auxd=auxd.siguienteFecha;
			}
			aux=aux.sigMedico;
		}
	}

}
