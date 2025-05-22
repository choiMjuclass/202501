package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public class GSelector extends GTransformer {

	public GSelector(GShape shape) {
		super(shape, shape.getEDrawingType());
	}
	
	public void start(Graphics2D graphics, int x, int y) {
		this.getShape().startDrawing(x, y);
	}
	
	public void drag(Graphics2D graphics, int x, int y) {
		this.getShape().drawing(x, y);
	}
	
	public void finish(Graphics2D graphics, int x, int y) {
	}
	
	@Override
	public void add(Graphics2D graphics, int x, int y) {
	}

}
