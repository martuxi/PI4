package ejercicio2;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Heuristic2 {
	
	public static Double heuristic(Vertex2 v1, Predicate<Vertex2> goal, Vertex2 v2) {
		
		return IntStream.range(0, Datos2.getNumContenedores()).boxed().mapToDouble(x -> Datos2.getTamContenedor(x)).sum();
	}

}
