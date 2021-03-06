import java.util.Random;

public class UnstableIceField extends IceField{

	private int limit;// Ha ennyi van rajta, még épp nem fordul át
	
	public UnstableIceField() {
		super();
		limit = new Random().nextInt(game.getCharacterCount()) + 1;
	}
	
	public UnstableIceField(String name) {
		super(name);
		limit = new Random().nextInt(game.getCharacterCount()) + 1;
	}

    public UnstableIceField(String name, int capacity) {
		super(name);
		limit=capacity;

    }
    
    public void setLimit(int limit) {
    	this.limit = limit;
    }

    @Override
	public void accept(Pawn p) {
		if(p != null) {
			characters.add(p);
			p.setField(this);
			if(characters.size() > limit) { //limitnél még épp ne forduljon át
				flip();
			}
		}
	}
	
	public void flip() {
		System.out.println("IceField "+name+ " flipped");
		for(Pawn pawn : characters)
			pawn.fallIntoWater();

	}

    @Override
    public String toString() {
        String res= "Field "+name+String.format("%n")+String.format("capacity 0%n");

        res+=toStringHelper();
        return res;
    }

}
