package shapes;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import transformers.GTransformer.EDrawingType;

public class GBounds extends GShape {
	private Rectangle2D rectangle;
	
	private Vector<GShape> shapes;
	private int px, py;
	
	public GBounds() {
		super(new Rectangle2D.Float(0, 0, 0, 0), EDrawingType.e2P);
		this.rectangle = (Rectangle2D) this.getShape();
		this.shapes = new Vector<GShape>();
	}	
	@Override
	public void startDrawing(int x, int y) {
		px = x;
		py = y;
	}
	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drawing(int x, int y) {
		double w = x - px;
		double h = y - py;
		this.rectangle.setFrame(px, py, w, h);
	}

	@Override
	public void add(GShape shape) {
		this.shapes.add(shape);
	}
	
	@Override
	public void drawAnchors(Graphics2D graphics, Rectangle2D bounds) {
	}
}
