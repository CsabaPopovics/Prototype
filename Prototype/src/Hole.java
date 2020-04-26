
public class Hole extends Field{
	
	public Hole() {super(); limit = 0;}
	
	public Hole(String name) {super(name); limit = 0;}
	
	@Override
	public void accept(Pawn p) {
		if(p != null) {
			characters.add(p);
			p.setField(this);
			p.fallIntoWater();
		}
	}
	
	@Override
	public boolean setupTent() {return false;}
	
	@Override
	public boolean setIgloo() {return false;}

	@Override
	public String toString() {
		String res= super.toString();
		res+="capacity 0%n";
		return res;
	}
}
