
public class Smoothness {
	public static int getHeuristicScore(Grid grid) {
		int penalty = 0;
		int last = 0;
		boolean first_find = true;

		for (int i = 0; i < 4; i++) {
			last = 0;
			first_find = true;
			for (int j = 0; j < 4; j++) {
				int temp = grid.getGrid()[i][j];
				if (temp != 0) {
					if (first_find) {
						for (int b = j+1; b < 4;b++) {
							int temp2 = grid.getGrid()[i][b];
							if (temp2 != 0) {
								if (temp != temp2) {
									if(temp > temp2) {
										penalty += (temp/temp2);
										first_find = false;
										last = temp2;
										b = j;
										break;
									}	
									else if (temp < temp2) {
										penalty += (temp2/temp);
										first_find = false;
										last = temp2;
										b = j;
										break;
									}			
								}
							}
						}
					} else {
						if (temp != last) {
							if(temp > last) {
								penalty += (temp/last);
								last = temp;
							}	
							else if (temp < last) {
								penalty += (last/temp);
								last = temp;
							}			
						}
					}
				}
			}
		}

		for (int j = 0; j < 4; j++) {
			last = 0;
			first_find = true;
			for (int i = 0; i < 4; i++) {
				int temp = grid.getGrid()[i][j];
				if (temp != 0) {
					if (first_find) {
						for (int b = i+1; b < 4;b++) {
							int temp2 = grid.getGrid()[b][j];
							if (temp2 != 0) {
								if (temp != temp2) {
									if(temp > temp2) {
										penalty += (temp/temp2);
										first_find = false;
										last = temp2;
										b = i;
										break;
									}	
									else if (temp < temp2) {
										penalty += (temp2/temp);
										first_find = false;
										last = temp2;
										b = i;
										break;
									}			
								}
							}
						}
					} else {
						if (temp != last) {
							if(temp > last) {
								penalty += (temp/last);
								last = temp;
							}	
							else if (temp < last) {
								penalty += (last/temp);
								last = temp;
							}			
						}
					}
				}
			}
		}		

		return penalty;
	}
}
