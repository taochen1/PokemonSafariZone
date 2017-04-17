package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import model.Map;
import model.Tile;

/**
 * Test : test if walkable tiles are greater than 500, if could be able to reach
 * every tiles in 500 steps, test if could generate a wild pokemon
 * 
 * @author Renfei Sun
 *
 */
public class MapTest {

	/** test if there are more 500 tiles that trainer could get*/
	@Test
	public void testMapSize() {
		// test map 1
		Map map = new Map(1);
		Tile[][] tiles = map.getTile();
		int walkable = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].getBarrier().isPassable())
					walkable++;
			}
		}
		assertTrue(walkable >= 500);

		// test map 2
		map = new Map(2);
		tiles = map.getTile();
		walkable = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].getBarrier().isPassable())
					walkable++;
			}
		}
		assertTrue(walkable >= 500);
	}

	/** check if use could go every where in 500 steps BY using DFS map search*/
	@Test
	public void testMapDistance() {
		// test map 1
		Map map = new Map(1);
		System.out.println(map);
		Queue<Tile> queue = new LinkedList<>();
		map.getTile()[29][0].reach();
		queue.add(map.getTile()[29][0]);
		go(map, queue, 1);
		System.out.println(map);
		Tile[][] tiles = map.getTile();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].isPassable())
					assertTrue(tiles[i][j].beenHere());
				else
					assertFalse(tiles[i][j].beenHere());
			}
		}
		// test map 2
		map = new Map(2);
		System.out.println(map);
		queue = new LinkedList<>();
		map.getTile()[33][27].reach();
		queue.add(map.getTile()[33][27]);
		go(map, queue, 1);
		System.out.println(map);
		tiles = map.getTile();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].isPassable())
					assertTrue(tiles[i][j].beenHere());
				else {
					assertFalse(tiles[i][j].beenHere());
				}
			}
		}
	}

	private void go(Map map, Queue<Tile> queue, int stemps) {
		if (stemps > 500)
			return;
		if (queue.isEmpty())
			return;
		Tile currTile = queue.poll();
		int x = currTile.getPosition().x;
		int y = currTile.getPosition().y;
		int num = 0;
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (i < 0 || j < 0 || j > 63 || i > 33 || !map.getTile()[i][j].isPassable()
						|| map.getTile()[i][j].beenHere() || (i + j) % 2 == (x + y) % 2)
					continue;
				else {
					queue.add(map.getTile()[i][j]);
					map.getTile()[i][j].reach();
					num++;
				}
			}
		}
		for (int i = 0; i < num; i++)
			go(map, queue, ++stemps);
	}

	@Test
	public void testPassable() {
		// test map 1
		Map map = new Map(1);
		Tile[][] tiles = map.getTile();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++)
				assertFalse(tiles[i][j].isPassable() ^ map.isPassable(new Point(j, i)));
		}

		// test map 2
		map = new Map(2);
		tiles = map.getTile();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++)
				assertFalse(tiles[i][j].isPassable() ^ map.isPassable(new Point(j, i)));
		}
	}

	/** check if there is a tile that shold not have generated a pokemon */
	@Test
	public void testGeneratePokemonFailed() {
		Map map = new Map(1);
		Point TrainerPosition = map.initTrainerPoint();
		assertEquals(new Point(0, 29), TrainerPosition);
		assertEquals(null, map.getPokemon(TrainerPosition));
		TrainerPosition = new Point(TrainerPosition.x + 1, TrainerPosition.y);
		assertEquals(null, map.getPokemon(TrainerPosition));
	}
}
