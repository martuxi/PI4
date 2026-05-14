package tests.ejercicio1;

import java.io.IOException;

import ejercicio1.CandidatosPD_Manual;
import ejercicio1.Datos1;

public class TestsPDManual {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		
		for (int id_fichero = 1; id_fichero < 4; id_fichero++) {
			Datos1.iniDatos("datos_entrada/ejercicio1/DatosEntrada" + id_fichero + ".txt");
			System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
			System.out.println("Solucion obtenida: " + CandidatosPD_Manual.search());
		}
	}
	
}