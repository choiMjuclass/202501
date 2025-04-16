package shapes;

import java.awt.geom.Rectangle2D;

public class GRectangle extends GShape {
	private Rectangle2D rectangle;
	private int px, py;
	
	public GRectangle() {
		super(new Rectangle2D.Float(0, 0, 0, 0), EDrawingType.e2P);
		this.rectangle = (Rectangle2D) this.shape;
	}	
	public void setPoint(int x, int y) {
		px = x;
		py = y;
	}
	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	public void resize(int x, int y) {
		double w = x - px;
		double h = y - py;
		this.rectangle.setFrame(px, py, w, h);
	}
	public void move(int x, int y) {
		double ox = rectangle.getX() + x - px;
		double oy = rectangle.getY() + y - py;
		this.rectangle.setFrame(ox, oy, rectangle.getWidth(), rectangle.getHeight());
		px = x;
		py = y;
	}
}
