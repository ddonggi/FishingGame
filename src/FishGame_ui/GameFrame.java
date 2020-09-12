package FishGame_ui;

import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import FishGame_item.ExitButton;
import FishGame_item.Life;
import FishGame_ui.IngameCanvas;

public class GameFrame extends Frame {

	private static GameFrame frame;
	private IntroCanvas introCanvas; // IntroCanvas 시작화면
	private IngameCanvas ingameCanvas; // IngameCanvas 게임화면
	private OutroCanvas outroCanvas; // OutroCanvas 종료화면
	private Life life;
	
	public GameFrame() {
		frame = this;
		introCanvas = new IntroCanvas();

		this.add(introCanvas);
		introCanvas.setFocusable(true);

		introCanvas.start();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(GameFrame.this, "나가겠습니까?");
				if (result == 0)
					System.exit(0);
			}

		});
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("res\\images\\cursor.jpg");			
		Cursor cursor = tk.createCustomCursor(cursorimage, new Point(0, 0), "haha");	
		frame.setCursor(cursor);
		
	}

	public static GameFrame getInstance() {
		return frame;
	}

	public void toIngame(int canvasId) {
		
		if(canvasId==1) {
			remove(introCanvas);
			introCanvas.stopSound();
		}
		
		else {
			life = new Life();
			life.hit(-3);
		remove(outroCanvas);
		outroCanvas.stopSound();
		}

		ingameCanvas = new IngameCanvas();
		add(ingameCanvas);
		ingameCanvas.start();
		ingameCanvas.setFocusable(true);
		ingameCanvas.requestFocus();
		this.revalidate();
	}
	
	public void toOutro() {
		remove(ingameCanvas);
		ingameCanvas.stopSound();
		outroCanvas = new OutroCanvas();
		add(outroCanvas);
		outroCanvas.start();
		outroCanvas.setFocusable(true);
		outroCanvas.requestFocus();
		this.revalidate();
	}



}
