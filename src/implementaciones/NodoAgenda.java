package implementaciones;



public class NodoAgenda {

	String abogado;
	NodoDia primaraFecha;
	NodoAgenda sigMedico;
	
	
	public String getAbogado() {
		return abogado;
	}
	public void setAbogado(String abogado) {
		this.abogado = abogado;
	}
	public NodoDia getPrimaraFecha() {
		return primaraFecha;
	}
	public void setPrimaraFecha(NodoDia primaraFecha) {
		this.primaraFecha = primaraFecha;
	}
	public NodoAgenda getSigMedico() {
		return sigMedico;
	}
	public void setSigMedico(NodoAgenda sigMedico) {
		this.sigMedico = sigMedico;
	}
	
	
	
}
