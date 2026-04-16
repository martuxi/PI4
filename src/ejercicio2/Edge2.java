package ejercicio2;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Edge2(Vertex2 source, Vertex2 target, Integer action, Double weight) implements SimpleEdgeAction<Vertex2,Integer>{

	public static Edge2 of(Vertex2 v1, Vertex2 v2, Integer action) {
		Double peso = (v1.contenedoresCompletos().size()<v2.contenedoresCompletos().size()? 1.: 0.);
		return new Edge2(v1,v2,action, peso);
	}

}
