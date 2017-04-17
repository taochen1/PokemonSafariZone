package model;

/**
 * This class defines one type of item called fishing rod: get number of this
 * type of item when trainer uses it or get it
 * 
 * @author Tao Chen
 *
 */
public class FishingRod extends Item {

	private static final long serialVersionUID = 1L;

	public FishingRod(int numOfFishingRod) {
		super("fishingRod", numOfFishingRod);
	}

	@Override
	public int getNum() {
		return numOfFishingRod;
	}

	@Override
	public String getIntro() {
		return "can fish pokmon near the river";
	}

	@Override
	public void use() {
		if (numOfFishingRod > 0) {
			numOfFishingRod -= 1;
		}

	}

	@Override
	public void get() {
		numOfFishingRod += 1;
	}

	public String getName() {
		return "Fishing Rod";
	}

}
