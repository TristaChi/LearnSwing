import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TestPane extends JPanel {
    private ArrayList<DefaultBox> boxes;
    private int width;
    private int height;

    private int timerTickCount = 0;
    
    public TestPane(int width, int height) {
    	this.width = width;
    	this.height = height;
    	
        boxes = new ArrayList<DefaultBox>();
        for (int index = 0; index < 100; index++) {
            boxes.add(new DefaultBox(Color.RED, new Dimension(10, 10)));
        }

        Timer timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerTickCount ++;
				int numberOfBoxesToShow = Math.min(boxes.size()-1, (timerTickCount)/10);
				for (DefaultBox box : boxes.subList(0, numberOfBoxesToShow)) {
					int nextXPosition = GetNextXPosition(box.getCurrentX(), width);
					int nextYPosition = GetNextYPosition(box.getCurrentY(), height);
					
                    box.update(nextXPosition, nextYPosition);
                }
                repaint();
			}
        });
        timer.start();
    }
    
    private int GetNextXPosition(int currentX, int width){
    	if (currentX > width){
    		currentX -= width;
    	}
    	return currentX+5;
    }
    
    private int GetNextYPosition(int currentY, int height){
    	if (currentY > height){
    		currentY -= height;
    	}
    	
    	return currentY+5;
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