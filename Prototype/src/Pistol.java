
public class Pistol extends Item {
	
	@Override
	public boolean use(String cmd) {
		if(cmd == "AddPart") {
			System.out.println(this.owner + " adds Pistol to the Flaregun.");
			Game.partFound();
			return true;
		}
		System.out.println("Invalid use of Pistol!");
		return false;
	}

	@Override
	public String toString() {
		return "pistol";
	}
}
