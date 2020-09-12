package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IntroCanvas;


public class ExitButton implements Movable {

	private static Image img;
	public static final int STATE_CLICK =2;
	public static final int STATE_PRESS = 3;
	public static final int STATE_RELEASE =4;
	public static final int STATE_ON = 1;
	public static final int STATE_OFF = 0;
	public static final int STATE_EXIT=2;
	
	private int x,y;
	private int width,height;
	private int stateValue;
	
	
	static{
		
		try {
			img = ImageIO.read(new File("res/images/ui.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ExitButton(int i) {
		
		x = 550;
		y = 450;
		width = 200;
		height = 62;
	
		
		if(i == 1)
			y=550;
	}
	
	public boolean contains(int x, int y) {
		
		if((this.x<=x && x<=this.x+width)&&
				(this.y<=y && y<=this.y+height))
					return true;
		
		return false;
	}

	public void update() {
		
	}

	public void draw(Graphics g) {

		
		if(stateValue == STATE_PRESS)
			g.drawImage(img, x-2, y+2, x+width-2, y+height+2,
				465,740,795,815,IntroCanvas.getInstance());
		
		else
			g.drawImage(img, x, y, x+width, y+height,
				465,740,795,815,IntroCanvas.getInstance());
		
	}
	
	public void state(int stateValue) {
		this.stateValue = stateValue;
		
	}


}
