package implementaciones;

import tdas.ArbolCitasTDA;

public class NodoDia {

	String dia;
	String fecha;
	ArbolCitasTDA turnos;
	NodoDia siguienteFecha;
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public ArbolCitasTDA getTurnos() {
		return turnos;
	}
	public void setTurnos(ArbolCitasTDA turnos) {
		this.turnos = turnos;
	}
	public NodoDia getSiguienteFecha() {
		return siguienteFecha;
	}
	public void setSiguienteFecha(NodoDia siguienteFecha) {
		this.siguienteFecha = siguienteFecha;
	}

}
