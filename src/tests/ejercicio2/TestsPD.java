package tests.ejercicio2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import ejercicio1.CandidatosHyperVertex;
import ejercicio2.ContenedoresHyperEdge;
import ejercicio2.ContenedoresHyperVertex;
import ejercicio2.Datos2;
import us.lsi.graphs.alg.PD;
import us.lsi.graphs.alg.PD.PDType;
import us.lsi.graphs.alg.PD.Sp;
import us.lsi.hypergraphs.GraphTree;
import us.lsi.hypergraphs.SimpleVirtualHyperGraph;

public class TestsPD {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		Locale.setDefault(Locale.of("en", "US"));
		for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

			Datos2.iniDatos("datos_entrada/ejercicio2/DatosEntrada" + id_fichero+ ".txt");
			System.out.println("=============");
			System.out.println("\tResultados para el test " + id_fichero + "\n");
			Datos2.toConsole();

			// V�rtices clave

			ContenedoresHyperVertex p = ContenedoresHyperVertex.initial();

			// Grafo

			System.out.println("\n\n#### Algoritmo PD ####");

			// Algoritmo PD
			
			System.out.println(p);				
			SimpleVirtualHyperGraph<ContenedoresHyperVertex,ContenedoresHyperEdge,Boolean> graph3 = 
					SimpleVirtualHyperGraph.simpleVirtualHyperGraph(p);
			
			PD<ContenedoresHyperVertex, ContenedoresHyperEdge, Boolean,List<Integer>> a = 
					PD.dynamicProgrammingSearch(graph3,PDType.Max);
			
			a.withGraph = true;
			a.search();
			
			//VISTA DEBUG: Mapa de 4 entradas, con todos los valores a null --> No funciona el SP<Boolean, CandidatosHyperEdge> ?? ¿Por qué?
			Map<ContenedoresHyperVertex, Sp<Boolean, ContenedoresHyperEdge>> s = a.getSolutionsTree();
			
			if (s.get(p) == null) {
				System.out.println("No hay solución");
			} else {			
				GraphTree<ContenedoresHyperVertex, ContenedoresHyperEdge, Boolean, List<Integer>> tree = 
						GraphTree.graphTree(p,s);

				System.out.println(tree.solution());

				System.out.println(ContenedoresHyperVertex.valor(tree.solution()));
			}
		}
	}
	
}