import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class IDDFS {
	// LinkedList<Grid> stack = new LinkedList<Grid>();
	Stack<Grid> stack = new Stack<Grid>();
	ArrayList<Grid> visited = new ArrayList<Grid>();
	Grid root = null;

	twozerofoureight game = new twozerofoureight();
	int m;
	int win;
	int maxDepth = 0;

	public IDDFS(int m) {
		root = game.GenGrid();
		this.m = m;
		win = m;
		completeTree();
	}

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
		boolean first_time = true;

		while (true) {
			if(first_time) {
				int[][] current = root.getGrid();
				int[][] old = new int[4][4];
				for (int i = 0; i < old.length; i++)
					for (int j = 0; j < old[i].length; j++)
						old[i][j] = current[i][j];
				Grid newRoot = new Grid(old, 0);
				stack.push(newRoot);
				first_time = false;
			} 

			if (stack.isEmpty()) {

				visited.clear();

				maxDepth++;

				int[][] current = root.getGrid();
				int[][] old = new int[4][4];
				for (int i = 0; i < old.length; i++)
					for (int j = 0; j < old[i].length; j++)
						old[i][j] = current[i][j];
				Grid newRoot = new Grid(old, 0);
				stack.push(newRoot);
			}

			int[][] current = stack.peek().getGrid();
			int[][] old = new int[4][4];
			for (int i = 0; i < old.length; i++)
				for (int j = 0; j < old[i].length; j++)
					old[i][j] = current[i][j];
			temp = new Grid(old, stack.pop().getScore());
			game.PrintGrid(temp);
			if (temp.getDepth() < maxDepth) {
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

						gridleft.setDepth(temp.getDepth() + 1);
						gridup.setDepth(temp.getDepth() + 1);
						griddown.setDepth(temp.getDepth() + 1);
						gridright.setDepth(temp.getDepth() + 1);

						stack.push(gridleft);
						stack.push(gridup);
						stack.push(griddown);
						stack.push(gridright);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// twozerofoureight game = new twozerofoureight();
		// Grid temp = game.GenGrid();
		// game.PrintGrid(game.GoRight(temp));
		IDDFS depth = new IDDFS(2048);
		Grid winner = depth.completeTree();
		depth.game.PrintGrid(winner);
	}
}