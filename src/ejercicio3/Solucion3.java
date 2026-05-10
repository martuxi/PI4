package ejercicio3;

import java.util.List;

import org.jgrapht.GraphPath;

public class Solucion3 {
	
	public static Solucion3 of(GraphPath<Vertex3, Edge3> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		Solucion3 res = create(ls);
		res.camino= ls;
		return res;
	}
	
    public static Solucion3 create(List<Integer> ls) {
        return new Solucion3(ls);
    }

    private List<Integer> camino;
    private Double totalTime, totalEffort, totalMns;

    private Solucion3(List<Integer> ls) {  // Lista de acciones/alternativas
    		camino = ls; 
    		totalTime   = 0.0;
    	    totalEffort = 0.0;
    	    totalMns    = 0.0;
    		for (int i = 0; i < ls.size() - 1; i++) {
				totalEffort += Datos3.esfuerzo(ls.get(i),ls.get(i+1));
				totalTime += Datos3.tiempo(ls.get(i),ls.get(i+1));
				if (Datos3.getVertex(ls.get(i)).conMonumento()) {
					 boolean prevMon = i > 0 && Datos3.getVertex(ls.get(i - 1)).conMonumento();
			         boolean nextMon = i < ls.size() - 1 && Datos3.getVertex(ls.get(i + 1)).conMonumento();
			         if (prevMon || nextMon) totalMns++;
				}
    		}
    }
    	
    	/* TODO Las propiedades de la clase son:    	
    		camino = secuencia de vertices visitados
    		totalTime = tiempo total empelado
    		totalEffort = esfuerzo total empleado
    		totalMns = monumentos visitados consecutivamente (antes o despues de otro) */
   	@Override
    public String toString() {
    	String s1 = String.format("\nTiempos (total/maximo): %.1f / %.1f", totalTime, Datos3.maxTime);
    	String s2 = String.format("\nEsfuerzo total: %.1f", totalEffort);
    	String s3 = String.format("\nNº de monumentos visitados antes o despues de otro: %d", totalMns.intValue());
    	return camino+s1+s2+s3;
   	}
}