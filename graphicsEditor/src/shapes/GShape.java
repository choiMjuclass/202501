package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class GShape {	
	public enum EDrawingType {
		e2P,
		eNP
	}
	
	protected Shape shape;
	protected EDrawingType eDrawingType;
	
	public GShape(Shape shape, EDrawingType eDrawingType) {
		this.shape = shape;
		this.eDrawingType = eDrawingType;
	}
	
	public Shape getShape() {
		return this.shape;
	}
	public EDrawingType getEDrawingType() {
		return this.eDrawingType;
	}
	
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void resize(int x, int y);
	public abstract void move(int x, int y);

	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
	public boolean contains(int x, int y) {
		return shape.contains(x, y);
	}
}
