package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GDrawer extends GTransformer {

	public GDrawer(GShape shape) {
		super(shape, shape.getEDrawingType());
	}
	public void start(Graphics2D graphics, int x, int y) {
		this.getShape().startDrawing(x, y);
	}
	public void drag(Graphics2D graphics, int x, int y) {
		graphics.setXORMode(graphics.getBackground());
		this.getShape().draw(graphics);
		this.getShape().drawing(x, y);
		this.getShape().draw(graphics);		
	}
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void add(Graphics2D graphics, int x, int y) {
		// TODO Auto-generated method stub
		this.getShape().addPoint(x, y);
	}
}
