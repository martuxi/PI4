package ejercicio1;

import java.util.Set;

import us.lsi.graphs.virtual.VirtualVertex;

public interface Vertex1 extends VirtualVertex<Vertex1, Edge1, Integer>{
	
	Integer indice(); 
	Double presRest(); 
	Set<Integer> candSel();
	Set<String> cualidadesCubrir();

}
