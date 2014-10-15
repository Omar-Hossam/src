
public class Monotonicity {
	
	public static int getHeuristicScore(Grid grid) {
		int penalty = 0;
		int last = 0;
		boolean left_bigger = false;
		boolean first_find = true;
				
		for (int i = 0; i < 4; i++) {
			last = 0;
			left_bigger = false;
			first_find = true;
			for (int j = 0; j < 4; j++) {
				int temp = grid.getGrid()[i][j];
				if(temp!= 0) {
					if (first_find) {
						for (int b = j+1; b < 4;b++) {
							int temp2 = grid.getGrid()[i][b];
							if (temp2 != 0) {
								if (temp > temp2) {
									left_bigger = true;
									first_find = false;
									last = temp2;
									j = b;
									break;
								} else if (temp < temp2) {
									left_bigger = false;
									first_find = false;
									last = temp2;
									j = b;
									break;
								}
							}
						}
					} else {
						if (left_bigger) {
							if (temp > last) {
								penalty += (temp - last);
								last = temp;
							}
						} else {
							if (temp < last) {
								penalty += (last - temp);
								last = temp;
							}
						}
					}
				}	
			}
		}
		
		for (int j = 0; j < 4; j++) {
			last = 0;
			left_bigger = false;
			first_find = true;
			for (int i = 0; i < 4; i++) {
				int temp = grid.getGrid()[i][j];
				if(temp!= 0) {
					if (first_find) {
						for (int b = i+1; b < 4;b++) {
							int temp2 = grid.getGrid()[b][j];
							if (temp2 != 0) {
								if (temp > temp2) {
									left_bigger = true;
									first_find = false;
									last = temp2;
									i = b;
									break;
								} else if (temp < temp2) {
									left_bigger = false;
									first_find = false;
									last = temp2;
									i = b;
									break;
								}
							}
						}
					} else {
						if (left_bigger) {
							if (temp > last) {
								penalty += (temp - last);
								last = temp;
							}
						} else {
							if (temp < last) {
								penalty += (last - temp);
								last = temp;
							}
						}
					}
				}	
			}
		}
		
		return penalty;
	}
	
	public static void main(String[]s) {
		twozerofoureight game = new twozerofoureight();
		Grid grid = game.GenGrid();
		grid  = game.GoDown(grid);
		grid = game.GoLeft(grid);
		grid = game.GoDown(grid);
		grid = game.GoLeft(grid);
		grid = game.GoRight(grid);
		grid = game.GoDown(grid);
		grid = game.GoLeft(grid);
		grid = game.GoUp(grid);
		grid = game.GoDown(grid);
		grid = game.GoLeft(grid);
		grid = game.GoDown(grid);
		grid  = game.GoDown(grid);
		grid = game.GoRight(grid);
		grid = game.GoLeft(grid);
		
		grid = game.GoDown(grid);
		grid = game.GoRight(grid);
		grid = game.GoLeft(grid);
		grid = game.GoRight(grid);
		grid = game.GoRight(grid);
		grid = game.GoDown(grid);
		grid = game.GoRight(grid);
		grid = game.GoLeft(grid);
		grid = game.GoUp(grid);
		grid = game.GoDown(grid);
		grid = game.GoRight(grid);
		grid = game.GoLeft(grid);
		grid = game.GoDown(grid);
		int penalty = Monotonicity.getHeuristicScore(grid);
		System.out.println(penalty);
		game.PrintGrid(grid);
	}
}


