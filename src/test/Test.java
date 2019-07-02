package test;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import algoritmos.IAlgoritmo;
import implementaciones.AgendaCitas;
import implementaciones.Algoritmos;
import implementaciones.ArbolCitas;
import implementaciones.Cola;
import implementaciones.ColaPrioridad;
import implementaciones.Conjunto;
import tdas.AgendaCitasTDA;
import tdas.ArbolCitasTDA;
import tdas.ColaPrioridadTDA;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;

public class Test {

	public static void main(String[] args) {
		
		/*Calendar rightNow = Calendar.getInstance();
		System.out.println(rightNow.get(Calendar.YEAR));
		System.out.println(rightNow.get(Calendar.MONTH));
		System.out.println(rightNow.get(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Locale locale = Locale.getDefault();	
		Calendar auxCal = Calendar.getInstance();
		auxCal.setTime(sdf.parse("2019/01/01", new ParsePosition(0)));
		System.out.println(auxCal.get(Calendar.DATE));
		String auxFecha = sdf.format(auxCal.getTime());
		*/
		IAlgoritmo algoritmo = new Algoritmos();
		AgendaCitasTDA agenda = new AgendaCitas();
		agenda.inicializar();
		agenda.agregarNuevoDia("un abogado", "miercoles", "2019/01/02");
		agenda.agregarNuevaCita("un abogado", "2019/01/02", "10:30", "un cliente");
		agenda.agregarNuevoDia("otro abogado", "miercoles", "2019/01/02");
		agenda.agregarNuevaCita("otro abogado", "2019/01/02", "11:30", "un cliente diferente");
		agenda.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agenda.agregarNuevaCita("un abogado", "2019/01/01", "14:30", "otro cliente");
		agenda.agregarNuevaCita("un abogado", "2019/01/01", "14:00", "otro cliente mas");
		agenda.agregarNuevoDia("un abogado", "lunes", "2019/01/07");
		agenda.agregarNuevaCita("un abogado", "2019/01/07", "13:30", "un ultimo cliente");
		agenda.mostrarAgendas();
		String[][] citas = algoritmo.obtenerCitas(agenda, "un abogado", "2018/12/31");

		
		for(int i=0; i < citas.length; i++) {
			for(int j=0; j<citas[i].length; j++) {
				System.out.print(citas[i][j]+" ");
			}
			System.out.println();
		}
		
		
		/*AgendaCitasTDA agenda = new AgendaCitas();
	    agenda.inicializar();
	    IAlgoritmo algoritmo = new Algoritmos();
		
		agenda.agregarNuevoDia("un abogado", "martes", "2019/01/01");
		agenda.agregarNuevaCita("un abogado", "2019/01/01", "12:00", "un cliente");
		agenda.agregarNuevaCita("un abogado", "2019/01/01", "13:00", "otro cliente");

		// Operaci�n
		ConjuntoTDA abogadosConMasCitas = algoritmo.masCitas(agenda, "2019/01/02", "2019/01/31");
		while(!abogadosConMasCitas.conjuntoVacio()) {
			String abogado = abogadosConMasCitas.elegir();
			System.out.println(abogado);
			abogadosConMasCitas.sacar(abogado);
		}
		System.out.println(abogadosConMasCitas.conjuntoVacio());*/

        /*AgendaCitasTDA act = new AgendaCitas();
        act.inicializar();
        IAlgoritmo method = new Algoritmos();
        act.agregarNuevoDia("Juan", "Lunes", "2019/01/01");
        act.agregarNuevoDia("Juan", "Martes", "2019/01/02");
        act.agregarNuevoDia("Juan", "Domingo", "2019/01/07");
        act.agregarNuevoDia("Juan", "Lunes", "2019/01/08");
        act.agregarNuevoDia("Juan", "Martes", "2019/01/02");
        act.agregarNuevoDia("Jose", "Miercoles", "2019/01/03");
        act.agregarNuevoDia("Jose", "Jueves", "2019/01/04");
        act.agregarNuevoDia("Kevin", "Jueves", "2019/01/05");
        act.agregarNuevaCita("Juan", "2019/01/01","08:00", "A");
        act.agregarNuevaCita("Juan", "2019/01/07","10:00", "A");
        act.agregarNuevaCita("Juan", "2019/01/01","10:00", "B");
        act.agregarNuevaCita("Juan", "2019/01/02","15:00", "C");
        act.agregarNuevaCita("Juan", "2019/01/02","14:00", "D");
        act.agregarNuevaCita("Juan", "2019/01/02","13:30", "E");
        act.agregarNuevaCita("Juan", "2019/01/07","13:30", "E");
        act.agregarNuevaCita("Juan", "2019/01/08","13:30", "E");
        act.agregarNuevaCita("Juan", "2019/01/09","13:30", "E");
        act.agregarNuevaCita("Jose", "2019/01/03","08:00", "F");
        act.agregarNuevaCita("Jose", "2019/01/03","10:00", "G");
        act.agregarNuevaCita("Jose", "2019/01/03","15:00", "H");
        act.agregarNuevaCita("Jose", "2019/01/04","13:30", "I");
        act.agregarNuevaCita("Jose", "2019/01/04","14:00", "A");
        act.agregarNuevaCita("Kevin", "2019/01/05","14:00", "A");
        
        act.mostrarAgendas();  
        
        System.out.println(method.abogadoUltimaVez(act, "A"));
        
        String [][] citas = method.obtenerCitas(act, "Juan", "2019/01/01");
        
        System.out.println("CITAS: ");
        for (int i=0;i<10;i++) {
        	 for (int j=0;j<10;j++) {
        		 if (citas[i][j]!=null) {
        			 System.out.println(citas[i][j]);
        		 }
        	 }
        }
        
        String [][] reuniones = method.conQuienSeReunio(act, "A");
        
        System.out.println("\nREUNIONES: ");
        for (int i=0;i<10;i++) {
       	 for (int j=0;j<10;j++) {
       		 if (reuniones[i][j]!=null) {
       			 System.out.println(reuniones[i][j]);
       			 
       		 }
       	 }
       }
           
     */
		
       // System.out.println(method.abogadoUltimaVez(act, "A"));
        
     /*   ColaPrioridadTDA c = new ColaPrioridad();
        c.inicializar();
        c.acolar("Juan", "08:00");
        c.acolar("Jose", "07:00");
        while(!c.colaVacia()) {
        	System.out.println(c.primero());
        	c.dasacolar();
        }
        */
        
        /*ColaPrioridadTDA cola =  method.libresTotal(act, "2019/01/01");
        
        while(!cola.colaVacia()) {
        	System.out.println(cola.primero() + "  " + cola.prioridad());
        	cola.dasacolar();
        }*/
        
        
	}
	
}
