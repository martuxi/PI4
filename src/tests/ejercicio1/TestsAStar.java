package tests.ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ejercicio1.Datos1;
import ejercicio1.Edge1;
import ejercicio1.Heuristic1;
import ejercicio1.Solucion1;
import ejercicio1.Vertex1;
import ejercicio1.Vertex1I;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;
public class TestsAStar {
	public static void main(String [] args)throws IOException  {
		

	// Set up
			Locale.setDefault(Locale.of("en", "US"));

			for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

				Datos1.iniDatos("datos_entrada/ejercicio1/DatosEntrada"+ id_fichero + ".txt");
				System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");

				// V�rtices clave

				Vertex1 start = Vertex1I.start();

				// Grafo

				System.out.println("#### Algoritmo A* ####");

				// Algoritmo A*
				EGraph<Vertex1, Edge1> graph =
						EGraph.virtual(start)
						.pathType(PathType.Sum)
						.type(Type.Min)
						.edgeWeight(x -> x.weight())
						.heuristic(Heuristic1::heuristic)
						.build();
						
				AStar<Vertex1, Edge1,?> aStar = AStar.ofGreedy(graph);
				
				GraphPath<Vertex1, Edge1> gp = aStar.search().get();
				
				List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
						.collect(Collectors.toList()); // getEdgeList();
				
				
				Solucion1 s_as = Solucion1.create(gp_as);
				
				
				System.out.println(s_as);
				System.out.println(gp_as);

//				GraphColors.toDot(aStar.outGraph, "ficheros_generados/p4/ejemplo1/multiconjuntosAStarGraph.gv", 
//						v -> v.toGraph(),
//						e -> e.action().toString(), 
//						v -> GraphColors.colorIf(Color.red, Vertex1I.goal().test(v)),
//						e -> GraphColors.colorIf(Color.red, gp.getEdgeList().contains(e)));
			}
		}
}