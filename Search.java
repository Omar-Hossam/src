import java.util.Stack;

public class Search {

	private Grid result;

	public Search(int m, String strategy, Boolean visualize) {
		switch (strategy) {
		case ("BF"):
			Breadth breadth = new Breadth((int) Math.pow(2, m));
			result = breadth.completeTree();
			break;
		case ("DF"):
			Depth depth = new Depth((int) Math.pow(2, m));
			result = depth.completeTree();
			break;
		case ("ID"):
			IDDFS iddfs = new IDDFS((int) Math.pow(2, m));
			result = iddfs.completeTree();
			break;
		case ("GR1"):
			Greedy greedy = new Greedy((int) Math.pow(2, m));
			result = greedy.completeTree(1);
			break;
		case ("GR2"):
			Greedy greedy2 = new Greedy((int) Math.pow(2, m));
			result = greedy2.completeTree(2);
			break;
		case ("AS1"):
			AStar astar = new AStar((int) Math.pow(2, m));
			result = astar.completeTree(1);
			break;
		case ("AS2"):
			AStar astar2 = new AStar((int) Math.pow(2, m));
			result = astar2.completeTree(2);
			break;
		}
		if (visualize) {
			Visualize(result);
		}

	}

	public void PrintGrid(Grid oldGrid) {
		int[][] grid = oldGrid.getGrid();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.printf("%5d ", grid[i][j]);
			}
			System.out.println();
		}
		
		String d = "";
		switch(oldGrid.operation){
		case(1): d = "Left";break;
		case(2): d = "Right"; break;
		case(3): d = "Up"; break;
		case(4): d = "Down"; break;
		}
		System.out.println();
		System.out.println("Score: " + oldGrid.getScore());
		System.out.println("Direction: " + d);
		System.out.println("-------------------------------------------");
	}

	public void Visualize(Grid grid) {
		Stack<Grid> stack = new Stack();
		stack.push(grid);
		while (stack.peek().parent != null) {
			stack.push((Grid) stack.peek().parent);
		}
		while (!stack.empty()) {
			this.PrintGrid(stack.pop());
		}
	}

	public static void main(String[] df) {
		Search search = new Search(12, "DF", true);
	}
}
