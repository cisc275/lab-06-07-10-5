import javax.swing.Action;
import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller extends JFrame {

	private Model model;
	private View view;
    Action drawAction;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}
	
	
        //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++)
		{
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect());
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	setSize(model.getHeight(), model.getWidth());
	    	setVisible(true);
	    	pack();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Animation4Thread a = new Animation4Thread();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});
	}
}
