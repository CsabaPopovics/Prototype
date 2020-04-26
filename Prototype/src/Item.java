import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public abstract class Item {
	protected Pawn owner = null;

	/// itemlistával tér vissza 1 adott karakterhez
    public static List<Item> parseItemList(Scanner scanner) {
        boolean parse=true;
        List<Item> inventory=new ArrayList<Item>();
        while (scanner.hasNextLine() && parse){
            String[] words=scanner.nextLine().split(" ");
            if(words.length==1){
                switch(words[0]){
                    case "shovel":
                        inventory.add(new Shovel()); break;
                    case "scubasuit":
                        inventory.add(new Scubasuit()); break;
                    case "food":
                        inventory.add(new Food()); break;
                    case "rope":
                        inventory.add(new Rope()); break;
                    case "tent":
                        inventory.add(new Tent()); break;
                    case "ammo":
                        inventory.add(new Ammo()); break;
                    case "flare":
                        inventory.add(new Flare()); break;
                    case "pistol":
                        inventory.add(new Pistol()); break;
                    default: break;
                }
            }
            if(words.length==2){
                if(words[0].equals("fragileShovel")){
                    inventory.add(new FragileShovel(parseInt(words[1])));
                }
            }
            if(words.length==0) parse=false;
        }
        return inventory;









    }

    public abstract boolean use(String cmd);
	
	public void setOwner(Pawn p) { owner = p;}
	
	public Pawn getOwner() { return owner;}

}
