package ejercicio3;

public class GrafosBT {

	private static Double mejorValor;
	private static GrafoState estado ; 
	private static Solucion3 solucion;

	public static void search() {
		solucion = null;
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		estado = GrafoState.initial();
		bt_search();
	}

	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido < mejorValor) {  // Estamos maximizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else if(!estado.esTerminal()){
			for (Integer a: estado.alternativas()) {
//				if (estado.cota(a) <= mejorValor) {  // Estamos maximizando
				if (estado.cota(a) < mejorValor) {  // Estamos maximizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

	public static Solucion3 getSolucion() {
		return solucion;
	}
}
