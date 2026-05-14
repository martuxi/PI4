package ejercicio2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import us.lsi.common.List2;

public record ContenedoresProblemPD_Manual(Integer indice, List<Integer> tamañoContenedores)  {
	
	public static ContenedoresProblemPD_Manual start() {
		List <Integer> capRest = IntStream.range(0, Datos2.getNumContenedores()).boxed().map( x -> Datos2.getTamContenedor(x)).toList();
		return new ContenedoresProblemPD_Manual(0, capRest);
	}
	
	public static ContenedoresProblemPD_Manual of (Integer i, List<Integer> cpR) {
		return new ContenedoresProblemPD_Manual(i, cpR);
	}
	
	public Boolean goal() {
		//El objetivo es emplear todos los elementos que tienes
		return this.indice() == Datos2.getNumElementos();
	}
	
	public List <Integer> capRest() {
		return List.copyOf(this.tamañoContenedores);
	}
	
	public Boolean goalHasSolution() {
		//Tendrá solución si hemos llenado todos los contenedores 
		 return this.tamañoContenedores.size() ==0;
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
	
	public ContenedoresProblemPD_Manual neighbor(Integer a) {
		List<Integer> capRest2 = List2.copy(tamañoContenedores);
		capRest2.set(a, capRest2.get(a) - Datos2.getTamElemento(indice));
		if (capRest2.get(a) == 0) {
			contenedoresCompletos().add(a);
		}
		return ContenedoresProblemPD_Manual.of(indice + 1, capRest2);
	}
	
	public List<ContenedoresProblemPD_Manual> neighbors(Integer a) {
		// TODO Auto-generated method stub
		List <Integer> tamCopia = new ArrayList <Integer> (tamañoContenedores);
		if (a < Datos2.getNumContenedores()) {
			tamCopia.set(a, tamCopia.get(a) - Datos2.getTamElemento(indice));
			/*Comprobación del contenedor vacío??*/
		}
		
		return List.of(ContenedoresProblemPD_Manual.of(indice+1, tamCopia));
	}
	
	public Boolean isBaseCase() {
		// TODO Auto-generated method stub
		Boolean elemUbi = false; 
		for (int i  = indice; i < Datos2.getNumElementos(); i++) {
			for (int j = 0; j < Datos2.getNumContenedores(); j++) {
				if (Datos2.getTamElemento(i) <= tamañoContenedores.get(j) && Datos2.getPuedeUbicarse(i, j)) {
					elemUbi = true;
					break; 
				}
			}
			
		}
		
		return indice == Datos2.getNumElementos() || !elemUbi;	
	
		
	}

	public Integer baseCaseWeight() {
		// TODO Auto-generated method stub
		return !this.contenedoresCompletos().isEmpty() ? 0: null;
	}

	public List<Integer> contenedoresCompletos() {
		List <Integer> l = new ArrayList <> ();
		for (int i = 0; i < tamañoContenedores.size(); i++) {
			if (tamañoContenedores.get(i) == 0) {
				l.add(i);
			}
		}
		
		return l;
	}
	

}
