import java.util.Random;

public class UnstableIceField extends IceField{
	
	public UnstableIceField() {
		super();
		limit = new Random().nextInt(Game.getCharacterCount()) + 1;
	}
	
	public UnstableIceField(String name) {
		super(name);
		limit = new Random().nextInt(Game.getCharacterCount()) + 1;
	}
	
	@Override
	public void accept(Pawn p) {
		if(p != null) {
			characters.add(p);
			p.setField(this);
			if(characters.size() >= limit) {
				flip();
			}
		}
	}
	
	public void flip() {
		for(Pawn pawn : characters)
			pawn.fallIntoWater();
	}

    @Override
    public String toString() {
        String res= toStringHelper();

        res+="capacity 0%n";
        return res;
    }

}
