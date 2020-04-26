
public class PolarBear {
	private Field field;
	
	public void step(Direction d) {
		Field destination = field.getNeighbour(d);
		if(destination != null) {
			field.removePolarBear();
			destination.accept(this);
		}
	}
	
	public void setField(Field field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "Bear "+field.name+"%n";
	}
}
