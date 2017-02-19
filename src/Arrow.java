import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arrow {
    private Color color;
    private Rectangle bounds;
    
    public Arrow(Color color, Dimension size) {
    	
    	
        this.color = color;
        int rand = (int)(Math.random()*4);
        switch (rand){
        	case 1: bounds = new Rectangle(new Point(0,StartPointY()), size);
        		break;
        	case 2: bounds = new Rectangle(new Point(StartPointX(),0), size);
        		break;
        	case 3: bounds = new Rectangle(new Point(600,StartPointY()), size);
    			break;
    		default: bounds = new Rectangle(new Point(StartPointX(),500), size);
				break;
        	
        }
    }
    
    public int StartPointX(){
    	int startPointX = (int)(Math.random()*600);
    	return startPointX;
    }
    
    public int StartPointY(){
    	int startPointY = (int)(Math.random()*500);
    	return startPointY;
    }
    

    public int getCurrentX(){
    	return bounds.x;
    }
    
    public int getCurrentY(){
    	return bounds.y;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(bounds);
    }

	public void update(int targetX, int targetY) {
		bounds.x = targetX;
		bounds.y = targetY;
	}

}