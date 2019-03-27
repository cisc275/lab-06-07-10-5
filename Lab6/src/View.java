
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
	
	private String movement;

	private int frameWidth = 800;
	private int frameHeight = 800;
	private int imgWidth = 165;
	private int imgHeight = 165;

	private BufferedImage[][] animations;
	
	// Fills an array with the file paths for 8 different orc images
	private String[] directionArray = {"southeast", "east", "north", "northeast", "northwest", "south", "southwest", "west"};
	private BufferedImage[] forwardArray;
	private BufferedImage[] fireArray;
	private BufferedImage[] jumpArray;
	private BufferedImage[] dieArray;

	public int count;
	
	private final int FORWARD_SUBCOUNT = 10; // reflects the number of sub-images for the forwardArray[]
	private final int DIE_SUBCOUNT = 7; // reflects the number of sub-images for the dieArray[]
	private final int FIRE_SUBCOUNT = 4; // reflects the number of sub-images for the fireArray[]
	private final int JUMP_SUBCOUNT = 8; // reflects the number of sub-images for the jumpArray[]
	
	
	private final int DIRECTION_COUNT = 8; // reflects the size of directionArray[]
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
		movement = "_forward_";
		count = FORWARD_SUBCOUNT;
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		
		button = new JButton("Start/Stop");
		button.setBackground(Color.RED);
		button.setOpaque(true);
		//button.setBounds(Controller.FRAME_START_SIZE / 2, 600, 100, 50);
		this.add(button);
		button.setActionCommand("Pressed");

		animations = new BufferedImage[DIRECTION_COUNT][count];
		forwardArray = new BufferedImage[DIRECTION_COUNT];
		
		fillImageArrays(forwardArray, "_forward_", DIRECTION_COUNT);
		fillImageArrays(fireArray, "_fire_", DIRECTION_COUNT);
		fillImageArrays(jumpArray, "_jump_", DIRECTION_COUNT);
		fillImageArrays(jumpArray, "_die_", DIE_SUBCOUNT);

		/*
		for (int i = 0; i < directionArray.length; i++) {
			forwardArray[i] = createImage("src/orc_animation/orc" + movement + directionArray[i] + ".png");
		}*/

		for (int i = 0; i < directionArray.length; i++) {
			for (int j = 0; j < FORWARD_SUBCOUNT; j++) {
				animations[i][j] = forwardArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
				//System.out.println("second loop");
			}
		}
		frame.setVisible(true);
		//button.setVisible(true);
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);
	}
	
	/**
	 * Helper method used to fill the arrays based on the orc movement (i.e. forward, fire, jump, etc)
	 * @param array the array to be filled with images
	 * @param movement the direction that corresponds to the array type
	 * @param count the number of sub-images for the image type
	 */
	private void fillImageArrays(BufferedImage[] array, String movement, int count) {
		for (int i = 0; i < count; i++) {
			array[i] = createImage("src/orc_animation/orc" + movement + directionArray[i] + ".png");
		}
	}
	
	public void updateButton(Controller c)	{
		button.addActionListener(c);
	}

	public BufferedImage createImage(String name) {

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
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.gray);
		picNum = (picNum + 1) % count;
		if (movement.equals("_forward_")) {
			g.drawImage(animations[this.dir][picNum], this.x, this.y, Color.gray, this);
		}
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
//		System.out.printf("Model x = %d \n", this.x);
//		System.out.printf("Model y = %d \n", this.y);
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
	
	public void setMovement(String movement) {
		this.movement = movement;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getFRAME_COUNT() {
		return this.FORWARD_SUBCOUNT;
	}
	
	public int getDIE_COUNT() {
		return this.DIE_SUBCOUNT;
	}
	
	public int getFIRE_COUNT() {
		return this.FIRE_SUBCOUNT;
	}
	
	public int getJUMP_COUNT() {
		return this.JUMP_SUBCOUNT;
	}
	
	public String getMovement() {
		return this.movement;
	}

	public String getDirectionOrcImage(int index) {
		return directionArray[index];
	}
}
