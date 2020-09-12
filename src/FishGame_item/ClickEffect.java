package FishGame_item;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FishGame_ui.IngameCanvas;

public class ClickEffect extends Canvas implements Movable {

	private static Image img;
	private int x;
	private int y;
	private int width;
	private int height;
	private int imgIndex = 0;

	static {
		try {
			img = ImageIO.read(new File("res/images/ClickEffect.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ClickEffect(int x, int y) {
		this.x = x;
		this.y = y;
		width = 100;
		height = 100;
	}

	public void draw(Graphics g) {

		int offsetX = width / 2;
		int offsetY = height / 2;
		int dx1 = (int) (x - offsetX);
		int dy1 = (int) (y - offsetY);
		int dx2 = (int) (x + width - offsetX);
		int dy2 = (int) (y + height - offsetY);

		g.drawImage(img, dx1 - 10, dy1 - 10, dx2 + 10, dy2 + 10, imgIndex * width, 0, imgIndex * width + width, height,
				IngameCanvas.getInstance());

	}

	public void start() {
		Thread gameThread = new Thread(new Runnable() {

			@Override
			public void run() { // 새 흐름의 메인 함수
				while (true) {
					update();
					repaint();
					try {
						Thread.sleep(17);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
		gameThread.start();
	}

	@Override
	public void update() {

		imgIndex++;
	}

}
