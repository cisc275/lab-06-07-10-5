/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
	private int direction;
    private int xloc = 0;
    private int yloc = 0;
    private int yMult = 1;
    private int xMult = 1;
    private final int xIncr = 8;
    private final int yIncr = 2;
    private int frameWidth = 500;
    private int frameHeight = 300;
    private int imageWidth = 165;
    private int imageHeight = 165;
    private int SOUTHEAST = 0;
    private int NORTHWEST = 4;
    private int SOUTHWEST = 6;
    private int NORTHEAST = 3;
	
	public Model(int frameWidth, int frameHeight, int imageHeight, int imageWidth) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}
	
	public void updateLocationAndDirection() {
		//collisions
		xloc+=(xMult*xIncr);
		yloc+=(yMult*yIncr);
	
		if (xloc + imageWidth >= getWidth())	{
    		xMult = -1;
    	}
    	if (xloc <= 0)	{
    		xMult = 1;
    	}
    	if (yloc + imageHeight >= getHeight())	{
    		yMult = -1;
    	}
    	if (yloc <= 0)	{
    		yMult = 1;
    	}
    	if (yMult == 1 && xMult == 1)	{
    		this.direction = SOUTHEAST;
    	} 
    	if (yMult == -1 && xMult == -1)	{
    		this.direction = NORTHWEST;
    	}
    	if (yMult == 1 && xMult == -1)	{
    		this.direction = SOUTHWEST;
    	}
    	if (yMult == -1 && xMult == 1)	{
    		this.direction = NORTHEAST;
    	}
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
	
	public int getX()	{
		return xloc;
	}
	
	public int getY()	{
		return yloc;
	}

	public int getDirect()	{
		return direction;
	}
}