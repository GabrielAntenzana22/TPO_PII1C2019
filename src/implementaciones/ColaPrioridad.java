package implementaciones;
import tdas.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA{
	
	String [] elementos;
	String [] prioridades;
	int indice;

	@Override
	public void inicializar() {
		indice = 0;
		elementos = new String [1000];
		prioridades = new String [1000];
		
	}

	@Override
	public void acolar(String valor, String prioridad) {
		// desplaza a derecha los elementos de la cola mientras
		// estos tengan mayor o igual prioridad que la de x
		int j;
		prioridad = prioridad.replaceAll(":", "");
		for (j = indice ; j>0 && Integer.valueOf(prioridades[j -1]) >= Integer.valueOf(prioridad); j-- ){
			elementos[j] = elementos[j -1];
			prioridades[j] = prioridades[j -1];
		}
		
		elementos[j] = valor;
		prioridades[j] = prioridad;
		indice++;
		
	}

	@Override
	public void dasacolar() {
		indice --;		
	}

	@Override
	public String primero() {
		return elementos[indice -1];
	}

	@Override
	public String prioridad() {
		return prioridades[indice -1];
	}

	@Override
	public boolean colaVacia() {
		return ( indice == 0);
	}

}
