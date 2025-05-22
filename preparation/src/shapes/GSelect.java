package shapes;

import java.awt.geom.Rectangle2D;
import java.util.Vector;

public class GSelect extends GShape {
	
	private Rectangle2D rectangle;	
	private Vector<GShape> shapes;
	
	public GSelect() {
		super(new Rectangle2D.Float(0, 0, 0, 0));
		this.rectangle = (Rectangle2D) this.getShape();
		this.shapes = new Vector<GShape>();
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.rectangle.setFrame(x, y, 0, 0);
	}
	@Override
	public void dragPoint(int x, int y) {
		double ox = rectangle.getX();
		double oy = rectangle.getY();
		double w = x - ox;
		double h = y - oy;
		this.rectangle.setFrame(ox, oy, w, h);
	}
	@Override
	public void addPoint(int x, int y) {
	}
	
	public Vector<GShape> getShapes() {
		return this.shapes;
	}
	public void add(GShape shape) {
		this.shapes.add(shape);
	}
}
