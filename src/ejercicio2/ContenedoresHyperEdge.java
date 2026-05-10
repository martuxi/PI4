package ejercicio2;

import java.util.List;

import us.lsi.hypergraphs.SimpleHyperEdge;

public record ContenedoresHyperEdge(ContenedoresHyperVertex source,List<ContenedoresHyperVertex> targets, Boolean action) implements SimpleHyperEdge<ContenedoresHyperVertex, ContenedoresHyperEdge, Boolean>{
	
	public static ContenedoresHyperEdge of(ContenedoresHyperVertex source, List<ContenedoresHyperVertex> targets, Boolean action) {
		return new ContenedoresHyperEdge(source,targets,action);
	}
	
	@Override
	public Double weight(List<Double> targetsWeight) {
		// TODO Auto-generated method stub
		return targetsWeight.get(0) + (this.action ? 1.:0.);
	}

	
}
