package model;
/**
 * This class can create a special pokemon. it can only be caught be using fishing rod.
 * 
 * @author Wang Tian
 *
 */
public class Special extends Pokemon {
	private static final long serialVersionUID = 1L;

	public Special() {
		super(4);
	}

	//return the pokemon type
	@Override
	public String getType() {
		return "special";
	}

	//return the pokemon hp
	@Override
	public int getHp() {
		return 30;
	}

	//return the origin runRate
	@Override
	public int getRunRate() {
		return 10;
	}

	//return the origin catchRate
	@Override
	public int getCatchRate() {
		return 80;
	}

	//return the damage
	@Override
	public int getDamage() {
		return 5;
	}

}
