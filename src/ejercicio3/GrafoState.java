package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class GrafoState {

	Vertex3I actual;
	Double acumulado;
	List<Integer> acciones ;
	List <Vertex3> anteriores;
	
	private GrafoState(Vertex3I pi, Double d, List<Integer> of, List<Vertex3> of2) {
		actual = pi;
		acumulado = d;
		acciones = of;
		anteriores = of2;
		// TODO Auto-generated constructor stub
	}
	

	public static GrafoState of(Vertex3I pi, Double d, List<Integer> of, List<Vertex3> of2) {
		// TODO Auto-generated method stub
		return new GrafoState(pi,d,of,of2);
	}
	
	public static GrafoState initial() {
		Vertex3I  pi = (Vertex3I) Vertex3I.start();
		return of(pi,0., new ArrayList<Integer> () , new ArrayList <Vertex3> ());
	}

	
	public void forward(Integer a) {
		acumulado += Datos3.esfuerzo(actual.indice(), a);
		acciones.add(a);
		anteriores.add(actual);
		actual = (Vertex3I) actual.neighbor(a);
	}
	
	
	public void back() {
		int last = acciones.size()-1;
		Vertex3I prob_ant = (Vertex3I) anteriores.get(last);
		
		acumulado -= Datos3.esfuerzo(prob_ant.indice(), acciones.get(last));
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}
	
	public List <Integer> alternativas() {
		return	actual.actions();
	}
	
	public Double cota(Integer a) {
		Double weight = Datos3.esfuerzo(actual.indice(), a);;
		return acumulado + weight + Heuristic3.heuristic(actual.neighbor(a), null, null);
	}
	
	public Boolean esTerminal() {
		return actual.indice() == Datos3.N;
	}
	
	public Boolean esSolucion() {
		return actual.goalHasSolution();
	}
	
	public Solucion3 getSolucion() {
		return Solucion3.create(acciones);
	}

}
