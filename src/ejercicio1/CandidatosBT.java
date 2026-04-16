package ejercicio1;

public class CandidatosBT {
	
	private static Double mejorValor;
	private static CandidatosState estado ; 
	private static Solucion1 solucion;

	public static void search() {
		solucion = null;
		mejorValor = Double.MIN_VALUE; // Estamos maximizando
		estado = CandidatosState.initial();
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
			for (Boolean a: estado.alternativas()) {
//				if (estado.cota(a) <= mejorValor) {  // Estamos maximizando
				if (estado.cota(a) > mejorValor) {  // Estamos maximizando
					estado.foward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

	public static Solucion1 getSolucion() {
		return solucion;
	}

}
