package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.OutroCanvas;;

public class GameOver implements Movable {

	private static Image img;
	private int width;
	private int height;
	private int x;
	private int y;
	public int dx1;
	public double dy2;

	static {
		try {
			img = ImageIO.read(new File("res/images/GameOver.png"));
			System.out.println("¿À¹ö");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GameOver() {
		width = img.getWidth(OutroCanvas.getInstance());
		height = img.getHeight(OutroCanvas.getInstance());
		x = 554;
		y = 416;
	}

	public void show() {

	}

	@Override
	public void draw(Graphics g) {


		g.drawImage(img, 350 ,100, width/2+350,400,  0,480,1280,880, OutroCanvas.getInstance());

	}

	@Override
	public void update() {

	}
}
