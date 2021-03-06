package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IntroCanvas;


public class StartButton implements Movable {
	private static Image img;
	public static final int STATE_CLICK =2;
	public static final int STATE_ON = 1;
	public static final int STATE_OFF = 0;
	public static final int STATE_PRESS = 3;
	public static final int STATE_RELEASE =4;
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int stateValue;
	
	
	static{
		
		try {
			img = ImageIO.read(new File("res/images/ui.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public StartButton() {
	//1320,80
		
		x = 550;
		y = 370;
		width = 200;
		height = 62;
		
	}
	
	public boolean contains(int x, int y) {
		
		if((this.x<=x && x<this.x+width)&&(this.y<=y && y<this.y+height))
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
		 if(stateValue == STATE_PRESS)
			g.drawImage(img, x-2, y+2, x+width-2, y+height+2,
					752,285,1013,356,IntroCanvas.getInstance());
		 
		 else if(stateValue == STATE_RELEASE)
				g.drawImage(img, x, y, x+width, y+height,
						457,285,718,356,IntroCanvas.getInstance());
		else
			g.drawImage(img, x, y, x+width, y+height,
				457,285,718,356,IntroCanvas.getInstance());
		
	}

	public void state(int stateValue) {
		this.stateValue = stateValue;
	}

}
