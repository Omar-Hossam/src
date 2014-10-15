import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Depth {
	// LinkedList<Grid> stack = new LinkedList<Grid>();
	Stack<Grid> stack = new Stack<Grid>();
	ArrayList<Grid> visited = new ArrayList<Grid>();

	twozerofoureight game = new twozerofoureight();
	int m;
	int win;


	public Depth(int m) {
		stack.add(game.GenGrid());
		this.m = m;
		win = m;
		completeTree();
	}

	public Boolean checkVisited(Grid grid) {
		Boolean seen = false;
		for (int i = 0; i < visited.size(); i++) {
			int [][] x = visited.get(i).getGrid();
			int [][] y = grid.getGrid();
			Boolean same = true;
			for (int k = 0; k < 4; k++) {
				for (int j = 0; j < 4; j++) {
					if(x[k][j] != y[k][j]){
						same = false;
					}
				}
			}
			if(same != false){
				seen = true;
			}
		}
		return seen;
	}

	public Grid completeTree() {
		Grid temp;
		while (true) {
			if (stack.isEmpty()) {
				return null;
			}
			int[][] current = stack.peek().getGrid();
			int[][] old = new int[4][4];
			for (int i = 0; i < old.length; i++)
				for (int j = 0; j < old[i].length; j++)
					old[i][j] = current[i][j];
			temp = new Grid(old, stack.pop().getScore());
			
			if (!checkVisited(temp)) {
				visited.add(temp);
				
				if (game.win(temp, win)) {
					return temp;
				}

				if (!game.GameOver(temp)) {
					//game.PrintGrid(temp);
					Grid gridleft = game.GoLeft(temp);
					Grid gridup = game.GoUp(temp);
					Grid griddown = game.GoDown(temp);
					Grid gridright = game.GoRight(temp);
					
					stack.push(gridleft);
					stack.push(gridup);
					stack.push(griddown);
					stack.push(gridright);
					
				} else {
					//game.PrintGrid(temp);
				}
			}
		}
	}

	public static void main(String[] args) {
		// twozerofoureight game = new twozerofoureight();
		// Grid temp = game.GenGrid();
		// game.PrintGrid(game.GoRight(temp));
		Depth depth = new Depth(2048*4);
		Grid winner = depth.completeTree();
		depth.game.PrintGrid(winner);
	}
}