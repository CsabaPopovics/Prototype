
public class FragileShovel extends Shovel{
	private int usageCount = 0;
	
	public FragileShovel() {}

	public FragileShovel(int usageCount) {
		this.usageCount=usageCount;
	}

	@Override
	public boolean use(String cmd) {
		if(cmd.equals("Clear")) {
			if(owner.getField().updateSnow(-2)) {
				usageCount++;
				(owner.workUnit)--;
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
