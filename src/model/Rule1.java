package model;


/**
 * This class defines one type of winning condition: total number of caught
 * Pokemons is bigger than 20 (including duplicates).
 * 
 * @author Wang Tian
 *
 */
public class Rule1 extends WinCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean checkWin(Trainer t) {
		return t.getPokemonList().size() > 19;//return t.getPokemonList().size() > 1; 
                                              //just for simple testing
	}

	@Override
	public String getName() {
		return "rule1";
	}
}
