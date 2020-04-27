
public class Flare extends Item {
	
	@Override
	public boolean use(String cmd) {
		if(cmd == "AddPart") {
			System.out.println(this.owner.name + " adds Flare to the Flaregun.");
			Game.partFound();
			return true;
		}
		System.out.println("Invalid use of Flare!");
		return false;
	}

	@Override
	public String toString() {
		return "flare";
	}
}
