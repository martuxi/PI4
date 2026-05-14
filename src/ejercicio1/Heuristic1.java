package ejercicio1;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Heuristic1 {
	
	public static Double heuristic(Vertex1 c1,Predicate<Vertex1> goal, Vertex1 c2) {
		return IntStream.range(0, Datos1.getNumCandidatos()).boxed().mapToDouble(x -> Datos1.getValoracion(x)).sum();
	}

}
