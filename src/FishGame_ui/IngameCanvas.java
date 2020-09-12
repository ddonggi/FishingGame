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
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import FishGame_item.Background;
import FishGame_item.ClickEffect;
import FishGame_item.Fever;
import FishGame_item.Fish;
import FishGame_item.Life;
import FishGame_item.Movable;

public class IngameCanvas extends Canvas {

	private static IngameCanvas ingame;
	private Background background;
	private ClickEffect clickEffect;
	private Fish piranha;
	private Random rand;
	private Life life;
	private Fever feverImg;
	private Clip clip;
	private Clip clip2;

	private Font font1 = new Font("Agency FB", Font.PLAIN, 20);

	private static boolean fever = false;
	private Movable[] items;
	private int unitIndex = 0;
	private int countdown;
	private int max;
	private int score;
	private int time;

	public IngameCanvas() {
		ingame = this;

		piranha = new Fish();
		background = new Background(1);
		life = new Life();
		feverImg = new Fever();
		timer();

		items = new Movable[10000];

		countdown = 5;
		max = 100;

		items[unitIndex++] = background;
		items[unitIndex++] = piranha;
		items[unitIndex++] = life;

		Sound("res\\sound\\deep_see.wav", true);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				for (int i = 0; i < unitIndex; i++) {

					if (items[i] instanceof Fish == false)
						continue;

					if (life.max() == 0)
						GameFrame.getInstance().toOutro();

					Fish piranha = (Fish) items[i];

					if (piranha.contains(e.getX(), e.getY()) == false)
						continue;
					score += 20;

					if (items[i] instanceof Fish) {
						items[i] = items[i + 1];
						
						if (piranha.getType() == 6) {
							score += 80;
							if (fever == true)
								score += 100;
						}
						if (piranha.getType() == 5) {
							score += 50;
							if (fever == true)
								score += 70;
						}
						if (piranha.getType() == 4) {
							score += 60;
							if (fever == true)
								score += 80;
						}
						if (piranha.getType() == 3) {
							score -= 30;
							life.hit(1);
						}
						if (piranha.getType() == 2) {
							score -= 10;
							if (fever == true)
								score += 10;
						}
						if (piranha.getType() == 1) {
							score += 10;
							if (fever == true)
								score += 30;
						}

					}

					
					System.out.println(score);
					break;
				}

				clickEffect = new ClickEffect(e.getX(), e.getY());
				items[unitIndex++] = clickEffect;
				Sound2("res\\sound\\Æø¹ß¼Ò¸®.wav", false);
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

	public void Sound2(String file, boolean Loop) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip2 = AudioSystem.getClip();
			clip2.open(ais);
			clip2.start();
			if (Loop)
				clip2.loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		Thread gameThread = new Thread(new Runnable() {

			@Override
			public void run() { // »õ Èå¸§ÀÇ ¸ÞÀÎ ÇÔ¼ö
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

	public static IngameCanvas getInstance() {
		return ingame;
	}

	private void update() {
		for (int i = 0; i < unitIndex; i++)
			items[i].update();

		if (--countdown == 0) {

			Fish fish = new Fish();

			if (unitIndex >= max) {
				Movable[] temp = new Movable[max + 100];
				for (int i = 0; i < unitIndex; i++) {
					temp[i] = items[i];
				}

				items = temp;
				max += 50;
			}

			items[unitIndex++] = fish;

			rand = new Random();
			countdown = rand.nextInt(10) +30;
		}

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
		}

		gg.setColor(Color.orange);// ±Û¾¾»ö±ò ¼³Á¤
		gg.setFont(font1);

		gg.drawString("SCORE :" + score, 800, 50);// ±Û¾¾»ö±ò¼³Á¤
		gg.drawString("TIME :" + time, 450, 50);// ±Û¾¾»ö±ò¼³Á¤

		g.drawImage(buf, 0, 0, this);

	}

	public void time(int i) {
		this.time = i;
	}

	public int getScore() {
		return score;
	}

	public void timer() {
		Thread th1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 30; i >= 0; i--) {
					if (i == 0 || life.max() == 0) {
						GameFrame.getInstance().toOutro();
						break;
					}
					if (i <= 5) {
						fever = true;
						items[unitIndex++] = feverImg;
					}
					time(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		});

		th1.start();
	}

}