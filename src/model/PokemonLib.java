package model;

import java.awt.Image;

//Author: Haodong Wang
//this is a pokemon data libraryto store the pokemon name, pokemon image, pokemon sound name
public enum PokemonLib {
	uncommon1("Bulbasaur"), uncommon2("Squirtle"), uncommon3("Charmander"), uncommon4("Dratini"), uncommon5(
			"Pikachu"), uncommon6("Jigglypuff"), uncommon7("Farfetch-d"), uncommon8("Kangaskhan"), common1(
					"Pidgey"), common2("Weedle"), common3("Abra"), common4("Onix"), common5("Caterpie"), common6(
							"Ekans"), common7("Nidoran"), common8("Geodude"), common9("Bellsprout"), common10(
									"Psyduck"), common11("Oddish"), common12("Nidorina"), rare("Mewto"),special("Magikarp");
	private String name;
	private String fileName;

	// constracter for the enum class
	private PokemonLib(String name) {
		this.name = name;
		this.fileName = this.name() + ".mp3";
	}

	// use this class to get the pokemon name
	public String getName() {
		return name;
	}

	public String getFileName() {
		return fileName;
	}

	// use the class to get rare pokemon info by a random number input

	public static PokemonLib getRareByNum() {

		return rare;
	}
	
	public static PokemonLib getSpecial() {

		return special;
	}

	// use the class to get common pokemon info by a random number input

	public static PokemonLib getCommonByNum(int n) {
		if (n == 0) {
			return common1;
		} else if (n == 1) {
			return common2;
		} else if (n == 2) {
			return common3;
		} else if (n == 3) {
			return common4;
		} else if (n == 4) {
			return common5;
		} else if (n == 5) {
			return common6;
		} else if (n == 6) {
			return common7;
		} else if (n == 7) {
			return common8;
		} else if (n == 8) {
			return common9;
		} else if (n == 9) {
			return common10;
		} else if (n == 10) {
			return common11;
		} else {
			return common12;
		}
	}

	// use the class to get uncommon pokemon info by a random number input
	public static PokemonLib getUncommonByNum(int n) {
		if (n == 0) {
			return uncommon1;
		} else if (n == 1) {
			return uncommon2;
		} else if (n == 2) {
			return uncommon3;
		} else if (n == 3) {
			return uncommon4;
		} else if (n == 4) {
			return uncommon5;
		} else if (n == 5) {
			return uncommon6;
		} else if (n == 6) {
			return uncommon7;
		} else {
			return uncommon8;
		}
	}
}
