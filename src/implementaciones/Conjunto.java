package implementaciones;

import tdas.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA {
	String[] a;
	int cant;
	@Override
	
	public void inicializar() {
		// TODO Auto-generated method stub
		a=new String[1000];
		cant = 0;
	}

	@Override
	public boolean conjuntoVacio() {
		// TODO Auto-generated method stub
		return cant == 0;
	}

	@Override
	public void agregar(String valor) {
		// TODO Auto-generated method stub
		if (!this.pertenece(valor)){
			a[cant] = valor;
			cant++; 
		}
	}

	@Override
	public String elegir() {
		// TODO Auto-generated method stub
		return a[cant - 1];
	}

	@Override
	public void sacar(String valor) {
		// TODO Auto-generated method stub
		int i = 0;
		while (i<cant && a[i]!=valor) { 
		i++;
		}
		if (i<cant){
		   a[i] = a[cant-1]; 
		   cant--;
		}
	}

	@Override
	public boolean pertenece(String valor) {
		// TODO Auto-generated method stub
		int i = 0;
		while (i<cant && a[i]!=valor) i++;
		return (i < cant);
	}

}
