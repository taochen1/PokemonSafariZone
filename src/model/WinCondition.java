package model;

import java.io.Serializable;

/**
 * This abstract class defines two methods to check if the trainer win or lose.
 * This class will be extended by Rule1,Rule2, Rule3 (or more Rules if needed).
 * 
 * checkWin method is abstract, so need to be implemented by subclass.
 * checkLost method defines the lost condition: The number of SafariBall is 0 or walked 500 steps.
 * 
 * @author Wang Tian
 *
 */

public abstract class WinCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	public abstract boolean checkWin(Trainer t);
	public abstract String getName();//return "rule1", "rule2" or "rule3"
	
	//lost if no safari ball remain or 0 step remain
	public boolean checkLost(Trainer t){
		int remainSteps = t.getRemainSteps();
		int remainBallNumber = t.getItemList().get(0).getNum();
		return remainSteps<1||remainBallNumber<1;	
	}
}
