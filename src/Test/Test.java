package Test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import algoritmos.IAlgoritmo;
import implementaciones.AgendaCitas;
import implementaciones.Algoritmos;
import tdas.AgendaCitasTDA;
import tdas.ConjuntoTDA;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AgendaCitasTDA act = new AgendaCitas();
        act.inicializar();
        act.agregarNuevoDia("Juan", "Lunes", "1996/08/17");
        act.agregarNuevoDia("Juan", "Lunes", "1996/08/18");
        act.agregarNuevoDia("Juana", "Lunes", "1997/08/17");
        act.agregarNuevoDia("Juana", "Lunes", "1997/08/16");
        act.agregarNuevoDia("Jose", "Lunes", "1998/08/17");
        act.agregarNuevoDia("Kevin", "Lunes", "1999/08/17");
        act.agregarNuevoDia("Kevin", "Lunes", "1999/08/15");
        act.eliminarFecha("Juan", "1996/08/17");
        act.eliminarFecha("Juan", "1996/08/18");
        act.eliminarFecha("Juana", "1997/08/16");
        act.eliminarFecha("Kevin", "1999/08/15");
        act.mostrarAgendas();
        
	}
    
}
