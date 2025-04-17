package shapes;

import java.awt.geom.Rectangle2D;

import transformers.GTransformer.EDrawingType;

public class GRectangle extends GShape {
	private Rectangle2D rectangle;
	private int px, py;
	
	public GRectangle() {
		super(new Rectangle2D.Float(0, 0, 0, 0), EDrawingType.e2P);
		this.rectangle = (Rectangle2D) this.getShape();
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
		// TODO Auto-generated method stub
		
	}
}
