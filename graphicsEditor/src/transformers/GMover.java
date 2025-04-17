package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GMover extends GTransformer {

	public GMover(GShape shape) {
		super(shape, EDrawingType.e2P);
	}
	public void start(Graphics2D graphics, int x, int y) {
		this.getShape().startMoving(x, y);
	}
	public void drag(Graphics2D graphics, int x, int y) {
		graphics.setXORMode(graphics.getBackground());
		this.getShape().draw(graphics);
		this.getShape().moving(x, y);
		this.getShape().draw(graphics);		
	}
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void add(Graphics2D graphics, int x, int y) {
	}
}
