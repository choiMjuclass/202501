package transformers;

import java.awt.Graphics2D;
import shapes.GShape;

public class GMover extends GTransformer {

	private GShape shape;
	private int px, py;
	
	public GMover(GShape shape) {
		super(shape);
		this.shape = shape;
	}
	@Override
	public void start(Graphics2D graphics, int x, int y) {
		this.px = x;
		this.py = y;
	}
	@Override
	public void drag(Graphics2D graphics, int x, int y) {
		// TODO Auto-generated method stub
		int dx = x - px;
		int dy = y - py;		
		
		this.shape.getAffineTransform().translate(dx, dy);
		
		this.px = x;
		this.py = y;	
	}
	@Override
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void addPoint(Graphics2D graphics, int x, int y) {
	}
}
