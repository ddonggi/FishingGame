package FishGame_item;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import FishGame_ui.IngameCanvas;

// https://www.subpng.com/png-qcqb4k/download.html
public class Fish implements Movable {
	private Image img;
	private static Image imgLarge;
	private static Image imgSmall;
	private static Image imgSkull;
	private static Image imgMermaid;
	private static Image imgShark;
	private static Image imgGaebokchi;
	private static Image imgKingbokchi;

	private double vx;
	private double x;
	private double y;

	private int width;
	private int height;
	private int imgIndex;
	private int timeout;

	private int xIndex = 0;
	private int yIndex = 0;
	private int type;
	private int type1;
	private double time = 0;
	private double radius = 15;

	private static int mermaidIndex = 0;


	static {
		try {
			
			imgSmall = ImageIO.read(new File("res/images/small.png"));
			imgLarge = ImageIO.read(new File("res/images/large.png"));
			imgSkull = ImageIO.read(new File("res/images/skull.png"));
			imgShark = ImageIO.read(new File("res/images/shark.png"));
			imgMermaid = ImageIO.read(new File("res/images/mermaid.png"));
			imgGaebokchi = ImageIO.read(new File("res/images/gaebokchi.png"));
			imgKingbokchi = ImageIO.read(new File("res/images/kingbokchi.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Fish() {
		Random rand = new Random();
		type = rand.nextInt(7);
		type1 = rand.nextInt(7);

		// 작은 물고기 생성

		img = imgSmall;

		width = 78;
		height = 57;

		y = rand.nextInt(650) + 50;

		vx = 5;

		System.out.println("type: " + type);
		// 큰 물고기 생성
		if (type == 1 ) {
			img = imgLarge;

			width = 313;
			height = 192;

			xIndex = 200;
			yIndex = 130;

			vx = 3;
		}

		else if (type == 2) {
			img = imgSkull;

			width = 76;
			height = 57;

			vx = 5;
		}

		else if (type == 3) {
			img = imgMermaid;

			width = 38;
			height = 65;

		}

		else if (type == 4) {
			img = imgShark;

			width = 208;
			height = 103;
		}
		
		else if (type == 5) {
			img = imgGaebokchi;
			width = 280;
			height = 280;

			xIndex = 150;
			yIndex = 150;
		}
		
		else if (type == 6) {
			img = imgKingbokchi;

			width = 320;
			height = 320;
			
			yIndex = 130;
			xIndex = 130;
		}

		y = rand.nextInt(500) + 50;
		vx = 3.5;

	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

//	public boolean isCitizen() {
//		return isCitizen;
//	}

	public void draw(Graphics g) {

		int dx1 = (int) x;
		int dy1 = (int) y;
		int dx2 = (int) (x + width);
		int dy2 = (int) (y + height);

		int sx1 = imgIndex * width;
		int sy1 = 0;
		int sx2 = imgIndex * width + width;
		int sy2 = height;

		dx1 += (int) (Math.cos(time) * radius);
		dy1 += (int) (Math.sin(time) * radius);

		dx2 += (int) (Math.cos(time) * radius);
		dy2 += (int) (Math.sin(time) * radius);

		if (type == 3) {

			sx1 = mermaidIndex * width;
			sx2 = mermaidIndex * width + width;

		}

		
		
		
		time += 0.1;

		g.drawImage(img, dx1, dy1, dx2 - xIndex, dy2 - yIndex, sx1, sy1, sx2, sy2, IngameCanvas.getInstance());


	}

	@Override
	public void update() {

		this.x += vx;

		if (timeout-- == 0) {
			imgIndex++;
			imgIndex %= 5; // 1mg1이 0~5로 한정되어서 순회하도록
			
			mermaidIndex++;
			mermaidIndex %= 3;
			
			if (type == 5 || type == 6) {
				imgIndex = 0;
				
			}
			
			timeout = 5;
		}

	}

	public boolean contains(int x, int y) {

		if ((this.x <= x && x <= this.x + width - xIndex) && (this.y <= y && y <= this.y + height - yIndex))
			return true;

		return false;
	}

	public int getType() {
		return type;
	}

}
