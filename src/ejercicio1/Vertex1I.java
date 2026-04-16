package ejercicio1;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import us.lsi.common.List2;

public record Vertex1I(Integer indice, Set<Integer> candSel, Integer presRest, Set<String> cualidadesCub) implements Vertex1  {

	public static Vertex1 of (Integer i, Set<Integer> sel, Integer pR, Set<String> c) {
		return new Vertex1I(i,sel,pR,c);
	}
	
	public static Vertex1 start() {
		return new Vertex1I(0,new HashSet<Integer>(),Datos1.getPresupuestoMax(),new HashSet<String> ());
	}
	
	public Boolean goal() {
		//Queremos maximizar el número de candidatos que contratemos
		return this.indice() == Datos1.getNumCandidatos();
	}
	
	public Boolean goalHasSolution() {
		//Tiene solución si todas las cualidades han sido cubiertas 
		Set<String> s = new HashSet<>(Datos1.getCualidades()); 
		Set <String> cc = new HashSet<> (cualidadesCub);
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
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		if (indice == Datos1.getNumCandidatos()) return List.of();

		List<Integer> lBool = List2.of(0);
		/*Si el sueldo del candidato no me supera el presupuesto restante
		y no es incompatible con ninguna otro candidato que ya haya contratado*/
		Boolean c = Datos1.getSueldoMin(indice) <= this.presRest &&
				this.candSel.stream()
				.noneMatch(i -> Datos1.getSonIncompatibles(this.indice(), i) && Datos1.getSonIncompatibles(i, this.indice()));
		if (c) 	//Si se me cumplen ambas condiciones, añadimos 'true'
			lBool.add(1);
		return lBool;
		
	}
	
	@Override
	public Vertex1 neighbor(Integer a) {
		//Calculamos el siguiente candidato a seleccionar, en caso de que exista
		Set <Integer> sel2 = new HashSet<Integer> (candSel);
		Integer pRest2 = presRest; 
		Set<String> cual2 = new HashSet<String> (cualidadesCub);
		if (a>0) {
			sel2.add(indice);
			pRest2 = (int) (pRest2 - Datos1.getSueldoMin(indice));
			cual2.addAll(Datos1.getCandidato(indice).cualidades());
		}
		return Vertex1I.of(indice+1,sel2, pRest2, cual2);
	}

	@Override
	public Edge1 edge(Integer a) {
		return Edge1.of(this, this.neighbor(a), a);
	}

	@Override
	public String toString() {
		Locale.setDefault(Locale.of("en","US"));
		return String.format("%d,%d,%.0f",indice, cualidadesCub.size(), Heuristic1.heuristic(this, v -> v.goal(), null));
	}

 
	
}
