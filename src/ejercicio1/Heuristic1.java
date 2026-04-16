package ejercicio1;

import java.util.function.Predicate;

public class Heuristic1 {
	
	public static Double heuristic (Vertex1 v1, Predicate<Vertex1> goal, Vertex1 v2) {
		return (Double) (Datos1.getNumCandidatos()-v1.indice()*5.0);
	}

}
