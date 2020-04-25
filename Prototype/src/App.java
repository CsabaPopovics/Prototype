import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {
	private static Game game;
	private static Scanner lineScanner;
	private static boolean determinism;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ammo ammo = new Ammo();
		ammo.use("AddPart");
		Pistol pistol = new Pistol();
		pistol.use("AddPart");
		Flare flare = new Flare();
		flare.use("AddPart");

		processInput();

	}

	private static void processInput() {
		lineScanner=new Scanner(System.in);
		String line;
		String[] words;
		while(lineScanner.hasNextLine()){
			line=lineScanner.nextLine();
			words=line.split(" ");
			if(words[0]!=null){
				switch (words[0]){
					case "newGame":
						game=newGame(lineScanner);
						break;
					case "loadGame":
						game=loadGame();
						break;
					case "saveGame":
						saveGame();
						break;
					case "determinism":
						setDeterminism(words);
						break;
					case "step":
						step(words);
						break;
					case "eat":
						eat(words);
						break;
					case "clearSnow":
						clearSnow(words);
						break;
					case "excavate":
						excavate();
						break;
					case "assembleGun":
						assembleGun();
						break;
					case "inspect":
						inspect(words);
						break;
					case "buildIgloo":
						buildIgloo();
						break;
					case "setupTent":
						setupTent(words);
						break;
					case "finish":
						finish();
						break;
					case "storm":
						storm(words);
						break;
					case "bear":
						bear(words);
						break;
					default:
						System.out.println("No recognized command");
						break;
				}
			}

		}
	}

	private static void clearSnow(String[] words) {
		String error="clearSnow index";
		if(words.length==2){
			Pawn pawn=game.getActivePawn();
			Item i=pawn.getItem(parseInt(words[1]));
			pawn.clearSnow(i);
		}
		else System.out.println(error);
	}

	private static void eat(String[] words) {
		String error="eat index";
		if(words.length==2){

				game.getActivePawn().eat(parseInt(words[1]));

		}
		else System.out.println(error);
	}

	private static void step(String[] words) {
		String error="Szintaxis: {up|down|right|left}";
		if(words.length==2){
			Pawn activePawn=game.getActivePawn();
			switch (words[1]){
				case "up":
					activePawn.step(Direction.Up);
					break;
				case "down":
					activePawn.step(Direction.Down);
					break;
				case "right":
					activePawn.step(Direction.Right);
					break;
				case "left":
					activePawn.step(Direction.Left);
					break;
				default:
					System.out.println(error);
					break;


			}
		}
		else System.out.println(error);
	}

	private static void setDeterminism(String[] words) {
		String error="Szintaxis: determinism {on|off}";
		if(words.length==2){
			switch (words[1]){
				case "on":
					determinism=true;
					break;
				case "off":
					determinism=false;
					break;
				default:
					System.out.println(error);
					break;
			}
		}
		else System.out.println(error);

	}

	private static void saveGame() {
		String save=game.toString();
		System.out.print(save);
	}

	private static Game newGame(Scanner lineScanner) {
		Game newGame=new Game();
		newGame.setup(lineScanner);
		return newGame;
	}

}
