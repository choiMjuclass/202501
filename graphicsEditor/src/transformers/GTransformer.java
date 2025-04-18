package transformers;

import java.awt.Graphics2D;

import shapes.GRectangle;

public class GTransformer {

	private GRectangle rectangle;
	public void start(Graphics2D graphics, int x, int y) {
		rectangle = new GRectangle();
		rectangle.setPoint(x, y);
		rectangle.dragPoint(x, y);
	}
	public void drag(Graphics2D graphics, int x, int y) {
		rectangle.draw(graphics);
		rectangle.dragPoint(x, y);
		rectangle.draw(graphics);		
	}
	public GRectangle finish(Graphics2D graphics, int x, int y) {
		return rectangle;
	}
}
