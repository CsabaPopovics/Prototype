import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Game {
	private static int progress = 0;
	private static boolean end = false;
	private static boolean win = false;
	
	private Field fieldArray[][];
	private ArrayList<Field> fields = new ArrayList<Field>();

	private ArrayList<Pawn> characters = new ArrayList<Pawn>();
	private PolarBear polarBear;
	private Pawn activeCharacter=null;
	public boolean determinism;
	
	public void convert2DArrayToArrayList() {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++)
				if((fieldArray[i][j])!=null)fields.add(fieldArray[i][j]);
		}
	}
	
	public void generateFields() {
		fieldArray = new Field[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i == 0 && j == 0) {
					fieldArray[i][j] = new IceField("Field" + (i+1) + (j+1));
				}
				Random random = new Random();
				double typeChance = random.nextDouble();
				if(typeChance <= 0.2) {
					fieldArray[i][j] = new Hole("Field" + (i+1) + (j+1));
				} else if(typeChance > 0.2 && typeChance <= 0.7) {
					fieldArray[i][j] = new IceField("Field" + (i+1) + (j+1));
				} else {
					Random limit = new Random();
					fieldArray[i][j] = new UnstableIceField("Field" + (i+1) + (j+1), limit.nextInt(characters.size()));
				}
				
			}
		}
	}
	
	public void setNeighbours() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(fieldArray[i][j]!=null){
					fieldArray[i][j].setNeighbour(fieldArray[i][j+1], Direction.Right);
					fieldArray[i][j].setNeighbour(fieldArray[i+1][j], Direction.Down);
				}

			}
		}
		for(int j=0;j<9;++j){
			if(fieldArray[9][j]!=null){
				fieldArray[9][j].setNeighbour(fieldArray[9][j+1], Direction.Right);
			}
		}
		for(int i=0;i<9;++i){
			if(fieldArray[i][9]!=null){
				fieldArray[i][9].setNeighbour(fieldArray[i+1][9], Direction.Down);
			}
		}
		
		for(int i = 9; i > 0; i--) {
			for(int j = 9; j > 0; j--) {
				if(fieldArray[i][j]!=null){
					fieldArray[i][j].setNeighbour(fieldArray[i][j-1], Direction.Left);
					fieldArray[i][j].setNeighbour(fieldArray[i-1][j], Direction.Up);
				}

			}
		}
		for(int j=9;j<0;--j){
			if(fieldArray[9][j]!=null){
				fieldArray[9][j].setNeighbour(fieldArray[9][j-1], Direction.Left);
			}
		}

		for(int i=9;i<0;--i){
			if(fieldArray[i][9]!=null){
				fieldArray[i][9].setNeighbour(fieldArray[i-1][9], Direction.Up);
			}
		}
	}
	
	public void insertField(Field f, int i, int j) {
		if(i >= 0 && i < 10 && j >= 0 && j < 10) {
			f.copyNeighbours(fieldArray[i][j]);
			fields.remove(fieldArray[i][j]);
			fields.add(f);
			
		}
	}
	
	public void generateItems() {
		Ammo ammo = new Ammo();
		placeItem(ammo);
		Pistol pistol = new Pistol();
		placeItem(pistol);
		Flare flare = new Flare();
		placeItem(flare);
		Random random = new Random();
		int itemCount = new Random().nextInt(20);
		double itemType;
		
		while(itemCount>0) {
			itemType = random.nextDouble();
			if(itemType <= 0.15) {
				placeItem(new Food());
			} else if(itemType > 0.15 && itemType <= 0.3) {
				placeItem(new FragileShovel());
			} else if(itemType > 0.3 && itemType <= 0.45) {
				placeItem(new Rope());
			} else if(itemType > 0.45 && itemType <= 0.6) {
				placeItem(new Scubasuit());
			} else if(itemType > 0.6 && itemType <= 0.75) {
				placeItem(new Shovel());
			} else
				placeItem(new Tent());
			--itemCount;
			
		}
		
	}
	
	public void placeItem(Item item) {
		boolean placementSuccess = false;
		while(!placementSuccess) {
			int i = new Random().nextInt(10);
			int j = new Random().nextInt(10);
			if(fieldArray[i][j].setItem(item))
				placementSuccess = true;
		}
		
	}

	public static void partFound() {
		progress++;

	}

	public static void end() { end = true;
		System.out.println("Game ended");
	}

	public void checkConditions() {
		if(progress == 3 && characters.get(0).getField().getCharacters().size() == characters.size()) {
			win = true;
			System.out.println("Flaregun Successfully Used");
			end();
		} else
			progress = 0;
			if(!win)System.out.println("Flaregun Assembly Failed");
			
	}

	public static Game newGame(Scanner lineScanner) {
		Game game=new Game();
		game.setup(lineScanner);
		return game;
	}

	public void setup(Scanner lineScanner){
		boolean read=true;
		boolean exit=false;
		System.out.println("adj hozzá legalább 3 játékost");
		while(lineScanner.hasNextLine() && read){

			String[] words=lineScanner.nextLine().split(" ");
			if(words.length==2){
				if(words[0].equals("addEskimo")) characters.add(new Eskimo(words[1]));
				if(words[0].equals("addResearcher")) characters.add(new Eskimo(words[1]));
			}
			if(words[0].equals("finishSetup")) {
				if(characters.size()>=3){
					read=false;
				}
				else{
					System.out.println("adj hozzá legalább 3 játékost");
				}

			}
			if(words[0].equals("exit")) {
				read=false;
				exit=true;
			}

		}

		if(!exit)init();
	}

	//fieldek generálása, karakterek elhelyezése
	private void init() {
		generateFields();
		setNeighbours();
		generateItems();
		convert2DArrayToArrayList();
	}



	public Pawn getActivePawn() {
		return activeCharacter;
	}

	//Adott mezőn a hóvihar, amount a tesztek miatt kell
	public void blizzardAt(String fieldName, int amount) {
		for (Field f: fields) {

			if(f.name.equals(fieldName)){
				if(determinism){
					System.out.println("Storm affected Field "+f.name);
					f.updateSnow(amount);
				}
				else{
					Random r=new Random();
					System.out.println("Storm affected Field "+f.name);
					f.updateSnow(r.nextInt(4)+1);
				}
			}
		}
	}

	public PolarBear getPolarBear() {
		return polarBear;
	}
	
	public int getCharacterCount() {return characters.size();}

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
			for(int i=0;i<characters.size();++i){
				Pawn ch=characters.get(i);
				res+="Character "+ch.name +String.format("%n")+ch.toString()+String.format("%n");
				res+="Inventory "+ch.name +String.format("%n")+ch.inventoryToString()+String.format("%n");
			}
		}
		if(fields!=null){
			for (Field f:fields
			) {
				res+=f.toString()+String.format("%n");
			}
		}
		if(polarBear!=null){
			res+=polarBear.toString()+String.format("%n");

		}

		res+="determinism "+ (determinism ? "on": "off")+String.format("%n");
		res+=String.format("End%n");
		return res;



	}

	public static Game parse(Scanner scanner) throws Exception {
		String[] words=null;
		Game game=new Game();
		List<Field> myCustomFields=new ArrayList<Field>();
		PolarBear myBear;
		boolean parse=true;
		while(scanner.hasNextLine() && parse){
			words=scanner.nextLine().split(" ");
			int number;
			switch (words[0]){
				case "Character":
					String name=(words[1]);
					words=scanner.nextLine().split(" ");
					Pawn p=null;
					if(words.length==2){
						if(words[1].equals("researcher")){
							p=Researcher.parse(scanner, name);
						}
						else{
							p=Eskimo.parse(scanner, name);
						}
					}


					if(p!=null){
						p.game=game;
						game.characters.add(p);
					}
					break;
				case "Inventory":
					String characterName=(words[1]);
					game.addItemsToCharacter(Item.parseItemList(scanner), characterName);
					break;
				case "Field":
					if(words.length==2){
						//game.fields.add(Field.parse(scanner, words[1]));
						String fieldName=words[1];
						Field f=null;
						if(scanner.hasNextLine()){
							words=scanner.nextLine().split(" ");
							if(words.length==2 && words[0].equals("capacity")){
								{
									if (words[1].equals("0")) {
										f = new Hole(fieldName);
										f.parse(scanner);
										myCustomFields.add(f);
										break;
									}
									if (words[1].equals("-1")) {
										f = new IceField(fieldName);
										f.parse(scanner);
										myCustomFields.add(f);
										break;
									}

								}
								f=new UnstableIceField(fieldName, parseInt(words[1]));
								myCustomFields.add(f);
								break;
							}
						}
						//if(f!=null) f.parse(scanner);
					}
					break;
				case "Bear":
					if(words.length==2){
						myBear=new PolarBear();
						for (Field f: myCustomFields){
							if(words[1].equals(f.name)){
								myBear.setField(f);
								f.polarBear=myBear;
								game.polarBear=myBear;
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
		if(myCustomFields.size()==0) throw new Exception("Nincs egy field se");
		game.setupCustomFields(myCustomFields);
		game.placePawnsToFieldsFirstTime();
		game.setActivePawn();
		return game;

	}

	private void setupCustomFields(List<Field> myCustomFields) {
		Field[][] myFieldArray=new Field[10][10];
		for(Field f: myCustomFields){
			String[] words=f.name.split("_");
			if(words.length==3){
				int i=parseInt(words[1]);
				int j=parseInt(words[2]);
				if(0<=i && i<10 && 0<=j && j<10){
					myFieldArray[i][j]=f;
				}
			}
		}
		fieldArray=myFieldArray;
		convert2DArrayToArrayList();
		setNeighbours();
	}

	//a listában levő itemeket hozzáadja az adott indexű karakterhez
	private void addItemsToCharacter(List<Item> parseItemList, String characterName) {
		Pawn character=getCharacterByName(characterName);
		for(Item i:parseItemList){
			character.addToInventory(i);
		}
	}

	private Pawn getCharacterByName(String characterName) {
		for(Pawn p: characters){
			if(p.name.equals(characterName))
				return p;
		}
		return null;
	}

	///Betöltéshez kell
	// végigmegy a karaktereken, és amelyik aktív, azt beállítja a Gameben aktívnak;
	private void setActivePawn() {
		for(Pawn p:characters){
			if(p.starterIsActive){
				activeCharacter=p;
				break;
			}
		}
	}

	//konzolról való betöltéshez kell
	//minden field, minden pawn: ha pawn.starterFieldName == field.name rárakja fieldre, pawn fieldjét is beálítja
	private void placePawnsToFieldsFirstTime() {
		for(Field f:fields){
			for(Pawn p:characters){
				if(f.name.equals(p.starterFieldName)){
					f.accept(p);
				}
			}
		}
	}

	public int getCharacterNumber(Pawn pawn) {
		return characters.indexOf(pawn);
	}

	//pawn aki lemondott róla (finishelt)
	public void setActiveCharacter(Pawn pawn) {
		int newindex=characters.indexOf(pawn)+1;
		newindex=newindex%characters.size();
		activeCharacter=characters.get(newindex);
		activeCharacter.finished=false;
		activeCharacter.setAsActive();
		if(newindex==0) nextTurn();


	}

	private void nextTurn() {
		for(Field f:fields){
			f.resetTent();
			blizzardAt(f, 1);

		}
		polarBearRandom();
	}

	private void polarBearRandom() {
		if(!determinism){
			Random random=new Random();
			switch (random.nextInt(4)){
				case 0: polarBear.step(Direction.Right);break;
				case 1: polarBear.step((Direction.Left));break;
				case 2: polarBear.step(Direction.Down);break;
				case 3: polarBear.step(Direction.Up);break;
			}
		}
	}

	private void blizzardAt(Field f, int amount) {
		if(!determinism){
			Random r=new Random();
			if(r.nextInt(10)==0){
				System.out.println("Storm affected Field "+f.name);
				f.updateSnow(r.nextInt(3)+1);
			}
		}
		System.out.println("Storm affected Field "+f.name);
		else f.updateSnow(amount);


	}
}
