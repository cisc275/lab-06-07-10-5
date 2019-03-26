import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/

public class Controller extends JFrame implements ActionListener {

	private Model model;
	private View drawPanel;
	Action drawAction;
	private boolean flag = true;
	final static int FRAME_START_SIZE = 800;
	final int DRAW_DELAY = 30;

	@SuppressWarnings("serial")
	public Controller() {
		drawPanel = new View();
		model = new Model(drawPanel.getFrameWidth(), drawPanel.getFrameHeight(), drawPanel.getImageWidth(),drawPanel.getImageHeight());
		drawPanel.updateButton(this);
		
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				
				if(key == KeyEvent.VK_F) {
					// Pressed F key to fire
					drawPanel.setMovement("_fire_");
					drawPanel.setCount(drawPanel.getFIRE_COUNT());
					System.out.println("F has been pressed");
				}
				else if(key == KeyEvent.VK_J) {
					// Pressed J key to jump
					drawPanel.setMovement("_jump_");
					drawPanel.setCount(drawPanel.getJUMP_COUNT());
					System.out.println("J has been pressed");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				drawPanel.setMovement("_forward_");
				drawPanel.setCount(drawPanel.getFRAME_COUNT());
				System.out.println("Key released");
				
				
			}
		};
	}

	// run the simulation
	public void start() {
		System.out.println("I m in start");
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (flag == true) {
					model.updateLocationAndDirection();
					drawPanel.update(model.getX(), model.getY(), model.getDirect());
				}
			}
		};

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(DRAW_DELAY, drawAction);
				t.start();
			}
		});
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if ("Pressed".equals(e.getActionCommand())) {
		flag = !flag;
	}
}