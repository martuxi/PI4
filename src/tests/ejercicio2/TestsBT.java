package tests.ejercicio2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;

import ejercicio2.Datos2;
import ejercicio2.Edge2;
import ejercicio2.Heuristic2;
import ejercicio2.Solucion2;
import ejercicio2.Vertex2;
import ejercicio2.Vertex2I;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
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

				for (Integer id_fichero = 1; id_fichero < 3; id_fichero++) {

					Datos2.iniDatos("datos_entrada/ejercicio2/DatosEntrada" + id_fichero + ".txt");
					System.out.println("\n\n>\tResultados para el test " + id_fichero + "\n");
					Datos2.toConsole();
					
					// V�rtices clave

					Vertex2 start = Vertex2I.start();

					// Grafo

					EGraph<Vertex2, Edge2> graph = 
							EGraph.virtual(start)
							.pathType(PathType.Last)
							.type(Type.Max)
							.vertexWeight(x-> (double) x.contenedoresCompletos().size())
							.build();

					Boolean conVoraz = false;
					
					System.out.println("\n\n#### Algoritmo BT ####");
					System.out.println(conVoraz?"\nCon Voraz":"\nSin Voraz");

					BT<Vertex2, Edge2, Solucion2> bta = null;
					Solucion2 sv = null;
					
					Optional<GraphPath<Vertex2, Edge2>> gp = Optional.empty();
					if (conVoraz) {
						GreedyOnGraph<Vertex2, Edge2> ga = GreedyOnGraph.of(graph);
						gp = ga.search();
						if (gp.isPresent()) sv = Solucion2.create(gp.get());
						System.out.println("Sv = "+sv);
					}
					if(gp.isPresent()) 
						bta = BT.of(graph,Solucion2::create,gp.get().getWeight(),gp.get(),true);
					else 
						bta = BT.of(graph, null, null, null, true);
					bta.search();
					
					sv = Solucion2.create(bta.optimalPath().orElse(null));
					List<Edge2> le = bta.optimalPath().get().getEdgeList();
					
					System.out.println("Sol opt = "+sv);

					//var outGraph = bta.outGraph();
				/*if(outGraph!=null)
						GraphColors.toDot(bta.outGraph(),"ficheros_generados/p4/ejemplo2/subconjuntosBTGraph.gv",
							v->v.toGraph(),
							e->e.action().toString(),
							v->GraphColors.colorIf(Color.red,v.goal()),
							e->GraphColors.colorIf(Color.red,le.contains(e))
							);
					
				}*/
			}
}	