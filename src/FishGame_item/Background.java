package FishGame_item;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;


public class Background implements Movable {
		
	private Image img;
	private double x;
	private double vx;
	private Image imgIntro;
	private Image imgIngame;
	
	public Background(int i) { //배경이미지 생성자

		try {
			imgIntro = ImageIO.read(new File("res/images/introground.png"));
			imgIngame = ImageIO.read(new File("res/images/IngameBackground.png"));
			System.out.println("인트로 출력중");
		} catch (Exception e) {
			e.printStackTrace();
		}

		img=imgIntro;
		
		x= 1320;
		vx = 1;	
		if(i == 1)
			img = imgIngame;
	}
	
	public void update() { 
		this.x -= vx;
		
	}

	public void draw (Graphics g) {

		g.drawImage(img,(int)x,0,1320,800, null);
		g.drawImage(img,(int)x-1320,0,1320,800, null);
		if(x==0)
			x= 1320;
		
	}
}
