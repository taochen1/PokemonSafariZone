package model;

import java.io.Serializable;

/**
 * This class defines one type of item called potion: get number of this type of
 * item when trainer uses it or get it
 * 
 * @author Tao Chen
 *
 */
public class Potion extends Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public Potion(int numOfPotion) {
		super("potion", numOfPotion);

	}

	@Override
	public int getNum() {
		return numOfPotion;
	}

	@Override
	public String getIntro() {
		return "recover trainer's HP";
	}

	@Override
	public void use() {
		if (numOfPotion > 0) {
			numOfPotion -= 1;
		}

	}

	@Override
	public void get() {
		numOfPotion += 1;
	}

	public String getName() {
		return "Potion";
	}

}
