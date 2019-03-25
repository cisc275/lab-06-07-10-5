import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Do not modify this file without permission from your TA.
 **/

public class Controller extends JFrame {

	private Model model;
	private View drawPanel;
	Action drawAction;
	private boolean flag = true;
	final static int FRAME_START_SIZE = 800;
	final int DRAW_DELAY = 30;
	    
	@SuppressWarnings("serial")
	public Controller() {
		drawPanel = new View();
		model = new Model(drawPanel.getFrameWidth(), drawPanel.getFrameHeight(), drawPanel.getImageWidth(), drawPanel.getImageHeight());
		//System.out.println(drawPanel.getFrameWidth() + ", " + drawPanel.getFrameHeight() + ", " + drawPanel.getImageWidth() + ", " + drawPanel.getImageHeight());
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			if ("Pressed".equals(e.getActionCommand())) {
    				flag = !flag;
    			}
    			else if (flag == true){
    				model.updateLocationAndDirection();
    				drawPanel.update(model.getX(), model.getY(), model.getDirect());
    			}
    		}
    	};
    	
    	//add(drawPanel);
    	
    	//setLayout(null);
    	
    	
    	//drawPanel.button.setActionCommand("Pressed");
    	//drawPanel.button.addActionListener(drawAction);
    	
    	
	}

        //run the simulation
	public void start() {
		System.out.println("I m in start");
		if(flag) {
			//increment the x and y coordinates, alter direction if necessary
			System.out.println("going to Model");
			model.updateLocationAndDirection();
			//update the view
			System.out.println("going to View");
			drawPanel.update(model.getX(), model.getY(), model.getDirect());
			
			EventQueue.invokeLater(new Runnable(){
				public void run(){
					Timer t = new Timer(DRAW_DELAY, drawAction);
					t.start();
				}
			});
		}
	}
	
}