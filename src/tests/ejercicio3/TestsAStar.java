package tests.ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import ejercicio2.Datos2;
import ejercicio3.Datos3;
import ejercicio3.Edge3;
import ejercicio3.Heuristic3;
import ejercicio3.Solucion3;
import ejercicio3.Vertex3;
import ejercicio3.Vertex3I;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestsAStar {

	public static void main(String[] args) throws IOException {
		
		// Set up
				Locale.setDefault(Locale.of("en", "US"));

				for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

					Datos3.iniDatos("datos_entrada/ejercicio3/DatosEntrada" + id_fichero + ".txt");
					System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
					
				}
				// V�rtices clave

				Vertex3 start = Vertex3I.start();

				// Grafo

				System.out.println("#### Algoritmo A* ####");

				// Algoritmo A*
				EGraph<Vertex3, Edge3> graph =
							EGraph.virtual(start)
							.pathType(PathType.Sum)
							.type(Type.Min)
							.edgeWeight(x -> x.weight())
							.heuristic(Heuristic3::heuristic)
							.build();
							
				AStar<Vertex3, Edge3,?> aStar = AStar.ofGreedy(graph);
					
				GraphPath<Vertex3, Edge3> gp = aStar.search().get();
					
				List<Integer> gp_as = gp.getEdgeList().stream().map(x -> x.action())
							.collect(Collectors.toList()); // getEdgeList();
			
				Solucion3 s_as = Solucion3.of(gp);

				System.out.println(s_as);
				System.out.println(gp_as);

				/*GraphColors.toDot(aStar.outGraph(), "ficheros_generados/p4/ejemplo3/AlumnosAStarGraph1.gv", 
							v -> v.toGraph(),
							e -> e.action().toString(), 
							v -> GraphColors.colorIf(Color.red,v.goal()),
							e -> GraphColors.colorIf(Color.red, gp.getEdgeList().contains(e)));*/
	}
}