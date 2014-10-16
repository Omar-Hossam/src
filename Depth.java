import java.util.ArrayList;
import java.util.Stack;

public class Depth {

	Stack<Grid> stack = new Stack<Grid>();
	ArrayList<Grid> visited = new ArrayList<Grid>();
	twozerofoureight game = new twozerofoureight();
	int win;

	public Depth(Grid root, int m) {
		stack.add(root);
		win = m;
	}

	/*
	 * Checks if grid has been visited by the search algorithm before. Ie: grid
	 * is found in visited ArrayList.
	 */
	public Boolean checkVisited(Grid grid) {
		Boolean seen = false;
		for (int i = 0; i < visited.size(); i++) {
			int[][] x = visited.get(i).getGrid();
			int[][] y = grid.getGrid();
			Boolean same = true;
			for (int k = 0; k < 4; k++) {
				for (int j = 0; j < 4; j++) {
					if (x[k][j] != y[k][j]) {
						same = false;
					}
				}
			}
			if (same != false) {
				seen = true;
			}
		}
		return seen;
	}

	/*
	 * Generates the tree. Searches.
	 */
	public Grid completeTree() {
		Grid temp;
		while (true) {

			if (stack.isEmpty()) {
				return null;
			}

			/*
			 * Creates a new Grid from root.
			 */
			int[][] current = stack.peek().getGrid();
			int[][] old = new int[4][4];
			for (int i = 0; i < old.length; i++)
				for (int j = 0; j < old[i].length; j++)
					old[i][j] = current[i][j];
			temp = new Grid(old, stack.peek().pathCost, stack.peek().depth,
					stack.peek().parent, stack.peek().operation, stack.pop()
							.getScore());

			/*
			 * If node hasn't been visited before; the search creates for new
			 * children and adds the to stack. If node has been visited is
			 * disregards the branch and doesn't go back to it or expand it.
			 */
			if (!checkVisited(temp)) {
				visited.add(temp);

				if (game.win(temp, win)) {
					return temp;
				}

				if (!game.GameOver(temp)) {
					Grid gridleft = game.GoLeft(temp);
					Grid gridup = game.GoUp(temp);
					Grid griddown = game.GoDown(temp);
					Grid gridright = game.GoRight(temp);

					stack.push(gridleft);
					stack.push(gridup);
					stack.push(griddown);
					stack.push(gridright);

				}
			}
		}
	}
}