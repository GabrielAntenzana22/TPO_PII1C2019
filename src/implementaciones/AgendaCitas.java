package implementaciones;

import tdas.AgendaCitasTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class AgendaCitas implements AgendaCitasTDA {

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarNuevoDia(String abogado, String dia, String fecha) {
		// TODO Auto-generated method stub
		
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

}
