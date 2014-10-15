
public abstract class GenericSearch {
	
	public abstract boolean win(Grid oldGrid, int win) ;
	
	public abstract boolean GameOver(Grid oldGrid);
	
	public abstract Grid GoLeft(Grid oldGrid);
	
	public abstract Grid GoRight(Grid oldGrid);
	
	public abstract Grid GoUp(Grid oldGrid);
	
	public abstract Grid GoDown(Grid oldGrid);

}
