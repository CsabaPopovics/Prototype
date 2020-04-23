
public class Food extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd == "Eat") {
			owner.updateBodyTemp(1);
			owner.removeFromInventory(this);
			return true;
		}
		return false;
	}

}
