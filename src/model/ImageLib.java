package model;
//Author: Haodong Wang
//use this class to makes the game not loading the image repeat
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLib {
	private HashMap<String, Image> imageTable;
	private BufferedImage img;

	//this is a constructor of the class
	public ImageLib() {
		imageTable = new HashMap<String, Image>();
		try {
			img = (BufferedImage) ImageIO.read(new File("images"+File.separator+"trainerMove.png"));
			Image tempImage = null;
			for (int a = 0; a < 3; a++) {
				tempImage = img.getSubimage(6 + 16 * a, 92, 15, 21);
				imageTable.put("trainerMoveSOUTH" + a, tempImage);
				tempImage = img.getSubimage(53 + 16 * a, 92, 15, 21);
				imageTable.put("trainerMoveWEST" + a, tempImage);
				tempImage = img.getSubimage(102 + 16 * a, 92, 15, 21);
				imageTable.put("trainerMoveNORTH" + a, tempImage);
				tempImage = img.getSubimage(149 + 16 * a, 92, 15, 21);
				imageTable.put("trainerMoveEAST" + a, tempImage);

				tempImage = img.getSubimage(9 + 21 * a, 144, 18, 28);
				imageTable.put("trainerFishSOUTH" + a, tempImage);
				tempImage = img.getSubimage(65 + 21 * a, 144, 19, 28);
				imageTable.put("trainerFishNORTH" + a, tempImage);

			}

			tempImage = img.getSubimage(126, 144, 21, 28);
			imageTable.put("trainerFishWEST0", tempImage);
			tempImage = img.getSubimage(200, 144, 22, 28);
			imageTable.put("trainerFishEAST0", tempImage);
			tempImage = img.getSubimage(147, 144, 27, 28);
			imageTable.put("trainerFishWEST1", tempImage);
			tempImage = img.getSubimage(222, 144, 33, 28);
			imageTable.put("trainerFishEAST1", tempImage);
			tempImage = img.getSubimage(174, 144, 26, 28);
			imageTable.put("trainerFishWEST2", tempImage);
			tempImage = img.getSubimage(255, 144, 32, 28);
			imageTable.put("trainerFishEAST2", tempImage);

			tempImage = img.getSubimage(47, 255, 44, 47);
			imageTable.put("trainerThrow0", tempImage);
			tempImage = img.getSubimage(92, 255, 63, 47);
			imageTable.put("trainerThrow1", tempImage);
			tempImage = img.getSubimage(155, 255, 50, 47);
			imageTable.put("trainerThrow2", tempImage);
			tempImage = img.getSubimage(205, 255, 50, 47);
			imageTable.put("trainerThrow3", tempImage);
			tempImage = img.getSubimage(260, 255, 38, 47);
			imageTable.put("trainerThrow4", tempImage);

			img = (BufferedImage) ImageIO.read(new File("images"+File.separator+"rolling ball.png"));
			for (int j = 0; j < 16; j++) {
				tempImage = img.getSubimage(4 + 14 * j, 56, 13, 13);
				imageTable.put("ball" + j, tempImage);
				tempImage = img.getSubimage(4 + 14 * j, 77, 13, 13);
				imageTable.put("ball" + (j + 16), tempImage);
			}
			img = (BufferedImage) ImageIO.read(new File("images"+File.separator+"battle background.png"));
			tempImage = img.getSubimage(10, 10, 235, 110);
			imageTable.put("background0", tempImage);
			tempImage = img.getSubimage(250, 10, 235, 110);
			imageTable.put("background1", tempImage);
			tempImage = img.getSubimage(495, 10, 235, 110);
			imageTable.put("background2", tempImage);
			tempImage = img.getSubimage(10, 120, 235, 110);
			imageTable.put("background3", tempImage);
			tempImage = img.getSubimage(250, 120, 235, 110);
			imageTable.put("background4", tempImage);
			tempImage = img.getSubimage(495, 120, 235, 110);
			imageTable.put("background5", tempImage);
			tempImage = img.getSubimage(10, 235, 235, 110);
			imageTable.put("background6", tempImage);
			tempImage = img.getSubimage(250, 235, 235, 110);
			imageTable.put("background7", tempImage);
			tempImage = img.getSubimage(495, 235, 235, 110);
			imageTable.put("background8", tempImage);
			tempImage = img.getSubimage(10, 350, 235, 110);
			imageTable.put("background9", tempImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//use this method to get image by a string input
	public Image getImage(String fn) {
		if (imageTable.containsKey(fn)) {
			return imageTable.get(fn);
		} else {
			try {
				Image img = ImageIO.read(new File("images"+File.separator + fn + ".png"));
				imageTable.put(fn, img);
				return img;
			} catch (IOException e) {
				System.out.println("can not find the file: " + "images"+File.separator + fn + ".png");
				e.printStackTrace();
			}
		}
		return null;
	}
}
