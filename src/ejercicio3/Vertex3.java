package ejercicio3;

import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public interface Vertex3 extends VirtualVertex<Vertex3,Edge3,Integer> {
	
	Integer indice();
	
	List<Integer> camino();


}
