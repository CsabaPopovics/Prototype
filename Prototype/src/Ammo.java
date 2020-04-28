
public class Ammo extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd == "AddPart") {
			System.out.println(this.owner.name + " adds Ammo to the Flaregun.");
			Game.partFound();
			return true;
		}
		System.out.println("Invalid use of Ammo!");
		return false;
	}

	@Override
	public String toString() {
		return "ammo";
	}
}
