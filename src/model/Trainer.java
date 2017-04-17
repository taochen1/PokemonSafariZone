package model;

/**
 * This is Trainer class. this class will generate a trainer 
 * and can walk on the map, use item, get item from map or battle and catch pokemon.
 * 
 * @author Tao Chen
 *
 */
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Trainer extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> itemList;
	private ArrayList<Pokemon> pokemonList;
	private int remainSteps;
	private int HP;
	private boolean trainerRun;
	private Point currentPoint;
	private Pokemon currentPokemon;
	private Map map;
	private String mapName;
	private WinCondition rule;
	private boolean battleTime;
	public static final String FILE_NAME = "Trainer.ser";
	private static Trainer self;
	private String direction;
	private int preIndex;
	// mark trainer is fishing or not
	private boolean isFishing;
	// mark trainer got a fish (Magikarp)
	private boolean getFish;

	public static synchronized Trainer getInstance() {
		if (self == null) {
			self = new Trainer();
		}
		return self;
	}

	public Trainer() {
		getFish = false;
		isFishing = false;
		battleTime = false;
		itemList = new ArrayList<Item>();
		itemList.add(new SafariBall(30));
		itemList.add(new Potion(1));
		itemList.add(new FishingRod(1));
		pokemonList = new ArrayList<Pokemon>();
		remainSteps = 500;
		HP = 100;
		trainerRun = true;
		direction = "SOUTH";
	}

	// choose win condition
	public void chooseRule(int choice) {
		if (choice == 0) {
			rule = new Rule1();
		}
		if (choice == 1) {
			rule = new Rule2();
		}
		if (choice == 2) {
			rule = new Rule3();
		}
	}

	// choose a map
	public void chooseMap(int choice) {
		if (choice == 0) {
			map = new Map(1);
			mapName = "map";
		}
		if (choice == 1) {
			map = new Map(2);
			mapName = "map2";
		}
		currentPoint = map.initTrainerPoint();
	}

	public String getmapName() {
		return mapName;
	}

	public String getDirection() {
		return direction;
	}

	public void setBattleTime(boolean sign) {
		battleTime = sign;
	}

	// get battletime and use for animation
	public boolean getBattleTime() {
		return battleTime;
	}

	public void runAwayOrNot(boolean run) {
		trainerRun = run;
	}

	public boolean getTrainerRun() {
		return trainerRun;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	// get trainer pokemon list
	public ArrayList<Pokemon> getPokemonList() {
		return pokemonList;
	}

	public int getCurrentHP() {
		return HP;
	}

	public int getRemainSteps() {
		return remainSteps;
	}

	public void subtractSteps() {
		remainSteps--;
	}

	public void substractHP(int n) {
		HP = HP - n;
	}

	public void addPokemon(Pokemon p) {
		pokemonList.add(p);
	}

	public void changeDirection(Direction direction) {
		this.direction = direction.name();
		setChanged();
		notifyObservers();
	}

	public boolean move(Direction direction) {

		boolean movebale = false;
		if (direction == Direction.NORTH) {
			if (currentPoint.y - 1 >= 0 && checkMovable(new Point(currentPoint.x, currentPoint.y - 1))) {
				currentPoint.y--;
				subtractSteps();
				movebale = true;
			}
		}
		if (direction == Direction.EAST) {
			if (currentPoint.x + 1 < Map.gameWidth && checkMovable(new Point(currentPoint.x + 1, currentPoint.y))) {
				currentPoint.x++;
				subtractSteps();
				movebale = true;
			}
		}
		if (direction == Direction.SOUTH) {
			if (currentPoint.y + 1 < Map.gameHeight && checkMovable(new Point(currentPoint.x, currentPoint.y + 1))) {
				currentPoint.y++;
				subtractSteps();
				movebale = true;
			}
		}
		if (direction == Direction.WEST) {
			if (currentPoint.x - 1 >= 0 && checkMovable(new Point(currentPoint.x - 1, currentPoint.y))) {
				currentPoint.x--;
				subtractSteps();
				movebale = true;
			}
		}
		if (movebale) {
			currentPokemon = map.getPokemon(currentPoint);
			setChanged();
			notifyObservers();
			// check if trainer in this position meet a pokemon
			if (currentPokemon != null) {
				this.substractHP(1);
				return true;
			} else {
				boolean newItem = map.getItem(currentPoint);
				// get item if map has in current position
				if (newItem) {
					Random generator = new Random();
					int index = generator.nextInt(3);
					while (preIndex == index) {
						// previous item cant be same as current item
						index = generator.nextInt(3);
					}
					preIndex = index;
					this.getItemList().get(index).get();
				}
				return false;
			}
		} else
			return false;
	}

	// check if next point is portal
	public boolean checkPortal(Point point) {
		return map.isPortal(point);
	}

	// if trainer is one the portal, go to the new map
	public void enterNewMap() {
		if (mapName.equals("map2")) {
			map = new Map(1);
			mapName = "map";
		} else {
			map = new Map(2);
			mapName = "map2";
		}
		currentPoint = map.initTrainerPoint();
		setChanged();
		notifyObservers();
	}

	public boolean checkMovable(Point point) {
		return map.isPassable(point);
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public Map getMap() {
		return map;
	}

	// check reach rule of not
	public String isGameOver() {
		if (rule.checkWin(this) && (!rule.checkLost(this))) {
			return "win";
		} else if ((!rule.checkWin(this)) && rule.checkLost(this)) {
			return "lost";
		} else {
			return null;
		}
	}

	public static void setInitial(Trainer readObject) {
		self = readObject;
		self.setChanged();
	}

	// return current pokemon, added by Wang Tian
	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}

	// return current wining condition, added by Wang Tian
	public WinCondition getWinCondition() {
		return rule;
	}

	// use for item View
	public boolean haveThisItem(String item) {
		int index = 0;
		for (index = 0; index < itemList.size(); index++) {
			if (itemList.get(index).getName().compareTo(item) == 0) {
				if (itemList.get(index).getNum() > 0)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	// use this function to use a item
	public boolean use(String item) {
		int index = 0;
		for (index = 0; index < itemList.size(); index++) {
			if (itemList.get(index).getName().compareTo(item) == 0 && itemList.get(index).getNum() > 0) {
				itemList.get(index).use();
				return true;
			}
		}
		return false;
	}

	// check if trainer could use fish rod or not
	public Point fishable() {
		Point fishingPoint;
		if (direction.equals("EAST"))
			fishingPoint = new Point(currentPoint.x + 1, currentPoint.y);
		else if (direction.equals("WEST"))
			fishingPoint = new Point(currentPoint.x - 1, currentPoint.y);
		else if (direction.equals("NORTH"))
			fishingPoint = new Point(currentPoint.x, currentPoint.y - 1);
		else
			fishingPoint = new Point(currentPoint.x, currentPoint.y + 1);
		if (map.isRiver(fishingPoint))
			return fishingPoint;
		return null;
	}

	// do the fishing
	public void fishing(Point point) {
		use(itemList.get(2).getName());
		isFishing = true;
		currentPokemon = map.getFish(point);
		getFish = (currentPokemon == null) ? false : true;
	}

	// mark trainer is fishing
	public boolean isFishing() {
		return isFishing;
	}

	// finish fishing
	public void endFishing() {
		isFishing = false;
	}

	// mark trainer met Magikarp
	public boolean getFish() {
		if (getFish) {
			getFish = false;
			return true;
		}
		return getFish;
	}

	public void setChange() {
		this.setChanged();
		this.notifyObservers();
	}
}
