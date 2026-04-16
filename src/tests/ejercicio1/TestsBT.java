package tests.ejercicio1;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio1.Datos1;
import ejercicio1.Edge1;
import ejercicio1.Heuristic1;
import ejercicio1.Solucion1;
import ejercicio1.Vertex1;
import ejercicio1.Vertex1I;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestsBT {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		// Set up
	Locale.setDefault(Locale.of("en", "US"));

	for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

		Datos1.iniDatos("datos_entrada/ejercicio1/DatosEntrada"+ id_fichero + ".txt");
		System.out.println("=============");
		System.out.println("\tResultados para el test " + id_fichero + "\n");
		
		Datos1.toConsole("datos_entrada/ejercicio1/DatosEntrada"+ id_fichero + ".txt");

		// V�rtices clave

		Vertex1 start = Vertex1I.start();

		// Grafo

		

		System.out.println("\n#### Algoritmo BT ####");
		
		// Algoritmo BT
		
		EGraph<Vertex1, Edge1> graph =
				EGraph.virtual(start)
				.pathType(PathType.Sum)
				.type(Type.Max)
				.edgeWeight(x -> x.weight())
				.heuristic(Heuristic1::heuristic)
				.build();
		
		
		GreedyOnGraph<Vertex1, Edge1> rr = GreedyOnGraph.of(graph);
		
		GraphPath<Vertex1, Edge1> r = rr.path();
		
		
		BT<Vertex1, Edge1, Solucion1> bta = BT.of(graph,
				Solucion1::of, null, null, true);

		if (rr.isSolution(r)) {
			bta = BT.of(graph, Solucion1::of, r.getWeight(), r, true);
		}
		Optional<GraphPath<Vertex1, Edge1>> gp = bta.search();
		System.out.println(Solucion1.of(gp.get()));
		
//		System.out.println(bta.path.getEdgeList().stream().map(x -> x.action())
//				.collect(Collectors.toList()));
		
		
//		GraphColors.toDot(bta.graph(), "ficheros_generados/p4/ejemplo1/multiconjuntosBTGraph.gv", 
//				v -> v.toGraph(),
//				e -> e.action().toString(), 
//				v -> GraphColors.colorIf(Color.red, Vertex1.goal().test(v)),
//				e -> GraphColors.colorIf(Color.red, bta.optimalPath.getEdgeList().contains(e)));

		}
	}
}