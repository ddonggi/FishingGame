package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IngameCanvas;

public class Fever implements Movable{
	
	public static Image img;
	
	private double x;
	private double y;

	private int width;
	private int height;
	
	static {
		try {
			img = ImageIO.read(new File("res/images/Fever.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Fever() {
		x = 500;
		y = 70;
		width = 600;
		height = 275;
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics g) {
		
		int sizeX = 250;
		int sizeY = 100;
		
		int dx1 = (int) x;
		int dy1 = (int) y;
		int dx2 = (int) x + sizeX;
		int dy2 = (int) y + sizeY;
		
		
		
		g.drawImage(img, dx1, dy1, dx2, dy2, 0, 0, width, height, IngameCanvas.getInstance());
	}
	
	
}
