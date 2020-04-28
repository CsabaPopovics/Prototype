
public class Shovel extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd.equals("Clear")){
			boolean success=owner.getField().updateSnow(-2);
			if(success) (owner.workUnit)--;
			return success;
		}

		return false;
	}

	@Override
	public String toString() {
		return "shovel";
	}
}
