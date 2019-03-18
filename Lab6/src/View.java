import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/



@SuppressWarnings("serial")
public class View extends JPanel {
	
	final int WalkingAnimations = 8;
    final int frameCount = 10;
    BufferedImage[] pics;
    BufferedImage[][] animations;
    int picNum = 0;
	private int imageHeight = 165;
	private int imageWidth = 165;
    private int frameWidth = 500;
    private int frameHeight = 300;
    private int orcx, orcy;
    private int orcDirect;
	
	public View()	{
    	animations = new BufferedImage[WalkingAnimations][frameCount];

		String[] directionArray = {"southeast", "east", "north", "northeast", "northwest", "south", "southwest", "west"};
    
    	pics = new BufferedImage[frameCount];
    	for(int i = 0; i < WalkingAnimations; i++)	{
    		pics[i] = createImage("src/orc_animation/orc_forward_" + directionArray[i] + ".png");
    		
    	}
    	for (int i = 0; i < WalkingAnimations; i++) {
    		for (int j = 0; j < frameCount; j++) {
    			animations[i][j] = pics[i].getSubimage(imageWidth*j, 0, imageWidth, imageHeight);
    		}
    	}
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    }  
	

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(animations[this.orcDirect][picNum], this.orcx, this.orcy, Color.gray, this);
	}
	public void update(int x, int y, int direction) {
		try {
			this.orcx = x;
			this.orcy = y;
			this.orcDirect = direction;
			this.setBackground(Color.gray);
			this.repaint();
			Thread.sleep(100);
		} catch (InterruptedException e)	{
			e.printStackTrace();
		}
	}

	//public static void main(String[] args) {
	//	Controller c = new Controller();
    //	c.start();
    //}
    
	
    private BufferedImage createImage(String fname){
    	BufferedImage bufferedImage;
    	
    	try {
    		bufferedImage = ImageIO.read(new File(fname));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }

	public int getHeight() {
		return frameHeight;
	}

	public int getWidth() {
		return frameWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getOrcx() {
		return orcx;
	}
	
	public int getOrcy() {
		return orcy;
	}

	public int getOrcDirect() {
		return orcDirect;
	}

	
}
