package ejercicio1;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import us.lsi.common.IntegerSet;

public record CandidatosHyperVertex (Integer indice, Set<Integer> cualidadesACubrir, Double presRest, Set<Integer> candidatosNoContr) implements CandidatosHyperVertexI{

	public static CandidatosHyperVertex initial() {
		Set<Integer> cualCub = new HashSet<Integer>();
		for (Integer j = 0; j < Datos1.getNumCualidades(); j++) {

			cualCub.add(j);

		}

		return of(0, cualCub, Datos1.getPresupuestoMax()*1.,new HashSet <Integer> ());
	}

	public static CandidatosHyperVertex of(Integer indice, Set<Integer> cualidadesACubrir, Double presRest, Set<Integer> candidatosNoContr) {
		return new CandidatosHyperVertex(indice, cualidadesACubrir,presRest,candidatosNoContr);
	}

	public Boolean isBaseCase() {
		return (this.presRest < IntStream.range(indice, Datos1.getNumCandidatos()).mapToDouble(i -> Datos1.getSueldoMin(i)).min().orElse(0)
				|| this.indice == Datos1.getNumCandidatos());
	}

	public Double baseCaseWeight() {
		return cualidadesACubrir().isEmpty()?0.:null;
	}

	public Set<Integer> baseCaseSolution() {
		Set <Integer> s = new HashSet<Integer> ();
		return this.cualidadesACubrir.isEmpty() ? s:null;
	}

	public List<Boolean> actions() {
		if(indice == Datos1.getNumCandidatos()) return List.of();
		List<Boolean> alternativas = new ArrayList<>();
		if (Datos1.getSueldoMin(indice) <= this.presRest
				&& !this.candidatosNoContr.contains(indice)) {
			alternativas.add(true);
		}
		
		return alternativas;
	}

	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return this.indice>=0 || this.indice<=Datos1.getNumCandidatos() && this.presRest <= Datos1.getPresupuestoMax();
	}

	public List<CandidatosHyperVertex> neighbors(Boolean a) {
		Set<Integer> cualidades = new HashSet<>(cualidadesACubrir());
		Double presupuesto = presRest();
		Set<Integer> candidatos = new HashSet<>(candidatosNoContr());
		candidatos.remove(indice);
		if (a) {
			cualidades.removeAll(Datos1.getCualidadesInt(indice));
			presupuesto = presupuesto - Datos1.getSueldoMin(indice);
			candidatos.addAll(IntegerSet.range(0,Datos1.getNumCandidatos()).stream().filter(x -> Datos1.getSonIncompatibles(indice, x)).toList());
		}

		return List.of(CandidatosHyperVertex.of(indice+1, cualidades, presupuesto, candidatos));
	}

	public CandidatosHyperEdge edge(Boolean a) {
		// TODO Auto-generated method stub
		List<CandidatosHyperVertex> targets = this.neighbors(a);
		return CandidatosHyperEdge.of(this,targets,a);
	}

	public static Integer valor(Set<Integer> conj) {
		return conj.size();
	}

	@Override
	public Set<Integer> solution(Boolean a, List<Set<Integer>> solutions) {
		// TODO Auto-generated method stub
		Set<Integer> s = solutions.get(0);
		if (a) {
			s.add(indice);
		}
		return s;
	}
	
	@Override
	public Set<Integer> cand() {
		// TODO Auto-generated method stub
		return this.candidatosNoContr;
	}

}
