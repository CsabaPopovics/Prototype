import java.util.ArrayList;

public class Pawn {
	protected String name;
	protected Field field;
	protected ArrayList<Item> inventory = new ArrayList<Item>();
	protected int bodyTemp = 4;
	protected int workUnit = 4;
	
	public String getName() {return name;}
	
	public void setField(Field field) {this.field = field;}

	public void die() {
		System.out.println(this.name + " died.");
		Game.end();
		
	}

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
		System.out.println(name + " attempts to step in " + d.name() + " direction.");
		Field destination = field.getNeighbour(d);
		if(destination != null) {
			System.out.println("In the given direction there is the " + destination.getName() + " field");
			System.out.println(name + " steps.");
			field.remove(this);
			destination.accept(this);
		} else
			System.out.println("There is nothing in that direction.");
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
				i.use("AddPart");
			}
		}
		fire();
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public void excavate() {
		Item excavatedItem = field.getItem();
		if(excavatedItem != null) {
			addToInventory(excavatedItem);
			field.removeItem();
		}
	}
	
	

}
