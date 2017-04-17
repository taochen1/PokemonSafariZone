package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.Common;
import model.Pokemon;
import model.Rare;
import model.Rule1;
import model.Rule2;
import model.Rule3;
import model.Special;
import model.Trainer;
import model.Uncommon;
import model.WinCondition;

/**
 * A JUnit test for different winning conditions such as Rule1, Rule2 and Rule3.
 * 
 * @author Wang Tian
 *
 */
public class WinConditionsTest {

	@Test
	public void testBasic() {
		WinCondition c1 = new Rule1();
		WinCondition c2 = new Rule2();
		Trainer trainer = new Trainer();
		assertFalse(c1.checkWin(trainer));
		assertFalse(c1.checkLost(trainer));
		assertFalse(c2.checkWin(trainer));
		assertFalse(c2.checkLost(trainer));
	}

	@Test
	public void testRule1() {
		WinCondition c1 = new Rule1();
		Trainer trainer = new Trainer();
		Pokemon common1 = new Common();
		Pokemon common2 = new Common();
		Pokemon common3 = new Common();
		Pokemon common4 = new Common();
		Pokemon common5 = new Common();
		Pokemon common6 = new Common();
		Pokemon uncomon1 = new Uncommon();
		Pokemon uncomon2 = new Uncommon();
		Pokemon uncomon3 = new Uncommon();
		Pokemon uncomon4 = new Uncommon();
		Pokemon rare = new Rare();
		trainer.addPokemon(common1);
		assertFalse(c1.checkWin(trainer));
		trainer.addPokemon(common2);
		trainer.addPokemon(common4);
		trainer.addPokemon(common5);
		trainer.addPokemon(common6);
		trainer.addPokemon(uncomon1);
		trainer.addPokemon(uncomon2);
		trainer.addPokemon(uncomon3);
		trainer.addPokemon(uncomon4);
		trainer.addPokemon(rare);
		for (int i = 0; i < 23; i++)
			trainer.addPokemon(uncomon2);
		assertTrue(c1.checkWin(trainer));
		trainer.addPokemon(common3);
		assertTrue(c1.checkWin(trainer));
		assertEquals("rule1", c1.getName());
	}

	@Test
	public void testRule2_1() {
		WinCondition c2 = new Rule2();
		Trainer trainer = new Trainer();
		assertFalse(c2.checkWin(trainer));
		assertFalse(c2.checkLost(trainer));
		Pokemon pokemon1 = new Special();
		Pokemon pokemon2 = new Common();
		trainer.getPokemonList().add(pokemon1);
		assertTrue(c2.checkWin(trainer));
		trainer.getPokemonList().add(pokemon2);
		assertTrue(c2.checkWin(trainer));
		assertEquals("rule2", c2.getName());
	}
	@Test
	public void testRule2_2() {
		WinCondition c2 = new Rule2();
		Trainer trainer = new Trainer();
		assertFalse(c2.checkWin(trainer));
		assertFalse(c2.checkLost(trainer));
		Pokemon pokemon1 = new Special();
		Pokemon pokemon2 = new Uncommon();
		trainer.getPokemonList().add(pokemon1);
		trainer.getPokemonList().add(pokemon2);
		assertTrue(c2.checkWin(trainer));
		assertEquals("rule2", c2.getName());
	}
	@Test
	public void testRule2_3() {
		WinCondition c2 = new Rule2();
		Trainer trainer = new Trainer();
		assertFalse(c2.checkWin(trainer));
		assertFalse(c2.checkLost(trainer));
		Pokemon pokemon1 = new Special();
		Pokemon pokemon2 = new Rare();
		trainer.getPokemonList().add(pokemon1);
		trainer.getPokemonList().add(pokemon2);
		assertTrue(c2.checkWin(trainer));
		assertEquals("rule2", c2.getName());
	}

	@Test
	public void testRule3Win() {
		int win = 0;
		int nowin = 0;
		for (int i = 0; i < 1000; i++) {
			WinCondition c3 = new Rule3();
			Trainer trainer = new Trainer();
			Pokemon com1 = new Common();
			Pokemon com2 = new Common();
			Pokemon com3 = new Common();
			Pokemon com4 = new Common();
			Pokemon com5 = new Common();
			Pokemon com6 = new Common();
			Pokemon com7 = new Common();
			Pokemon com8 = new Common();
			Pokemon com9 = new Common();
			Pokemon com10 = new Common();
			Pokemon com11 = new Common();
			Pokemon com12 = new Common();

			Pokemon uncom1 = new Uncommon();
			Pokemon uncom2 = new Uncommon();
			Pokemon uncom3 = new Uncommon();
			Pokemon uncom4 = new Uncommon();
			Pokemon uncom5 = new Uncommon();
			Pokemon uncom6 = new Uncommon();
			Pokemon uncom7 = new Uncommon();
			Pokemon uncom8 = new Uncommon();

			Pokemon rare = new Rare();
			trainer.addPokemon(com1);
			trainer.addPokemon(com2);
			trainer.addPokemon(com3);
			trainer.addPokemon(com4);
			trainer.addPokemon(com5);
			trainer.addPokemon(com6);
			trainer.addPokemon(com7);
			trainer.addPokemon(com8);
			trainer.addPokemon(com9);
			trainer.addPokemon(com10);
			trainer.addPokemon(com11);
			trainer.addPokemon(com12);
			trainer.addPokemon(uncom1);
			trainer.addPokemon(uncom2);
			trainer.addPokemon(uncom3);
			trainer.addPokemon(uncom4);
			trainer.addPokemon(uncom5);
			trainer.addPokemon(uncom6);
			trainer.addPokemon(uncom7);
			trainer.addPokemon(uncom8);
			trainer.addPokemon(rare);

			if (c3.checkWin(trainer) == true)
				win++;
			else
				nowin++;
		}

		System.out.println("number of win: " + win);
		System.out.println("number of nowin: " + nowin);
	}

	@Test
	public void testLost1() {
		WinCondition c1 = new Rule1();
		WinCondition c2 = new Rule2();
		WinCondition c3 = new Rule3();
		Trainer trainer = new Trainer();
		assertFalse(c1.checkLost(trainer));
		assertFalse(c2.checkLost(trainer));
		assertFalse(c3.checkLost(trainer));
		assertEquals("rule3", c3.getName());
		for (int i = 0; i < 500; i++)
			trainer.subtractSteps();
		assertTrue(c1.checkLost(trainer));
		assertTrue(c2.checkLost(trainer));
		assertTrue(c3.checkLost(trainer));
	}

	@Test
	public void testLost2() {
		WinCondition c1 = new Rule1();
		WinCondition c2 = new Rule2();
		WinCondition c3 = new Rule3();
		Trainer trainer = new Trainer();
		assertFalse(c1.checkLost(trainer));
		assertFalse(c2.checkLost(trainer));
		assertFalse(c3.checkLost(trainer));
		assertEquals("rule3", c3.getName());
		for (int i = 0; i < 30; i++)
			trainer.getItemList().get(0).use();
		assertTrue(c1.checkLost(trainer));
		assertTrue(c2.checkLost(trainer));
		assertTrue(c3.checkLost(trainer));
	}
}
