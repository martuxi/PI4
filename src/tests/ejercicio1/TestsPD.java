package tests.ejercicio1;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

import ejercicio1.CandidatosHyperEdge;
import ejercicio1.CandidatosHyperVertex;
import ejercicio1.CandidatosHyperVertexI;
import ejercicio1.Datos1;
import us.lsi.graphs.alg.PD;
import us.lsi.graphs.alg.PD.PDType;
import us.lsi.graphs.alg.PD.Sp;
import us.lsi.hypergraphs.GraphTree;
import us.lsi.hypergraphs.SimpleVirtualHyperGraph;
public class TestsPD {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		// Set up
				Locale.setDefault(Locale.of("en", "US"));
				for (Integer id_fichero = 1; id_fichero < 4; id_fichero++) {

					Datos1.iniDatos("datos_entrada/ejercicio1/DatosEntrada" + id_fichero+ ".txt");
					System.out.println("=============");
					System.out.println("\tResultados para el test " + id_fichero + "\n");
					Datos1.toConsole("datos_entrada/ejercicio1/DatosEntrada" + id_fichero + ".txt");

					// V�rtices clave

					CandidatosHyperVertex p = CandidatosHyperVertex.initial();

					// Grafo

					System.out.println("\n\n#### Algoritmo PD ####");

					// Algoritmo PD
					
					System.out.println(p);				
					SimpleVirtualHyperGraph<CandidatosHyperVertex,CandidatosHyperEdge,Boolean> graph3 = 
							SimpleVirtualHyperGraph.simpleVirtualHyperGraph(p);
					
					PD<CandidatosHyperVertex, CandidatosHyperEdge, Boolean, Set<Integer>> a = 
							PD.dynamicProgrammingSearch(graph3,PDType.Max);
					
					//a.withGraph = true;
					a.search();
					
					//VISTA DEBUG: Mapa de 4 entradas, con todos los valores a null --> No funciona el SP<Boolean, CandidatosHyperEdge> ?? ¿Por qué?
					Map<CandidatosHyperVertex, Sp<Boolean, CandidatosHyperEdge>> s = a.getSolutionsTree();
					
					if (s.get(p) == null) {
						System.out.println("No hay solución");
					} else {			
						GraphTree<CandidatosHyperVertex, CandidatosHyperEdge, Boolean, Set<Integer>> tree = 
								GraphTree.graphTree(p,s);

						System.out.println(tree.solution());

						System.out.println(CandidatosHyperVertex.valor(tree.solution()));
					}
				}
	}
	
}