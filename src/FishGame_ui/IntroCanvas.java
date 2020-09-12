package FishGame_ui;
import java.awt.Canvas;
import java.awt.Component;
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
import FishGame_item.Movable;
import FishGame_item.StartButton;
import FishGame_item.Title;


public class IntroCanvas extends Canvas{

	private static IntroCanvas intro;
	private StartButton startButton;
	private ExitButton exitButton;
	private Background background;
	private Title title;
	
	private Movable[] items;
	
	private int unitIndex = 0;
	
	private Clip clip;
	

	public IntroCanvas() { // 시작화면 생성자

		Sound("res\\sound\\seatrain.wav", true);
		
		intro = this;
		background = new Background(0);
		startButton = new StartButton();
		exitButton= new ExitButton(0);
		title = new Title();
		items = new Movable[100];

		items[unitIndex++] = background; //1번째로 로드 되어야함
		items[unitIndex++] = title;
		items[unitIndex++] = startButton;
		items[unitIndex++] = exitButton;
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (startButton.contains(e.getX(), e.getY())) {
					startButton.state(StartButton.STATE_CLICK);
					GameFrame.getInstance().toIngame(1);;
				}

				if (exitButton.contains(e.getX(), e.getY())) {
					exitButton.state(ExitButton.STATE_CLICK);
					System.exit(0);
				}

			}
		});

		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if (startButton.contains(e.getX(), e.getY()))
					startButton.state(StartButton.STATE_ON);
				else
					startButton.state(StartButton.STATE_OFF);
			}
			
		});
	
			addMouseListener(new MouseAdapter() {
				
				public void mouseReleased(MouseEvent e) {
					if (startButton.contains(e.getX(), e.getY()))
						startButton.state(StartButton.STATE_RELEASE);
					if (exitButton.contains(e.getX(), e.getY()))
						exitButton.state(ExitButton.STATE_RELEASE);
				}
	});
			
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mousePressed(MouseEvent e) {
					if (startButton.contains(e.getX(), e.getY()))
						startButton.state(StartButton.STATE_PRESS);
					if (exitButton.contains(e.getX(), e.getY()))
						exitButton.state(ExitButton.STATE_PRESS);
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
			// Loop 값이true면 사운드재생을무한반복시킵니다.
			// false면 한번만재생시킵니다.

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

	public static Component getInstance() {
		return intro;
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
		for (int i = 0; i < unitIndex; i++)
			items[i].draw(gg);
		g.drawImage(buf, 0, 0, this);

	}

}
