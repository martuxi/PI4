package tests.ejercicio2;

import java.io.IOException;

import ejercicio2.ContenedoresPD_Manual;
import ejercicio2.Datos2;



public class TestsPDManual {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		for (int id_fichero = 1; id_fichero < 4; id_fichero++) {
			Datos2.iniDatos("datos_entrada/ejercicio2/DatosEntrada" + id_fichero +".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			System.out.println("Solucion obtenida: " + ContenedoresPD_Manual.search());
		}
	}
	
}