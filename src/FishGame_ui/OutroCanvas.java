package FishGame_ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import FishGame_item.Background;
import FishGame_item.ExitButton;
import FishGame_item.GameOver;
import FishGame_item.Movable;
import FishGame_item.RestartButton;

public class OutroCanvas extends Canvas {

	protected static final int IntroCanvas = 0;
	
	private static OutroCanvas outro;
	private Background introBackground;
	private GameOver gameOver;
	private RestartButton restartButton;
	private ExitButton exitButton;

	private Movable[] items;
	private int unitIndex = 0;
	private Clip clip;

	int score;

	private Font font1;

	public OutroCanvas() { // 종료화면 생성자
		font1 = new Font("Agency FB", Font.PLAIN, 80);
		outro = this;
		introBackground = new Background(0);
		gameOver = new GameOver();
		restartButton = new RestartButton();
		exitButton = new ExitButton(1);
		items = new Movable[100];

		items[unitIndex++] = introBackground;	
		items[unitIndex++] = gameOver;
		items[unitIndex++] = restartButton;
		items[unitIndex++] = exitButton;
		
		Sound("res\\sound\\aquarium.wav",true);

		addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (restartButton.contains(e.getX(), e.getY())) {
					restartButton.state(RestartButton.STATE_CLICK);
					GameFrame.getInstance().toIngame(2);
				}
				if (exitButton.contains(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_CLICK);
					System.exit(0);
//					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (restartButton.contains(e.getX(), e.getY()))
					restartButton.state(RestartButton.STATE_PRESS);
				if (exitButton.contains(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_PRESS);
				}
			}
		});
	}

	public void Sound(String file, boolean Loop) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop)
				clip.loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopSound() {
		clip.stop();
		clip.close();
	}

	public void start() {
		Thread gameThread = new Thread(new Runnable() {
			@Override
			public void run() { // 새 흐름의 메인 함수
				while (true) {
					update();
					repaint();
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});
		gameThread.start();
	}

	public static OutroCanvas getInstance() {
		return outro;
	}

	protected void update() {
		for (int i = 0; i < unitIndex; i++)
			items[i].update();
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image buf = createImage(this.getWidth(), this.getHeight());
		Graphics gg = buf.getGraphics();
		for (int i = 0; i < unitIndex; i++) {
			items[i].draw(gg);
			gg.setColor(Color.orange);
			gg.setFont(font1);
			gg.drawString("SCORE :" + IngameCanvas.getInstance().getScore(), 510, 420);// 글씨색깔설정
		}
		g.drawImage(buf, 0, 0, this);
	}
	
	public void score(int i) {
		this.score = i;
			
		}
	
	
}
