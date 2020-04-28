import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class Item {
	protected Pawn owner = null;

    /**
     * Stringtömb alapján létrehoz egy Item-listát pontosan egy elemmel
     * @param words egy db item leírása szavanként
     * @return egy db , a létrehozott itemet tartalmazó lista
     */
	public static List<Item> parseItem(String[] words){
	    List<Item> inv=new ArrayList<Item>();
        if(words.length==1){
            switch(words[0]){
                case "shovel":
                   inv.add(new Shovel());break;
                case "scubasuit":
                    inv.add (new Scubasuit());break;
                case "food":
                    inv.add(new Food());break;
                case "rope":
                    inv.add(new Rope());break;
                case "tent":
                    inv.add(new Tent());break;
                case "ammo":
                    inv.add(new Ammo());break;
                case "flare":
                    inv.add(new Flare());break;
                case "pistol":
                    inv.add(new Pistol());break;
                default: return inv;
            }
        }
        if(words.length==2){
            if(words[0].equals("fragileShovel")){
                inv.add(new FragileShovel(parseInt(words[1])));
            }
        }
        return inv;
    }

    /**
     * Beolvassa szövegből egy karakterhez tartozó Item-listát
     * @param scanner std inputról olvasó Scanner
     * @return beolvasott és létrehozott itemeket tartalmazó lista
     */
    public static List<Item> parseItemList(Scanner scanner) {
        boolean parse=true;
        List<Item> inventory=new ArrayList<Item>();
        while (scanner.hasNextLine() && parse){
            String[] words=scanner.nextLine().split(" ");
            if(words.length>0)
                if(words[0].equals("")) return inventory;
                inventory.addAll(parseItem(words));
            if(words.length==0) parse=false;
        }
        return inventory;

    }

    public abstract boolean use(String cmd);
	
	public void setOwner(Pawn p) { owner = p;}
	
	public Pawn getOwner() { return owner;}

}
