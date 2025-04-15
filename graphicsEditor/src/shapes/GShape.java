package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class GShape {
	protected Shape shape;
	
	public abstract void setPoint(int x, int y);
	public abstract void resize(int x, int y);
	public abstract void move(int x, int y);

	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
	public boolean contains(int x, int y) {
		return shape.contains(x, y);
	}
}
