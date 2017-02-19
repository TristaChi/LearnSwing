import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestPane extends JPanel implements MouseMotionListener {
	final double pre = System.currentTimeMillis();
    private ArrayList<Arrow> arrowes;
    private int timerTickCount = 0;
    private Arrow myMainArrow;
    
    public TestPane(int width, int height, JFrame firstGameFrame) {  
    	
    	this.addMouseMotionListener(this); 	
		myMainArrow = new Arrow(Color.BLUE, new Dimension(20,20));
    	
    	arrowes = new ArrayList<Arrow>();
        for (int index = 0; index < 1000; index++) {
        	arrowes.add(new Arrow(Color.RED, new Dimension(10, 10)));
        }

        Timer timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTickCount ++;
				int numberOfArrowesToShow = Math.min(arrowes.size()-1, (timerTickCount));
				for (Arrow arrow : arrowes.subList(0, numberOfArrowesToShow/3)) {
					
					int myXPosition = myMainArrow.getCurrentX();
					int myYPosition = myMainArrow.getCurrentY();
					int nextXPosition = GetNextXPosition(arrow.getCurrentX(), width, myXPosition);
					int nextYPosition = GetNextYPosition(arrow.getCurrentY(), height, myYPosition);
					
					if (CollisionDetect(arrow.getCurrentX(), myXPosition, arrow.getCurrentY(), myYPosition)){
						firstGameFrame.dispatchEvent(new WindowEvent(firstGameFrame, WindowEvent.WINDOW_CLOSING));
						//System.out.println("You lose.");	
					}
					
                    arrow.update(nextXPosition, nextYPosition);
                }
				
                repaint();
			}
        });
        timer.start();
    }
    
    private boolean CollisionDetect(int currentX, int myXPosition, int currentY, int myYPosition){
    	if (currentX-myXPosition <= 20 && myXPosition-currentX <= 10 && currentY-myYPosition <= 20 && myYPosition-currentY <= 10){
    		double post= System.currentTimeMillis();
    		System.out.println("You have last: "+ (double)(post-pre)/1000.0+ "seconds, Great Job!");
    		System.out.printf("The score you get is: %.2f", (Math.pow((double)(post-pre)/1000,2))*100);
    		return true;
    	}
    	else{
    		return false;
    		}
    }
    
    private int GetNextXPosition(int currentX, int width, int myXPosition){
    	if (currentX > width){
    		currentX -= width;
    	}
    	if((myXPosition - currentX)>0){
    		return currentX+8;
    	}
    	if((myXPosition - currentX)<0){
    		return currentX-8;
    	}
    	else{
    		return currentX;
    	}
    }
    
    private int GetNextYPosition(int currentY, int height, int myYPosition){
    	if (currentY > height){
    		currentY -= height;
    	}
    	if((myYPosition - currentY)<0){
    		return currentY-8;
    	}
    	if((myYPosition - currentY)>0){
    		return currentY+8;
    	}
    	else{
    		return currentY;
    	}
    	
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d;
        
        for (Arrow arrow : arrowes) {
        	g2d = (Graphics2D) g.create();
            arrow.paint(g2d);
            g2d.dispose();
        }
      
        g2d = (Graphics2D) g.create();
        myMainArrow.paint(g2d);
        g2d.dispose();
        
    }
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int mousex=e.getX();
		int mousey=e.getY();
		myMainArrow.update(mousex, mousey);
	}
   
	@Override
	public void mouseDragged(MouseEvent e){	
	}
}