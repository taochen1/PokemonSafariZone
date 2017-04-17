package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.PokemonGUI;
import model.Direction;
import model.ImageLib;
import model.Trainer;
import songPlayer.SoundEffect;

/**
 * This class shows the graphic view and animations when battle happens. When a pokemon is
 * encountered, the view will switch to Battle-View, both trainer and pokemon will be moving
 * to the corresponding positions. 
 * After choosing action options, corresponding animations will happen such as throwing rock,
 * bait or safari ball. 
 * 
 * There are also animations when trainer or pokemon runs away.
 * 
 *
 * @author Wang Tian, Haodong Wang, Tao Chen, Renfei Sun.
 *
 */
@SuppressWarnings("serial")
public class BattleView extends JPanel implements Observer {
	private BufferedImage currentBattleBackground;
	private BufferedImage trainerImage;
	private BufferedImage pokemonImage;
	private BufferedImage chatBar;
	private Image moveImage;
	private Image optionBox;
	private Image chatBarImage;
	private Image ball;
	private Image rock;
	private Image bait;
	private Trainer trainer;
	private int width;
	private int viewHeight;
	private SoundEffect se;
	private boolean throwItem = false;
	private int x = 500;
	private int y = 500;
	private int trainer_x = 330;
	private int pokemon_x = 950;
	private Timer timer = null;
	private Timer battleStartTimer = null;
	private Timer pokemonRunTimer = null;
	private Timer trainerRunTimer = null;
	private Timer trainerThrowTimer = null;
	private ImageLib imageLib = PokemonGUI.imageLib;
	private int position;
	private boolean currentBattleEnd;
	private Graphics2D g2;
	private String chatBoxMessage;
	private String chatBoxMessageStorage;
	private boolean animation;
	private boolean isBattleStart;
	private boolean pokemonRun;
	private boolean trainerRun;
	private int step;
	private boolean isThrowBall;

	//constructor
	public BattleView(Trainer trainer, int width, int viewHeight, SoundEffect se) {
		this.se = se;
		animation = false;
		isBattleStart = true;
		pokemonRun = false;
		trainerRun = false;
		battleStartTimer = new Timer(15, new MoveListener());
		timer = new Timer(40, new MoveItemListener());
		pokemonRunTimer = new Timer(15, new PokemonRunListener());
		trainerRunTimer = new Timer(15, new TrainerRunListener());
		trainerThrowTimer = new Timer(200, new TrainerThrowListener());
		chatBoxMessage = "a wild pokemon appears";
		position = 1;
		currentBattleEnd = false;
		this.trainer = trainer;
		this.width = width;
		this.viewHeight = viewHeight;
		this.setPreferredSize(new Dimension(width, viewHeight));
		setUpImages();
		step = 0;
		isThrowBall = false;
	}

	public BattleView getSelf() {
		return this;
	}

	// reset the battle view
	public void resetBettleView() {
		chatBoxMessage = "a wild pokemon appears";
		position = 1;
		currentBattleEnd = false;
	}

	// use this function to set up all the images
	private void setUpImages() {
		// set up all the buffered Image first
		trainerImage = (BufferedImage) imageLib.getImage("trainerThrow0");
		chatBar = (BufferedImage) imageLib.getImage("chatBar");
		// use the bufferedImage to cut image we need or just set up the image
		chatBarImage = chatBar.getSubimage(251, 356, 249, 45);
		// chooseImage = imageLib.getImageCollection("choose").getImage();
		optionBox = imageLib.getImage("optionBox");
		ball = imageLib.getImage("ball0");
		bait = imageLib.getImage("bait");
		rock = imageLib.getImage("rock");
	}

	// return true if the current battle was end
	public boolean getCurrentBattleEnd() {
		return currentBattleEnd;
	}
	
	//use this method to get all the pokemon image
	private BufferedImage getPokemonImage() {
		String text = trainer.getCurrentPokemon().getPokemonLib().name();
		BufferedImage image = null;
		image = (BufferedImage) imageLib.getImage(text);
		return image;
	}
	
	//use this method to get background setup and it is chosen randomly
	private BufferedImage getBattleBackground() {
		Random random = new Random();
		int n = random.nextInt(10);
		return (BufferedImage) imageLib.getImage("background" + n);
	}
	
	//paint all the component
	public void paintComponent(Graphics g) {
		g2 = (Graphics2D) g;
		super.paintComponent(g2);

		g2.drawImage(currentBattleBackground, 0, 0, width, viewHeight, this);
		if (!trainerRun)
			g2.drawImage(trainerImage, trainer_x, 450, 150, 370, null);

		if (!pokemonRun) {
			g2.drawImage(pokemonImage, pokemon_x, 175, 300, 300, null);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g2.drawString("Type: " + trainer.getCurrentPokemon().getType(), 1100, 80);
			g2.drawString("Name: " + trainer.getCurrentPokemon().getPokemonLib().getName(), 1100, 110);
			g2.drawString("HP: " + trainer.getCurrentPokemon().getCurrentHp(), 1100, 140);
		}

		if (throwItem) {
			g2.drawImage(moveImage, x, y, 30, 30, null);
		}
		if (!isBattleStart) {
			paintChatBox();
			paintOptionBox();
			paintOptions();
			paintChooseBox();
			// draw the message
			drawMessage();
		}
	}
	
	//use this method to move the choosing box
	private void moveChooseingBox(Direction dir) {
		if (dir == Direction.EAST) {
			if (position == 4)
				return;
			else
				position++;
		}
		if (dir == Direction.NORTH) {
			if (position < 3) {
				return;
			} else
				position -= 2;
		}
		if (dir == Direction.SOUTH) {
			if (position > 2)
				return;
			else
				position += 2;
		}
		if (dir == Direction.WEST) {
			if (position == 1) {
				return;
			} else
				position--;
		}
		return;
	}
	//you use this method to get the control box moving
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_LEFT) {
			this.moveChooseingBox(Direction.WEST);
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.moveChooseingBox(Direction.EAST);
		}
		if (key == KeyEvent.VK_UP) {
			this.moveChooseingBox(Direction.NORTH);
		}
		if (key == KeyEvent.VK_DOWN) {
			this.moveChooseingBox(Direction.SOUTH);
		}
		if (key == KeyEvent.VK_SPACE) {
			pressedOption();
		}

		repaint();
	}
	//we have 4 options here and use this method to recognize which one are we choosing
	private void pressedOption() {
		if (position == 1) {
			doThrowBaitActions();
		}
		if (position == 2) {
			doThrowRockActions();
		}
		if (position == 3) {
			doThrowBallAction();
		}
		if (position == 4) {
			se.playRunAway();
			currentBattleEnd = true;
			chatBoxMessageStorage = "You run away. Be brave!";
			changeMessage();
			trainerRunTimer.start();
			// repaint();
		}
	}

	// when ball option selected
	private void doThrowBallAction() {
		trainer.use("Safari Ball");
		se.playSafariBall();
		moveImage = ball;
		isThrowBall = true;
		trainerThrowTimer.start();

	}

	// when bait option selected
	private void doThrowBaitActions() {
		se.playThrowBait();
		moveImage = bait;
		trainerThrowTimer.start();
	}

	// when rock option selected
	private void doThrowRockActions() {
		se.playThrowRock();
		moveImage = rock;
		trainerThrowTimer.start();
	}
	
	//use this method when pokemon get run away
	private void pokemonEscapeAction() {
		se.playRunAway();
		trainer.setBattleTime(false);
		chatBoxMessageStorage = "Uh Oh! Pokemon run away!";
		changeMessage();
		repaint();
		currentBattleEnd = true;
		pokemonRunTimer.start();
	}

	//use this method to paint the choosing box
	private void paintChooseBox() {
		g2.setColor(Color.red);
		if (position == 1) {
			g2.drawRect(735, 675, 70, 35);
		} else if (position == 2) {
			g2.drawRect(840, 675, 70, 35);
		} else if (position == 3) {
			g2.drawRect(720, 715, 120, 35);
		} else {
			g2.drawRect(840, 715, 70, 35);
		}
	}
	//use this method to paint all the options
	private void paintOptions() {
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g2.drawString("Bait", 750, 700);
		g2.drawString("Rock", 850, 700);
		g2.drawString("SafariBall", 735, 740);
		g2.drawString("Run", 850, 740);

	}

	// use this function to draw paint Option Box
	private void paintOptionBox() {
		g2.drawImage(optionBox, 700, 635, 250, 150, null);

	}

	// use this function to draw chat box
	private void paintChatBox() {
		g2.drawImage(chatBarImage, 950, 635, 350, 150, null);

	}

	// use this function to draw message in chat box
	public void drawMessage() {
		g2.setColor(Color.black);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		String str = chatBoxMessage;
		String temp = chatBoxMessage;
		int n = 0;
		while (temp.length() > 27) {
			str = temp.substring(0, 27);
			temp = temp.substring(27);
			g2.drawString(str, 970, 690 + n * 30);
			n++;

		}
		if (chatBoxMessage.length() <= 27)
			g2.drawString(str, 970, 690 + n * 30);
		else
			g2.drawString(temp, 970, 690 + n * 30);
	}
	//use this method to change the message when animation ended and update the message from message storage
	private void changeMessage() {
		if (animation == false)
			chatBoxMessage = chatBoxMessageStorage;
	}
	//use this method to update the battleView
	@Override
	public void update(Observable arg0, Object arg1) {
		currentBattleBackground = getBattleBackground();
		if (trainer.getCurrentPokemon() != null)
			pokemonImage = getPokemonImage();
		pokemonRun = false;
		trainerRun = false;
		isBattleStart = true;
		if (isBattleStart) {
			trainer_x = 1300;
			pokemon_x = 0;
			battleStartTimer.start();
		}

	}
	//this is actionListener for the moving trainer and pokemon at the beginning of a battle
	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (trainer_x >= 330 && pokemon_x <= 950) {
				trainer_x = trainer_x - 10;
				pokemon_x = pokemon_x + 10;
				repaint();
			} else {
				battleStartTimer.stop();
				isBattleStart = false;
				repaint();
			}
		}

	}
	
	private class PokemonRunListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (pokemon_x > -290) {
				pokemonRun = false;
				pokemon_x = pokemon_x - 10;
				repaint();
			} else {
				pokemonRunTimer.stop();
				pokemonRun = true;
				repaint();
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			}
		}

	}

	private class TrainerRunListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (trainer_x > -290) {
				trainerRun = false;
				trainer_x = trainer_x - 10;
				repaint();
			} else {
				trainerRunTimer.stop();
				trainerRun = true;
				repaint();
				trainerRun = false;
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			}

		}

	}

	public void setThrowRockFalse() {
		throwItem = false;
	}

	public boolean getThrowRock() {
		return throwItem;
	}

	public void resetXY() {
		x = 500;
		y = 500;
	}

	public void moveItem() {
		throwItem = true;
		animation = true;
		resetXY();
		timer.start();
	}

	private class MoveItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (x < 992) {
				if (isThrowBall) {
					moveImage = imageLib.getImage("ball" + step);
					step = (step + 1) % 32;
				}
				getSelf().setFocusable(false);
				x += 10;
				y = (int) Math.floor(0.0045 * x * x - 7.35 * x + 3050);
				repaint();
			} else {
				step = 0;
				isThrowBall = false;
				if (position == 1)
					afterMoveBaitAction();
				else if (position == 2)
					afterMoveRockAction();
				else if (position == 3)
					afterMoveBallAction();
				else
					timer.stop();

			}
		}

		private void afterMoveBallAction() {
			int result = trainer.getCurrentPokemon().getMessage("ball");
			timer.stop();
			if (result == -1) {
				chatBoxMessageStorage = "Uh Oh! Pokemon run away! Press any key to exit battle.";
				throwItem = false;
				animation = false;
				changeMessage();
				// timer.stop();
				pokemonEscapeAction();
			} else if (result == 0) {
				se.playCapturePokemon();
				chatBoxMessageStorage = "Congradulations! You caught a pokemon! Press any key to exit battle.";
				trainer.addPokemon(trainer.getCurrentPokemon());
				throwItem = false;
				animation = false;
				currentBattleEnd = true;
				changeMessage();
				// timer.stop();
				repaint();
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			} else {
				chatBoxMessageStorage = "You used a safariball, but nothing happened! Please continue.";
				throwItem = false;
				animation = false;
				// timer.stop();
				changeMessage();
				repaint();
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			}

		}

		private void afterMoveBaitAction() {
			int result = trainer.getCurrentPokemon().getMessage("bait");
			timer.stop();
			if (result == -1) {
				chatBoxMessageStorage = "Uh Oh! Pokemon run away! Press any key to exit battle.";
				throwItem = false;
				animation = false;
				changeMessage();
				// timer.stop();
				pokemonEscapeAction();
			} else {
				chatBoxMessageStorage = "You have successfully used the bait! Please continue.";
				throwItem = false;
				animation = false;
				// timer.stop();
				changeMessage();
				repaint();
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			}

		}

		private void afterMoveRockAction() {
			int result = trainer.getCurrentPokemon().getMessage("rock");
			timer.stop();
			if (result == -1) {
				chatBoxMessageStorage = "Uh Oh! Pokemon run away! Press any key to exit battle.";
				throwItem = false;
				animation = false;
				changeMessage();
				// timer.stop();
				pokemonEscapeAction();
				// trainer.addPokemon(trainer.getCurrentPokemon());
				// se.playCapturePokemon();
				// repaint();
				// getSelf().setFocusable(true);
				// getSelf().requestFocus();
			} else {
				chatBoxMessageStorage = "You have successfully used the rock! Please continue.";
				throwItem = false;
				animation = false;
				// timer.stop();
				changeMessage();
				repaint();
				getSelf().setFocusable(true);
				getSelf().requestFocus();
			}

		}
	}

	private class TrainerThrowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (step < 5) {
				trainerImage = (BufferedImage) imageLib.getImage("trainerThrow" + step);
				getSelf().setFocusable(false);
				repaint();
				step++;
			} else {
				step = 0;
				trainerImage = (BufferedImage) imageLib.getImage("trainerThrow" + step);
				trainerThrowTimer.stop();
				moveItem();
			}
		}

	}
}
