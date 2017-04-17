package model;

/**
 * This class defines one type of item called safari ball: get number of this
 * type of item when trainer uses it or get it
 * 
 * @author Tao Chen
 *
 */
public class SafariBall extends Item {
	private static final long serialVersionUID = 1L;

	public SafariBall(int numOfBall) {
		super("ball", numOfBall);

	}

	@Override
	public int getNum() {
		return numOfBall;
	}

	@Override
	public String getIntro() {
		return "use for catching pokemon";
	}

	@Override
	public void use() {
		if (numOfBall > 0) {
			numOfBall -= 1;
		}
	}

	@Override
	public void get() {
		numOfBall += 1;
	}

	public String getName() {
		return "Safari Ball";
	}

}
