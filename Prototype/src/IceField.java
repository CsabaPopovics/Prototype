
public class IceField extends Field{
	protected Item item;
	
	public IceField() {super();}
	
	public IceField(String name) {super(name);}
	
	@Override
	public Item getItem() { return item;}

	@Override
	public void removeItem() {
		item = null;
	}

}
