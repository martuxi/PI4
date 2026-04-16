package ejercicio2;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record SubconjuntoProblem(Integer indice, List<Integer> capRest) {
	
	public static SubconjuntoProblem of(Integer i, List<Integer> cpR) {
		return new SubconjuntoProblem(i,cpR);
	}
	
	public static SubconjuntoProblem initial() {
		//Creamos una lista con los tamaños de los distintos contenedores 
		List <Integer> capRest = IntStream.range(0, Datos2.getNumContenedores()).boxed().map(x -> Datos2.getTamContenedor(x)).toList();
		return new SubconjuntoProblem(0,capRest);
	}

	
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
	
	public SubconjuntoProblem neighbor(Integer a) {
		// TODO Auto-generated method stub
		List<Integer> capRest2 = List2.copy(capRest);
		capRest.set(a, capRest.get(a) - Datos2.getTamElemento(indice));
		SubconjuntoProblem r = SubconjuntoProblem.of(indice+1, capRest2); 
		return r;
	}
	

}
