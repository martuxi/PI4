package ejercicio1;

import java.util.List;

import us.lsi.hypergraphs.SimpleHyperEdge;

public record CandidatosHyperEdge(CandidatosHyperVertex source, List<CandidatosHyperVertex> targets, Boolean action) implements SimpleHyperEdge<CandidatosHyperVertex, CandidatosHyperEdge, Boolean> {

	public static CandidatosHyperEdge of(CandidatosHyperVertex source, List<CandidatosHyperVertex> targets, Boolean action) {
		CandidatosHyperEdge a = new CandidatosHyperEdge(source, targets, action);
		return a;
	}
	@Override
	public Double weight(List<Double> targetsWeight) {
		
		//Lo cambiaste
		return targetsWeight.get(0) + (this.action ? Datos1.getValoracion(source.indice()):0.);
		
	}

}
