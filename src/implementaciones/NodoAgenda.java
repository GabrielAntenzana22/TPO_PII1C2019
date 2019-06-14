package implementaciones;



public class NodoAgenda {

	String abogado;
	NodoDia primeraFecha;
	NodoAgenda sigMedico;
	
	
	public String getAbogado() {
		return abogado;
	}
	public void setAbogado(String abogado) {
		this.abogado = abogado;
	}
	public NodoDia getPrimaraFecha() {
		return primeraFecha;
	}
	public void setPrimaraFecha(NodoDia primaraFecha) {
		this.primeraFecha = primaraFecha;
	}
	public NodoAgenda getSigMedico() {
		return sigMedico;
	}
	public void setSigMedico(NodoAgenda sigMedico) {
		this.sigMedico = sigMedico;
	}
	
	
	
}
