package Test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import algoritmos.IAlgoritmo;
import implementaciones.AgendaCitas;
import implementaciones.Algoritmos;
import implementaciones.ArbolCitas;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ConjuntoTDA;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AgendaCitasTDA act = new AgendaCitas();
        act.inicializar();
        ArbolCitasTDA arbol = new ArbolCitas();
        arbol.inicializar();
        arbol.agregar("05:00", "K");
        arbol.agregar("06:00", "K");
        arbol.agregar("04:00", "K");
        
        inOrder(arbol);
        System.out.println(arbol.hora());
        System.out.println(arbol.cliente());
        System.out.println(arbol.hijoDerecho().hora());
        System.out.println(arbol.hijoIzquierdo().hora());
	}

	private static void inOrder(ArbolCitasTDA arbol) {
		// TODO Auto-generated method stub
		if (!arbol.arbolVacio()){
			inOrder(arbol.hijoIzquierdo()); 
			System.out.println(arbol.hora()+" "+arbol.cliente()); 
			inOrder(arbol.hijoDerecho());
			}
	}

    
}
