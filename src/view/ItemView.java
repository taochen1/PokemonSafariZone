package view;
//Author: HaodongWang
//this is the class actually draw the itemView
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.PokemonGUI;
import model.Direction;
import model.Trainer;
import model.ImageLib;
import model.Item;
import model.Pokemon;
/**
 * 
 * @author Haodong Wang
 *
 */
@SuppressWarnings("serial")
public class ItemView extends JPanel implements Observer {
	private Image bg;
	private Image theItemBag;
	private Image theBallBag;
	private Image thePokemonBag;
	private Image potionImage;
	private Image ballImage;
	private Image fishPoleImage;
	private Image chooseImage;
	private Image chatBarImage;
	private Image arrowLeft;
	private Image arrowRight;
	private Trainer trainer;
	private int width;
	private int height;
	private ImageLib imageLib;
	private BufferedImage background;
	private BufferedImage items;
	private BufferedImage chatBar;
	private String currentPanel;
	private Item currentItem;
	private Pokemon currentPokemon;
	private int choosePosition;
	private int currentPosition;
	private ArrayList<Item> itemsList;
	private ArrayList<Item> ballList;
	private ArrayList<Pokemon> pokemonList;
	private int showFrom;
	private boolean usingPotion;
	private PokemonGUI controller;

	// use this function to set up the ItemView
	public ItemView(Trainer trainer, int width, int viewHeight, PokemonGUI controller) {
		this.controller = controller;
		this.trainer = trainer;
		this.width = width;
		this.height = viewHeight;
		this.setPreferredSize(new Dimension(width, viewHeight));
		setUpImages();
		if (trainer.getItemList().size() == 0) {
			currentPanel = null;
			choosePosition = 0;
		} else {
			choosePosition = 1;
			currentPanel = "item";
		}
		usingPotion = false;
		showFrom = 1;
		currentPosition = 1;
		spliteList();
		currentItem = itemsList.get(0);
	}

	// key bag:25,0;
	// use this function to set up all images needed
	// 251 356 498 400
	public void setUpImages() {
		// setup the imageLib
		imageLib = new ImageLib();
		background = (BufferedImage) imageLib.getImage("bag");
		items = (BufferedImage) imageLib.getImage("items");
		chatBar = (BufferedImage) imageLib.getImage("chatBar");

		// get sub images
		bg = background.getSubimage(270, 110, 250, 160);
		theItemBag = background.getSubimage(267, 15, 60, 60);
		thePokemonBag = background.getSubimage(327, 15, 60, 60);
		theBallBag = background.getSubimage(387, 15, 60, 60);
		this.potionImage = items.getSubimage(97, 22, 16, 22);
		arrowLeft = background.getSubimage(275, 89, 11, 15);
		arrowRight = background.getSubimage(292, 89, 11, 15);
		this.ballImage = items.getSubimage(321, 333, 19, 19);
		this.fishPoleImage = items.getSubimage(29, 370, 24, 25);
		chooseImage = imageLib.getImage("choose");
		chatBarImage = chatBar.getSubimage(251, 356, 249, 45);

	}

	// use this function to paint all the component
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		paintBackground(g2);
		spliteList();

		paintBag(g2);
		paintTab(g2);
		paintItems(g2);
		paintItemImg(g2);
		paintChatBar(g2);
		paintChoose(g2);
		paintExplaination(g2);

		// g2.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		// g2.drawString("trainer HP: 100", 200, 800);
		// g2.drawString("pokemon HP: 50", 1150, 800);

	}
	//use this class to paint the explanation 
	private void paintExplaination(Graphics2D g2) {
		if (currentPanel.compareTo("pokemon") == 0) {
			// draw type as well here!
			if (pokemonList.size() == 0)
				return;
			g2.drawString(
					currentPokemon.getPokemonLib().getName() + ", " + currentPokemon.getType() + " Pokemon" + " ("
							+ currentPokemon.getCurrentHp() + "/" + currentPokemon.getMaxHp() + ")",
					100 + width * 320 / 1600, 100 + height * 650 / 850);
			return;
		}
		g2.drawString(currentItem.getIntro(), 100 + width * 320 / 1600, 100 + height * 650 / 850);
	}

	//use this class to paint the items
	private void paintItems(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.setFont(new Font("Lucida Sans", Font.BOLD, 40));
		int index = 0;
		String str;
		if (currentPanel.compareTo("item") == 0) {
			while (index < itemsList.size()) {
				str = itemsList.get(index).getName();
				g2.drawString(str, width * 700 / 1600, height * (120 + 85 * index) / 850);
				g2.drawString("" + itemsList.get(index).getNum(), width * 1300 / 1600,
						height * (120 + 85 * index) / 850);
				index++;
			}
		} else if (currentPanel.compareTo("pokemon") == 0) {
			index = showFrom;
			int count = 0;
			while (index < pokemonList.size() + 1 && count < 5) {
				str = pokemonList.get(index - 1).getPokemonLib().getName();
				g2.drawString(str, width * 700 / 1600, height * (120 + 85 * count) / 850);
				index++;
				count++;
			}
		} else {
			while (index < ballList.size()) {
				str = ballList.get(index).getName();
				g2.drawString(str, width * 700 / 1600, height * (120 + 85 * index) / 850);
				g2.drawString("" + ballList.get(index).getNum(), width * 1300 / 1600,
						height * (120 + 85 * index) / 850);
				index++;
			}
		}

	}
	
	//use this class to split the list we get from trainer class
	private void spliteList() {
		int index = 0;
		Item theItem;
		itemsList = new ArrayList<Item>();
		ballList = new ArrayList<Item>();
		pokemonList = new ArrayList<Pokemon>();
		while (index < trainer.getItemList().size()) {
			theItem = trainer.getItemList().get(index);
			if (theItem.getName().compareTo("Safari Ball") == 0) {
				ballList.add(theItem);
			}
			if (theItem.getName().compareTo("Potion") == 0) {
				itemsList.add(theItem);
			}
			if (theItem.getName().compareTo("Fishing Rod") == 0) {
				itemsList.add(theItem);
			}
			index++;
		}
		pokemonList = trainer.getPokemonList();
	}

	// paint the choose board
	private void paintChoose(Graphics2D g2) {
		if (choosePosition == 0) {
			return;
		} else {
			if (currentPosition < 5)
				g2.drawImage(chooseImage, width * 630 / 1600, height * (60 + 85 * (currentPosition - 1)) / 850,
						width * 840 / 1600, height * 90 / 850, null);
			else
				g2.drawImage(chooseImage, width * 630 / 1600, height * (60 + 85 * 4) / 850, width * 840 / 1600,
						height * 90 / 850, null);

		}
	}

	// paint the chatBar and information on it
	private void paintChatBar(Graphics2D g2) {
		g2.drawImage(chatBarImage, width * 320 / 1600, height * 650 / 850, width * 1200 / 1600, height * 170 / 850,
				null);
	}

	// use this function to paint the item image
	private void paintItemImg(Graphics2D g2) {
		if (currentPanel.compareTo("pokemon") == 0) {
			if (pokemonList.size() == 0)
				return;
			Image a = imageLib.getImage(currentPokemon.getPokemonLib().name() + "Small");
			g2.drawImage(a, width * 90 / 1600, height * 680 / 850, width * 100 / 1600, height * 100 / 850, null);
			return;
		}
		if (currentItem.getName().compareTo("Safari Ball") == 0) {
			g2.drawImage(ballImage, width * 90 / 1600, height * 680 / 850, width * 100 / 1600, height * 100 / 850,
					null);
		} else if (currentItem.getName().compareTo("Potion") == 0) {
			g2.drawImage(potionImage, width * 90 / 1600, height * 680 / 850, width * 100 / 1600, height * 100 / 850,
					null);
		} else {
			g2.drawImage(fishPoleImage, width * 90 / 1600, height * 680 / 850, width * 100 / 1600, height * 100 / 850,
					null);
		}

	}

	// item size:1630,8500
	// use this function to paint the tab
	private void paintTab(Graphics2D g2) {
		if (currentPanel.compareTo("ball") == 0) {
			// g2.drawImage(ballTab, width * 55 / 1600, height * 70 / 850, width
			// * 493 / 1600, height * 99 / 850, null);
			g2.setColor(Color.gray);
			g2.setFont(new Font("Lucida Sans", Font.ITALIC, 50));
			g2.drawString("Pokemon Ball", width * 142 / 1600, height * 122 / 850);
			g2.setColor(Color.white);
			g2.drawString("Pokemon Ball", width * 140 / 1600, height * 120 / 850);
		} else if (currentPanel.compareTo("pokemon") == 0) {
			g2.setColor(Color.gray);
			g2.setFont(new Font("Lucida Sans", Font.ITALIC, 50));
			g2.drawString("Pokemon", width * 172 / 1600, height * 122 / 850);
			g2.setColor(Color.white);
			g2.drawString("Pokemon", width * 170 / 1600, height * 120 / 850);
		} else {
			// g2.drawImage(itemTab, width * 55 / 1600, height * 70 / 850, width
			// * 493 / 1600, height * 99 / 850, null);
			g2.setColor(Color.gray);
			g2.setFont(new Font("Lucida Sans", Font.ITALIC, 50));
			g2.drawString("Item", width * 232 / 1600, height * 122 / 850);
			g2.setColor(Color.white);
			g2.drawString("Item", width * 230 / 1600, height * 120 / 850);
		}

	}

	// use this function to print the bag
	private void paintBag(Graphics2D g2) {
		if (currentPanel.compareTo("item") == 0) {
			g2.drawImage(theItemBag, width * 138 / 1600, height * 200 / 850, width * 300 / 1600, height * 300 / 850,
					null);
		} else if (currentPanel.compareTo("pokemon") == 0) {
			g2.drawImage(thePokemonBag, width * 138 / 1600, height * 200 / 850, width * 300 / 1600, height * 300 / 850,
					null);
		} else {
			g2.drawImage(theBallBag, width * 138 / 1600, height * 200 / 850, width * 300 / 1600, height * 300 / 850,
					null);
		}
		g2.drawImage(arrowLeft, width * 100 / 1600, height * 330 / 850, 44, 60, null);
		g2.drawImage(arrowRight, width * 430 / 1600, height * 330 / 850, 44, 60, null);

	}

	// use this function to paint the background
	private void paintBackground(Graphics2D g2) {
		g2.drawImage(bg, 0, 0, width * 1630 / 1600, height * 850 / 850, null);

	}

	// use this function to switch different tabs
	public void switchPanel(Direction direc) {
		if (usingPotion) {
			currentPanel = "pokemon";
			if (pokemonList.size() != 0)
				currentPokemon = pokemonList.get(0);
			choosePosition = 1;
			currentPosition = 1;
			showFrom = 1;
			repaint();
			return;
		}
		if (currentPanel.compareTo("pokemon") == 0) {
			if (direc == Direction.WEST) {
				currentPanel = "ball";
				currentItem = ballList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			} else {
				currentPanel = "item";
				currentItem = itemsList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			}
		} else if (currentPanel.compareTo("item") == 0) {
			if (direc == Direction.WEST) {
				currentPanel = "pokemon";
				if (pokemonList.size() != 0)
					currentPokemon = pokemonList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			} else {
				currentPanel = "ball";
				currentItem = ballList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			}

		}

		else {
			if (direc == Direction.WEST) {
				currentPanel = "item";
				currentItem = itemsList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			} else {
				currentPanel = "pokemon";
				if (pokemonList.size() != 0)
					currentPokemon = pokemonList.get(0);
				choosePosition = 1;
				currentPosition = 1;
				showFrom = 1;
				repaint();
			}
		}
	}

	// use this function to switch item
	private void switchItem(Direction dir) {
		if (currentPanel.compareTo("item") == 0) {
			if (dir == Direction.NORTH) {
				if (choosePosition != 1) {
					choosePosition--;
					currentPosition--;
					currentItem = itemsList.get(choosePosition - 1);
					repaint();
					return;
				}
			} else {
				if (choosePosition != itemsList.size()) {
					choosePosition++;
					currentPosition++;
					currentItem = itemsList.get(choosePosition - 1);
					repaint();
					return;
				}
			}
		}
		if (currentPanel.compareTo("pokemon") == 0) {
			if (trainer.getPokemonList().size() == 0) {
				return;
			}
			if (dir == Direction.NORTH) {
				if (choosePosition != 1) {
					if (currentPosition == 1) {
						showFrom--;
						choosePosition--;
						currentPokemon = pokemonList.get(choosePosition - 1);
						repaint();
						return;
					}
					choosePosition--;
					currentPosition--;
					currentPokemon = pokemonList.get(choosePosition - 1);
					repaint();
					return;
				}
			} else {
				if (choosePosition != pokemonList.size()) {
					if (currentPosition == 5) {
						showFrom++;
						choosePosition++;
						currentPokemon = pokemonList.get(choosePosition - 1);
						repaint();
						return;
					}
					choosePosition++;
					currentPosition++;
					currentPokemon = pokemonList.get(choosePosition - 1);
					repaint();
					return;
				}
			}
		}
		if (currentPanel.compareTo("ball") == 0) {
			if (dir == Direction.NORTH) {
				if (choosePosition != 1) {
					choosePosition--;
					currentPosition--;
					currentItem = ballList.get(choosePosition - 1);
					repaint();
					return;
				}
			} else {
				if (choosePosition != ballList.size()) {
					choosePosition++;
					currentPosition++;
					currentItem = ballList.get(choosePosition - 1);
					repaint();
					return;
				}
			}
		}
	}

	// use this function in the listener to control the view
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_LEFT) {
			this.switchPanel(Direction.WEST);
		}
		if (key == KeyEvent.VK_RIGHT) {
			this.switchPanel(Direction.EAST);
		}
		if (key == KeyEvent.VK_UP) {
			this.switchItem(Direction.NORTH);
		}
		if (key == KeyEvent.VK_DOWN) {
			this.switchItem(Direction.SOUTH);
		}
		if (key == KeyEvent.VK_SPACE) {
			if (usingPotion) {
				usePotionOnPokemon();
				return;
			}
			if (currentPanel.compareTo("pokemon") == 0) {
				return;
			}
			if (currentItem.getName().equals("Safari Ball")) {
				JOptionPane.showMessageDialog(null, "You can't use this item at his time");
				return;
			} else if (currentItem.getName().equals("Fishing Rod")) {
				if (!trainer.haveThisItem(currentItem.getName())) {
					JOptionPane.showMessageDialog(null, "You don't have this item");
					return;
				} else {
					Point point = trainer.fishable();
					if (point == null) {
						JOptionPane.showMessageDialog(null, "This position cannot fish");
						return;
					} else {
						trainer.fishing(point);
						controller.setViewToMapView();
						controller.doFish();
						return;
					}
				}
			} else {
				if (currentItem.getName().equals("Potion")) {
					boolean haveItem = trainer.haveThisItem(currentItem.getName());
					if (!haveItem) {
						JOptionPane.showMessageDialog(null, "You don't have this item");
						return;
					} else if (pokemonList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "You don't have any pokemon");
						return;
					} else {
						usePotion();
						return;
					}
				}
			}
		}
		repaint();
	}

	//use this method to use potion on pokemon
	private void usePotionOnPokemon() {
		currentPokemon.usePotion(30);
		usingPotion = false;
		repaint();
	}

	// use this method when successfully used the item
	private void usePotion() {
		trainer.use(currentItem.getName());
		if (this.currentItem.getName().equals("Potion")) {
			usingPotion = true;
			switchPanel(Direction.EAST);
			repaint();
		}
	}

	//use this class to update the itemView
	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

}
