import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class DefaultBox implements Box {

    private Color color;
    private Rectangle bounds;

    public DefaultBox(Color color, Dimension size) {
        this.color = color;
        bounds = new Rectangle(new Point(0, 0), size);
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(bounds);
    }

	@Override
	public void update(int targetX, int targetY) {
		bounds.x = targetX;
		bounds.y = targetY;
	}

}