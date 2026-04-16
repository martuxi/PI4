package ejercicio1;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Heuristic1 {
	
	public static Double heuristic (Vertex1 v1, Predicate<Vertex1> goal, Vertex1 v2) {
		return IntStream.range(v1.indice(),Datos1.getNumCandidatos() -1)
				.mapToDouble(c-> Datos1.getValoracion(c))
				.sum();
	}

}
