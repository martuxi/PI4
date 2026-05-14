package ejercicio1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import us.lsi.common.IntegerSet;

public record CandidatosProblemPD_Manual(Integer indice, Double presRest, Set<Integer> candSel, Set<String> cualidadesCubrir) {
	
	public static CandidatosProblemPD_Manual start() {
		return CandidatosProblemPD_Manual.of(0, Datos1.getPresupuestoMax()*1.0, new HashSet<Integer> (), new HashSet<String>());
	}
	
	public static CandidatosProblemPD_Manual of(Integer a, Double pM, Set<Integer> cand, Set<String> cual) {
		return new CandidatosProblemPD_Manual (a,pM, cand, cual);
	}
	
	public Boolean goal() {
		return this.indice() == Datos1.getNumCandidatos();
	}
	
	public Boolean goalHasSolution() {
		//Tiene solución si todas las cualidades han sido cubiertas 
		Set<String> s = new HashSet<>(Datos1.getCualidades()); 
		Set <String> cc = new HashSet<> (cualidadesCubrir);
		s.removeAll(cc);
		Boolean c2  = false; 
		for(Integer a: candSel) { 
			for(Integer b: candSel) { 
				c2 = Datos1.getSonIncompatibles(b, a) || Datos1.getSonIncompatibles(a, b); 
				if(c2) return false; 
			}
		}
		
		return s.isEmpty();
	}
	
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

	public List<CandidatosProblemPD_Manual> neighbors(Integer a) {
		Set<String> cualidades = new HashSet<>(cualidadesCubrir());
		Double presupuesto = presRest();
		Set<Integer> candidatos = new HashSet<>(candSel());
		candidatos.remove(indice);
		if (a>0) {
			cualidades.removeAll(Datos1.getCualidades(indice));
			presupuesto = presupuesto - Datos1.getSueldoMin(indice);
			candidatos.addAll(IntegerSet.range(0,Datos1.getNumCandidatos()).stream().filter(x -> Datos1.getSonIncompatibles(indice, x)).toList());
		}

		return List.of(CandidatosProblemPD_Manual.of(indice+1, presupuesto, candidatos, cualidades));
	}

	public Boolean isBaseCase() {
		return (this.presRest < IntStream.range(indice, Datos1.getNumCandidatos()).mapToDouble(i -> Datos1.getSueldoMin(i)).min().orElse(0)
				|| this.indice == Datos1.getNumCandidatos());
	}

	public Integer baseCaseWeight() {
		return cualidadesCubrir().isEmpty()?0:null;
	}
	
	public CandidatosProblemPD_Manual neighbor(Integer a) {
		Double copPres = Double.valueOf(presRest);
		Set<Integer> copCand = new HashSet<Integer> (candSel);
		Set<String> copCual = new HashSet<String> (cualidadesCubrir);
		if (a > 0) {
			copPres = copPres - Datos1.getSueldoMin(indice);
			copCand.add(indice);
			copCual.addAll(Datos1.getCualidades(indice));
		}
		return new CandidatosProblemPD_Manual(indice+1, copPres, copCand, copCual);
	}


	
}
