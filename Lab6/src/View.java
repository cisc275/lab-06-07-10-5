
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
	
	// Flags to represent the state of key presses
	public boolean isFire = false;
	public boolean isJumping = false;
	
	private String movement; // used when building the file path for the images (ex. "_fire_")

	private int frameWidth = 800;
	private int frameHeight = 800;
	private int imgWidth = 165;
	private int imgHeight = 165;

	private BufferedImage[][] animations;
	private BufferedImage[][] fire_animations;
	private BufferedImage[][] jump_animations;
	
	private int count;
	
	private final int FRAME_COUNT = 10;
	private final int FIRE_COUNT = 4;
	private final int JUMP_COUNT = 7;
	
	private final int DIRECTION_COUNT = 8;
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
		movement = "_forward_"; // default
		count = FRAME_COUNT; // default
		
		// Creates the frame and selects settings
		JFrame frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		
		// Creates the button
		button = new JButton("Start/Stop");
		this.add(button);
		button.setActionCommand("Pressed"); // action command used to track if button was pressed

		animations = new BufferedImage[DIRECTION_COUNT][count]; // 2d array containing the forward moving imagess
		fire_animations = new BufferedImage[DIRECTION_COUNT][FIRE_COUNT]; // 2d array containing the fire images
		jump_animations = new BufferedImage[DIRECTION_COUNT][JUMP_COUNT]; // 2d array containing the jump images

		BufferedImage[] indexArray = new BufferedImage[DIRECTION_COUNT];
		BufferedImage[] indexFireArray = new BufferedImage[DIRECTION_COUNT];
		BufferedImage[] indexJumpArray = new BufferedImage[DIRECTION_COUNT];

		// Fills an array with the file paths for 8 different orc images
		String[] directionArray = {"southeast", "east", "north", "northeast", "northwest", "south", "southwest", "west"};

		for (int i = 0; i < directionArray.length; i++) {
			indexArray[i] = createImage("src/orc_animation/orc" + movement + directionArray[i] + ".png");
			indexFireArray[i] = createImage("src/orc_animation/orc_fire_" + directionArray[i] + ".png");
			indexJumpArray[i] = createImage("src/orc_animation/orc_jump_" + directionArray[i] + ".png");
		}

		// fills in the 2D forward moving images array
		for (int i = 0; i < DIRECTION_COUNT; i++) {
			for (int j = 0; j < FRAME_COUNT; j++) {
				animations[i][j] = indexArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
			}
		}

		// fills in the 2D fire array
		for (int i = 0; i < DIRECTION_COUNT; i++) {
			for (int j = 0; j < FIRE_COUNT; j++) {
				fire_animations[i][j] = indexFireArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
			}
		}

		// fills up the 2D jump array
		for (int i = 0; i < DIRECTION_COUNT; i++) {
			for (int j = 0; j < JUMP_COUNT; j++) {
				jump_animations[i][j] = indexJumpArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
			}
		}

		frame.setVisible(true);
		
		//Allows key presses to work with JPanel
		this.setFocusable(true);

	}
	
	public void updateButton(Controller c)	{
		button.addActionListener(c);
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
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.gray);
		picNum = (picNum + 1) % count;
		
		if (isFire) {

			// if user clicked on f, start drawing the fire animation
			g.drawImage(fire_animations[this.dir][picNum % FIRE_COUNT], this.x, this.y, Color.gray, this);

		} else if (isJumping || (isJumping && isFire)) {

			// if user clicked on j, start drawing the jumping animation
			g.drawImage(jump_animations[this.dir][picNum % JUMP_COUNT], this.x, this.y, Color.gray, this);

			// after finishing 1 cycle of jumping animations, set the orc back to forward moving
			if (picNum % JUMP_COUNT == 0) {
				this.updateJump();
			}

		} else if (!isFire || !isJumping || (isFire && !isJumping)) {

			// else draw the foward moving animations
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
		return this.FRAME_COUNT;
	}
	
	public int getFIRE_COUNT() {
		return this.FIRE_COUNT;
	}
	
	public int getJUMP_COUNT() {
		return this.JUMP_COUNT;
	}
	
	public void updateFire() {
		this.isFire = !this.isFire;
	}
	
	public void updateJump() {
		this.isJumping = !this.isJumping;
	}

}
