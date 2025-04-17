package transformers;

import java.awt.Graphics2D;

import shapes.GShape;

public abstract class GTransformer {
	public enum EDrawingType {
		e2P,
		eNP
	}
	private GShape shape;
	private EDrawingType eDrawingType;
	
	public GShape getShape() {
		return this.shape;
	}
	public EDrawingType getEDrawingType() {
		return this.eDrawingType;
	}
	public GTransformer(GShape shape, EDrawingType eDrawingType) {
		this.shape = shape;
		this.eDrawingType = eDrawingType;
	}
	public abstract void start(Graphics2D graphics, int x, int y);
	public abstract void add(Graphics2D graphics, int x, int y);
	public abstract void drag(Graphics2D graphics, int x, int y);
	public abstract void finish(Graphics2D graphics, int x, int y);
}
