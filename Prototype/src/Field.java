import java.util.ArrayList;
import java.util.HashMap;

public class Field {
	protected int snowLevel;
	protected int limit;
	protected boolean tent;
	protected boolean igloo;
	protected ArrayList<Pawn> characters = new ArrayList<Pawn>();
	protected HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(); 
	protected PolarBear polarBear = null;
	protected String name;
	
	public void accept(Pawn p) {
		if(p != null) {
			characters.add(p);
			p.setField(this);
			System.out.println(p.getName() + "'s current location: " + this.name);
		}
	}
	
	public void accept(PolarBear polarBear) {
		if(polarBear != null) {
			this.polarBear = polarBear;
			polarBear.setField(this);
			System.out.println("The bear's current location: " + this.name);
			if(!characters.isEmpty() && !igloo) {
				System.out.println("Bear attack on " + this.name + "! Casualties: ");
				for(Pawn p : characters) {
					p.die();
				}
			}
		}
	}
	
	public void remove(Pawn p) {
		if(p != null && characters.contains(p)) {
			characters.remove(p);
		}
	}
	
	public void removePolarBear() {
		this.polarBear = null;
	}
	
	public boolean updateSnow(int i) {
		if(i >= 0) {
			System.out.println("Blizzard on " + this.name);
			if(!this.igloo && !this.tent && !characters.isEmpty()) {
				for(Pawn p : characters)
					p.updateBodyTemp(-1);
			}
			this.snowLevel += i;
			System.out.println(this.name + "'s current level of snow: " + this.snowLevel);
			return true;
		} else {
			if(this.snowLevel == 0) {
				System.out.println(this.name + " doesn't have any snow on it!");
				return false;
			}
			this.snowLevel += i;
			System.out.println(this.name + "'s current level of snow: " + this.snowLevel);
			return true;
		}
	}
	
	public Field getNeighbour(Direction d) {
		if(neighbours.containsKey(d)) {
			return neighbours.get(d);
		} else return null;
	}
	
	public int getLimit() { return -1;}
	
	public int getLoad() { return characters.size();}
	
	public int getSnowLevel() { return this.snowLevel;}
	
	public ArrayList<Pawn> getCharacters(){
		if(!characters.isEmpty())
			return characters;
		return null;
	}
	
	public boolean setupTent() {
		if(!tent) {
			tent = true;
			return true;
		} else
			return false;
	}
	
	public void resetTent() { tent = false;}
	
	public Item getItem() { return null;}

	public String getName() {
		return name;
	}

	public void removeItem() {
		return;
	}

}
