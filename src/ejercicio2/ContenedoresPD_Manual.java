package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ContenedoresPD_Manual {
	
	public static record Spm(Integer a, Integer weight) implements Comparable <Spm> {
		public static Spm of(Integer a, Integer w) {
			return new Spm(a,w);
		}
		
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}
		
	}

	public static Map<ContenedoresProblemPD_Manual,Spm> memory; 
	
	public static Set<Integer> search() {
		memory = new HashMap<ContenedoresProblemPD_Manual, Spm> ();
		pd_search(ContenedoresProblemPD_Manual.start());
		return getSolucion2PDM();
		
	}
	
	private static Spm pd_search(ContenedoresProblemPD_Manual problema) {
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
					ContenedoresProblemPD_Manual vecino = problema.neighbor(action);
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
	
	public static Set<Integer> getSolucion2PDM () {
		
		Set<Integer> sol = new HashSet<Integer> (); 
		ContenedoresProblemPD_Manual problema = ContenedoresProblemPD_Manual.start();
		if (memory.get(problema) == null) return null;
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
