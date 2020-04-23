
public class Shovel extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd == "Clear")
			return owner.getField().updateSnow(-1);
		return false;
	}

}
