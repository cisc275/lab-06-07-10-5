//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import javax.imageio.ImageIO;
//import javax.swing.AbstractAction;
//import javax.swing.Action;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//import javax.swing.JButton;
//
//public class Animation4Thread extends JFrame {
//
//	final int frameCount = 10;
//    BufferedImage[] pics;
//    int xloc = 100;
//    int yloc = 100;
//    final int xIncr = 1;
//    final int yIncr = 1;
//    final int picSize = 165;
//    final int frameStartSize = 800;
//    final int drawDelay = 30; //msec
//    boolean flag = true;
//    
//    /*
//    //controller
//    DrawPanel drawPanel = new DrawPanel();
//    Action drawAction;
//    */
//
//    //controller
//    /*
//    public Animation4Thread() {
//    	drawAction = new AbstractAction(){
//    		public void actionPerformed(ActionEvent e){
//    			if ("Pressed".equals(e.getActionCommand())) {
//    				flag = !flag;
//    			}
//    			else if (flag == true){
//    				drawPanel.repaint();
//    			}
//    		}
//    	};
//    	
//    	add(drawPanel);
//    	*/
//    	
//    	//we already have this code
//    /*
//    	BufferedImage img = createImage();
//    	pics = new BufferedImage[frameCount];//get this dynamically
//    	for(int i = 0; i < frameCount; i++)
//    		pics[i] = img.getSubimage(picSize*i, 0, picSize, picSize);
//    		*/
//        //In controller
//    	/*
//    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	setSize(frameStartSize, frameStartSize);
//    	setVisible(true);
//    	pack();
//    	*/
//    }
//	
//    //---------------------------------------------------------------------------------------------------------
//    @SuppressWarnings("serial")
//	private class DrawPanel extends JPanel {
//    	int picNum = 0;
//
//		protected void paintComponent(Graphics g) {
//			super.paintComponent(g);
//			g.setColor(Color.gray);
//	    	picNum = (picNum + 1) % frameCount;
//	    	g.drawImage(pics[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
//	    	setBackground(Color.gray);
//	    	
//	    	setLayout(null);
//	    	JButton button = new JButton("Start/Stop");
//	    	button.setActionCommand("Pressed");
//	    	button.addActionListener(drawAction);
//	    	button.setBounds(frameStartSize/2, 600, 75, 50);
//	    	add(button, BorderLayout.SOUTH);
//		}
//
//		public Dimension getPreferredSize() {
//			return new Dimension(frameStartSize, frameStartSize);
//		}
//	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable(){
//			public void run(){
//				Animation4Thread a = new Animation4Thread();
//				Timer t = new Timer(a.drawDelay, a.drawAction);
//				t.start();
//			}
//		});
//	}
//    
//    //Read image from file and return
//    private BufferedImage createImage(){
//    	BufferedImage bufferedImage;
//    	try {
//    		bufferedImage = ImageIO.read(new File("src/orc_animation/orc_forward_southeast.png"));
//    		return bufferedImage;
//    	} catch (IOException e) {
//    		e.printStackTrace();
//    	}
//    	return null;
//    }
//}
//
