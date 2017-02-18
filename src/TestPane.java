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
    private ArrayList<Box> boxes;
   
    public TestPane() {
        boxes = new ArrayList<Box>();
        for (int index = 0; index < 100; index++) {
            boxes.add(new DefaultBox(Color.RED, new Dimension(10, 10)));
        }

        Timer timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Box box : boxes) {
					int x = (int)(Math.random() * 600);
					int y = (int)(Math.random() * 500);
                    box.update(x, y);
                }
                repaint();
			}
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Box box : boxes) {
            Graphics2D g2d = (Graphics2D) g.create();
            box.paint(g2d);
            g2d.dispose();
        }
    }

}