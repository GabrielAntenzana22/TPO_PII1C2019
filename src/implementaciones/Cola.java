package implementaciones;
import tdas.ColaTDA;

public class Cola implements ColaTDA {
	String [] a;
	int indice;
	
	@Override
	public void inicializar() {
		a = new String [100];
		indice = 0;
		
	}
	@Override
	public void acolar(String valor) {
		for ( int i = indice -1; i >=0; i--)
			a[i+1] = a[i];
		a[0] = valor;
		indice++;
		
	}
	@Override
	public void desacolar() {
		indice --;
	}
	@Override
	public String primero() {
		return a[indice -1];
	}
	@Override
	public boolean colaVacia() {
		return ( indice == 0);
	}
}
