package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.FishingRod;
import model.Item;
import model.Potion;
import model.SafariBall;

public class ItemTest {

	@Test
	public void testBegin() {
		Item ball = new SafariBall(30);
		Item potion = new Potion(1);
		Item fishingRod = new FishingRod(1);
		assertEquals(30, ball.getNum());
		assertEquals(1, potion.getNum());
		assertEquals(1, fishingRod.getNum());
		ball.getIntro();
		potion.getIntro();
		fishingRod.getIntro();
	}

	@Test
	public void testProcess() {
		Item ball = new SafariBall(30);
		Item potion = new Potion(1);
		Item fishingRod = new FishingRod(1);
		ball.use();
		potion.use();
		fishingRod.use();
		assertEquals(29, ball.getNum());
		assertEquals(0, potion.getNum());
		assertEquals(0, fishingRod.getNum());
		ball.get();
		potion.get();
		fishingRod.get();
		assertEquals(30, ball.getNum());
		assertEquals(1, potion.getNum());
		assertEquals(1, fishingRod.getNum());
	}
}
