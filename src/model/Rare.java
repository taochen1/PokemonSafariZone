//Author: Haodong Wang
//this is a rate pokemon subclass for pokemon

package model;

public class Rare extends Pokemon {
	private static final long serialVersionUID = 1L;

	public Rare() {
		super(3);
	}

	//return the pokemon type
	@Override
	public String getType() {
		return "rare";
	}

	//return the pokemon hp
	@Override
	public int getHp() {
		return 100;
	}

	//return the origin runRate
	@Override
	public int getRunRate() {
		return 60;
	}

	//return the origin catchRate
	@Override
	public int getCatchRate() {
		return 20;
	}

	//return the damage
	@Override
	public int getDamage() {
		return 10;
	}
}
