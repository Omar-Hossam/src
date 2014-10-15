import java.util.ArrayList;
import java.util.LinkedList;


public class Greedy {
	PriorityQueue2 queue = new PriorityQueue2();
	twozerofoureight game = new twozerofoureight();
	ArrayList<Grid> visited = new ArrayList<Grid>();
	int m;
	int win;

	public Greedy(int m) {
		queue.add(game.GenGrid());
		this.m = m;
		win = m;
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


	//1 for monotonicity 2 for smoothness
	public Grid completeTree(int control) {
		Grid temp;
		Grid temp2;
		Grid temp3;
		Grid temp4;
		while(true) {
			if (queue.isEmpty()) {
				return null;
			}
			int[][] current = queue.peek().getGrid();
			int[][] old = new int [4][4];
			for(int i=0; i<old.length; i++)
				for(int j=0; j<old[i].length; j++)
					old[i][j]=current[i][j];
			temp = new Grid(old, queue.peek().pathCost, queue.peek().depth, queue.peek(), 0, queue.pop().getScore());

			game.PrintGrid(temp);

			if (control == 1) {
				temp.setHeuristic(Monotonicity.getHeuristicScore(temp));
			} else {
				temp.setHeuristic(Smoothness.getHeuristicScore(temp));
			}

			int[][] old2 = new int [4][4];
			for(int i=0; i<old2.length; i++)
				for(int j=0; j<old2[i].length; j++)
					old2[i][j]=current[i][j];
			temp2 = new Grid(old2, temp.pathCost, temp.depth, temp, 0, temp.getScore());

			if (control == 1) {
				temp2.setHeuristic(Monotonicity.getHeuristicScore(temp2));
			} else {
				temp2.setHeuristic(Smoothness.getHeuristicScore(temp2));
			}

			int[][] old3 = new int [4][4];
			for(int i=0; i<old3.length; i++)
				for(int j=0; j<old3[i].length; j++)
					old3[i][j]=current[i][j];
			temp3 = new Grid(old3, temp.pathCost, temp.depth, temp, 0, temp.getScore());

			if (control == 1) {
				temp3.setHeuristic(Monotonicity.getHeuristicScore(temp3));
			} else {
				temp3.setHeuristic(Smoothness.getHeuristicScore(temp3));
			}

			int[][] old4 = new int [4][4];
			for(int i=0; i<old4.length; i++)
				for(int j=0; j<old4[i].length; j++)
					old4[i][j]=current[i][j];
			temp4 = new Grid(old4, temp.pathCost, temp.depth, temp, 0, temp.getScore());

			if (control == 1) {
				temp4.setHeuristic(Monotonicity.getHeuristicScore(temp4));
			} else {
				temp4.setHeuristic(Smoothness.getHeuristicScore(temp4));
			}

			if (game.win(temp, win)) {
				return temp;
			}
			if (!checkVisited(temp)) {
				visited.add(temp);
				if (!game.GameOver(temp)) {
					Grid gridleft = game.GoLeft(temp);
					if (control == 1) {
						gridleft.setHeuristic(Monotonicity.getHeuristicScore(gridleft));
					} else {
						gridleft.setHeuristic(Smoothness.getHeuristicScore(gridleft));
					}
					//game.PrintGrid(gridleft);
					Grid gridright = game.GoRight(temp2);
					if (control == 1) {
						gridright.setHeuristic(Monotonicity.getHeuristicScore(gridright));
					} else {
						gridright.setHeuristic(Smoothness.getHeuristicScore(gridright));
					}
					//game.PrintGrid(gridright);
					Grid gridup = game.GoUp(temp3);
					if (control == 1) {
						gridup.setHeuristic(Monotonicity.getHeuristicScore(gridup));
					} else {
						gridup.setHeuristic(Smoothness.getHeuristicScore(gridup));
					}
					//game.PrintGrid(gridup);
					Grid griddown = game.GoDown(temp4);
					if (control == 1) {
						griddown.setHeuristic(Monotonicity.getHeuristicScore(griddown));
					} else {
						griddown.setHeuristic(Smoothness.getHeuristicScore(griddown));
					}
					//game.PrintGrid(griddown);
					//System.out.println(gridleft.compareTo(temp));
					//System.out.println("11");
					queue.add(gridleft);
					//System.out.println(gridright.compareTo(temp2));
					//System.out.println("22");
					queue.add(gridright);
					//System.out.println(gridup.compareTo(temp3));
					//System.out.println("33");
					queue.add(gridup);
					//System.out.println(griddown.compareTo(temp4));
					//System.out.println("44");
					queue.add(griddown);
				}
			}
			else {
				//game.PrintGrid(temp);
			}
		}
	}

	public static void main(String[] args) {
		//twozerofoureight game = new twozerofoureight();
		//Grid temp = game.GenGrid();
		//game.PrintGrid(game.GoRight(temp));
		AStar breadth = new AStar(1024);
		Grid winner = breadth.completeTree(2);
		breadth.game.PrintGrid(winner);
	}
}