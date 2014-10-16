import java.util.Stack;

public class Search {

	private Grid result;

	public Search(int m, String strategy, Boolean visualize) {
		switch (strategy) {
		case ("BF"):
			Breadth breadth = new Breadth(2 ^ m);
			result = breadth.completeTree();
		case ("DF"):
			Depth depth = new Depth(2048);
			result = depth.completeTree();
		case ("ID"):
			IDDFS iddfs = new IDDFS(2 ^ m);
			result = iddfs.completeTree();
		case ("GR1"):
			Greedy greedy = new Greedy(2 ^ m);
			result = greedy.completeTree(1);
		case ("GR2"):
			Greedy greedy2 = new Greedy(2 ^ m);
			result = greedy2.completeTree(2);
		case ("AS1"):
			AStar astar = new AStar(2 ^ m);
			result = astar.completeTree(1);
		case ("AS2"):
			AStar astar2 = new AStar(2 ^ m);
			result = astar2.completeTree(2);
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
		System.out.println(oldGrid.getScore() + oldGrid.operation);
	}

	public void Visualize(Grid grid) {
		Stack<Grid> stack = new Stack();
		stack.push(grid);
		while (grid.parent != null) {
			stack.push((Grid) stack.peek().parent);
		}
		while (!stack.empty()) {
			this.PrintGrid(stack.pop());
		}
	}

	public static void main(String[] df) {
		Search search = new Search(11, "DF", false);
	}
}
