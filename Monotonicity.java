
public class Monotonicity {
	
	public static int getHeuristicScore(Grid grid) {
		int penalty = 0;
		int last = 0;
		boolean left_bigger = false;
		boolean first_find = true;
				
		for (int i = 0; i < 4; i++) {
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
								} else if (temp < temp2) {
									left_bigger = false;
									first_find = false;
									last = temp2;
								}
							}
						}
					} else {
						if (left_bigger) {
							if (temp > last) {
								//penalty
							}
						} else {
							if (temp < last) {
								//penalty
							}
						}
					}
				}
			}
		}
		
		return penalty;
	}
}
