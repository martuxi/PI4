package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ejercicio3.Datos3;

public record ContenedoresHyperVertex(Integer indice, List<Integer> tamContenedores) implements ContenedoresHyperVertexI {

	public static ContenedoresHyperVertex initial() {
		List<Integer> tamC = IntStream.range(0, Datos2.getNumContenedores()).boxed().map(x -> Datos2.getTamContenedor(x)).toList();
		return new ContenedoresHyperVertex(0, tamC);
	}
	
	public static ContenedoresHyperVertex of(Integer i, List<Integer> tamCont) {
		return new ContenedoresHyperVertex(i,tamCont);
	}
	
	public List<Integer> actions() {
		if (this.indice == Datos2.getNumContenedores()) return List.of();
		List <Integer> alternativas = new ArrayList<> ();
		for (int i = 0; i < Datos2.getNumElementos(); i++) {
			if (Datos2.getPuedeUbicarse(i, indice) || Datos2.getTamElemento(i) <= this.tamContenedores().get(this.indice)) {
				alternativas.add(1);
			}
			else {
				alternativas.add(0);
				
			}
		}
		
		return alternativas;
	}

	@Override
	public Boolean isBaseCase() {
		// TODO Auto-generated method stub
		Boolean elemUbi = false; 
		for (int i  = indice; i < Datos2.getNumElementos(); i++) {
			for (int j = 0; j < Datos2.getNumContenedores(); j++) {
				if (Datos2.getTamElemento(i) <= tamContenedores.get(j) && Datos2.getPuedeUbicarse(i, j)) {
					elemUbi = true;
					break; 
				}
			}
			
		}
		
		return indice == Datos2.getNumElementos() || !elemUbi;	
	
		
	}

	@Override
	public Double baseCaseWeight() {
		// TODO Auto-generated method stub
		return !this.contCompletos().isEmpty() ? 0.: null;
	}


	@Override
	public List<Integer> baseCaseSolution() {
		List <Integer> s = new ArrayList<Integer> ();
		return !contCompletos().isEmpty() ? s : null;
	}

	public List<Integer> solution(Integer a, List<List<Integer>> solutions) {
		// TODO Auto-generated method stub
		List<Integer> s = solutions.get(0);
		if (a> Datos2.getNumContenedores()) {
			s.set(indice, a);
		}
		return s;
	}

	@Override
	public List<ContenedoresHyperVertex> neighbors(Integer a) {
		// TODO Auto-generated method stub
		List <Integer> tamCopia = new ArrayList <Integer> (tamContenedores);
		if (a < Datos2.getNumContenedores()) {
			tamCopia.set(a, tamCopia.get(a) - Datos2.getTamElemento(indice));
		}
		
		return List.of(ContenedoresHyperVertex.of(indice+1, tamCopia));
	}

	@Override
	public ContenedoresHyperEdge edge(Integer a) {
		// TODO Auto-generated method stub
		List<ContenedoresHyperVertex> targets = this.neighbors(a);
		return ContenedoresHyperEdge.of(this, targets, a);
		
	}

	public static Integer valor(List<Integer> solution) {
		// TODO Auto-generated method stub
		return solution.size();
	}

	public Set<Integer> contCompletos() {
		return IntStream.range(0,Datos2.getNumContenedores()).boxed().filter( j -> tamContenedores.get(j).equals(0)).collect(Collectors.toSet());
	}

	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		return (indice >=  0 || indice <= Datos2.getNumContenedores()) && this.contCompletos().stream()
				.allMatch(x -> Datos2.getTamContenedor(x)>= 0 || contCompletos().contains(x));
	}
	

}
