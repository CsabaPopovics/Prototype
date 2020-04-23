
public class Scubasuit extends Item {

	@Override
	public boolean use(String cmd) {
		if(cmd == "PutOn")
			return true;
		return false;
	}

}
