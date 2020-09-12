package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IngameCanvas;

public class Life implements Movable {

   private static Image imgRed;
   private static Image imgEmpty;

   private double x;
   private double y;
   private static int max = 3;


   static {

      try {
         imgRed = ImageIO.read(new File("res/images/heart.png"));
         imgEmpty = ImageIO.read(new File("res/images/empty.png"));
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }


   public void hit(int point) {
      max -= point;
      System.out.printf("%d", point);
   }

   public Life() {

      x = 1107; // width-offsetX-(sizeX*3)-6
      y = 2;

   }

   @Override
   public void draw(Graphics g) {

      Image img1 = imgRed;
      Image img2 = imgRed;
      Image img3 = imgRed;

      int sizeX = 64;
      int sizeY = 56;
      int dx1 = (int) x;
      int dy1 = (int) y;
      int dx2 = (int) x + sizeX;
      int dy2 = (int) y + sizeY;

      if (max == 2)
         img1 = imgEmpty;

      if (max == 1) {
         img1 = imgEmpty;
         img2 = imgEmpty;
      }

      if (max <= 0) {
         img1 = imgEmpty;
         img2 = imgEmpty;
         img3 = imgEmpty;
      }

      g.drawImage(img1, dx1, dy1, dx2, dy2, 48, 87, 588, 554, IngameCanvas.getInstance());
      g.drawImage(img2, dx1 + sizeX + 2, dy1, dx2 + sizeX + 2, dy2, 48, 87, 588, 554, IngameCanvas.getInstance());
      g.drawImage(img3, dx1 + (sizeX * 2) + 4, dy1, dx2 + (sizeX * 2) + 4, dy2, 48, 87, 588, 554,
            IngameCanvas.getInstance());

//      g.drawImage(img, width-offsetX-(sizeX*3)-6, 2, width-offsetX-(sizeX*2)-6, sizeY+2, 48, 87, 588, 554, IngameCanvas.getInstance());

   }

   @Override
   public void update() {
      // TODO Auto-generated method stub

   }

public int max() {
	return max;
}

}