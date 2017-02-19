import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestPane extends JPanel {
    private ArrayList<DefaultBox> boxes;
    private int width;
    private int height;
    private int timerTickCount = 0;
    private JFrame firstGameFrame = null;
    
    public TestPane(int width, int height, JFrame firstGameFrame) {
    	this.width = width;
    	this.height = height;
    	this.firstGameFrame = firstGameFrame;
    	
        boxes = new ArrayList<DefaultBox>();
        for (int index = 0; index < 1000; index++) {
            boxes.add(new DefaultBox(Color.RED, new Dimension(10, 10)));
        }

        Timer timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTickCount ++;
				int numberOfBoxesToShow = Math.min(boxes.size()-1, (timerTickCount));
				for (DefaultBox box : boxes.subList(0, numberOfBoxesToShow)) {
					
					
					int myXPosition = 400;
					int myYPosition = 300;
					int nextXPosition = GetNextXPosition(box.getCurrentX(), width, myXPosition);
					int nextYPosition = GetNextYPosition(box.getCurrentY(), height, myYPosition);
					
					if (CollisionDetect(box.getCurrentX(), myXPosition, box.getCurrentY(), myYPosition)){
						firstGameFrame.dispatchEvent(new WindowEvent(firstGameFrame, WindowEvent.WINDOW_CLOSING));
						//System.out.println("You lose.");	
					}
					
                    box.update(nextXPosition, nextYPosition);
					
                }
                repaint();
			}
        });
        timer.start();
    }
    
    private boolean CollisionDetect(int currentX, int myXPosition, int currentY, int myYPosition){
    	if (Math.abs(currentX-myXPosition) <= 10 && Math.abs(currentY-myYPosition) <= 10){
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
        for (DefaultBox box : boxes) {
            Graphics2D g2d = (Graphics2D) g.create();
            box.paint(g2d);
            g2d.dispose();
        }
    }

}