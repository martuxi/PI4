package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record Vertex2I(Integer indice, List <Integer> capRest) implements Vertex2{
	
	public static Vertex2I start() {
		//Creamos una lista con los tamaños de los distintos contenedores 
		List <Integer> capRest = IntStream.range(0, Datos2.getNumContenedores()).boxed().map(x -> Datos2.getTamContenedor(x)).toList();
		return new Vertex2I(0,capRest);
	}
	
	public static Vertex2I of(Integer i, List <Integer> cpR) {
		return new Vertex2I(i,cpR);
	}
	
	public Boolean goal() {
		//El objetivo es emplear todos los elementos que tienes
		return this.indice() == Datos2.getNumElementos();
	}
	
	public List <Integer> capRest() {
		return List.copyOf(this.capRest);
	}
	
	public List<Integer> contenedoresCompletos() {
		List <Integer> l = new ArrayList <> ();
		for (int i = 0; i < capRest.size(); i++) {
			if (capRest.get(i) == 0) {
				l.add(i);
			}
		}
		
		return l;
	}
	public Boolean hasSolution() {
		//Tendrá solución si hemos llenado todos los contenedores 
		 return this.capRest.size() ==0;
	}

	
	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		if (this.indice().equals(Datos2.getNumElementos())) return List.of();
		/*Creamos una lista con los contenedores que cumplan los siguientes requisitos:  
			* No estén llenos/completos
			* No muestren incompatibilidad con los elementos que hay en el mismo contenedor
			* Al añadir el elemento, no rebose el contenedor
		*/
		List <Integer> lista = IntStream.range(0, Datos2.getNumContenedores()).boxed()
										.filter(x -> !contenedoresCompletos().contains(x))
										.filter(x -> Datos2.getPuedeUbicarse(this.indice(), x))
										.filter(x -> Datos2.getTamElemento(this.indice()) <= this.capRest().get(x))
										.toList();
		
		return lista;
		
	}

	public Integer greedyAction() {
		Function<Integer,Integer> f = a -> this.capRest().get(a) - Datos2.getTamElemento(this.indice());
		List<Integer> ls = this.actions();
		return ls.stream().min(Comparator.comparing(a -> f.apply(a))).get();
	}
	
	@Override
	public Vertex2 neighbor(Integer a) {
		// TODO Auto-generated method stub
		List<Integer> capRest2 = List2.copy(capRest);
		capRest2.set(a, capRest.get(a) - Datos2.getTamElemento(indice));
		Vertex2I r = Vertex2I.of(indice+1, capRest2); 
		return r;
	}

	@Override
	public Edge2 edge(Integer a) {
		// TODO Auto-generated method stub
		return Edge2.of(this, this.neighbor(a), a);
	}

	@Override
	public Integer getTamContenedor() {
		// TODO Auto-generated method stub
		return this.getTamContenedor();
	}

	@Override
	public Integer getTamElemento() {
		// TODO Auto-generated method stub
		return this.getTamElemento();
	}

	@Override
	public Boolean getPuedeUbicarse() {
		// TODO Auto-generated method stub
		return this.getPuedeUbicarse();
	}	
}
