package ejercicio1;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import us.lsi.common.IntegerSet;
import us.lsi.common.List2;

public record CandidatosProblem (Integer indice, IntegerSet candSel, Integer presRest) {
	
	public static CandidatosProblem of(Integer i, IntegerSet sel, Integer pR ) {
		return new CandidatosProblem(i,sel,pR);
	}
	
	public static CandidatosProblem initial() {
		return new CandidatosProblem(0,IntegerSet.empty(),Datos1.getPresupuestoMax());
	}
	
	public List<Boolean> actions() {
		// TODO Auto-generated method stub
		if (indice == Datos1.getNumCandidatos()) return List.of();
		List<Boolean> lBool = List2.of(false);
		/*Si el sueldo del candidato no me supera el presupuesto restante
		y no es incompatible con ninguna otro candidato que ya haya contratado*/
		Boolean c = Datos1.getSueldoMin(indice) <= this.presRest &&
				this.candSel.stream().noneMatch(i -> Datos1.getSonIncompatibles(this.indice(), i));
		if (c) 	//Si se me cumplen ambas condiciones, añadimos 'true'
			lBool.add(true);
		return lBool;
	}

	public CandidatosProblem neighbor(Boolean a) {
		//Calculamos el siguiente candidato a seleccionar, en caso de que exista
		IntegerSet sel2 = IntegerSet.copy(candSel);
		Integer presRest2 = presRest; 
		if (a) {
			sel2.add(indice);
			presRest2 = (int) (presRest2 - Datos1.getSueldoMin(indice));
		}
		// TODO Auto-generated method stub
		return CandidatosProblem.of(indice+1,sel2, presRest2);
	}
	public Double heuristic () {
		return (Double) (Datos1.getNumCandidatos()-this.indice()*5.0);
	}
	
	public Boolean goal() {
		//Queremos maximizar el número de candidatos que contratemos
		return this.indice() == Datos1.getNumCandidatos();
	}
	
	public Set<Integer> cualidadesCubiertas() {
		// TODO Auto-generated method stub
		return this.candSel().stream()
				.flatMap(i -> Datos1.getCualidadesInt(i).stream())
				.collect(Collectors.toSet());
	}
	
	public Boolean hasSolution() {
		//Tiene solución si todas las cualidades han sido cubiertas 
		return this.cualidadesCubiertas().size() == Datos1.getNumCualidades();
	}
}
