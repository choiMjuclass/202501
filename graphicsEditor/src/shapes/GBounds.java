package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import transformers.GTransformer.EDrawingType;

public class GBounds extends GShape {
	private Rectangle2D rectangle;
	private int px, py;
	
	private Vector<GShape> shapes;
	
	public GBounds() {
		super(new Rectangle2D.Float(0, 0, 0, 0), EDrawingType.e2P);
		this.rectangle = (Rectangle2D) this.getShape();
		this.shapes = new Vector<GShape>();
	}	
	public void startDrawing(int x, int y) {
		px = x;
		py = y;
	}
	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public void drawing(int x, int y) {
		double w = x - px;
		double h = y - py;
		this.rectangle.setFrame(px, py, w, h);
	}
	public void startMoving(int x, int y) {
		px = x;
		py = y;
	}
	public void moving(int x, int y) {
		double ox = rectangle.getX() + x - px;
		double oy = rectangle.getY() + y - py;
		this.rectangle.setFrame(ox, oy, rectangle.getWidth(), rectangle.getHeight());
		px = x;
		py = y;
	}
	@Override
	public void add(GShape shape) {
		this.shapes.add(shape);
	}
	
	public void drawAnchors(Graphics2D graphics, Rectangle2D bounds) {
	}
}
