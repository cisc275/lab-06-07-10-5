
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

	private final int FRAME_COUNT = 10;
	private final int DIE_COUNT = 4;
	private final int FIRE_COUNT = 8;
	private final int JUMP_COUNT = 8;
	
	
	private final int DIRECTION_COUNT = 24;
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
    	

		animations = new BufferedImage[DIRECTION_COUNT][FRAME_COUNT];
		BufferedImage[] indexArray = new BufferedImage[DIRECTION_COUNT];

		// Fills an array with the file paths for 8 different orc images
		String[] directionArray = { "_forward_southeast", "_forward_east", "_forward_north", "_forward_northeast", "_forward_northwest", "_forward_south", "_forward_southwest",
				"_forward_west", "_die_east", "_die_north", "_die_south", "_die_west", "_fire_east", "_fire_north", "_fire_northeast", "_fire_northwest", 
				"_fire_south", "_fire_southeast", "_fire_southwest", "_fire_west"};

		for (int i = 0; i < directionArray.length; i++) {
			indexArray[i] = createImage("src/orc_animation/orc" + directionArray[i] + ".png");
		}

		for (int i = 0; i < directionArray.length; i++) {
			for (int j = 0; j < FRAME_COUNT; j++) {
				animations[i][j] = indexArray[i].getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
			}
		}
		frame.setVisible(true);
		//button.setVisible(true);

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
		picNum = (picNum + 1) % FRAME_COUNT;
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

}
