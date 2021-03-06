import java.util.Scanner;

import static java.lang.Integer.parseInt;

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


	/**
	 * Meghívja a Field toString() metódusát, és ehhez hozzáadja a tartalmazott itemeket
	 * @return
	 */
    protected String toStringHelper(){
        String res= super.toString();
        if(item!=null) res+="item "+item.toString();
        return res;
    }

	/**
	 * Stringgé alakítja a Field-et, nevével együtt
	 * @return
	 */
	@Override
    public String toString() {
        String res="Field "+name+String.format("%n")+String.format("capacity -1%n");
        res+=toStringHelper();
        res+=String.format("%n");
        return res;
    }

	/**
	 * Beolvas egy IceField leírást, és beállítja e szerint a Field objektumot
	 * @param scanner std inputról olvasó scanner a következő leetséges sorokkal:
	 *                	 [item itemtípusa]
	 *                   [capacityDiscovered]
	 *                   [hasIgloo]
	 *                   [hasTent]
	 *                   snow mennyiség
	 */
	@Override
    public void parse(Scanner scanner) {
	    if(scanner.hasNextLine()){
	        String[] words=scanner.nextLine().split(" ");
	        if(words[0].equals("item") && words.length==2){
                item=Item.parseItem(new String[]{words[1]}).get(0);
            }
	        if(words[0].equals("")) return;
	        if(words[0].equals("hasIgloo")) igloo=true;
			if(words[0].equals("hasTent")) tent=true;
			if(words[0].equals("snow")) snowLevel=parseInt(words[1]);
        }
        super.parse(scanner);

    }

	@Override
	public boolean setItem(Item i) {
		if(item != null) {
			item = i;
			return true;
		}
		return false;
	}
}
