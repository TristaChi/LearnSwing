import java.awt.Dimension;
import java.awt.Graphics2D;

public interface Box {
    public void update(int targetX, int targetY);
    public void paint(Graphics2D g2d);
}
