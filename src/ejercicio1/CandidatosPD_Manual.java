package ejercicio1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CandidatosPD_Manual {
	
	public static record Spm(Integer a, Integer weight) implements Comparable <Spm> {
		public static Spm of(Integer a, Integer w) {
			return new Spm(a,w);
		}
		
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
		
	}
	
	public static Map<CandidatosProblemPD_Manual,Spm> memory; 
	
	public static Set<Integer> search() {
		memory = new HashMap<CandidatosProblemPD_Manual, Spm> ();
		pd_search(CandidatosProblemPD_Manual.start());
		return getSolucion1PDM();
		
	}
	
	private static Spm pd_search(CandidatosProblemPD_Manual problema) {
		Spm res = null; 
		if (memory.containsKey(problema)) {
			res = memory.get(problema);
		} else if (problema.isBaseCase()) {
			Integer w = problema.baseCaseWeight();
			if (w!= null) res = Spm.of(null, w);
			else res = null; 
			memory.put(problema,res); }
			else {
				List<Spm> subproblemas = new ArrayList<>();
				for (Integer action: problema.actions()) {
					CandidatosProblemPD_Manual vecino = problema.neighbor(action);
					Spm subprobVecino = pd_search(vecino);
					if (subprobVecino!= null) {
						Spm amp = Spm.of(action, subprobVecino.weight() + action);
						subproblemas.add(amp);
					}
				}
				res = subproblemas.stream().max(Comparator.naturalOrder()).orElse(null);
				memory.put(problema,res);
			}	
			return res;
		
	}
	
	public static Set<Integer> getSolucion1PDM () {
		
		Set<Integer> sol = null; 
		CandidatosProblemPD_Manual problema = CandidatosProblemPD_Manual.start();
		if (memory.get(problema) == null) return null;
		sol = Set.of();
		while (!problema.isBaseCase()) {
			Spm subprob = memory.get(problema);
			if (subprob.a>0) {
				sol.add(problema.indice()); 
			}
			
			problema = problema.neighbors(subprob.a).get(0);
			
		}
		return sol;
		
	}

}
