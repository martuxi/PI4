package ejercicio2;

import java.util.function.Predicate;

public class Heuristic2 {

	public static Double heuristic (Vertex2 v1, Predicate<Vertex2> goal, Vertex2 v2) {
		return v1.contenedoresCompletos().size() + Math.min((double) Datos2.getNumContenedores() - v1.contenedoresCompletos().size(), 
					(double) Datos2.getNumElementos() - v1.indice());
		

	}
}
