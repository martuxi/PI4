package ejercicio1;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.List2;

public class CandidatosState {
	
	CandidatosProblem actual;
	Double acumulado;
	List<Boolean> acciones ;
	List <CandidatosProblem> anteriores;
	
	private CandidatosState(CandidatosProblem pi, Double d, List<Boolean> of, List<CandidatosProblem> of2) {
		actual = pi;
		acumulado = d;
		acciones = of;
		anteriores = of2;
		// TODO Auto-generated constructor stub
	}
	

	public static CandidatosState of(CandidatosProblem pi, Double d, List<Boolean> of, List<CandidatosProblem> of2) {
		// TODO Auto-generated method stub
		return new CandidatosState(pi,d,of,of2);
	}
	
	public static CandidatosState initial() {
		CandidatosProblem  pi = CandidatosProblem.initial();
		return of(pi,0., new ArrayList<Boolean> () , new ArrayList <CandidatosProblem> ());
	}

	
	public void foward(Boolean a) {
		acumulado += a ? Datos1.getValoracion(actual.indice()): 0;
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}
	
	
	public void back() {
		int last = acciones.size()-1;
		var prob_ant = anteriores.get(last);
		
		acumulado -= acciones.get(last) ? Datos1.getValoracion(prob_ant.indice()): 0.;
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}
	
	public List <Boolean> alternativas() {
		return	actual.actions();
	}
	
	public Double cota(Boolean a) {
		Double weight = a ? Datos1.getValoracion(actual.indice()): 0.;
		return acumulado + weight + actual.neighbor(a).heuristic();
	}
	
	public Boolean esTerminal() {
		return actual.indice() == Datos1.getNumCandidatos();
	}
	
	public Boolean esSolucion() {
		return actual.cualidadesCubiertas().size() == Datos1.getNumCualidades();
	}
	
	public Solucion1 getSolucion() {
		List <Integer> ls = new ArrayList <> ();
		for (Boolean b: acciones) {
			if (b) {
				ls.add(1);
			}
			else {
				ls.add(0);
			}
		
		}
		
		return Solucion1.create(ls);
	}
}


