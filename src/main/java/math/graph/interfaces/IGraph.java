package math.graph.interfaces;

import java.util.List;

import math.graph.classes.Vertex;

public interface IGraph {

	List<Integer> getAdjacentList(Integer key);

	List<Integer> getPreviousList(Integer key);

	List<Integer> getSucessorList(Integer key);

	List<Vertex> parseIntergerListToGraphicVerticeList(List<Integer> verticesInIntergerForm);

	int getOrder();

	int getLength();

	int[] getContent();

	void setContent(int[] content);

	List<Vertex> getVertices();

	void setVertices(List<Vertex> vertices);

	Vertex getInitialVertex();

	void setInitialVertex(Vertex InitialVertex);

	int[][] getAdjacencyMatrix();

	void setAdjacencyMatrix(int[][] adjacencyMatrix);

	int getEdgeNumber();

	void setEdgeNumber(int edgeNumber);

	int getVerticesNumber();

	void setVerticesNumber(int verticesNumber);

	Vertex getVertex(int key);

	Vertex getFinalVertex();

	void setFinalVertex(Vertex finalVertice);
	
	void runBFSAlgorithm() throws Exception;
	
	void runDFSAlgorithm() throws Exception;

}