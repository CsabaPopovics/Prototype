import java.util.ArrayList;
import java.util.Scanner;

public class Pawn {
	protected String name;
	protected Field field;
	protected ArrayList<Item> inventory = new ArrayList<Item>();
	protected int bodyTemp = 4;
	protected int workUnit = 4;
	private boolean finished = false;
	private boolean isActive = false;
	
	public Pawn() {}
	
	public Pawn(String name) {this.name = name;}
	
	public String getName() { return name;}
	
	public void setName(String name) {this.name = name;}
	
	public void setField(Field field) { this.field = field;}

	public void die() { Game.end();}

	public void updateBodyTemp(int i) {
		bodyTemp += i;
		if(bodyTemp == 0)
			die();
		
	}

	public Field getField() {
		return field;
	}
	
	public void addToInventory(Item i) {
		if(i != null) {
			inventory.add(i);
			i.setOwner(this);
		}
	}

	public void removeFromInventory(Item item) {
		if(item != null && inventory.contains(item)) {
			inventory.remove(item);
		}
		
	}
	
	public void step(Direction d) {
		Field destination = field.getNeighbour(d);
		if(destination != null) {
			field.remove(this);
			destination.accept(this);
			workUnit--;
		}
	}
	
	public void clearSnow(Item i) {
		boolean cleaningWithItem = false;
		if(i != null) {
			cleaningWithItem = i.use("Clear");
		}
		
		if(cleaningWithItem || field.updateSnow(-1))
			workUnit--;
	}
	
	public void fire() { Game.checkConditions();}
	
	public void assembleGun() {
		for(Pawn p : field.getCharacters()) {
			for(Item i : p.getInventory()) {
				addPart(i);
			}
		}
		fire();
		workUnit--;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void excavate() {
		Item excavatedItem = field.getItem();
		if(excavatedItem != null) {
			addToInventory(excavatedItem);
			field.removeItem();
			workUnit--;
		}
	}
	
	public boolean rescue(Pawn p) {
		for(Item i : inventory) {
			if(throwRope(i, p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean cryForHelp() {
		for(Direction d : Direction.values()) {
			Field helpingField = field.getNeighbour(d);
			if(helpingField != null) {
				ArrayList<Pawn> mySaviors = new ArrayList<Pawn>();
				mySaviors = helpingField.getCharacters();
				if(mySaviors != null) {
					for(Pawn savior : mySaviors) {
						if(savior.rescue(this)) {
							return true;
						}
					}
				}
			}
			
		}
		return false;
	}
	
	public boolean throwRope(Item i, Pawn p) {
		if(i.use("Throw")) {
			p.getField().remove(p);
			field.accept(p);
			return true;
		}
		return false;
	}
	
	public boolean putOn(Item i) {
		return i.use("PutOn");
	}
	
	public void fallIntoWater() {
		for(Item i : inventory) {
			if(putOn(i))
				return;
		}
		if(!cryForHelp())
			die();
	}
	
	public void addPart(Item i) {
		i.use("AddPart");
	}


	public void eat(int parseInt) {
		Item i = inventory.get(parseInt);
		if(i != null) {
			if(i.use("Eat"))
				workUnit--;
		}
	}

	public Item getItem(int index) {
		return inventory.get(index);
	}

	public void inspect(Direction right) {
	}

	public void buildIgloo() {
	}

	public void setupTent(int parseInt) {
		Item i = inventory.get(parseInt);
		if(i != null) {
			if(i.use("Setup"))
				workUnit--;
		}
	}
	
	public void command() {
		
	}

	public void finish() {
		finished = true;
		isActive = false;
	}
	
	public void resetWorkunits() {
		workUnit = 4;
	}
	
	public void setAsActive() {isActive = true;}
}
