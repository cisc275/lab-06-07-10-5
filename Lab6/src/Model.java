/** 
 * 	
 * 
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
    private int xM = 1;
    private int yM = 1;
    private int xloc = 0;
    private int yloc = 0;
    
    private final int xIncr = 8;
    private final int yIncr = 2;
    
    private final int NE = 3;
    private final int NW = 4;
    private final int SE = 0;
    private final int SW = 6;
    
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
    	xloc+=(xIncr*xM);
    	yloc+=(yIncr*yM);
    	
    	if (xloc + imgWidth >= frameWidth) {
			xM = -1;
		} else if (xloc + (frameHeight / 10) <= 0) {
			xM = 1;
		}
    	
    	if (yloc + imgHeight >= frameHeight) {
			yM = -1;
		} else if (yloc + (frameHeight / 10) <= 0) {
			yM = 1;
		}
    	
    	if (xM == 1 && yM == 1) {
    		direction = SE;
    	}
    	
    	if (xM == -1 && yM == -1) {
    		direction = NW;
    	}
    	if (xM == 1 && yM == -1) {
    		direction = NE;
    	}
    	
    	if (xM == -1 && yM == 1){
    		direction = SW;
    	}
    }
    
    public int getX() {
    	System.out.printf("Model x = %d",xloc);
		return xloc;
	}
    
    public int getY() {
    	System.out.printf("Model y = %d",yloc);
		return yloc;
		
	}
    
    public int getXIncr() {
    	return xIncr;
    }
    
    public int getYIncr() {
    	return yIncr;
    }
    
    public int getXM() {
    	return xM;
    }
    
    public int getYM() {
    	return yM;
    }
    
    public int getDirect() {
    	return direction;
    }
    
    public void setX(int x) {
    	xloc = x;
    }
    
    public void setY(int y) {
    	yloc = y;
    }
    
    
}