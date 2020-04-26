import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Game {
	private static int progress = 0;
	private static boolean end = false;
	private static boolean win = false;
	
	private ArrayList<Field> fields = new ArrayList<Field>();

	private static ArrayList<Pawn> characters = new ArrayList<Pawn>();
	private PolarBear polarBear;
	private boolean determinism;



	public static void partFound() {
		progress++;
		System.out.println("Flaregun assembly: " + progress + "/3");
		
	}

	public static void end() { end = true;
		System.out.println("Game ended");
	}

	public static void checkConditions() {
		if(progress == 3 && characters.get(0).getField().getCharacters().size() == characters.size()) {
			win = true;
		} else
			progress = 0;
			
	}

	public static Game newGame(Scanner lineScanner) {
		Game game=new Game();
		game.setup(lineScanner);
		return game;
	}

	public void setup(Scanner lineScanner){
		boolean read=true;

		while(lineScanner.hasNextLine() && read){
			if(characters.size()<3) System.out.println("adj hozzá legalább 3 játékost");
			String[] words=lineScanner.nextLine().split(" ");
			if(words.length==2){
				if(words[0].equals("addEskimo")) characters.add(new Eskimo(words[1]));
				if(words[0].equals("addResearcher")) characters.add(new Eskimo(words[1]));
			}
			if(words[0].equals("finishSetup")) read=false;

		}

		init();
	}

	//fieldek generálása, karakterek elhelyezése
	private void init() {
		throw new UnsupportedOperationException("Not Implemented");
	}



	public Pawn getActivePawn() {
		throw new UnsupportedOperationException("Not Implemented");
	}

	//Adott mezőn a hóvihar
	public void blizzardAt(int i, int j) {
		throw new UnsupportedOperationException("Not Implemented");
	}

	public PolarBear getPolarBear() {
		return polarBear;
	}
	
	public static int getCharacterCount() {return characters.size();}

	public Field getField(String name) {
		for(Field f : fields) {
			if(f.getName() == name)
				return f;
		}
		return null;
	}

	@Override
	public String toString(){
		String res="";
		if(characters!=null){
			for(int i=0;i<characters.size()-1;++i){
				Pawn ch=characters.get(i);
				res+="Character "+i+"%n"+ch.toString()+"%n";
				res+="Inventory "+i+"%n"+ch.inventoryToString()+"%n";
			}
		}
		if(fields!=null){
			for (Field f:fields
			) {
				res+=f.toString()+"%n";
			}
		}
		if(polarBear!=null){
			res+=polarBear.toString()+"%n";

		}

		res+="determinism "+ (determinism ? "on": "off")+"%n";
		res+="End%n";
		return res;



	}

	public static Game parse(Scanner scanner){
		String[] words=null;
		Game game=new Game();
		boolean parse=true;
		while(scanner.hasNextLine() && parse){
			words=scanner.nextLine().split(" ");
			int number;
			switch (words[0]){
				case "Character":
					number=parseInt(words[1]);
					Pawn p=Researcher.parse(scanner);
					if(p==null) p=Eskimo.parse(scanner);
					if(p!=null) characters.add(number, p);
					break;
				case "Inventory":
					number=parseInt(words[1]);
					game.addItemsToCharacter(Item.parseItemList(scanner), number);
					break;
				case "Field":
					if(words.length==2){
						//game.fields.add(Field.parse(scanner, words[1]));
						String fieldName=words[1];
						Field f=null;
						if(scanner.hasNextLine()){
							words=scanner.nextLine().split(" ");
							if(words.length==2 && words[0].equals("capacity")){
								if(words[1].equals("0")) {f=new Hole(fieldName); break;}
								if(words[1].equals("-1")) {f=new IceField(fieldName); break;}
								f=new UnstableIceField(fieldName, parseInt(words[1])); break;
							}
						}
						if(f!=null) f.parse(scanner);
					}
					break;
				case "Bear":
					if(words.length==2){
						for (Field f: game.fields
							 ) {
							if(f.name.equals(words[1])){
								PolarBear pb=new PolarBear();
								pb.setField(f);
								f.polarBear=pb;
							}
						}
					}
					break;
				case "determinism":
					if(words.length==2){
						if(words[1].equals("on")) game.determinism=true;
						else game.determinism=false;
					}
					break;
				case "End":
					parse=false;
					break;
			}

		}
		game.placePawnsToFieldsFirstTime();
		game.setActivePawn();
		return game;

	}

	//a listában levő itemeket hozzáadja az adott indexű karakterhez
	private void addItemsToCharacter(List<Item> parseItemList, int number) {
		throw new UnsupportedOperationException("Not Implemented");
	}

	///Betöltéshez kell
	// végigmegy a karaktereken, és amelyik aktív, azt beállítja a Gameben aktívnak;
	private void setActivePawn() {
	}

	//konzolról való betöltéshez kell
	//minden field, minden pawn: ha pawn.starterFieldName == field.name rárakja fieldre, pawn fieldjét is beálítja
	private void placePawnsToFieldsFirstTime() {
	}

	public int getCharacterNumber(Pawn pawn) {
		return characters.indexOf(pawn);
	}
}
