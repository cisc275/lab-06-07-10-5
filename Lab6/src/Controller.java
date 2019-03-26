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

public class Controller extends JFrame implements ActionListener, KeyListener {

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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}