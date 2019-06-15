package implementaciones;

import tdas.ArbolCitasTDA;

public class ArbolCitas implements ArbolCitasTDA {
    NodoArbol raiz;
	
	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		raiz=null;
	}

	@Override
	public void agregar(String hora, String cliente) {
		// TODO Auto-generated method stub
		if(raiz==null) {
			raiz = new NodoArbol();
			raiz.hora = hora;
			raiz.cliente = cliente;
			raiz.hijoIzq = new ArbolCitas();
			raiz.hijoIzq.inicializar();
			raiz.hijoDer = new ArbolCitas();
			raiz.hijoDer.inicializar();
		} else if(horaMasTemprana(hora, raiz.hora)) {
			raiz.hijoIzq.agregar(hora,cliente);
		} else if(horaMasTemprana(raiz.hora, hora)) {
			raiz.hijoDer.agregar(hora, cliente);
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
	public void eliminar(String hora, String cliente) {
		// TODO Auto-generated method stub
		if(raiz!=null) {
			if(raiz.hora==hora && raiz.cliente==cliente && raiz.hijoDer.arbolVacio() && raiz.hijoIzq.arbolVacio()) {
				raiz=null;
			}else if(raiz.hora==hora && raiz.cliente==cliente && !raiz.hijoIzq.arbolVacio()) {
				raiz.hora = this.mayorHora(raiz.hijoIzq);
				raiz.cliente = this.mayorCliente(raiz.hijoIzq);
				raiz.hijoIzq.eliminar(raiz.hora, raiz.cliente);
			}else if(raiz.hora==hora && raiz.cliente==cliente && raiz.hijoIzq.arbolVacio()) {
				raiz.hora = this.menorHora(raiz.hijoDer);
				raiz.cliente = this.menorCliente(raiz.hijoDer);
				raiz.hijoDer.eliminar(raiz.hora, raiz.cliente);
			}else if(horaMasTemprana(raiz.hora, hora)) {
				raiz.hijoDer.eliminar(hora, cliente);
			}else if(horaMasTemprana(hora, raiz.hora)) {
				raiz.hijoIzq.eliminar(hora, cliente);
			}
		}
	}
	private String mayorHora(ArbolCitasTDA a) {
		if(a.hijoDerecho().arbolVacio())
			return a.hora();
		else
			return mayorHora(a.hijoDerecho());
	}
	private String menorHora(ArbolCitasTDA a) {
		if(a.hijoIzquierdo().arbolVacio())
			return a.hora();
		else
			return menorHora(a.hijoIzquierdo());
	}
	private String mayorCliente(ArbolCitasTDA a) {
		if(a.hijoDerecho().arbolVacio())
			return a.cliente();
		else
			return mayorCliente(a.hijoDerecho());
	}
	private String menorCliente(ArbolCitasTDA a) {
		if(a.hijoIzquierdo().arbolVacio())
			return a.cliente();
		else
			return menorCliente(a.hijoIzquierdo());
	}

	@Override
	public String hora() {
		// TODO Auto-generated method stub
		return raiz.hora;
	}

	@Override
	public String cliente() {
		// TODO Auto-generated method stub
		return raiz.cliente;
	}

	@Override
	public ArbolCitasTDA hijoDerecho() {
		// TODO Auto-generated method stub
		return raiz.hijoDer;
	}

	@Override
	public ArbolCitasTDA hijoIzquierdo() {
		// TODO Auto-generated method stub
		return raiz.hijoIzq;
	}

	@Override
	public boolean arbolVacio() {
		// TODO Auto-generated method stub
		return (raiz==null);
	}

}
