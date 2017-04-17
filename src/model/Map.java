package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is map class, main responsibility: generate a map, and contains the
 * position of trainer, and decided if generator a wild Pokemon.
 * 
 * @Map size:34 X 63, Walkable tiles: 1449, Longest Distance (from start point):
 *      210
 * 
 * @author Renfei Sun
 *
 */
public class Map implements Serializable {
	private static final long serialVersionUID = 1L;
	private Tile[][] tiles;
	private Point trainerPosition;
	private String[] map;
	private ArrayList<Point> items;
	public static final int width = (int) (64 * 21);// 1600
	public static final int height = (int) (34 * 25);// 850
	public static final int gameWidth = 64;
	public static final int gameHeight = 34;
	public static String choice;
	public static final String[] map1 = {
			"B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B",
			"B G G G G N G G G N N N G N G B N N N N G G G G B G N G G N N G G G G G B G G N N G G G N G G N N G N G N G N N G G G G G N G B",
			"B G G N N N G N G G N N G G N B G N G N N G N G B N G N G N G N N N N N B N N N N G N N G N N G G G G N N N G N N N N G G G N B",
			"B B B B B B B B B N N G G G G B G N N B G G G G B N G G G G N B B B B B B B B B G N G N G G N N N N G N N N G G N N N G G G N B",
			"B N N G N N N G N G G G N N N B G N G B G N N N B N N N N G G G G N G N G G N G G G G G G G G G N N G G N N G N G N N N G G G B",
			"B G N N G G N N N N G G N N G N N N G B N G G G G G G G N N N N G G G N N N N N G G N G G N G N G G G G N G G N N G N G G G G B",
			"B B N N B B B B B B B B B B G N N N N B G G N G N N G G G N N G B B B B B B B B B B B B G G G N N G N G G G G G G G N N G G N B",
			"B B N N B B G N N G G N N B G B B B B B B B B B B B B G G G B B B G G N B B B B B B B B B N G N N G N N G N G G G G G G G N R B",
			"B B N G B B G N G N N N G B B G G G G G G G G G G G B B B B B G G N N B B B B B B B B B B B N N N G N G N G G N G G N R G G R B",
			"B G N G G G G N N N G N N B B G G G G G G G G G G G B G G G G N N N B B B B B B B B B G N N B G N G G G N G N G N R R R G G R B",
			"B G G N N G G N G N N G N N B G G G G G G G G G G G B N G G N G B B B B B B B B N N G N G G N B B B G N N N N R R R R R G G G B",
			"B G N N G G N N N N G N N N B B N N B B B B B N N B B G N N N N N G G G G N N N G N G G N G N G N G B B G N R R R R R N G N N B",
			"B B B B B B B B B B B G G N N G N G N B B N N N N G N N G N G N G N N N N G N G N N G N G N N N N G N N B B R R R N G N N G N B",
			"P N G G N G G N N G N B N G G N N G G B B N N N G N N N N B B B B B B B B B N N G N N G G N G G G G G N N R R R N N G G G G N B",
			"P G N G N G G N N G G G B N N R R R R R R R R R R R R R R R R R R R R R R R R R G G R R R R N G N G N N R R R N N G G N G G G B",
			"B B B G N N N N G N G N R R R R R R R R R R R R R R R R R R R R R R R R R R R R G G R R R R R R R R R R R R R G G G N N N G N B",
			"B G N G G G N N R G G R R R R R R R R R R R R R R B G G G G G G G G G B B R R R G G R R R R R R R R R R R R N G G N G N N G N B",
			"B N N N R R R R R G G R R R R N G N N G N N G R R B G G G G G G G G G B B N G N G N N N N N R R R R R R R R N N N G N G N N G B",
			"B R R R R R R R R G G R N G G G N N N N G N N R R B G G G G G G G G G B B N N G G N N G G N G G N N R R R R R R N N N G G N N B",
			"B R R R R R R R N G N G N N G G G G N G N N G R R B B B G G B N N B B B B G G N N N N G N G G G G G N R R R R R R R R N G N G B",
			"B R R R N G G G N G N G G G N N N N N R R R R R R R R B G G B N G G G G B G G N G N G G G G N G G N B B R R R R R R R R R G G B",
			"B N G N G N N N G G N G G N G N B B B R R R R R R R R B G G B G G N N N B B B B G G N N G N N B B B B N G N N N R R R R R G G B",
			"B G N G N N N G N N G G G G G G B G G G G G G G G G G G G G B G N G N G G N G G G N N G G B B B N G G G N G G G N N N R R G G B",
			"B N G G G G N G G N G N G N G N B G G G G G G G G G G G G G B N G N N N N N N N N N G G B B N N N G N N N N N G N G G G N N G B",
			"B B B B B B B B B N G G N N G G B G G G G G G G G G G G G G B G N G N G B B B B B B B B B G G N N G G N N N G G G N N G N G G B",
			"B B B B B B B B B N G G G N N N B G G G G G G G G G G G G G B N N N N N B N N N N N G N G N N N N G N G G N N N G N G N G N N B",
			"B B B B B B B B B G G N G G G N B B B B N N B B B B B B B B B B B B B B B B G G G N N G G N G G G N G N N G N N G N G G N G N B",
			"B B B B B B B B B B B G N N G N G G G G N G G B N G N G N N N N G G N G G N N N N N G N N N N N G G G N N G G N N G N G N G G B",
			"G N N G G G G G N N B G G N G G G N N G G N N B N N N N N N N G N G N N N N N N G G N G N N N N G N N N G N G G N G N G N G G B",
			"N N G G G N N N G N B B B B B B B B B B B B B B N N N N G G N N N B B B B B N N N G G G N G G N N G G G N G N N N G N N N N N B",
			"B G G G G N G G G N G G N G N N N G G N N G G G N G N N N N G N N N G N B G N G N G G G G G N N G N N N G G N G G G N N G G N B",
			"B N N N G G G G G N N N G G G N G N N N N G G N G N G G N G N G G G N G B G G G G N N G G G G G G G N N N G G N G N G G N N N B",
			"B N N N N N G N N N N G N N G N N G G G N N N G N N N N N G N G N N N N B N G N N N N N G N G N N G G N N N G G G G N N G G G B",
			"B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B", };

	public static final String[] map2 = {
			"B B B B B B B B B B P P B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B",
			"B B B B B B B N N B G N B G N N G N N G G G N G N N N N N N N G G N N N N N G G N N G G G G G G N N G G G G G N N N N N G N N B",
			"B G G G G G G B G B G N B B B G N G N G N N N N G N N G N N N G G G G G N N G N N G N N N N N G G N G G N N G G N G N N N N G B",
			"B B B B G G G B N B G G G N N N G N G N G G G G G N G N G B B B B B B B B B B B B B B B B B B B N N G N N N G N G N G G N N N B",
			"B G G B G N G B G B N N G G N G G N G G R R R G G G N N G B G G G G G G G G G G G G G G G G G B N N N N N N N N N G N G N G G B",
			"B N G B G N N B G B B B B B B N N G N R R R R R N G G G N B N N G N G N G N N G G G N N G G N B N N G N N G N N G G G G N N N B",
			"B G N B N N G B G N G G N G G G G G R R R R R R R N G G G B G G N G N N N G N N G G G G G G N B G G N G G N N G N N G G G N G B",
			"B G N N G N G B B B B G N G G G N G R R R R R R R G N N G B G N G G N G G G R R R R R R G G N B G G G G G N N G N N N G N G G B",
			"B G N N N G G G G G G B N G G N N N R R R R R R R N G G N B N G G G N N N R R R R R N G N N G B B B B B B B B B B N N N N N N B",
			"B N N B G G G G N G N B N N N G N N N R R R R R G G N G G B G N N N N N R R R R R N G G G G N B G G G G G G G G B N G N G N G B",
			"B N N B B B B B B N G B G G N N N G N N R R R N N G N N N B G G N N N G R R R R G N G N G N N N G N G G G G N G B N N G N G N B",
			"B N N G G G G G B G N B B B B B B B B B B B B B B B B B B B G N G N N N R R R R N N N G N G G N N G G N G N G N N N N N N G G B",
			"B N G N N N N N B N G G G G G G G G G G G G G G G G G G G G G G G N N G R R R R R N N G G N G N G G N G G G N G N N G N G N G B",
			"B G N G N G G G B G N N G G G G G N N N G G G N G G G G G G N G N G N N G R R R R R R R G G G N G G N N N G N G N G N N N N G B",
			"B N G G N N G N B N G G G N G G N N G G G N N G G N G G N G G G N G G G G R R R R R R R R R G G G G G G G G G G B G G N G N G B",
			"B N N N N G N N B N G G B B B B B B B B B B B B B B B B B B B G G G G N G G N R R R R R R R R N G G N G N N N G B N G G G G G B",
			"B N G N G G N G B N N N B G N N N G N G N N G N B G N G N G B G N G G G G G N N G N G N N N N N G N N G N N G G B G N N B B B B",
			"B N N G N N N N B N G G B N G N N G G G G G N G B G G N N N B G N G G N N N G G G G N G G N N G G G G N N G N G B B B B B G G B",
			"B N N N G N G N B N G N B B B N G N N N N G N B B G G G G G B N G G G N G G B B B B B B B B B B B B B B B B B B B N N N N N N B",
			"B G N G N G N G B G N B G G G B N G G B N N G B B G G B G G B N G G N N N N B N G N G N G G N N G N N G G G G N G N G N N N G B",
			"B N N G G G N N B N N B N G G B G N G B G G G B N G N B G G B G G G N G G G B N G N N N N N G N N N N N G G G N N G N G G G G B",
			"B N N N G G G G B B B B G G G B N N N B N G N B N G G B G N B B B B B B B B B N N G N N N N N G N N N G N N G N N G N G G G N B",
			"B G N N N G N G G G G B G G G N N G N B B N N N G G G B G N G N N N G G G G N G N G G G G N G N G B B B N N N B B B G N N N N B",
			"B G N N N N G N N G G B G G G N N G G N B N G N N N G B B N G N N N G G N N N N G G N G G G N G N B G G G N N N G B N N G N N B",
			"B G G G N G G N N N N B G G G B B B B B B B B B B B B B B N G G N G G N N G G G N G N N N G G N G B G N G G G N N B N G G N G B",
			"B N G G G N N N G N N B N N N G G G G G G G G G G G B B B N N G G N G G B B B B B B B B B B B B B B G G G N N G G B N N N G G B",
			"B B B N N N B B B B B B N N N G N N N N G G N G G G B B B G B B B B B B B G G G N N N G G G G G N G G G G G G G N B N N N G G B",
			"B N G N G G G N N G B G G N N N N N N N N G G N N G B B B B B G G N N N N N N N N G N G G G G G G G G G G G G N G B N G G N N B",
			"B G N N N G G N N G B B G N N G G G N G B B B B B B B N G G N N G G N N N N N N G N N G G N G G G N G N G G N N G B N G B G N B",
			"B G N N N G G G G N N B B N N G G N N N B N N N N B G N G G N G G N G G B B B B B B B B B B B B B B B B B B B B B B N B B B N B",
			"B B B B B B B N N N B B B B B N N N B B B N N N N B N G G B B B G G N N G G G G G G G G G G G G G G G G G G G G G G B B B B B B",
			"B G N G G N G N N N G N N G G G N N G N N N N G N G B G G B N G N G G N N N G G N N N G N G G N N G G N G G N N N N G G G G N B",
			"B G G G N G G N N G G G G N N G G N N G G N G N G G B G G B N N N N N G G G N G G G G N G N G G N G G N G G N N N G N G G N N B",
			"B B B B B B B B B B B B B B B B B B B B B B B B B B B G N B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B", };

	/** Generate a map, and decide the start position of trainer */
	public Map(int choice) {
		items = new ArrayList<>();
		if (choice == 1) {
			Map.choice = "map";
			map = map1;
			trainerPosition = new Point(0, 29);
			items.add(new Point(35, 32));
			items.add(new Point(3, 2));
			items.add(new Point(35, 2));
			items.add(new Point(35, 7));
			items.add(new Point(18, 20));
		} else {
			Map.choice = "map2";
			map = map2;
			trainerPosition = new Point(27, 33);
			items.add(new Point(58, 32));
			items.add(new Point(62, 29));
			items.add(new Point(25, 27));
			items.add(new Point(1, 2));
			items.add(new Point(7, 1));
		}
		tiles = new Tile[34][64];
		for (int i = 0; i < 34; i++) {
			for (int j = 0; j < 64; j++)
				tiles[i][j] = new Tile(map[i].split(" ")[j].charAt(0), new Point(j, i));
		}
	}

	/** return the trainer's start point*/
	public Point initTrainerPoint() {
		return new Point(trainerPosition.x, trainerPosition.y);
	}

	/** Decided if generated a wild Pokemon */
	public Pokemon getPokemon(Point point) {
		Pokemon wild = point.equals(trainerPosition) ? null : tiles[point.y][point.x].generatePokemon();
		trainerPosition = new Point(point.x, point.y);
		return wild;
	}

	/** generate a Magikarp by using fish rod*/
	public Pokemon getFish(Point point) {
		return tiles[point.y][point.x].generatePokemon();
	}

	/** tell trainer could pick up a item at this point*/
	public boolean getItem(Point currentPoint) {
		for (Point pt : items) {
			if (pt.equals(currentPoint)) {
				items.remove(pt);
				return true;
			}
		}
		return false;
	}

	/** tell map the point contains items*/
	public ArrayList<Point> getItemsPoint() {
		return items;
	}

	/** return tiles*/
	public Tile[][] getTile() {
		return tiles;
	}

	/** check if trainer is able to reach this point*/
	public boolean isPassable(Point point) {
		return  point.x * point.y >= 0 && point.x < 64 && point.y < 34 && tiles[point.y][point.x].isPassable();
	}

	/** return if a portal*/
	public boolean isPortal(Point point) {
		return tiles[point.y][point.x].isPortal();
	}

	/** check if this point is a river*/
	public boolean isRiver(Point point) {
		return tiles[point.y][point.x].isRiver();
	}

	@Override
	public String toString() {
		String str = new String();
		for (Tile[] row : tiles) {
			for (Tile col : row)
				str += col + " ";
			str += '\n';
		}
		return str;
	}
}
