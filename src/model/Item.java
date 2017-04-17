package model;

import java.io.Serializable;

/**
 * This abstract class defines three methods to get number of item trainer has,
 * use item and get item from map. This class will be extended by SafariBall,
 * Potion, FishingRod.
 * 
 * @author Tao Chen
 *
 */
public abstract class Item implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int numOfPotion;
	protected int numOfFishingRod;
	protected int numOfBall;

	public Item(String itemName, int num) {
		if (itemName.equals("ball")) {
			numOfBall = num;
		}
		if (itemName.equals("potion")) {
			numOfPotion = num;
		}
		if (itemName.equals("fishingRod")) {
			numOfFishingRod = num;
		}
	}

	public abstract int getNum();

	public abstract String getIntro();

	public abstract void use();

	public abstract void get();
	
	public abstract String getName();

}
