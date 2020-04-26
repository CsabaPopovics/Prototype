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

	public static void end() { end = true;}

	public static void checkConditions() {
		if(progress == 3 && characters.get(0).getField().getCharacters().size() == characters.size()) {
			win = true;
		} else
			progress = 0;
			
	}

	public void setup(Scanner lineScanner){
		Scanner intScanner=lineScanner;
		int numberOfPlayers=0;
		int numberOfEskimos=0;
		int numberOfResearchers=0;
		boolean equals=false;
		while(numberOfPlayers<3 || !equals){
			System.out.println("Játékosok száma");
			numberOfPlayers=intScanner.nextInt();
			System.out.println("Eszkimók száma");
			numberOfEskimos=intScanner.nextInt();
			System.out.println("Kutatók száma");
			numberOfResearchers=intScanner.nextInt();
			equals=numberOfEskimos+numberOfResearchers==numberOfPlayers ||
					numberOfEskimos<0 ||
					numberOfPlayers<3 ||
					numberOfResearchers<0;
			if(!equals) System.out.println("összeghiba");
		}
		init();
	}

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
		while(scanner.hasNextLine()){
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
								if(words[1].equals("0")) f=new Hole(fieldName); break;
								if(words[1].equals("-1")) f=new IceField(fieldName); break;
								f=new UnstableIceField(fieldName, parseInt(words[1])); break;
							}
						}
						if(f!=null) f.parse(scanner);
					}

			}

		}
		game.placePawnsToFieldsFirstTime();
		game.setActivePawn();

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
}
