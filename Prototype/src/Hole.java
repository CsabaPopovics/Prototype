
public class Hole extends Field{
	
	public Hole() {super(); limit = 0;}
	
	public Hole(String name) {super(name); limit = 0;}

    public Hole(Field f) {
		super(f);
    }

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

		String res= "Field "+name+String.format("%n")+String.format("capacity 0%n");
		res+=super.toString();
		return res;
	}

	@Override
	public boolean setItem(Item i) {
		return false;
	}
}
