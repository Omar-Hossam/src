
public class Monotonicity {

	//calculates penalty for not having increasing or descending rows and columns ordered
	public static int getHeuristicScore(Grid grid) {
		int penalty = 0;
		int last = 0;
		// to know is this row or column should be sorted ascending or descending
		boolean left_bigger = false;
		boolean first_find = true;

		for (int i = 0; i < 4; i++) {
			last = 0;
			left_bigger = false;
			first_find = true;
			for (int j = 0; j < 4; j++) {
				// get current number in loop 
				int temp = grid.getGrid()[i][j];
				if(temp!= 0) {
					//if number wasnt 0 because 0 represents empty tile 
					//so we dont want to check with empty tile
					if (first_find) {
						//first time to check in row so we dont have a previous element nor is it ascending or descending
						for (int b = j+1; b < 4;b++) {
							// loop to get next element not equal to 0
							int temp2 = grid.getGrid()[i][b];
							if (temp2 != 0) {
								if (temp > temp2) {
									// set descending but no penalty now
									left_bigger = true;
									first_find = false;
									last = temp2;
									j = b;
									break;
								} else if (temp < temp2) {
									//set ascending but no penalty now
									left_bigger = false;
									first_find = false;
									last = temp2;
									j = b;
									break;
								}
							}
						}
					} else {
						// not first time so we have a previous element and know is it descending or ascending
						if (left_bigger) {
							//should be descending but current item doesnt follow order
							if (temp > last) {
								//increase penalty with difference between last and current item
								penalty += (temp - last);
								last = temp;
							}
						} else {
							//should be descending but current item doesnt follow order
							if (temp < last) {
								penalty += (last - temp);
								last = temp;
							}
						}
					}
				}	
			}
		}
		
		//same as above but checks for columns
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


