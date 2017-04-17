package tests;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import model.Common;
import model.Direction;
import model.Pokemon;
import model.Trainer;

public class TrainerTest {

	@Test
	public void testBegin() {
		Trainer trainer = new Trainer();
		trainer.chooseRule(2);
		trainer.chooseMap(0);
		assertEquals(500, trainer.getRemainSteps());
		assertEquals(100, trainer.getCurrentHP());
		assertTrue(trainer.getTrainerRun());
		assertEquals("map", trainer.getmapName());
		assertEquals("SOUTH", trainer.getDirection());
	}

	@Test
	public void testProcess() {
		Trainer trainer = new Trainer();
		trainer.chooseRule(1);
		trainer.chooseMap(1);
		trainer.substractHP(10);
		assertEquals(90, trainer.getCurrentHP());
		trainer.use("Safari Ball");
		assertEquals(29, trainer.getItemList().get(0).getNum());
		trainer.use("Potion");
		assertEquals(0, trainer.getItemList().get(1).getNum());
		trainer.runAwayOrNot(false);
		assertFalse(trainer.getTrainerRun());
		trainer.subtractSteps();
		assertEquals(499, trainer.getRemainSteps());
		trainer.getPokemonList();
		Pokemon pokemon = new Common();
		trainer.addPokemon(pokemon);
	}

	@Test
	public void testMove() {
		Trainer trainer = new Trainer();
		trainer.chooseRule(0);
		trainer.chooseMap(0);
		trainer.move(Direction.SOUTH);
		trainer.changeDirection(Direction.SOUTH);
		assertEquals(Direction.SOUTH.name(), trainer.getDirection());
		assertEquals(new Point(0, 29), trainer.getCurrentPoint());
		trainer.move(Direction.NORTH);
		assertEquals(new Point(0, 28), trainer.getCurrentPoint());
		trainer.move(Direction.EAST);
		assertEquals(new Point(1, 28), trainer.getCurrentPoint());
		trainer.move(Direction.WEST);
		assertEquals(new Point(0, 28), trainer.getCurrentPoint());
	}

	@Test
	public void testMoveBorderOrBarrier() {
		Trainer trainer = new Trainer();
		trainer.chooseMap(0);
		trainer.chooseRule(0);
		trainer.move(Direction.NORTH);
		assertEquals(new Point(0, 28), trainer.getCurrentPoint());
		trainer.move(Direction.WEST);
		assertEquals(new Point(0, 28), trainer.getCurrentPoint());
		trainer.move(Direction.SOUTH);
		assertEquals(new Point(0, 29), trainer.getCurrentPoint());
	}

	@Test
	public void testIsGameOver() {
		Trainer trainer = new Trainer();
		trainer.chooseMap(0);
		trainer.chooseRule(0);
		trainer.move(Direction.SOUTH);
		trainer.move(Direction.NORTH);
		assertEquals(null, trainer.isGameOver());
		for (int i = 0; i < 250; i++) {
			trainer.move(Direction.SOUTH);
			trainer.move(Direction.NORTH);
		}
		assertEquals("lost", trainer.isGameOver());
	}

	@Test
	public void testBattleSign() {
		Trainer trainer = new Trainer();
		trainer.chooseMap(0);
		trainer.chooseRule(0);
		assertFalse(trainer.getBattleTime());
		trainer.setBattleTime(true);
		assertTrue(trainer.getBattleTime());
	}

	@Test
	public void testHaveThisItem() {
		Trainer trainer = new Trainer();
		System.out.println(trainer.getItemList().get(1).getNum());
		trainer.haveThisItem("Potion");
		trainer.use("Potion");
		trainer.use("Potion");
		System.out.println(trainer.getItemList().get(1).getNum());
		trainer.haveThisItem("Potion");
		trainer.haveThisItem("unknow");
	}

	@Test
	public void testGetItem() {
		Trainer trainer = new Trainer();
		trainer.chooseMap(1);
		trainer.chooseRule(0);
		for (int i = 0; i < 4; i++) {
			trainer.move(Direction.NORTH);
		}
		for (int i = 0; i < 8; i++) {
			trainer.move(Direction.EAST);
		}
		for (int i = 0; i < 3; i++) {
			trainer.move(Direction.SOUTH);
		}
		for (int i = 0; i < 24; i++) {
			trainer.move(Direction.EAST);
		}
		trainer.getMap();
		trainer.getCurrentPokemon();
		trainer.getWinCondition();
		assertEquals(null,trainer.fishable());
		trainer.changeDirection(Direction.NORTH);
		assertEquals(null,trainer.fishable());
		trainer.changeDirection(Direction.WEST);
		assertEquals(null,trainer.fishable());
		trainer.changeDirection(Direction.EAST);
		assertEquals(null,trainer.fishable());
		assertFalse(trainer.checkPortal(trainer.getCurrentPoint()));
		trainer.fishing(trainer.getCurrentPoint());
		assertTrue(trainer.isFishing());
		trainer.endFishing();
		trainer.getFish();
		trainer.setChange();
	}
	
	@Test
	public void testGetInstance(){
		Trainer t = Trainer.getInstance();
		t.chooseMap(1);
		t.enterNewMap();
		t.chooseMap(2);
		t.enterNewMap();
	}
}
