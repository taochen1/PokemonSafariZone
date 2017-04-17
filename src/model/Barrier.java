package model;

/***
 * This is the enum class of map's elemens. Every elements contains the
 * information that coulld pass or cold generate pokemons
 * 
 * @author Renfei Sun
 *
 */
public enum Barrier {
	None(true, -1), Grass(true, 15), Portal(true, -1), River(false, 70), Barrier(false, -1);

	private boolean isPassable;
	private int possibility;

	private Barrier(boolean isPassable, int possibility) {
		this.isPassable = isPassable;
		this.possibility = possibility;
	}

	//tell if could pass through this tile
	public boolean isPassable() {
		return isPassable;
	}

	//tell if could generate a new pokemon
	public int canGeneratePokemon() {
		return possibility;
	}
}
