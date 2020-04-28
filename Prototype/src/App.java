import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * A konzolos megjelenítésért
 * és konzolon keresztüli inputért felel
 */
public class App {
	private static Game game;
	private static Scanner lineScanner;
	private static boolean exit=false;

	/**
	 * A program fő belépési pontja
	 * @param args argumentumok - jelenleg nincs kezelve ilyen
	 */
	public static void main(String[] args) {
		boolean exit=false;
		while(!exit){
			try {
				exit=processInput();
			}catch (Exception e){
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}


	}

	/**
	 * A std. inputon kapott parancsokat értelmezi,
	 * meghívja az ezekkel kapcsolatos metódusokat
	 * @return igazzal tér vissza, ha nincs több input(EOF-ot kaptunk),vagy exit parancsot adtunk ki
	 */
	public static boolean processInput() {
		lineScanner=new Scanner(System.in);
		String line;
		String[] words;
		while(lineScanner.hasNextLine() && !exit){
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

					case "exit":exit=true;break;
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
		return true;
	}

	/**
	 * betölti a standard inputról a szöveges játékkonfigurációt (mentett állapot)
	 * @return Ha sikerült létrehozni Game-et, ezt adja vissza, egyébként null-t
	 */
	private static Game loadGame() {
		try {
			if(lineScanner!=null) return Game.parse(lineScanner);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Adott irányba lépteti a medvét, ha van medve és ha a játék determinisztikusra van állítva
	 * @param words Stringtömb: második eleme egy a következők közül: up down right left
	 */
	private static void bear(String[] words) {
		String error="Szintaxis: bear {up|down|right|left}";
		if(words.length==2 && game.determinism){
			PolarBear bear=game.getPolarBear();
			if(bear !=null){
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

		}
		else System.out.println(error);
	}

	/**
	 * Vihart generál adott mezőkön adott hómennyiséggel
	 * @param words words[1]: célmező neve, words[2]: mennyi havat adjon a célmezőhöz
	 */
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

	/**
	 * Az aktív karakter lemond a cselekvési jogáról
	 */
	private static void finish() {
		game.getActivePawn().finish();
	}

	/**
	 * Sátort állít fel
	 * @param words words[1]: sátor item indexe (0val kezdődően) a karakter inventoryjában
	 */
	private static void setupTent(String[] words) {
		if(words.length==2) game.getActivePawn().setupTent(parseInt(words[1]));
		else System.out.println("Szintaxis: setupTent index");
	}

	/**
	 * Iglu építését kezdeményezi
	 */
	private static void buildIgloo() {
		game.getActivePawn().buildIgloo();
	}

	/**
	 * Aktív karakter megvizsgálja a mező megadott szomszédját teherbírás szempontjából
	 * @param words words[1]: {up|down|left|right}, melyik irányú szomszédot vizsgálja
	 */
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

	/**
	 * Aktív karakter megkísérli összeszerelni a fegyvert
	 */
	private static void assembleGun() {
		game.getActivePawn().assembleGun();
	}

	/**
	 * Aktív karakter kiássa a mezején elrejtett tárgyat
	 */
	private static void excavate() {
		game.getActivePawn().excavate();
	}

	/**
	 * Adott indexű itemmel hó eltakarítása
	 * @param words words[1]: ha "null", akkor hóeltakarítása kézzel, egyébként a használni kívánt item indexe
	 *              0-val kezdődően
	 */
	private static void clearSnow(String[] words) {
		String error="clearSnow index";
		if(words.length==2){
			Pawn pawn=game.getActivePawn();
			Item i;
			if(words[1].equals("null")) i=null;
			else{
				i=pawn.getItem(parseInt(words[1]));
			}

			pawn.clearSnow(i);
		}
		else System.out.println(error);
	}

	/**
	 * Adott indexű item megevése
	 * @param words words[1]: item indexe (0-val kezdve)
	 */
	private static void eat(String[] words) {
		String error="eat index";
		if(words.length==2){

				game.getActivePawn().eat(parseInt(words[1]));

		}
		else System.out.println(error);
	}

	/**
	 * Adott irányba lépés a mezőből
	 * @param words words[1]: valamenyik ezekből {up|down|left|right}
	 */
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

	/**
	 * Beállítja, hogy determinisztikus legyen-e a játék
	 * @param words words[1]: {on|off}
	 */
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

	/**
	 * Kiírja a játék állapotát a std outputra
	 */
	private static void saveGame() {
		String save=game.toString();
		System.out.print(save);
	}

	/**
	 * Meghívja az új játék indításához és beűllításához szükséges függvényt
	 * @param lineScanner A std inputot olvasó Scanner
	 * @return Létrehozott game
	 */
	private static Game newGame(Scanner lineScanner) {
		return Game.newGame(lineScanner);
	}

}
