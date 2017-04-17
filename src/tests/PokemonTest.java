package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Common;
import model.FormPokemon;
import model.Pokemon;
import model.Rare;
import model.Special;
import model.Uncommon;

public class PokemonTest {
	@Test
	public void testPokemon() {
		FormPokemon occure = new FormPokemon();
		Pokemon pkm1 = new Rare();
		Pokemon pkm2 = new Common();
		Pokemon pkm3 = new Uncommon();
		Pokemon pkm4 = new Special();

		assertEquals(20, pkm1.getCatchRate());
		assertEquals(10, pkm1.getDamage());
		assertEquals(100, pkm1.getHp());
		assertEquals(60, pkm1.getRunRate());
		assertEquals("rare", pkm1.getType());
		assertEquals(5, pkm3.getDamage());
		assertEquals(2, pkm2.getDamage());
		assertEquals(5, pkm4.getDamage());
		assertEquals("special",pkm4.getType());
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		System.out.println(pkm1.getPokemonLib().getName() + ":" + "HP=" + pkm1.getHp() + "; " + "catchRate="
				+ pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("bait");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + "HP=" + pkm1.getHp() + "; " + "catchRate="
				+ pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("bait");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + "HP=" + pkm1.getHp() + "; " + "catchRate="
				+ pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("bait");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + "HP=" + pkm1.getHp() + "; " + "catchRate="
				+ pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("bait");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + "HP=" + pkm1.getHp() + "; " + "catchRate="
				+ pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");

		pkm1.getMessage("unknow input");

		pkm1.getPokemonLib().getFileName();
		pkm1.getMessage("ball");
		pkm1.getMessage("ball");
		pkm1.getMessage("ball");
		pkm1.getMessage("ball");

		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + "HP=" + pkm1.getHp() + "; "
				+ "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate=" + pkm1.getCurrentRunRate() + ";");
		pkm1.getMessage("bait");
		pkm1.getMessage("rock");
		pkm1.getMessage("ball");
		System.out.println(pkm1.getPokemonLib().getName() + ":" + pkm1.getType() + "; " + pkm1.getCurrentHp() + "; "
				+ "HP=" + pkm1.getHp() + "; " + "catchRate=" + pkm1.getCurrentCatchRate() + "; " + "runRate="
				+ pkm1.getCurrentRunRate() + ";");

		System.out.println(pkm2.getPokemonLib().getName() + ":" + pkm2.getType() + "; " + "HP=" + pkm2.getHp() + "; "
				+ "catchRate=" + pkm2.getCurrentCatchRate() + "; " + "runRate=" + pkm2.getCurrentRunRate() + ";");
		System.out.println(pkm3.getPokemonLib().getName() + ":" + pkm3.getType() + "; " + "HP=" + pkm3.getHp() + "; "
				+ "catchRate=" + pkm3.getCurrentCatchRate() + "; " + "runRate=" + pkm3.getCurrentRunRate() + ";");

		// to random many pokemon that every possibility is reached
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		occure.newPokemon();
		System.out.println(
				occure.getCurrentPokemon().getPokemonLib().getName() + " " + occure.getCurrentPokemon().getType());
		Pokemon pkm = new Rare();
		pkm.usePotion(60);
		pkm.getMaxHp();
	}
}
