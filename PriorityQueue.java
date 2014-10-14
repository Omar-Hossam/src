import java.util.LinkedList;


public class PriorityQueue {
	LinkedList<Grid> queue = new LinkedList<Grid>();

	public PriorityQueue(LinkedList<Grid> queue) {
		super();
		this.queue = queue;
	}
	
	public void add(Grid grid) {
		int index  = 0;
		for(int i = 0; i < queue.size(); i++) {
			Grid temp = queue.get(i);
			if (temp.getCost()+temp.getHeuristic() > grid.getCost()+grid.getHeuristic()) {
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
	
}
