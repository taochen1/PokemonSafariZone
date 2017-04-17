package model;

import java.util.ArrayList;

/**
 * This class defines one of the winning conditions. The player will win if he
 * caught totally at least 7 common, 5 uncommon and 1 rare pokemons. Note:
 * duplicate pokemons does not count. For example, if player caught 2 exactly
 * same pokemons, only one counts.
 * 
 * If the player caught 12 common, 8 uncommon and 1 rare pokemons(including
 * duplicates), the possibility of winning is about 70%(by Junit test for 1000
 * running).
 * 
 * @author Wang Tian
 *
 */

public class Rule3 extends WinCondition {
	private static final long serialVersionUID = 1L;
	private ArrayList<Pokemon> currentList = new ArrayList<>();
	private ArrayList<Pokemon> commonList = new ArrayList<>();
	private ArrayList<Pokemon> uncommonList = new ArrayList<>();
	private ArrayList<Pokemon> rareList = new ArrayList<>();

	@Override
	public boolean checkWin(Trainer t) {
		currentList = t.getPokemonList();
		divideToThreeLists();
		int numberOfCommon = commonList.size();
		int numberOfUncommon = uncommonList.size();
		int numberOfRare = rareList.size();
		return numberOfCommon > 6 && numberOfUncommon > 4 && numberOfRare > 0;
	}

	private void divideToThreeLists() {
		for (Pokemon p : currentList) {
			if (p.getType().equals("common"))
				commonList.add(p);
			else if (p.getType().equals("uncommon"))
				uncommonList.add(p);
			else
				rareList.add(p);
		}

		removeDuplicate(commonList);
		removeDuplicate(uncommonList);
		removeDuplicate(rareList);
	}

	private void removeDuplicate(ArrayList<Pokemon> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j).getPokemonLib().getName().equals(list.get(i).getPokemonLib().getName())) {
					list.remove(j);
					j--;
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return "rule3";
	}

}
