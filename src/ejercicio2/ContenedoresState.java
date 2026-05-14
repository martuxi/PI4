package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class ContenedoresState {
	
	//Puedes usar tanto la clase ContenedoresProblem como el vértice directamente
	Vertex2I actual;
	Double acumulado;
	List<Integer> acciones ;
	List <Vertex2> anteriores;
	
	private ContenedoresState(Vertex2I pi, Double d, List<Integer> of, List<Vertex2> of2) {
		actual = pi;
		acumulado = d;
		acciones = of;
		anteriores = of2;
		// TODO Auto-generated constructor stub
	}
	

	public static ContenedoresState of(Vertex2I pi, Double d, List<Integer> of, List<Vertex2> of2) {
		// TODO Auto-generated method stub
		return new ContenedoresState(pi,d,of,of2);
	}
	
	public static ContenedoresState initial() {
		Vertex2I  pi = Vertex2I.start();
		return of(pi,0., new ArrayList<Integer> () , new ArrayList <Vertex2> ());
	}

	
	public void forward(Integer a) {
		acumulado += actual.tamañoContenedores().get(a) - Datos2.getTamElemento(actual.indice()) == 0 ? 1.: 0.;
		acciones.add(a);
		anteriores.add(actual);
		actual = (Vertex2I) actual.neighbor(a);
	}
	
	
	public void back() {
		int last = acciones.size()-1;
		Vertex2I prob_ant = (Vertex2I) anteriores.get(last);
		
		acumulado -= prob_ant.tamañoContenedores().get(acciones.get(last)) - Datos2.getTamElemento(prob_ant.indice()) == 0 ? 1.: 0.;
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}
	
	public List <Integer> alternativas() {
		return	actual.actions();
	}
	
	public Double cota(Integer a) {
		Double weight = actual.tamañoContenedores().get(a) - Datos2.getTamElemento(actual.indice()) == 0 ? 1.: 0.;
		return acumulado + weight + Heuristic2.heuristic(actual.neighbor(a), null, null);
	}
	
	public Boolean esTerminal() {
		return actual.actions().isEmpty();
	}
	
	public Boolean esSolucion() {
		return  actual.indice() == Datos2.getNumElementos();
	}
	
	public Solucion2 getSolucion() {
		return Solucion2.create(acciones);
	}

}
