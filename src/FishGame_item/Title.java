package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IntroCanvas;

public class Title implements Movable {
		
		private static Image img;
		private int imgIndex;
		private int width;
		private int height;
		
		private double x;
		private double y;
		
		static {
			try {
				img = ImageIO.read(new File("res/images/Title.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public Title() {
			
			width = img.getWidth(IntroCanvas.getInstance());
			height = img.getWidth(IntroCanvas.getInstance());
			x = 660;
			y = 350;
			
		}
		
		public void show() {
		}
		
		
		public void draw(Graphics g) {

		
			
			int w = width;
			int h = height;
			int offsetX = w/2;
			int offsetY = h/2;
			int dx1 = (int)(x-offsetX);
			int dx2 = (int)(x+w-offsetX);
			int dy1 = (int)(y-offsetY);
			int dy2 = (int)(y+h-offsetY);

			
			g.drawImage(img,dx1,dy1,dx2,dy2, imgIndex*w,0, imgIndex*w+w, h, IntroCanvas.getInstance());
			
		}
		
		@Override
		public void update() {
	
			}
		
		
}


