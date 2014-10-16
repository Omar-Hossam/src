
public class Smoothness {
	
	//calculates penalty for not having same numbers next to each other
	public static int getHeuristicScore(Grid grid) {
		//penalty sum
		int penalty = 0;
		int last = 0;
		boolean first_find = true;

		for (int i = 0; i < 4; i++) {
			last = 0;
			first_find = true;
			for (int j = 0; j < 4; j++) {
				// get current number in loop 
				int temp = grid.getGrid()[i][j];
				if (temp != 0) {
					//if number wasnt 0 because 0 represents empty tile 
					//so we dont want to check with empty tile
					if (first_find) {
						//first time to check in row so we dont have a previous element yet
						for (int b = j+1; b < 4;b++) {
							// loop to get next element not equal to 0
							int temp2 = grid.getGrid()[i][b];
							if (temp2 != 0) {
								if (temp != temp2) {
									//if they are not equal
									if(temp > temp2) {
										//increase penalty with the division between them
										penalty += (temp/temp2);
										first_find = false;
										last = temp2;
										b = j;
										break;
									}	
									else if (temp < temp2) {
										//increase penalty with the division between them
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
						// not first time so we have a previous element
						if (temp != last) {
							if(temp > last) {
								//increase penalty
								penalty += (temp/last);
								last = temp;
							}	
							else if (temp < last) {
								//increase penalty
								penalty += (last/temp);
								last = temp;
							}			
						}
					}
				}
			}
		}

		//same as above but for columns
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
