package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public record ContenedoresHyperVertex(Integer indice, List<Integer> tamContenedores, List<Integer> contCompletos) implements ContenedoresHyperVertexI {

	public static ContenedoresHyperVertex initial() {
		List <Integer> tamC = IntStream.range(0, Datos2.getNumContenedores()).boxed().map(x -> Datos2.getTamContenedor(x)).toList();
		return new ContenedoresHyperVertex(0, tamC , new ArrayList<>());
	}
	
	public static ContenedoresHyperVertex of(Integer i, List<Integer> tamCont, List<Integer> contComp) {
		return new ContenedoresHyperVertex(i,tamCont,contComp);
	}
	
	public List<Boolean> actions() {
		if (this.indice == Datos2.getNumContenedores()) return List.of();
		List <Boolean> alternativas = new ArrayList<> ();
		for (int i = 0; i <= Datos2.getNumElementos(); i++) {
			if (Datos2.getPuedeUbicarse(i, indice)) {
				alternativas.add(true);
			}
			else {
				alternativas.add(false);
				
			}
		}
		
		return alternativas;
	}

	@Override
	public Boolean isBaseCase() {
		// TODO Auto-generated method stub
		return (this.indice == Datos2.getNumContenedores() || this.contCompletos.size() == 0);
	}

	@Override
	public Double baseCaseWeight() {
		// TODO Auto-generated method stub
		return !this.contCompletos.isEmpty() ? 0.: null;
	}

	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return (indice>= 0 || indice <= Datos2.getNumContenedores() || IntStream.range(0, Datos2.getNumContenedores()).boxed().filter(x -> Datos2.getPuedeUbicarse(x, this.indice)).equals(true));
		
	}

	@Override
	public List<Integer> baseCaseSolution() {
		List <Integer> s = new ArrayList<Integer> ();
		return !this.contCompletos.isEmpty() ? s : null;
	}

	public List<Integer> solution(Boolean a, List<List<Integer>> solutions) {
		// TODO Auto-generated method stub
		List<Integer> s = solutions.get(0);
		if (a) {
			s.add(this.indice);
		}
		return s;
	}

	@Override
	public List<ContenedoresHyperVertex> neighbors(Boolean a) {
		// TODO Auto-generated method stub
		List <Integer> contCopia = new ArrayList<Integer> (contCompletos);
		List <Integer> tamCopia = new ArrayList<Integer> (tamContenedores);
		if (a) {
			for (int i  = 0; i < Datos2.getNumElementos(); i++) {
				if (tamCopia.get(this.indice) - Datos2.getTamElemento(i) == 0 && Datos2.getPuedeUbicarse(i, indice)) {
					contCopia.add(this.indice);
				}
			}
			
			tamCopia.add(Datos2.getTamContenedor(indice+1));
		}
		return List.of(ContenedoresHyperVertex.of(indice+1, tamCopia, contCopia));
	}

	@Override
	public ContenedoresHyperEdge edge(Boolean a) {
		// TODO Auto-generated method stub
		List<ContenedoresHyperVertex> targets = this.neighbors(a);
		return ContenedoresHyperEdge.of(this, targets, a);
		
	}

	public static Integer valor(List<Integer> solution) {
		// TODO Auto-generated method stub
		return solution.size();
	}

	

}
