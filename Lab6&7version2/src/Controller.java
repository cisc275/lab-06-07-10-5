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
	//private View view;
	
	View drawPanel;
	Action drawAction;
	private boolean flag = true;
	final static int FRAME_START_SIZE = 800;
	final int DRAW_DELAY = 30;
	    
	@SuppressWarnings("serial")
	public Controller() {
		drawPanel = new View();
		model = new Model(drawPanel.getFrameWidth(), drawPanel.getFrameHeight(), drawPanel.getImageWidth(), drawPanel.getImageHeight());
		drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			if ("Pressed".equals(e.getActionCommand())) {
    				flag = !flag;
    			}
    			else if (flag == true){
    				drawPanel.repaint();
    			}
    		}
    	};
    	
    	add(drawPanel);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(FRAME_START_SIZE, FRAME_START_SIZE);
    	setVisible(true);
    	pack();
    	
    	setLayout(null);
    	JButton button = new JButton("Start/Stop");
    	button.setActionCommand("Pressed");
    	button.addActionListener(drawAction);
    	button.setBounds(Controller.FRAME_START_SIZE/2, 600, 100, 50);
    	add(button, BorderLayout.SOUTH);
	}

        //run the simulation
	public void start() {
		for(int i = 0; i < 5000; i++) {
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			drawPanel.update(model.getX(), model.getY(), model.getDirect());
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Controller c = new Controller();
				Timer t = new Timer(c.DRAW_DELAY, c.drawAction);
				t.start();
			}
		});
	}
}