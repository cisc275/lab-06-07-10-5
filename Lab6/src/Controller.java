import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Controller implements ActionListener, KeyListener {

	private Model model;
	private View drawPanel;
	private Action drawAction;
	
	private boolean buttonFlag = true; // used to determine if the button has been pressed or not
	
	final static int FRAME_START_SIZE = 800;
	final int DRAW_DELAY = 30;

	public Controller() {
		drawPanel = new View();
		model = new Model(drawPanel.getFrameWidth(), drawPanel.getFrameHeight(), drawPanel.getImageWidth(),drawPanel.getImageHeight());
		drawPanel.updateButton(this);
		drawPanel.addKeyListener(this);
	}

	/** start()
	 * 
	 */
	public void start() {
		drawAction = new AbstractAction() {
			
			public void actionPerformed(ActionEvent e) {
				if (buttonFlag) {
					if (!drawPanel.isFire || (!drawPanel.isFire && !drawPanel.isJumping)) {
						model.updateLocationAndDirection();
					}
					
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

	
	/** actionPerformed()
	 * determines when a button is pressed, and the action command for that button will be set to "Pressed"
	 * toggles the button flag
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Pressed".equals(e.getActionCommand())) {
			//Without setFocusable(false), the KeyListener stops working because the container it is listening to has lost focus to the JButton.
			drawPanel.button.setFocusable(false);
			
			buttonFlag = !buttonFlag;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Nothing needed to key track of typed keys at this time
		// Method implementation not necessary for Lab 6 or 7
	}
	
	/** keyPressed()
	 * Determines if specific keys are pressed
	 * changes the movement to correspond with the key pressed (so that the file path for the image can be updated)
	 * updates the count to reflect the appropriate number of sub-images for the image type (forward, fire, jump, etc...)
	 * sets the flag for key pressed to be the opposite (since it was just changed)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_F) {
			// Pressed F key to fire
			drawPanel.setMovement("_fire_");
			drawPanel.setCount(drawPanel.getFIRE_COUNT());
			
			drawPanel.updateFire();
		}
		else if(key == KeyEvent.VK_J) {
			
			drawPanel.setMovement("_jump_");
			drawPanel.setCount(drawPanel.getJUMP_COUNT());
			
			drawPanel.updateJump();
		}
	}

	/** keyReleased()
	 * returns the orc to the default movement, which is forward
	 * sets the count to be the frame count
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		drawPanel.setMovement("_forward_");
		drawPanel.setCount(drawPanel.getFRAME_COUNT());
	}
		
}