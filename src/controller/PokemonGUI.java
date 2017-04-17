package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Direction;
import model.ImageLib;
import model.Item;
import model.Map;
import model.Pokemon;
import model.Trainer;
import model.WinCondition;
import songPlayer.SoundEffect;
import view.BattleView;
import view.ItemView;
import view.MapView;

/**
 * GUI for Pokemon game.
 * 
 * User starts with selecting to load previous data or start a new game.
 * Clicking “Yes” to load old data or “No” to start a new game.
 * 
 * User then will be asked to choose a wining condition. There are three
 * options: ---Condition 1: catch more than 20 pokemons in total (regardless
 * type, duplicate counts); ---Condition 2: catch a Magikarp (special pokemon
 * only can be caught by fishing) and all pokemon in the bag have full HP;
 * ---Condition 3: catch 7 common+3 uncommon+1 rare pokemons in total (duplicate
 * does not count!); Note: there is a “Lost condition” for all these 3 options:
 * use up 30 SafariBall or emaining step is 0.
 * 
 * User next will be asked to choose a map from two options (map1 and map2).
 * There is a special location in each map where the trainer can transit between
 * these two maps.
 * 
 * Then user will start the game. In the map view of the game, user can walk the
 * trainer by pressing “arrow keys” in the keyboard. The information of
 * “remaining steps” and “pokemons caught” is shown on the up right of the map.
 * At the bottom of the map, there are two buttons (“open bag” and “close
 * bag”).By clicking “open bag”, user can check information for items, safari
 * balls and pokemons in the bag. User can use left and right arrow keys to
 * switch among these options, and use space key to apply them if applicable.
 * 
 * Trainer will encounter a pokemon randomly when walking around. A battle view
 * will show up. In the battle view, there are 4 options to choose: “rock”,
 * “bait”, “safari ball” and “run”. User can use arrow keys to choose action
 * options and use space key to execute. There are 4 types of pokemons: common,
 * uncommon, rare and special. Trainer can only encounter first 3 when walking
 * around. The “special” will show when trainer uses fishing rod (must adjacent
 * to river).
 * 
 * When the game is over (win or lost), the final information about trainer will
 * be shown. If the game is not over, user will be asked to save the data when
 * closing the game.
 * 
 * @author Wang Tian,Tao Chen, Haodong Wang, Renfei Sun
 * 
 */
@SuppressWarnings("serial")
public class PokemonGUI extends JFrame {

	public static void main(String[] args) {
		PokemonGUI gui = new PokemonGUI();
		gui.setVisible(true);
	}

	BattleView battleView;
	MapView mapView;
	ItemView itemView;
	BattleControl battleControl;
	MapControl mapControl;
	SoundEffect se;
	private Trainer trainer;
	public static WinCondition rule;
	private JPanel view = new JPanel();
	private JPanel control = new JPanel();
	private JPanel currentView;
	private JPanel currentControl;
	public static final int width = Map.width;
	public static final int viewHeight = Map.height;
	public static final int controlHeight = 200;
	public static final ImageLib imageLib = new ImageLib();
	private WindowListener window;

	public PokemonGUI() {
		// tell user game tips
		String intro = "Game Information\n";
		intro += "Welcome to Pokemon Safari Zone, here are some for you to play this game\n";
		intro += "1.use arrow key to walk four direction\n";
		intro += "2.in the bag, use Left-Right arrow key to switch itemList to pokemonList\n";
		intro += "3.in the bag, press space key to use item(if river in your front, you can use FishingRod,and you need to press space to continue)\n";
		intro += "4.in the battle, use arrow key to choose and use space to access and you need to press any key to continue if battle end\n";
		intro += "enjoy your game time";
		JOptionPane.showMessageDialog(null, intro);
		// load previous data
		loadData();
		setupSystem();
	}

	private void loadData() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to load data?");

		if (choice == 0) {
			try {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Trainer.ser"));
				Trainer.setInitial((Trainer) inFile.readObject());
				trainer = Trainer.getInstance();
				inFile.close();
				rule = trainer.getWinCondition();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			trainer = Trainer.getInstance();
			chooseRule();
			chooseMap();
		}

	}

	// choose a wincondition
	private void chooseRule() {
		Object[] options = { "condition 1", "condition 2", "condition 3" };
		String rulesInformation = "1: catch more than 20 pokemons in total (regardless type, duplicate counts);" + "\n"
				+ "2: catch a Magikarp and all pokemon in the bag have full HP" + "\n"
				+ "3: catch 7 common+3 uncommon+1 rare pokemons in total(duplicate does not count!);" + "\n"
				+ "\n" + "Lost condition: use up 30 SafariBall or remaining step is 0." + "\n";
		int choice = JOptionPane.showOptionDialog(null, rulesInformation, "choose a win condition",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		trainer.chooseRule(choice);
		rule = trainer.getWinCondition();
	}

	// choose a map
	public void chooseMap() {
		Object[] options = { "map1", "map2" };
		int choice = JOptionPane.showOptionDialog(null, "choose a map", "choose a map",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		trainer.chooseMap(choice);
	}

	private void setupSystem() {
		se = new SoundEffect();
		setupLayout();
		setupListener();
		setObserver();
	}

	// set layout
	private void setupLayout() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width + 20, viewHeight + controlHeight);
		this.setLocation(10, 10);
		this.setTitle("Pokemon Game");
		setLayout(null);
		view.setLocation(0, 0);
		view.setSize(width, viewHeight);
		control.setLocation(0, viewHeight);
		control.setSize(width, controlHeight);
		mapView = new MapView(trainer, width, viewHeight);
		itemView = new ItemView(trainer, width, viewHeight, this);
		battleView = new BattleView(trainer, width, viewHeight, se);
		battleControl = new BattleControl(trainer, width, controlHeight);
		mapControl = new MapControl(trainer, width, controlHeight);
		setViewTo(mapView);
		setControlTo(mapControl);
		se.playMapSound();
		add(view);
		add(control);
	}

	// switch view according to event
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			view.remove(currentView);
		currentView = newView;
		view.add(currentView);
		currentView.repaint();
		validate();
	}

	// switch to map view
	public void setViewToMapView() {
		if (currentView != null)
			view.remove(currentView);
		currentView = mapView;
		view.add(currentView);
		currentView.repaint();
		mapView.requestFocus();
		validate();
	}

	// switch controller
	private void setControlTo(JPanel newControl) {
		if (currentControl != null)
			control.remove(currentControl);
		currentControl = newControl;
		control.add(currentControl);
		currentControl.repaint();
		validate();
	}

	// add listeners
	private void setupListener() {
		MoveListener listener = new MoveListener();
		ItemViewMoveListener itemListener = new ItemViewMoveListener();
		BattleViewMoveListener battleListener = new BattleViewMoveListener();
		mapView.addKeyListener(listener);
		itemView.addKeyListener(itemListener);
		battleView.addKeyListener(battleListener);
		window = new WindowListener();
		addWindowListener(window);
	}

	// add observer
	private void setObserver() {
		trainer.addObserver((Observer) mapView);
		trainer.addObserver((Observer) battleView);
		trainer.addObserver((Observer) itemView);
	}

	private class MoveListener implements KeyListener {
		private long lastPressProcessed = 0;

		// add action to arrow key to make trainer move
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (System.currentTimeMillis() - lastPressProcessed > 200) {
				int key = arg0.getKeyCode();
				boolean issue = false;
				Point currentPoint = trainer.getCurrentPoint();
				if (key == KeyEvent.VK_LEFT) {
					if (trainer.isFishing())
						return;
					trainer.changeDirection(Direction.WEST);
					if (trainer.checkMovable(new Point(currentPoint.x - 1, currentPoint.y))) {
						issue = trainer.move(Direction.WEST);// update data to
																// model
						mapView.move();// update to view
					}
				}
				if (key == KeyEvent.VK_RIGHT) {
					if (trainer.isFishing())
						return;
					trainer.changeDirection(Direction.EAST);
					if (trainer.checkMovable(new Point(currentPoint.x + 1, currentPoint.y))) {
						issue = trainer.move(Direction.EAST);
						mapView.move();
					}
				}
				if (key == KeyEvent.VK_UP) {
					if (trainer.isFishing())
						return;
					trainer.changeDirection(Direction.NORTH);
					if (trainer.checkMovable(new Point(currentPoint.x, currentPoint.y - 1))) {
						issue = trainer.move(Direction.NORTH);
						mapView.move();
					}
				}
				if (key == KeyEvent.VK_DOWN) {
					if (trainer.isFishing())
						return;
					trainer.changeDirection(Direction.SOUTH);
					if (trainer.checkMovable(new Point(currentPoint.x, currentPoint.y + 1))) {
						issue = trainer.move(Direction.SOUTH);
						mapView.move();
					}
				}

				if (key == KeyEvent.VK_SPACE) {
					if (trainer.isFishing()) {
						issue = trainer.getFish();
						trainer.endFishing();
						trainer.setChange();
					}
				}

				if (issue) {
					// switch to Battle if meet pokemon
					trainer.setBattleTime(true);
					setViewTo(battleView);
					trainer.setChange();
					battleView.requestFocus();
					setControlTo(battleControl);
					se.playEnterBattle();
				}
				if (trainer.checkPortal(trainer.getCurrentPoint()))
					trainer.enterNewMap();
				lastPressProcessed = System.currentTimeMillis();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
		}
	}

	// save data when closing window
	private class WindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent w) {
			int choice = JOptionPane.showConfirmDialog(null, "Do you want to save?");
			if (choice == 0) {
				FileOutputStream bytesToDisk = null;
				try {
					bytesToDisk = new FileOutputStream(Trainer.FILE_NAME);
					ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
					outFile.writeObject(Trainer.getInstance());
					outFile.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// make trainer can open bag to see current items
	public class MapControl extends JPanel {
		private JButton openBag = new JButton("open the bag");
		private JButton closeBag = new JButton("close the bag");

		public MapControl(Trainer trainer, int width, int controlHeight) {
			this.setPreferredSize(new Dimension(width, controlHeight));
			OpenItemButton listener = new OpenItemButton();
			openBag.addActionListener(listener);
			closeBag.addActionListener(listener);
			this.add(openBag);
			this.add(closeBag);
		}

		private class OpenItemButton implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == openBag) {
					setViewTo(itemView);
					itemView.requestFocus();
				} else {
					setViewTo(mapView);
					mapView.requestFocus();
				}
			}

		}
	}

	/**
	 * Simple private class. Just add some information on the bottom of the MapView.
	 * 
	 * @author Wang Tian
	 *
	 */
	private class BattleControl extends JPanel {
		String text = "Please use arrow keys to choose action options, use space key to execute.";
		private JLabel label = new JLabel(text);

		public BattleControl(Trainer trainer, int width, int controlHeight) {
			this.setBackground(Color.CYAN);
			label.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			setPreferredSize(new Dimension(width, controlHeight));
			this.add(label);
		}
	}

	private class ItemViewMoveListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			int key = arg0.getKeyCode();
			itemView.keyPressed(key);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	private class BattleViewMoveListener implements KeyListener {
		// press any key to switch back to Mapview when battle end
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (battleView.getCurrentBattleEnd()) {
				battleView.resetBettleView();
				setViewTo(mapView);
				setControlTo(mapControl);
				mapView.requestFocus();
				checkWinOrLost();
				return;
			}
			int key = arg0.getKeyCode();
			battleView.keyPressed(key);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	public void doFish() {
		mapView.fish();
	}

	// check if trainer win or lost
	private void checkWinOrLost() {
		if (rule.checkWin(trainer)) {
			se.playVictory();
			JOptionPane.showMessageDialog(null, "You win!" + "\n\n" + getFinalStatus());
			System.exit(0);
		}

		if (rule.checkLost(trainer)) {
			JOptionPane.showMessageDialog(null, "You lost!" + "\n\n" + getFinalStatus());
			System.exit(0);
		}

	}

	// get trainer status
	private String getFinalStatus() {
		int remainStep = trainer.getRemainSteps();
		String stepStr = "remaining steps: " + remainStep + "\n";
		int pokemonCaught = trainer.getPokemonList().size();
		String pstr = "pokemons caught: " + pokemonCaught + "\n";
		for (Pokemon p : trainer.getPokemonList()) {
			pstr = pstr + "  " + p.getPokemonLib().getName() + "  " + p.getType() + " " + p.getCurrentHp() + " (max: "
					+ p.getMaxHp() + ")" + "\n";
		}
		String itemStr = "Items:\n";
		for (Item i : trainer.getItemList()) {
			itemStr = itemStr + "  " + i.getName() + "  " + i.getNum() + "\n";
		}

		return stepStr + pstr + itemStr;
	}
}
