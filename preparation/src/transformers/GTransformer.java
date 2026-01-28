package transformers;

import java.awt.Graphics2D;
import shapes.GShape;

public abstract class GTransformer {

	protected GShape shape;
	protected int px, py;
	
	public GTransformer(GShape shape) {
		this.shape = shape;
		this.px = 0;
		this.py = 0;
	}
	
	public abstract void start(Graphics2D graphics, int x, int y);
	public abstract void drag(Graphics2D graphics, int x, int y);
	public abstract void finish(Graphics2D graphics, int x, int y);
	public abstract void addPoint(Graphics2D graphics, int x, int y);
}
