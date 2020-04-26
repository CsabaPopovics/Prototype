import java.util.ArrayList;
import java.util.HashMap;

public class Field {
	protected int snowLevel;
	protected int limit = -1;
	protected boolean tent = false;
	protected boolean igloo = false;
	protected ArrayList<Pawn> characters = new ArrayList<Pawn>();
	protected HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(); 
	protected PolarBear polarBear = null;
	protected String name;
	
	public Field() {}
	
	public Field(String name) {this.name = name;}
	
	public void accept(Pawn p) {
		if(p != null) {
			characters.add(p);
			p.setField(this);
		}
	}
	
	public void accept(PolarBear polarBear) {
		if(polarBear != null) {
			this.polarBear = polarBear;
			polarBear.setField(this);
			if(!characters.isEmpty() && !igloo) {
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
			if(!this.igloo && !this.tent && !characters.isEmpty()) {
				for(Pawn p : characters)
					p.updateBodyTemp(-1);
			}
			this.snowLevel += i;
			return true;
		} else {
			if(this.snowLevel == 0) {
				return false;
			}
			this.snowLevel += i;
			return true;
		}
	}
	
	public Field getNeighbour(Direction d) {
		if(neighbours.containsKey(d)) {
			return neighbours.get(d);
		} else return null;
	}
	
	public int getLimit() { return limit;}
	
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

	public void removeItem() {
		return;
	}
	
	public boolean setIgloo() {
		if(igloo)
			return false;
		return true;
	}
	
	public void setName(String name) {this.name = name;}
	
	public String getName() {return name;}

	@Override
	public String toString() {
		String res;
		res="Field "+name+"%n";
		if(capacityDiscovered()) res+="capacityDiscovered%n";
		res+="snow "+snowLevel+"%n";
		if(igloo) res+="hasIgloo%n";
		if(tent) res+="hasTent%n";
		return res;
	}

	private boolean capacityDiscovered() {
		throw new UnsupportedOperationException("Not Implemented");
	}
}
