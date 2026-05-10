package tests.ejercicio3;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import ejercicio3.Datos3;
import ejercicio3.Edge3;
import ejercicio3.Heuristic3;
import ejercicio3.Solucion3;
import ejercicio3.Vertex3;
import ejercicio3.Vertex3I;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestsBT {

	public static void main(String[] args) throws IOException {
		// TODO Consulte los ejemplos del repositorio
		for (int i = 1; i<4; i++) {
		Datos3.iniDatos("datos_entrada/ejercicio3/DatosEntrada"+ i + ".txt");

		Vertex3 vInicial = Vertex3I.start();
		
		EGraph<Vertex3, Edge3> graph = //(Vertex3 v_inicial, Predicate<Vertex3> es_terminal) { 
			EGraph.virtual(vInicial)
				.pathType(PathType.Last)
				.type(Type.Min)
				.heuristic(Heuristic3::heuristic)
				.build();

		GreedyOnGraph<Vertex3, Edge3> alg_voraz = GreedyOnGraph.of(graph);		
		GraphPath<Vertex3, Edge3> path = alg_voraz.path();
		path = alg_voraz.isSolution(path)? path: null;

		path = null;
		
		BT<Vertex3,Edge3,Solucion3>alg_bt = path==null? BT.of(graph):
			BT.of(graph, null, path.getWeight(), path, true);
		
		var res = alg_bt.search().orElse(null);
		/*var outGraph = alg_bt.outGraph();
		if(outGraph!=null) {
			Predicate<Vertex3> vs = v -> res.getVertexList().contains(v);
			Predicate<Edge3> es = e -> res.getEdgeList().contains(e);
			GraphColors.toDot(outGraph, "ficheros_generados/p4/ejemplo3/AlumnosBTGraph1.gv", 
					v -> v.toGraph(),
					e -> e.action().toString(), 
					v -> GraphColors.colorIf(Color.red, vs.test(v)),
					e -> GraphColors.colorIf(Color.red, es.test(e)));

		}	
		 */
		if(res!=null)
			System.out.println("Solucion BT: " + Solucion3.of(res) + "\n");
		else 
			System.out.println("BT no obtuvo solucion\n");
		
		
	}	
}
}