/**
 * 
 */
package math.graph.classes;

import java.util.ArrayList;
import java.util.List;

import utils.EnumColor;

/**
 * @author cristoferSilva
 *
 */
public class Vertex {

	private Integer key;
	private int distance;
	private EnumColor color;
	private int initialTime, endTime;
	private List<Vertex> previous;

	public Vertex(Integer key, EnumColor color) {
		this.key = key;
		this.color = color;
		this.previous = new ArrayList<Vertex>();
	}

	public Vertex getPreviousVerticeWithShortestDistance() {
		if (previous.size() == 0) {
			return null;
		}

		Integer verticeKeyWithShortestDistance = this.previous.get(0).getDistance();
		Integer currentdistance = 0, verticeIndexWithShortestDistance = 0, listSize = this.previous.size();

		for (int j = 0; j < listSize; j++) {
			currentdistance = this.previous.get(j).getDistance();
			if (currentdistance < verticeKeyWithShortestDistance) {
				verticeKeyWithShortestDistance = currentdistance;
				verticeIndexWithShortestDistance = j;
			}
		}

		return this.previous.get(verticeIndexWithShortestDistance);
	}

	public Vertex getPreviousVerticeWithGreaterDistance() {
		if (previous.size() == 0) {
			return null;
		}

		Integer verticeKeyWithGreaterDistance = this.previous.get(0).getDistance();
		Integer currentdistance = 0, verticeIndexWithGreaterDistance = 0, listSize = this.previous.size();

		for (int j = 0; j < listSize; j++) {
			currentdistance = this.previous.get(j).getDistance();
			if (currentdistance > verticeKeyWithGreaterDistance) {
				verticeKeyWithGreaterDistance = currentdistance;
				verticeIndexWithGreaterDistance = j;
			}
		}

		return this.previous.get(verticeIndexWithGreaterDistance);
	}

	public void setColor(EnumColor color) {
		this.color = color;
	}

	public EnumColor getColor() {
		return color;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Integer getKey() {
		return key;
	}

	public void setPrevious(Vertex previous) {
		this.previous.add(previous);
	}

	public List<Vertex> getPreviousList() {
		return this.previous;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public int getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(int initialTime) {
		this.initialTime = initialTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex) {
			return ((Vertex) obj).getKey().equals(this.getKey());
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		String string = "{ Key: " + this.key + "} <- ";
		return string;
	}

}
