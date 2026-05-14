package ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Edge1(Vertex1 source, Vertex1 target, Integer action, Double weight) implements SimpleEdgeAction<Vertex1, Integer>{
	
	public static Edge1 of(Vertex1 c1, Vertex1 c2, Integer action) {
		Double w = action>0 ? Datos1.getValoracion(c1.indice()): 0.; 
		return new Edge1(c1,c2,action,w);
	}
}
