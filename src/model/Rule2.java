package model;

import java.util.ArrayList;

/**
 * This class defines one type of winning condition:  catch a Magikarp and all pokemons in the bag have full HP
 *(only one Magikarp with full HP will win ) 
 *
 * @author Tao Chen
 *
 */
public class Rule2 extends WinCondition {
	private static final long serialVersionUID = 1L;
	private ArrayList<Pokemon> currentList = new ArrayList<>();
	private ArrayList<Pokemon> commonList = new ArrayList<>();
	private ArrayList<Pokemon> uncommonList = new ArrayList<>();
	private ArrayList<Pokemon> rareList = new ArrayList<>();
	private ArrayList<Pokemon> specialList = new ArrayList<>();
	
	@Override
	public boolean checkWin(Trainer t) {
		currentList = t.getPokemonList();
		boolean getMagikarp = false;
		for (int a = 0; a < currentList.size(); a++) {
			if (currentList.get(a).getType().equals("special")) {
				getMagikarp = true;
			}
		}
		if (!getMagikarp) {
			return false;
		}
		divideToThreeLists();
		for (int i = 0; i < commonList.size(); i++) {
			if (commonList.get(i).getHp() != 60) {
				return false;
			}
		}

		for (int j = 0; j < uncommonList.size(); j++) {
			if (uncommonList.get(j).getHp() != 80) {
				return false;
			}
		}

		for (int k = 0; k < rareList.size(); k++) {
			if (rareList.get(k).getHp() != 100) {
				return false;
			}
		}
		return true;
	}

	private void divideToThreeLists() {
		for (Pokemon p : currentList) {
			if (p.getType().equals("common"))
				commonList.add(p);
			else if (p.getType().equals("uncommon"))
				uncommonList.add(p);
			else if (p.getType().equals("special"))
				specialList.add(p);
			else
				rareList.add(p);
		}

	}

	@Override
	public String getName() {
		return "rule2";
	}

}
