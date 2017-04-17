//Author: Haodong Wang
//this is a uncommon pokemon subclass for pokemon

package model;

public class Uncommon extends Pokemon {
	private static final long serialVersionUID = 1L;

	public Uncommon() {
		super(2);
	}

	//return the pokemon type
	@Override
	public String getType() {
		return "uncommon";
	}

	//return the pokemon hp
	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 80;
	}

	//return the origin runRate
	@Override
	public int getRunRate() {
		// TODO Auto-generated method stub
		return 50;
	}

	//return the origin catchRate
	@Override
	public int getCatchRate() {
		// TODO Auto-generated method stub
		return 40;
	}

	//return the damage
	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return 5;
	}

}
