package ejercicio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public record Vertex1I(Integer indice, Double presRest, Set<Integer> candSel, Set<String> cualidadesCubrir) implements Vertex1 {

	public static Vertex1I start() {
		return Vertex1I.of(0, Datos1.getPresupuestoMax()*1.0, new HashSet<Integer> (), new HashSet<String>());
	}
	
	public static Vertex1I of(Integer a, Double pM, Set<Integer> cand, Set<String> cual) {
		return new Vertex1I (a,pM, cand, cual);
	}
	
	public Boolean goal() {
		return this.indice() == Datos1.getNumCandidatos();
	}
	
	public Boolean goalHasSolution() {
		//Tiene solución si todas las cualidades han sido cubiertas 
		Set<String> s = new HashSet<>(Datos1.getCualidades()); 
		Set <String> cc = new HashSet<> (cualidadesCubrir);
		s.removeAll(cc);
		Boolean c2  = true;
		Boolean c3 = false;
		for(Integer a: candSel) { 
			for(Integer b: candSel) { 
				c2 = Datos1.getSonIncompatibles(b, a) || Datos1.getSonIncompatibles(a, b);
				c3 = Datos1.getSueldoMin(a) >= presRest;
				if(c2 && c3) return false; 
			}
		}
		
		return s.isEmpty();
	}
	
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		List <Integer> alternativas = new ArrayList<>();
		if (this.indice() == Datos1.getNumCandidatos()) return List.of(); 
		else if (this.indice()>=0) {
			if (this.presRest()>= Datos1.getSueldoMin(this.indice()) && this.candSel().stream().noneMatch(x -> Datos1.getSonIncompatibles(x, this.indice()))) {
				alternativas.add(1);
			}
		
			alternativas.add(0);
		}
		return alternativas;
	}

	@Override
	public Vertex1 neighbor(Integer a) {
		Double copPres = Double.valueOf(presRest);
		Set<Integer> copCand = new HashSet<Integer> (candSel);
		Set<String> copCual = new HashSet<String> (cualidadesCubrir);
		if (a > 0) {
			copPres = copPres - Datos1.getSueldoMin(indice);
			copCand.add(indice);
			copCual.addAll(Datos1.getCualidades(indice));
		}
		return new Vertex1I(indice+1, copPres, copCand, copCual);
	}

	@Override
	public Edge1 edge(Integer a) {
		// TODO Auto-generated method stub
		return Edge1.of(this, this.neighbor(a),a);
	}

	//EXCLUSIVO PARA BT 
	public Integer greedyAction() {
		 boolean puedeAfrontar = this.presRest() >= Datos1.getSueldoMin(indice);
	      boolean sinIncompat = this.candSel().stream()
	          .noneMatch(x -> Datos1.getSonIncompatibles(x, indice) || Datos1.getSonIncompatibles(indice, x));
	      boolean aportaCualidades = Datos1.getCualidades(indice).stream()
	          .anyMatch(q -> !this.cualidadesCubrir().contains(q));

	      if (puedeAfrontar && sinIncompat && aportaCualidades) return 1;
	      return 0;
	}
	
}
