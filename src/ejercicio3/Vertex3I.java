package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record Vertex3I (Integer indice, Integer sumaTotRest, Integer nConsec, List<Integer> camino) implements Vertex3 {

	public static Vertex3 of(Integer i, Integer sumaTotRest, Integer nConsec, List<Integer> camino) {
		return new Vertex3I(i,sumaTotRest,nConsec,camino);
	}
	
	public static Vertex3 start() {
		return Vertex3I.of(0, (int) Datos3.maxTime.intValue(),0, new ArrayList<Integer>());
	}
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		if (indice == 0) {
			List <Integer> l = new ArrayList<> ();
			l.add(0);
			return l;
		}
		
		Integer ultVert = camino.getLast();
		if (indice == Datos3.N) {
			return List2.empty();
		}
		
		Set<Integer> porVisitarAux = IntStream.range(0, Datos3.N).boxed().collect(Collectors.toSet());
		porVisitarAux.removeAll(this.camino());
		
		List <Integer> ls = Datos3.g2.edgesOf(ultVert).stream()
				.map(e -> e.otherVertex(ultVert))
				.filter(x -> porVisitarAux.contains(x))
				.filter(x -> this.sumaTotRest>=Datos3.grafo.getEdge(Datos3.g2.getVertex(ultVert), Datos3.getVertex(x)).tiempo())
				.collect(Collectors.toList());
		if (indice == Datos3.N-1 && ls.size() ==1 && !Datos3.grafo.containsEdge(Datos3.getVertex(ultVert), Datos3.getVertex(0))) {
			return List.of();
		}
		return ls;
	}

	public Boolean goalHasSolution() {
		return this.indice == Datos3.N && this.nConsec >=2 && this.sumaTotRest == 0;
	}
	
	private static final Random r = new Random();
	public Integer greedyAction() {
		
		List <Integer> actions = this.actions();
		Integer n = actions.size();
		return actions.get(r.nextInt(n));
		
	}
	
	public Boolean goal() {
		return this.indice == Datos3.N;
	}
	@Override
	public Vertex3 neighbor(Integer a) {
		// TODO Auto-generated method stub
		List<Integer> caminoAux = List2.copy(camino);
		caminoAux.add(a);
		Integer ultVert = null;
		if (!camino.isEmpty()) ultVert = camino.getLast();
		Integer sumaTotRestAux = this.sumaTotRest;
		Integer nConsecAux = this.nConsec;
		if (ultVert != null) {
			//NO FUNCIONA EL DATOS3.GRAFO.GETVERTEX()
			//sumaTotRestAux = sumaTotRestAux - (int) Datos3.grafo.getEdge(Datos3.grafo.getVertex(a),Datos3.grafo.getVertex(a)).tiempo();
		}
		nConsecAux++;
		return Vertex3I.of(indice+1,sumaTotRestAux,nConsecAux,caminoAux);
	}

	@Override
	public Edge3 edge(Integer a) {
		// TODO Auto-generated method stub
		return Edge3.of(this,this.neighbor(a),a);
	}
	@Override
	public List<Integer> camino() {
		return this.camino;
	}

	
	

}
