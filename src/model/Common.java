//Author: Haodong Wang
//this is a common pokemon subclass for pokemon
package model;

public class Common extends Pokemon {
	private static final long serialVersionUID = 1L;
	public Common() {
		super(1);
	}
	//return the pokemon type
	@Override
	public String getType() {
		return "common";
	}
	//return the pokemon hp
	@Override
	public int getHp() {
		return 60;
	}
	//return the origin runRate
	@Override
	public int getRunRate() {
		return 20;
	}
	//return the origin catchRate
	@Override
	public int getCatchRate() {
		return 60;
	}
	//return the damage
	@Override
	public int getDamage() {
		return 2;
	}
}
