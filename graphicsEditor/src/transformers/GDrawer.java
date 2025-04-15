package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GDrawer extends GTransformer {

	public GDrawer(GShape shape) {
		super(shape);
	}
	public void start(Graphics2D graphics, int x, int y) {
		shape.setPoint(x, y);
		shape.resize(x, y);
	}
	public void drag(Graphics2D graphics, int x, int y) {
		graphics.setXORMode(graphics.getBackground());
		shape.draw(graphics);
		shape.resize(x, y);
		shape.draw(graphics);		
	}
	public GShape finish(Graphics2D graphics, int x, int y) {
		return shape;
	}
}
