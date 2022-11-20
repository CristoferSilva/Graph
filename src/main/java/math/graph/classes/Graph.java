/**
 * 
 */
package math.graph.classes;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import utils.EnumColor;
import utils.InputFileReader;
import math.graph.interfaces.IGraph;

/**
 * @author cristoferSilva
 *
 */
public class Graph implements IGraph {

	private int[] content;
	private int[][] adjacencyMatrix;
	private Vertex initialVertex, finalVertex;
	private int edgeNumber, verticesNumber, timer;
	private List<Vertex> vertices = new ArrayList<Vertex>();
	private Logger LOGGER = Logger.getLogger(Graph.class.getName());

	public Graph(String pathSourceCode, int initialKey, int finalKey) throws Exception {
		createGraph(pathSourceCode, initialKey, finalKey);
	}

	private void fillAdjacentMatrix() {
		for (int i = 2; i < content.length; i = i + 2) {
			if (i + 1 < content.length) {
				this.adjacencyMatrix[content[i]][content[i + 1]] = 1;
			}
		}
	}

	@Override
	public List<Integer> getAdjacentList(Integer key) {
		List<Integer> adjacentList = new ArrayList<Integer>();
		adjacentList.addAll(getSucessorList(key));
		adjacentList.addAll(getPreviousList(key));
		return adjacentList;
	}

	@Override
	public List<Integer> getPreviousList(Integer key) {
		List<Integer> previousList = new ArrayList<Integer>();

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[i][key] == 1 && !previousList.contains(i)) {
				previousList.add(i);
			}
		}

		return previousList;
	}

	@Override
	public List<Integer> getSucessorList(Integer key) {
		List<Integer> sucessorsList = new ArrayList<Integer>();

		// for Nexts
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[key][i] == 1) {
				sucessorsList.add(i);
			}
		}

		return sucessorsList;
	}

	private void createGraph(String pathSourceCode, int initialVertexKey, int finalVertexKey) throws Exception {
		this.content = parseStringArrayToIntArray(InputFileReader.returnStringFromFile(pathSourceCode).split(" "));
		this.edgeNumber = this.content[0];
		this.verticesNumber = this.content[1];
		this.adjacencyMatrix = new int[edgeNumber][verticesNumber];

		putAllVertices(this.content, initialVertexKey, finalVertexKey);
		fillAdjacentMatrix();
	}

	private void putAllVertices(int[] contentArrayString, int initialVertexKey, int finalVertexKey) throws Exception {
		Vertex currentVertex;
		for (int i = 2; i < contentArrayString.length; i++) {
			currentVertex = new Vertex(contentArrayString[i], EnumColor.WHITE);

			if (this.vertices.indexOf(currentVertex) == -1) {
				this.vertices.add(currentVertex);
			}

			if (currentVertex.getKey().equals(initialVertexKey))
				this.initialVertex = currentVertex;
			else if (currentVertex.getKey().equals(finalVertexKey))
				this.finalVertex = currentVertex;
		}

		if (this.finalVertex == null || this.initialVertex == null) {
			throw new Exception("That initial Vertex key or final Vertice key does not exist!");
		}

	}

	private int[] parseStringArrayToIntArray(String[] stringArray) {
		int[] contentArray = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			if (!stringArray[i].isEmpty())
				contentArray[i] = Integer.parseInt(stringArray[i]);
		}
		return contentArray;
	}

	private void DFS_Start(int initalKey) {
		Vertex InitialVertex = getVertex(initalKey);

		for (Vertex currentGraphVertice : vertices) {
			currentGraphVertice.setColor(EnumColor.WHITE);
			currentGraphVertice.setInitialTime(-1);
			currentGraphVertice.setEndTime(-1);
		}
		this.timer = 1;

		DFS_Visit(InitialVertex);
	}

	private void DFS_Visit(Vertex Vertex) {
		LOGGER.log(Level.WARNING, "[DFS_Visit][VISIT] - [VERTEX KEY : " + Vertex.getKey() + "]");

		Vertex.setColor(EnumColor.GRAY);
		this.timer++;
		Vertex.setInitialTime(this.timer);

		List<Vertex> currentAdjacentList = parseIntergerListToGraphicVerticeList(getAdjacentList(Vertex.getKey()));
		for (Vertex currentVertice : currentAdjacentList) {
			if (currentVertice.getColor() == EnumColor.WHITE) {
				currentVertice.setPrevious(Vertex);
				DFS_Visit(currentVertice);
			}
		}
		Vertex.setColor(EnumColor.BLACK);
		this.timer++;
		Vertex.setEndTime(this.timer);
	}

	private void BFSAlgorithm(int initialKey) throws Exception {
		Vertex currentVertice;
		List<Vertex> currentAdjacentList;
		Stack<Vertex> stack = new Stack<Vertex>();

		if (this.content.length == 0)
			throw new Exception("The graph is empty");

		this.initialVertex = getVertex(initialKey);
		this.initialVertex.setColor(EnumColor.GRAY);
		this.initialVertex.setDistance(0);

		stack.push(this.initialVertex);

		while (!stack.empty()) {
			currentVertice = stack.pop();
			currentAdjacentList = parseIntergerListToGraphicVerticeList(getAdjacentList(currentVertice.getKey()));
			for (Vertex graphVertice : currentAdjacentList) {
				if (graphVertice.getColor() == EnumColor.WHITE) {
					graphVertice.setColor(EnumColor.GRAY);
					graphVertice.setDistance(currentVertice.getDistance() + 1);
					graphVertice.setPrevious(currentVertice);
					stack.push(graphVertice);
				}

				if (!graphVertice.getPreviousList().contains(currentVertice))
					graphVertice.setPrevious(currentVertice);

			}
			currentVertice.setColor(EnumColor.BLACK);
		}

		this.finalVertex = this.getVertex(this.finalVertex.getKey());
	}

	@Override
	public List<Vertex> parseIntergerListToGraphicVerticeList(List<Integer> verticesInIntergerForm) {
		Vertex currentVertice;
		List<Vertex> adjacentsVertices = new ArrayList<Vertex>();

		for (Integer integerVertice : verticesInIntergerForm) {
			currentVertice = new Vertex(integerVertice, EnumColor.WHITE);
			if (this.vertices.contains(currentVertice)) {
				currentVertice = getVertex(currentVertice.getKey());
				adjacentsVertices.add(currentVertice);
			}
		}
		return adjacentsVertices;
	}

	@Override
	public int getOrder() {
		return this.verticesNumber;
	}

	@Override
	public int getLength() {
		return this.edgeNumber;
	}

	@Override
	public int[] getContent() {
		return content;
	}

	@Override
	public void setContent(int[] content) {
		this.content = content;
	}

	@Override
	public List<Vertex> getVertices() {
		return vertices;
	}

	@Override
	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	@Override
	public Vertex getInitialVertex() {
		return initialVertex;
	}

	@Override
	public void setInitialVertex(Vertex InitialVertex) {
		this.initialVertex = InitialVertex;
	}

	@Override
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	@Override
	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	@Override
	public int getEdgeNumber() {
		return edgeNumber;
	}

	@Override
	public void setEdgeNumber(int edgeNumber) {
		this.edgeNumber = edgeNumber;
	}

	@Override
	public int getVerticesNumber() {
		return verticesNumber;
	}

	@Override
	public void setVerticesNumber(int verticesNumber) {
		this.verticesNumber = verticesNumber;
	}

	@Override
	public Vertex getVertex(int key) {
		return vertices.get(this.vertices.indexOf(new Vertex(key, EnumColor.WHITE)));
	}

	@Override
	public Vertex getFinalVertex() {
		return finalVertex;
	}

	@Override
	public void setFinalVertex(Vertex finalVertice) {
		this.finalVertex = finalVertice;
	}

	@Override
	public void runBFSAlgorithm() throws Exception {
		this.BFSAlgorithm(this.initialVertex.getKey());
		shortestPathBetweenInitialVertex_Log(this.finalVertex);
	}

	@Override
	public void runDFSAlgorithm() throws Exception {
		this.DFS_Start(this.initialVertex.getKey());
	}

	private void shortestPathBetweenInitialVertex_Log(Vertex finalVertice) {
		if (finalVertice.equals(this.initialVertex)) {
			LOGGER.log(Level.WARNING, "[BFS SHORTEST PATH]:" + this.initialVertex);
		} else {
			if (finalVertice.getPreviousVerticeWithShortestDistance() == null) {
				System.out.println("There is not a path");
				LOGGER.log(Level.WARNING, "[BFS SHORTEST PATH]: THERE IS NOT A PATH");
			} else {
				shortestPathBetweenInitialVertex_Log(finalVertice.getPreviousVerticeWithShortestDistance());
				LOGGER.log(Level.WARNING, "[BFS SHORTEST PATH]:" + finalVertice);
			}
		}
	}

}
