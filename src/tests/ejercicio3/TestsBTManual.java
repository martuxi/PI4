package tests.ejercicio3;

import java.io.IOException;

import ejercicio3.Datos3;
import ejercicio3.GrafosBT;

public class TestsBTManual {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			Datos3.iniDatos("datos_entrada/ejercicio3/DatosEntrada" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			Datos3.toConsole();
			
			GrafosBT.search();
			
			System.out.println(GrafosBT.getSolucion()+ "\n");
//			
			
		}
	}	
}