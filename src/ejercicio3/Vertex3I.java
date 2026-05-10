package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record Vertex3I (Integer indice, Double durAcum, Integer nConsec, List<Integer> camino) implements Vertex3 {

	public static Vertex3 of(Integer i, Double duraAcum, Integer nConsec, List<Integer> camino) {
		return new Vertex3I(i,duraAcum,nConsec,camino);
	}

	public static Vertex3 start() {
		return Vertex3I.of(0, Datos3.maxTime, 0, new ArrayList<Integer>());
	}
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		if (indice == 0) {
			List <Integer> l = new ArrayList<> ();
			l.add(0);
			return l;
		}
		
		if (indice == Datos3.N) {
			return List2.empty();
		}
		
		Integer ultVert = camino.getLast();
		Set<Integer> porVisitarAux = IntStream.range(0, Datos3.N).boxed().collect(Collectors.toSet());
		porVisitarAux.removeAll(this.camino());
		
		List <Integer> ls = Datos3.g2.edgesOf(ultVert).stream()
				.map(e -> e.otherVertex(ultVert))
				.filter(x -> porVisitarAux.contains(x))
				.filter(x -> Datos3.grafo.getEdge(Datos3.g2.getVertex(ultVert), Datos3.getVertex(x)).tiempo() <= this.durAcum)
				.collect(Collectors.toList());
		if (indice == Datos3.N-1 && ls.size() ==1 && !Datos3.grafo.containsEdge(Datos3.getVertex(ls.get(0)), Datos3.getVertex(0))) {
			return List.of();
		}
		return ls;
	}

	public Boolean goalHasSolution() {
		return this.indice == Datos3.N && this.nConsec >= 2 && this.durAcum >= 0.0;
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
		if (camino.isEmpty()==false) ultVert = camino.getLast();
		Double sumaTotRestAux = this.durAcum;
		Integer nConsecAux = this.nConsec;
		if (ultVert != null) {
			sumaTotRestAux = sumaTotRestAux - Datos3.grafo.getEdge(Datos3.g2.getVertex(ultVert),Datos3.g2.getVertex(a)).tiempo();
		}
		nConsecAux++;
		return Vertex3I.of(indice+1,sumaTotRestAux,nConsecAux,caminoAux);
	}

	@Override
	public Edge3 edge(Integer a) {
		// TODO Auto-generated method stub
		return Edge3.of(this,this.neighbor(a),a);
	}
	
	public String toGraph() {
		return String.format("%d", indice);
	}

	
	

}
