import java.util.Scanner;

public class App {
	private static Game game;

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
		Scanner lineScanner=new Scanner(System.in);
		String line;
		String[] words;
		while(lineScanner.hasNextLine()){
			line=lineScanner.nextLine();
			words=line.split(" ");
			if(words[0]!=null){
				switch (words[0]){
					case "newGame":
						game=newGame();
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

	private static Game newGame() {
		Game newGame=new Game();
		newGame.setup();
		return newGame;
	}

}
