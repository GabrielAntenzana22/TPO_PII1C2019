package implementaciones;

import tdas.ArbolCitasTDA;

public class NodoArbol {

	String hora;
	String cliente;
	ArbolCitasTDA hijoIzq;
	ArbolCitasTDA hijoDer;
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public ArbolCitasTDA getHijoIzq() {
		return hijoIzq;
	}
	public void setHijoIzq(ArbolCitasTDA hijoIzq) {
		this.hijoIzq = hijoIzq;
	}
	public ArbolCitasTDA getHijoDer() {
		return hijoDer;
	}
	public void setHijoDer(ArbolCitasTDA hijoDer) {
		this.hijoDer = hijoDer;
	}
}
