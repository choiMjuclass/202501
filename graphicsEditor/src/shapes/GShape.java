package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class GShape {
	protected Shape shape;
	public GShape() {		
	}
	
	public abstract void setPoint(int x, int y);
	public abstract void dragPoint(int x, int y);

	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
}
