package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GTransformer {

	private GShape shape;
	public GTransformer(GShape shape) {
		this.shape = shape;
	}
	public void start(Graphics2D graphics, int x, int y) {
		shape.setPoint(x, y);
		shape.dragPoint(x, y);
	}
	public void drag(Graphics2D graphics, int x, int y) {
		shape.draw(graphics);
		shape.dragPoint(x, y);
		shape.draw(graphics);		
	}
	public GShape finish(Graphics2D graphics, int x, int y) {
		return shape;
	}
}
