package Test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import algoritmos.IAlgoritmo;
import implementaciones.AgendaCitas;
import implementaciones.Algoritmos;
import implementaciones.ArbolCitas;
import implementaciones.Cola;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AgendaCitasTDA act = new AgendaCitas();
        act.inicializar();
        ArbolCitasTDA arbol = new ArbolCitas();
        arbol.inicializar();
        act.agregarNuevoDia("Juan", "Lunes", "2019/01/01");
        act.agregarNuevoDia("Juan", "Lunes", "2019/01/02");
        act.agregarNuevoDia("Jose", "Lunes", "2019/01/01");
        act.agregarNuevoDia("Jose", "Lunes", "2019/01/02");
        act.agregarNuevaCita("Juan", "2019/01/01", "22:00", "A");
        act.agregarNuevaCita("Juan", "2019/01/01", "24:00", "B");
        act.agregarNuevaCita("Juan", "2019/01/01", "18:30", "C");
        act.agregarNuevaCita("Juan", "2019/01/01", "08:00", "D");
        act.agregarNuevaCita("Juan", "2019/01/01", "05:00", "e");
        act.agregarNuevaCita("Juan", "2019/01/01", "08:30", "i");
        act.agregarNuevaCita("Juan", "2019/01/01", "17:00", "v");
        act.agregarNuevaCita("Juan", "2019/01/01", "12:00", "n");
        
        
        act.mostrarAgendas();      
        
	}
	
}
