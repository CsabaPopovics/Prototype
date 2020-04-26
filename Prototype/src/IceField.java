
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

    protected Item item=null;

    protected String toStringHelper(){
        String res= super.toString();
        if(item!=null) res+=item.toString();
        return res;
    }

    @Override
    public String toString() {
        String res=toStringHelper();
        res+="capacity -1%n";
        return res;
    }
}
