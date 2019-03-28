/** 
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 * detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model {
	private int frameWidth;
    private int frameHeight;
    private int imgWidth;
    private int imgHeight;
    
    private int xloc = 0;
    private int yloc = 0;
    
    private int xVector = 1; // multiplier vector (used to determine the x-position)
    private int yVector = 1; // multiplier vector (used to determine the y-position)
    
    private final int xIncr = 8; // determines the speed at which the orc moves in the x-direction
    private final int yIncr = 2; // determines the speed at which the orc moves in the y-direction
    
    private final int NE = 3; // numerical value for the North East direction
    private final int NW = 4; // numerical value for the North West direction
    private final int SE = 0; // numerical value for the South East direction
    private final int SW = 6; // numerical value for the South West direction
    
    private int direction;
    
    public Model(int w, int h, int iw, int ih) {
    	this.frameWidth = w;
    	this.frameHeight = h;
    	this.imgWidth = iw;
    	this.imgHeight = ih;
    }
    
    /**
     * Updates the location and direction of the orc
     * Checks where the orc is on the screen and changes direction when it nears any edge
     */
    public void updateLocationAndDirection() {
    	xloc+=(xIncr*xVector); // updates the x-direction
    	yloc+=(yIncr*yVector); // updates the y-direction
    	
    	// Updates the x vectors when the orc moves off the screen
    	if (xloc + imgWidth >= frameWidth) {
			xVector = -1; // orc is moving off the screen in the right direction; start moving left
		} else if (xloc <= 0) {
			xVector = 1; // orc is moving off the screen in the left direction; start moving right
		}
    	
    	// Updates the x vectors when the orc moves off the screen
    	if (yloc + imgHeight >= frameHeight) {
			yVector = -1; // orc is moving off screen in the up direction; start moving down
		} else if (yloc + (frameHeight / 10) <= 0) {
			yVector = 1; // orc is moving off screen in the down direction; start moving up
		}
    	
    	// Determines the direction the orc is moving based using the x and y vectors
    	if (xVector == 1 && yVector == 1) {
    		direction = SE;
    	} else if (xVector == -1 && yVector == -1) {
    		direction = NW;
    	} else if (xVector == 1 && yVector == -1) {
    		direction = NE;
    	} else if (xVector == -1 && yVector == 1) {
    		direction = SW;
    	}
    }
    
    public int getX() {
		return xloc;
	}
    
    public int getY() {
		return yloc;
	}
    
    public void setX(int x) {
    	xloc = x;
    }
    
    public void setY(int y) {
    	yloc = y;
    }
    
    public int getXIncr() {
    	return xIncr;
    }
    
    public int getYIncr() {
    	return yIncr;
    }
    
    public int getXVector() {
    	return xVector;
    }
    
    public int getYVector() {
    	return yVector;
    }
    
    public int getDirect() {
    	return direction;
    }
}