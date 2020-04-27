import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {
	private static Game game;
	private static Scanner lineScanner;
	//private static boolean determinism;

	public static void main(String[] args) {
		processInput();
	}

	public static void processInput() {
		lineScanner=new Scanner(System.in);
		String line;
		String[] words;
		while(lineScanner.hasNextLine()){
			line=lineScanner.nextLine();
			words=line.split(" ");
			if(words[0]!=null){
				switch (words[0]){
					case "test":
						Tester tester=new Tester();
						if(words.length==1){

							tester.runAll();
							break;
						}
						tester.run(parseInt(words[1]));
						break;


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
					///////// tesztek
					case "": break;
					default:
						System.out.println("No recognized command");
						break;
				}
			}

		}
	}

	private static Game loadGame() {
		try {
			if(lineScanner!=null) return Game.parse(lineScanner);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	private static void bear(String[] words) {
		String error="Szintaxis: bear {up|down|right|left}";
		if(words.length==2 && game.determinism){
			PolarBear bear=game.getPolarBear();
			switch (words[1]){
				case "up":
					bear.step(Direction.Up);
					break;
				case "down":
					bear.step(Direction.Down);
					break;
				case "right":
					bear.step(Direction.Right);
					break;
				case "left":
					bear.step(Direction.Left);
					break;
				default:
					System.out.println(error);
					break;
			}
		}
		else System.out.println(error);
	}

	private static void storm(String[] words) {
		String error="Szintaxis: storm fieldNev mennyiseg";
		if(words.length==3 && game.determinism){
			try{
				game.blizzardAt(words[1], parseInt(words[2]));
			}catch (NumberFormatException e){
				System.out.println(error);
			}
		}
		else System.out.println(error);
	}

	private static void finish() {
		game.getActivePawn().finish();
	}

	private static void setupTent(String[] words) {
		if(words.length==2) game.getActivePawn().setupTent(parseInt(words[1]));
		else System.out.println("Szintaxis: setupTent index");
	}

	private static void buildIgloo() {
		game.getActivePawn().buildIgloo();
	}

	private static void inspect(String[] words) {
		String error="Szintaxis: {up|down|right|left}";
		if(words.length==2){
			Pawn activePawn=game.getActivePawn();
			switch (words[1]){
				case "up":
					activePawn.inspect(Direction.Up);
					break;
				case "down":
					activePawn.inspect(Direction.Down);
					break;
				case "right":
					activePawn.inspect(Direction.Right);
					break;
				case "left":
					activePawn.inspect(Direction.Left);
					break;
				default:
					System.out.println(error);
					break;
			}
		}
		else System.out.println(error);
	}

	private static void assembleGun() {
		game.getActivePawn().assembleGun();
	}

	private static void excavate() {
		game.getActivePawn().excavate();
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
					game.determinism=true;
					break;
				case "off":
					game.determinism=false;
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
		return Game.newGame(lineScanner);
	}

}
