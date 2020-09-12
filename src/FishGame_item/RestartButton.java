package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.OutroCanvas;

public class RestartButton implements Movable {
	private static Image img;
	public static final int STATE_CLICK = 2;
	public static final int STATE_PRESS = 3;


	private int x;
	private int y;
	private int width;
	private int height;
	private int stateValue;

	static {

		try {
			img = ImageIO.read(new File("res/images/ReStart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public RestartButton() {
		// 1320,80

		x = 550;
		y = 470;
		width = 200;
		height = 62;

	}

	public boolean contains(int x, int y) {

		if ((this.x <= x && x < this.x + width) && 
				(this.y <= y && y < this.y + height))
			return true;

		return false;
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g) {

//		if(stateValue == STATE_ON) 
//			g.drawImage(img, 300, 270, 500 ,330,
//					457,285,718,356,IntroCanvas.getInstance());
//		
		if (stateValue == STATE_PRESS)
			g.drawImage(img, x-2, y+2, x + width-2, y + height+2, 0, 0, 435, 92,OutroCanvas.getInstance());
		else
			g.drawImage(img, x, y, x + width, y + height, 0, 0, 435, 92, OutroCanvas.getInstance());

	}

	public void state(int stateValue) {
		this.stateValue = stateValue;
		
	}
}
