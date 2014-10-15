import java.util.LinkedList;


public class PriorityQueue2 {
	LinkedList<Grid> queue = new LinkedList<Grid>();

	public PriorityQueue2() {
	}
	
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
	
	public Grid peek() {
		return queue.get(0);
	}
	
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
