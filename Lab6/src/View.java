
/** 
 * 	
 * 
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

	private int frameWidth = 800;
	private int frameHeight = 800;
	private int imgWidth = 165;
	private int imgHeight = 165;

	private BufferedImage[][] animations;

	private final int frameCount = 10;
	private final int directionCount = 8;
	private int picNum = 0;

	private int x;
	private int y;
	private int dir;
	JButton button;

	/**
	 * View() Constructor Initializes a new frame to view the animation. Loads orc
	 * images into a BufferedImage array.
	 */
	public View() {
		
		 JFrame frame = new JFrame(); frame.getContentPane().add(this);
		 frame.setBackground(Color.gray);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setSize(frameWidth, frameHeight); frame.setVisible(true);
		 JButton button = new JButton("Start/Stop");
		 

		animations = new BufferedImage[directionCount][frameCount];
		BufferedImage[] indexArray = new BufferedImage[directionCount];

		// Fills an array with the file paths for 8 different orc images
		String[] directionArray = { "southeast", "east", "north", "northeast", "northwest", "south", "southwest",
				"west" };

		for (int i = 0; i < directionArray.length; i++) {
			indexArray[i] = createImage("src/orc_animation/orc_forward_" + directionArray[i] + ".png");
		}

		for (int i = 0; i < directionCount; i++) {
			for (int j = 0; j < frameCount; j++) {
				animations[i][j] = indexArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
			}
		}
		button.setBounds(Controller.FRAME_START_SIZE/2, 600, 100, 50);
    	add(button, BorderLayout.SOUTH);

	}
	

	private BufferedImage createImage(String name) {

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(name));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;
		g.drawImage(animations[this.dir][picNum], this.x, this.y, Color.gray, this);

	}

	public Dimension getPreferredSize() {
		return new Dimension(Controller.FRAME_START_SIZE, Controller.FRAME_START_SIZE);

	}

	/**
	 * Updates the position of the orc and repaints the frame with the orc's new
	 * position
	 * 
	 * @param x, the x coordinate of the orc
	 * @param y, the y coordinate of the orc
	 * @param d, the direction the orc is moving
	 */
	public void update(int x, int y, int d) {

		this.x = x;
		this.y = y;
		this.dir = d;
		System.out.printf("Model x = %d",this.x);
		System.out.printf("Model y = %d",this.y);
		try {
			this.setBackground(Color.gray);
			this.repaint();
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public int getImageWidth() {
		return imgWidth;
	}

	public int getImageHeight() {
		return imgHeight;
	}

}

