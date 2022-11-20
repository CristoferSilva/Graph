/**
 * 
 */
package utils;

import math.graph.classes.Graph;
import math.graph.classes.Vertex;

/**
 * @author cristoferSilva
 *
 */
public class GraphPrinter {
	private Graph graph;

	public GraphPrinter(Graph graph) {
		this.graph = graph;
	}

	public void printAdjacencyMatrix() {
		int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
		printMatrixHeadInConsole(adjacencyMatrix);
		printMatrixLinesInConsole(adjacencyMatrix);
	}

	private void printMatrixLinesInConsole(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (i < 10) {
				System.out.print((i) + "   ");
			} else {
				System.out.print((i) + "  ");
			}
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	private void printMatrixHeadInConsole(int[][] matrix) {
		System.out.print("  ");
		for (int i = 0; i < matrix.length; i++) {
			if (i < 10) {
				System.out.print("  " + (i));
				continue;
			}
			System.out.print(" " + (i));
		}
		System.out.println();
	}

	public void printShortestPathBetweenInitialAndFinalVertices() {
		printShortestPathBetweenInitialAndFinalVertices(this.graph.getInitialVertex());
	}

	private void printShortestPathBetweenInitialAndFinalVertices(Vertex finalVertice) {
		if (this.graph.getFinalVertex().equals(this.graph.getInitialVertex())) {
			System.out.print(this.graph.getInitialVertex());
		} else {
			if (this.graph.getFinalVertex().getPreviousVerticeWithShortestDistance() == null) {
				System.out.println("There is not a path");
			} else {
				printShortestPathBetweenInitialAndFinalVertices(
						this.graph.getFinalVertex().getPreviousVerticeWithShortestDistance());
				System.out.print(this.graph.getFinalVertex());
			}
		}
	}

	public static void printConsoleHeader() {
		System.out.println();
		System.out.println("* Adjacent Matrix from the current graph in file :\n");
	}
}
