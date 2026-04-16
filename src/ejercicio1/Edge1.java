package ejercicio1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Edge1(Vertex1 source, Vertex1 target, Integer action, Double weight) implements SimpleEdgeAction<Vertex1,Integer>{

	public static Edge1 of(Vertex1 v1, Vertex1 v2, Integer act) {
		//Si tomamos ese camino, obtenemos la valoración de ese candidato y ese es el peso de la arista. EOC no tenemos peso de la arista. 
		Integer valoracion = act > 0? Datos1.getValoracion(v1.indice()) : 0;
		return new Edge1(v1,v2,act,valoracion *1.0);
				
	}
	
}
