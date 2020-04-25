import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	private static int progress = 0;
	private static boolean end = false;
	private static boolean win = false;
	
	private static ArrayList<Pawn> characters = new ArrayList<Pawn>();

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



}
