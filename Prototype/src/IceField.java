import java.util.Scanner;

public class IceField extends Field{
	protected Item item;
	
	public IceField() {super();}
	
	public IceField(String name) {
	    super(name);

	}

    public IceField(Field f) {
	    super(f);

    }

    @Override
	public Item getItem() { return item;}

	@Override
	public void removeItem() {
		item = null;
	}



    protected String toStringHelper(){
        String res= super.toString();
        if(item!=null) res+=item.toString();
        return res;
    }

    @Override
    public String toString() {
        String res="capacity -1%n";
        res+=toStringHelper();
        return res;
    }

    @Override
    public void parse(Scanner scanner) {
	    if(scanner.hasNextLine()){
	        String[] words=scanner.nextLine().split(" ");
	        if(words[0].equals("item") && words.length==2){
                item=Item.parseItem(new String[]{words[1]}).get(0);
            }
        }
        super.parse(scanner);

    }
}
