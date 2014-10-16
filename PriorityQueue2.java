import java.util.LinkedList;


public class PriorityQueue2 {
	LinkedList<Grid> queue = new LinkedList<Grid>();

	public PriorityQueue2() {
	}
	
	// add node to priority queue based on heuristic score
	public void add(Grid grid) {
		int index  = 0;
		for(int i = 0; i < queue.size(); i++) {
			Grid temp = queue.get(i);
			if (temp.getHeuristic() > grid.getHeuristic()) {
				index = i;
				break;
			}
		}
		queue.add(index, grid);
	}
	
	//gets first element
	public Grid peek() {
		return queue.get(0);
	}
	
	//gets and removes first element
	public Grid pop() {
		return queue.remove(0);
	}
	
	public LinkedList<Grid> getQueue() {
		return queue;
	}

	public void setQueue(LinkedList<Grid> queue) {
		this.queue = queue;
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
}
