package ejercicio2;

import java.util.List;

import us.lsi.hypergraphs.SimpleHyperEdge;

public record ContenedoresHyperEdge(ContenedoresHyperVertex source,List<ContenedoresHyperVertex> targets, Integer action) implements SimpleHyperEdge<ContenedoresHyperVertex, ContenedoresHyperEdge,Integer>{
	
	public static ContenedoresHyperEdge of(ContenedoresHyperVertex source, List<ContenedoresHyperVertex> targets, Integer action) {
		return new ContenedoresHyperEdge(source,targets,action);
	}
	
	@Override
	public Double weight(List<Double> targetsWeight) {
		// TODO Auto-generated method stub
		return targetsWeight.get(0) + (source.contCompletos().size() < targets.get(0).contCompletos().size() ? 1. : 0.);
	}

	
}
