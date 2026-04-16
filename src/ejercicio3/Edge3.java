package ejercicio3;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Edge3 (Vertex3 source, Vertex3 target, Integer action, Double weight) implements SimpleEdgeAction<Vertex3, Integer>{
	
	public static Edge3 of(Vertex3 source, Vertex3 target, Integer durAc) {
		if (source.indice() == 0) return new Edge3(source,target,durAc,0.);
		Integer ultVert = source.camino().getLast();
		Double peso = Datos3.grafo.getEdge(Datos3.getVertex(ultVert),Datos3.getVertex(durAc)).esfuerzo();
		if (source.indice()==Datos3.N-1) {
			peso+= Datos3.grafo.getEdge(Datos3.getVertex(0), Datos3.getVertex(durAc)).esfuerzo();
			
		}
		return new Edge3(source,target, durAc, peso);
	}
}
