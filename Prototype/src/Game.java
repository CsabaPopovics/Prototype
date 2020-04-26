import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private static int progress = 0;
	private static boolean end = false;
	private static boolean win = false;
	
	private ArrayList<Field> fields = new ArrayList<Field>();

	private static ArrayList<Pawn> characters = new ArrayList<Pawn>();
	private PolarBear polarBear;

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

	@Override
	public String toString() {
		throw new UnsupportedOperationException("Not Implemented");
		return super.toString();
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
}
