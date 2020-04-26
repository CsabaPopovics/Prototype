
public class FragileShovel extends Shovel{
	private int usageCount = 0;

	public FragileShovel(int usageCount) {
		this.usageCount=usageCount;
	}

	@Override
	public boolean use(String cmd) {
		if(cmd == "Clear") {
			if(owner.getField().updateSnow(-1)) {
				usageCount++;
				if(usageCount == 3)
					owner.removeFromInventory(this);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "fragileShovel "+usageCount;
	}
}
