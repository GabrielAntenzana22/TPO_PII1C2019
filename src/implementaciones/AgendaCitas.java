package implementaciones;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.text.SimpleDateFormat;

import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitas implements AgendaCitasTDA {
	
	NodoAgenda primero;

	@Override
	public void inicializar() {
		primero = null;
	}

	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {
		if(primero==null) {
			//Si no hay abogados creo el nodo agenda para el abogado
			NodoAgenda auxAgenda = new NodoAgenda();
			auxAgenda.abogado=abogado;
			auxAgenda.sigMedico=null;
			
			//Creo el nodo dia
			NodoDia auxDia = new NodoDia();
			auxDia.dia=dia;
			auxDia.fecha=fecha;
			auxDia.siguienteFecha=null;
			
			//Creo el arbol de citas para ese dia
			auxDia.turnos= new ArbolCitas();
			auxDia.turnos.inicializar();
			primero=auxAgenda;
			primero.primeraFecha=auxDia;
			
		}else{
			//Si hay abogados creo el nodo para el dia
			NodoDia auxDia = new NodoDia();
			auxDia.dia=dia;
			auxDia.fecha=fecha;
			auxDia.siguienteFecha=null;
			
			//Creo el arbol de citas para ese dia
			auxDia.turnos= new ArbolCitas();
			auxDia.turnos.inicializar();
			
			//Me guardo el primer nodo agenda
			NodoAgenda aux = primero;
			
			//Busco si existe el nodo agenda para ese abogado desde el primero
			while(aux!=null) {
				
				//Si no coinciden los abogados sigo
				if(aux.abogado!=abogado){
					
					//Si el siguiente es null llegue al final, creo el nodo agenda para el nuevo abogado
					if(aux.sigMedico==null) {
						   NodoAgenda auxAgenda = new NodoAgenda();
						   auxAgenda.abogado=abogado;
					       auxAgenda.primeraFecha=auxDia;
						   auxAgenda.sigMedico=null;
						   aux.sigMedico=auxAgenda;
						   break;
					   }
					
				//Avanzo al siguiente abogado
				aux = aux.sigMedico;
				
				//Si coincidieron los abogados busco la posicion para el nodo dia
				}else {
					
					//Me guardo el nodo fecha anterior y posterior (empiezan los 2 con la primera)
					NodoDia auxPost = aux.primeraFecha;
					NodoDia auxAnt = aux.primeraFecha;
					
					
					while(auxPost!=null) {
						
						//Si la fecha que se inserta es menor a la referencia de auxPost y no son iguales
						if(!fechaPosterior(auxPost.fecha,fecha) && auxPost.fecha!=fecha){
							
							//Pongo el nuevo nodo dia atras del que tengo en auxPost
							auxDia.siguienteFecha=auxPost;
							
							//Si son distintos es porque estoy insertando entre 2 nodos, pongo el nuevo nodo dia adelante del auxAnt
							if(auxPost!=auxAnt) {
							   auxAnt.siguienteFecha=auxDia;
							   break;
							 
							//Sino lo seteo como primera fecha
							}else {
								aux.primeraFecha=auxDia;
								break;
							}
						}else {
							
							//El dia ya existe, no hago nada
							if(auxPost.fecha.equalsIgnoreCase(fecha)) {
								break;
							}
							
							//Avanzo a la siguiente fecha
							auxAnt=auxPost;
							auxPost=auxPost.siguienteFecha;
						}
					}
					
					//Si entra acá inserta el dia como ultimo
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
	
	// si fecha 2 es posterior a fecha 1 return true
	private boolean fechaPosterior(String fecha1, String fecha2){     
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
		NodoAgenda auxAbogado = primero;
		
		//Busco al abogado
		while(auxAbogado!=null) {
			if(auxAbogado.abogado.equalsIgnoreCase(abogado)) {
				NodoDia auxDia = auxAbogado.primeraFecha;
				
				//Busco al dia
				while(auxDia!=null) {
					if(auxDia.fecha.equalsIgnoreCase(fecha)) {
						    ArbolCitasTDA auxArbol = auxDia.turnos ;
							auxArbol.agregar(hora, cliente);
							break;
					}
					//Si la fecha no coincide avanzo
					auxDia=auxDia.siguienteFecha;
				}
				break;
			}
			//Si el abogado no coincide avanzo
			auxAbogado=auxAbogado.sigMedico;
		}
		//Si no encontro al abogado y/o al dia no agrega nada
	}

	@Override
	public void eliminarAbogado(String abogado) {
		
		if(primero.abogado.equalsIgnoreCase(abogado)) {
			primero=primero.sigMedico;
		}else {
			
			//Me guardo las referencias al anterior y al posterior
			NodoAgenda auxPost = primero;
			NodoAgenda auxAnt = primero;
			
			//Si no es el ultimo
		    while(auxPost!=null) {
		    	
		    	//Si encontre el abocado relaciono el anterior y el proximo
		    	if(auxPost.abogado.equalsIgnoreCase(abogado)) {
		    		auxAnt.sigMedico=auxPost.sigMedico;
		    		break;
		    	
		    	//Sino avanzo
				}else {
					auxAnt=auxPost;
					auxPost=auxPost.sigMedico;
				}
		   }
	   }
		
	}

	@Override
	public void eliminarFecha(String abogado, String fecha) {

		NodoAgenda auxPost = primero;
		NodoAgenda auxAnt = primero;
		
		//Busco al abogado
		while(auxPost!=null) {
		   if(auxPost.abogado.equalsIgnoreCase(abogado)) {
			  NodoDia auxDiaPost = auxPost.primeraFecha;
			  NodoDia auxDiaAnt = auxPost.primeraFecha;
			  
              //Busco la fecha
			  while(auxDiaPost!=null ) {
				  if(auxDiaPost.fecha.equalsIgnoreCase(fecha)) {
					  
					  if(auxDiaPost.siguienteFecha==null) {
						  
						  //El abogado se elimina porque tiene solo la fecha que se borra 
						  if(auxDiaPost==auxDiaAnt) {
						     this.eliminarAbogado(abogado);
						     break;
						  
						  }else {
							  //Se elimina la referencia la ultimo nodo (podria ir null?)
							  auxDiaAnt.siguienteFecha=auxDiaPost.siguienteFecha; 
							  break;
						  }
					  }
					  
					  //Se elimina una fecha intermedia
					  if(auxDiaAnt.siguienteFecha==auxDiaPost) {   //Si la fecha queda en el Nodo Intermedio
					      auxDiaAnt.siguienteFecha=auxDiaPost.siguienteFecha;
					      break;
					  }
					  
					  //Se elimina la primer fecha
					  if(auxDiaAnt==auxDiaPost) {
						  auxPost.primeraFecha=auxDiaPost.siguienteFecha;
						  break;
					  }
					  
					  //No es la fecha que busco asi que avanzo
				  }else {
					  auxDiaAnt=auxDiaPost;
					  auxDiaPost=auxDiaPost.siguienteFecha;
				  }
			  }
			  break;
			 
			//No es el abogado que busco asi que avanzo
		   }else {
		     auxAnt=auxPost;
		     auxPost=auxPost.sigMedico;
		   }
		}
		//Si no encontre la fecha para el abogado no hago nada		
	}

	@Override
	public void eliminarCita(String abogado, String fecha, String hora, String cliente) {

		NodoAgenda auxAbogado = primero;
		
		//Busco al abogado
		while(auxAbogado!=null) {
			if(auxAbogado.abogado.equalsIgnoreCase(abogado)) {
				NodoDia auxDia = auxAbogado.primeraFecha;
				
				//Busco la fecha
				while(auxDia!=null) {
					if(auxDia.fecha.equalsIgnoreCase(fecha)) {
					   ArbolCitasTDA auxArbol = auxDia.turnos;
					   auxArbol.eliminar(hora, cliente);
					   break;
					}
					
					//No es la fecha que busco asi que avanzo
					auxDia=auxDia.siguienteFecha;
				}
				break;
			}
			//No es el abogado que busco asi que avanzo
			auxAbogado=auxAbogado.sigMedico;
		}
	}

	@Override
	public boolean existeCita(String abogado, String fecha, String hora) {

		NodoAgenda auxAbogado = primero;
		
		//Busco al abogado
		while(auxAbogado!=null) {
			if(auxAbogado.abogado.equalsIgnoreCase(abogado)) {
				NodoDia auxDia = auxAbogado.primeraFecha;
				
				//Busco la fecha
				while(auxDia!=null) {
					if(auxDia.fecha.equalsIgnoreCase(fecha)) {
					   ArbolCitasTDA auxArbol = auxDia.turnos;
					   return existeCitaEnArbol(auxArbol, hora);
					}
					
					//No es la fecha que busco asi que avanzo
					auxDia=auxDia.siguienteFecha;
				}
			}
			
			//No es el abogado que busco asi que avanzo
			auxAbogado=auxAbogado.sigMedico;
		}
		return false;
	}
	
	private boolean existeCitaEnArbol (ArbolCitasTDA arbol, String hora) {
		if(arbol.arbolVacio()) {
			return false;
		}else if(arbol.hora().equalsIgnoreCase(hora)) {
			return true;
		}else if(horaMasTemprana(hora,arbol.hora())) {
			return this.existeCitaEnArbol(arbol.hijoIzquierdo(), hora);
		}else {
			return this.existeCitaEnArbol(arbol.hijoDerecho(), hora);
		}
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
	public String clienteEnCita(String abogado, String fecha, String hora) {

		NodoAgenda auxAbogado = primero;
		
		//Busco al abogado
		while(auxAbogado!=null) {
			if(auxAbogado.abogado.equalsIgnoreCase(abogado)) {
				NodoDia auxDia = auxAbogado.primeraFecha;
				
				//Busco la fecha
				while(auxDia!=null) {
					if(auxDia.fecha.equalsIgnoreCase(fecha)) {
					   ArbolCitasTDA auxArbol = auxDia.turnos;
					   return clienteEnArbol(auxArbol,hora);
					}
					
					//No es la fecha que busco asi que avanzo
					auxDia=auxDia.siguienteFecha;
				}
			}
			
			//No es el abogado que busco asi que avanzo
			auxAbogado=auxAbogado.sigMedico;
		}
		return null;      //Nunca se llega a null por precondicion : Existe abogado, fehca y hora
	}
	
	private String clienteEnArbol(ArbolCitasTDA arbol, String hora) {
		if(arbol.hora().equalsIgnoreCase(hora)) {
			return arbol.cliente();
		}else if(horaMasTemprana(hora,arbol.hora())) {
			return this.clienteEnArbol(arbol.hijoIzquierdo(), hora);
		}else {
			return this.clienteEnArbol(arbol.hijoDerecho(), hora);
		}
	}
	

	@Override
	public ConjuntoTDA abogados() {
		// TODO Auto-generated method stub
		ConjuntoTDA resultado = new Conjunto();
		resultado.inicializar();
		NodoAgenda aux = primero;
		while(aux!=null){
			resultado.agregar(aux.abogado);
			aux=aux.sigMedico;
		}
		return resultado;
	}

	@Override
	public ColaTDA turnos(String abogado, String fecha) {

		ColaTDA resultado = new Cola();
		resultado.inicializar();
		NodoAgenda auxAbogado = primero;
		
		//Busco al abogado
		while(auxAbogado!=null) {
			if(auxAbogado.abogado.equalsIgnoreCase(abogado)) {
				NodoDia auxDia = auxAbogado.primeraFecha;
				
				//Busco la fecha
				while(auxDia!=null) {
					if(auxDia.fecha.equalsIgnoreCase(fecha)) {
					   ArbolCitasTDA auxArbol = auxDia.turnos;
					   resultado = acolarHoras(auxArbol);
					}
					
					//No es la fecha que busco asi que avanzo
					auxDia=auxDia.siguienteFecha;
				}
			}
			
			//No es el abogado que busco asi que avanzo
			auxAbogado=auxAbogado.sigMedico;
		}
		      
		return resultado;
	}
	
	private ColaTDA acolarHoras(ArbolCitasTDA arbol) {
		ColaTDA resultado = new Cola();
		resultado.inicializar();
		
		if (!arbol.arbolVacio()){
			resultado = concatenar(resultado,acolarHoras(arbol.hijoIzquierdo()));
			resultado.acolar(arbol.hora());
			resultado = concatenar(resultado,acolarHoras(arbol.hijoDerecho()));
		}
		return resultado;
	}
	
	private ColaTDA concatenar(ColaTDA a,ColaTDA b) {
		while(!b.colaVacia()) {
			a.acolar(b.primero());
			b.desacolar();
		}
		return a;
	}
	
	public void mostrarAgendas() {
		NodoAgenda aux = primero;
		
		while(aux!=null) {
			System.out.println("Abogado : "+aux.abogado);
			NodoDia auxd = aux.primeraFecha;
			
			//Imprimo todas las fechas para ese abogado
			while(auxd!=null) {
			   System.out.println(auxd.dia+" "+auxd.fecha);
			   ArbolCitasTDA auxArbol = auxd.turnos;
			   
			   //Imprimo todas las horas y clientes para ese dia
			   if(auxArbol!=null) {
				 System.out.println("Hora  Cliente");
			     inOrder(auxArbol);
			   }
			   auxd=auxd.siguienteFecha;
			}
			System.out.println();
			aux=aux.sigMedico;
		}
	}
	private void inOrder(ArbolCitasTDA arbol) {
		// TODO Auto-generated method stub
		if (!arbol.arbolVacio()){
			inOrder(arbol.hijoIzquierdo()); 
			System.out.println(arbol.hora()+" "+arbol.cliente()); 
			inOrder(arbol.hijoDerecho());
			}
	}
   
	public ConjuntoTDA fechas (String abogado) {
		ConjuntoTDA resultado = new Conjunto();
		resultado.inicializar();
		NodoAgenda auxAgenda = primero;
		while(auxAgenda!=null) {
			if(auxAgenda.abogado.equalsIgnoreCase(abogado)) {
			    NodoDia auxDia = auxAgenda.primeraFecha;
			    while(auxDia!=null) {
			    	resultado.agregar(auxDia.fecha);
			    	auxDia=auxDia.siguienteFecha;
			    }
			}
			auxAgenda = auxAgenda.sigMedico;
		}
		return resultado;
	}
	

}
