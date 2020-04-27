import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class Field {
	protected int snowLevel;
	protected int limit = -1;
	protected boolean tent = false;
	protected boolean igloo = false;
	protected ArrayList<Pawn> characters = new ArrayList<Pawn>();
	protected HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(); 
	protected PolarBear polarBear = null;
	protected String name;
	protected boolean capacityDiscovered=false;
	protected Game game;
	
	public Field() {}
	
	public Field(String name) {this.name = name;}

	public Field(Field f) {
		snowLevel=f.snowLevel;
		limit=f.limit;
		tent=f.tent;
		igloo=f.igloo;
		characters=f.characters;
		neighbours=f.neighbours;
		polarBear=f.polarBear;
		name=f.name;
		capacityDiscovered=f.capacityDiscovered;
	}

	public void parse(Scanner scanner) {

		boolean parse=true;
		String[] words=null;


		while(scanner.hasNextLine() && parse){
			words=scanner.nextLine().split(" ");
			if(words.length==1){
				switch (words[0]){
					case "capacityDiscovered": capacityDiscovered=true;break;
					case "hasIgloo": igloo=true;break;
					case "hasTent": tent=true;break;
					default: break;

				}
			}
			if(words.length==2){
				switch (words[0]){
					case "snow":
						snowLevel=parseInt(words[1]);break;




				}
			}
			if(words[0].equals("")) parse=false;

		}
		return;
	}
	
	public void setNeighbour(Field f, Direction d) {
		neighbours.put(d, f);
	}

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
	
	public abstract boolean setItem(Item i);
	
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
		res="";
		if(capacityDiscovered()) res+=String.format("capacityDiscovered%n");
		res+=String.format("snow ")+snowLevel+String.format("%n");
		if(igloo) res+=String.format("hasIgloo%n");
		if(tent) res+=String.format("hasTent%n");
		return res;
	}

	private boolean capacityDiscovered() {
		return capacityDiscovered;
	}

	//Betöltéshez pawn elhelyezése vizsgálat nélkül
	public void placePawnFirstTime(Pawn p){
		characters.add(p);
	}
	
	public void copyNeighbours(Field f) {
		for(Direction d : Direction.values()) {
			Field neighbour = f.getNeighbour(d);
			if(neighbour != null)
				setNeighbour(neighbour, d);
		}
	}
}
