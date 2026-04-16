package ejercicio2;

import java.util.List;

import us.lsi.graphs.virtual.VirtualVertex;

public interface Vertex2 extends VirtualVertex<Vertex2, Edge2, Integer> {
	
	Integer indice();
	
	Integer getTamContenedor();
	
	Integer getTamElemento();
	
	Boolean getPuedeUbicarse();
	
	List <Integer> contenedoresCompletos();
	
	

}
