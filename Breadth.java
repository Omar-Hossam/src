import java.util.LinkedList;


public class Breadth {
	LinkedList<Grid> queue = new LinkedList<Grid>();
	twozerofoureight game = new twozerofoureight();
	int m;
	int win;
	
	public Breadth(int m) {
		queue.add(game.GenGrid());
		this.m = m;
		win = m;
		completeTree();
	}

	public Grid completeTree() {
		Grid temp;
		Grid temp2;
		Grid temp3;
		Grid temp4;
		while(true) {
			if (queue.isEmpty()) {
				return null;
			}
			int[][] current = queue.getFirst().getGrid();
			int[][] old = new int [4][4];
			for(int i=0; i<old.length; i++)
				  for(int j=0; j<old[i].length; j++)
				    old[i][j]=current[i][j];
			temp = new Grid(old, queue.pop().getScore());
			
			int[][] old2 = new int [4][4];
			for(int i=0; i<old2.length; i++)
				  for(int j=0; j<old2[i].length; j++)
				    old2[i][j]=current[i][j];
			temp2 = new Grid(old2,temp.getScore());
			
			int[][] old3 = new int [4][4];
			for(int i=0; i<old3.length; i++)
				  for(int j=0; j<old3[i].length; j++)
				    old3[i][j]=current[i][j];
			temp3 = new Grid(old3,temp.getScore());
			
			int[][] old4 = new int [4][4];
			for(int i=0; i<old4.length; i++)
				  for(int j=0; j<old4[i].length; j++)
				    old4[i][j]=current[i][j];
			temp4 = new Grid(old4,temp.getScore());
			
			if (game.win(temp, win)) {
				return temp;
			}
			
			game.PrintGrid(temp);
			
			if (!game.GameOver(temp)) {
				Grid gridleft = game.GoLeft(temp);
				game.PrintGrid(gridleft);
				Grid gridright = game.GoRight(temp2);
				game.PrintGrid(gridright);
				Grid gridup = game.GoUp(temp3);
				game.PrintGrid(gridup);
				Grid griddown = game.GoDown(temp4);
				game.PrintGrid(griddown);
				System.out.println(gridleft.compareTo(temp));
				if (gridleft.compareTo(temp) == 0){
					System.out.println("11");
					queue.add(gridleft);
				}
				System.out.println(gridright.compareTo(temp2));
				if (gridright.compareTo(temp2) == 0){
					System.out.println("22");
					queue.add(gridright);
				}
				System.out.println(gridup.compareTo(temp3));
				if (gridup.compareTo(temp3) == 0){
					System.out.println("33");
					queue.add(gridup);
				}
				System.out.println(griddown.compareTo(temp4));
				if (griddown.compareTo(temp4) == 0){
					System.out.println("44");
					queue.add(griddown);
				}
			} else {
				game.PrintGrid(temp);
			}
		}
	}
	
	public static void main(String[] args) {
		//twozerofoureight game = new twozerofoureight();
		//Grid temp = game.GenGrid();
		//game.PrintGrid(game.GoRight(temp));
		Breadth breadth = new Breadth(16);
		Grid winner = breadth.completeTree();
		breadth.game.PrintGrid(winner);
	}
}