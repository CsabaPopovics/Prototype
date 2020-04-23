
public abstract class Item {
	protected Pawn owner = null;
	
	public abstract boolean use(String cmd);
	
	public void setOwner(Pawn p) { owner = p;}
	
	public Pawn getOwner() { return owner;}

}
