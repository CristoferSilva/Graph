package math.graph;

import java.io.FileNotFoundException;
import math.graph.classes.Graph;
import utils.GraphPrinter;

/**
 * @author cristoferSilva
 *
 */
public class App {
	static GraphPrinter graphPrinter;
	static String filePath = "assets//pequenoG.txt";

	public static void main(String[] args) throws Exception {
		runBFSInConsoleInterface(filePath);
		runDFSInConsoleInterface(filePath);
	}

	public static void runBFSInConsoleInterface(String filePath) throws Exception {
		Graph graph = new Graph(filePath, 6, 3);
		graphPrinter = new GraphPrinter(graph);

		GraphPrinter.printConsoleHeader();
		graphPrinter.printAdjacencyMatrix();
		try {

			System.out.println("> > Shortest path between 6 and 3 BFS: ");

			graph.runBFSAlgorithm();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void runDFSInConsoleInterface(String filePath) throws Exception {
		Graph graph = new Graph(filePath, 6, 3);
		graphPrinter = new GraphPrinter(graph);

		GraphPrinter.printConsoleHeader();
		graphPrinter.printAdjacencyMatrix();
		try {

			System.out.println("> > RUN DFS: ");

			graph.runDFSAlgorithm();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
