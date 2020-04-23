import java.util.ArrayList;

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

}
