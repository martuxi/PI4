package ejercicio2;

import java.util.List;
import java.util.Set;

import us.lsi.hypergraphs.VirtualHyperVertex;

public interface ContenedoresHyperVertexI extends VirtualHyperVertex<ContenedoresHyperVertex,ContenedoresHyperEdge, Boolean, List<Integer>> {
	
	Integer indice();
	List<Integer> tamContenedores();
	List<Integer> contCompletos();
	
	
}
