
public class Tent extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd == "Setup") {
			return owner.getField().setupTent();
		}
		return false;
	}

}
