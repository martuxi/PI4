package ejercicio1;

import java.util.Set;

import us.lsi.hypergraphs.VirtualHyperVertex;

public interface CandidatosHyperVertexI extends VirtualHyperVertex<CandidatosHyperVertex,CandidatosHyperEdge, Boolean, Set<Integer>> {
	
	Integer indice();
	Set<Integer> cualidadesACubrir();
	Double presRest();
	Set<Integer> cand();
	
	
}
