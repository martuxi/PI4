package ejercicio2;

public class ContenedoresBT {
	
	private static Double mejorValor;
	private static ContenedoresState estado ; 
	private static Solucion2 solucion;

	public static void search() {
		solucion = null;
		mejorValor = Double.MIN_VALUE; // Estamos maximizando
		estado = ContenedoresState.initial();
		bt_search();
	}

	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido > mejorValor) {  // Estamos maximizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else if(!estado.esTerminal()){
			for (Integer a: estado.alternativas()) {
//				if (estado.cota(a) <= mejorValor) {  // Estamos minimizando
				if (estado.cota(a) > mejorValor) {  // Estamos maximizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

	public static Solucion2 getSolucion() {
		return solucion;
	}

}
