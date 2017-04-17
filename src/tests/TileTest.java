package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import model.Barrier;
import model.Tile;

/**
 * Test if passible works good and if could generate pokemon
 * 
 * @author Renfei Sun
 *
 */
public class TileTest {
	private final char[] list = { 'N', 'B', 'R', 'G', 'P' };

	@Test
	public void testPassable() {
		Tile[] tile = new Tile[5];
		for (int i = 0; i < list.length; i++) {
			tile[i] = new Tile(list[i], new Point(i, i));
		}
		for (int i = 0; i < list.length; i++) {
			if (tile[i].getBarrier().equals(Barrier.None) || tile[i].getBarrier().equals(Barrier.Grass)
					|| tile[i].getBarrier().equals(Barrier.Portal))
				assertTrue(tile[i].isPassable());
			else
				assertFalse(tile[i].isPassable());
			if (i == 2)
				assertTrue(tile[i].isRiver());
			if (i == 4)
				assertTrue(tile[i].isPortal());
		}
	}

	@Test
	public void testGeneratePokemon() {
		Tile[] tile = new Tile[5];
		for (int i = 0; i < list.length; i++)
			tile[i] = new Tile(list[i], new Point(i, i));
		for (int j = 0; j < 125; j++) {
			for (int i = 0; i < list.length; i++) {
				tile[i].generatePokemon();
				if (!tile[i].getBarrier().equals(Barrier.River) && (!tile[i].isPassable()
						|| tile[i].getBarrier().equals(Barrier.None) || tile[i].getBarrier().equals(Barrier.Portal)))
					assertEquals(null, tile[i].generatePokemon());
			}
		}
	}

}
