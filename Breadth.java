import java.util.ArrayList;
import java.util.LinkedList;

public class Breadth {
	LinkedList<Grid> queue = new LinkedList<Grid>();
	twozerofoureight game = new twozerofoureight();
	ArrayList<Grid> visited = new ArrayList<Grid>();
	int m;
	int win;

	public Breadth(int m) {
		queue.add(game.GenGrid());
		this.m = m;
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

	public Grid completeTree() {
		Grid temp;
		Grid temp2;
		Grid temp3;
		Grid temp4;
		while (true) {
			if (queue.isEmpty()) {
				return null;
			}
			int[][] current = queue.getFirst().getGrid();

			// Creates new Grid for left move.
			int[][] old = new int[4][4];
			for (int i = 0; i < old.length; i++)
				for (int j = 0; j < old[i].length; j++)
					old[i][j] = current[i][j];
			temp = new Grid(old, queue.getFirst().pathCost,
					queue.getFirst().depth, queue.getFirst().parent, queue
							.getFirst().getOperation(), queue.pop().getScore());

			// Creates new Grid for right move.
			int[][] old2 = new int[4][4];
			for (int i = 0; i < old2.length; i++)
				for (int j = 0; j < old2[i].length; j++)
					old2[i][j] = current[i][j];
			temp2 = new Grid(old2, temp.pathCost, temp.depth, temp.parent,
					temp.operation, temp.getScore());

			// Creates new Grid for up move.
			int[][] old3 = new int[4][4];
			for (int i = 0; i < old3.length; i++)
				for (int j = 0; j < old3[i].length; j++)
					old3[i][j] = current[i][j];
			temp3 = new Grid(old3, temp.pathCost, temp.depth, temp.parent,
					temp.operation, temp.getScore());

			// Creates new Grid for down move.
			int[][] old4 = new int[4][4];
			for (int i = 0; i < old4.length; i++)
				for (int j = 0; j < old4[i].length; j++)
					old4[i][j] = current[i][j];
			temp4 = new Grid(old4, temp.pathCost, temp.depth, temp.parent,
					temp.operation, temp.getScore());

			game.PrintGrid(temp);
			if (!checkVisited(temp)) {
				visited.add(temp);
				if (game.win(temp, win)) {
					return temp;
				}

				game.PrintGrid(temp);

				// If the current node did reach a game over state. It is
				// allowed to
				// create children.
				if (!game.GameOver(temp)) {
					// Creates grids in all directions.
					Grid gridleft = game.GoLeft(temp);
					Grid gridright = game.GoRight(temp2);
					Grid gridup = game.GoUp(temp3);
					Grid griddown = game.GoDown(temp4);

					/*
					 * This set of Ifs checks if a certain move did not create a
					 * similar grid of its parent node. For example; A grid with
					 * only 2s on the leftmost part could not possibly make
					 * another left move. Thus not adding a left child to the
					 * tree.
					 */
					queue.add(gridleft);
					queue.add(gridright);
					queue.add(gridup);
					queue.add(griddown);
				}
			}
		}
	}

	public static void main(String[] args) {
		Breadth breadth = new Breadth(16);
		Grid winner = breadth.completeTree();
		breadth.game.PrintGrid(winner);
	}
}