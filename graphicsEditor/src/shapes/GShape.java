package shapes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import transformers.GTransformer.EDrawingType;

public abstract class GShape {
	private final static int ANCHOR_WIDTH = 10;
	private final static int ANCHOR_HEIGHT = 10;

	public enum EAnchor {
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eRR(new Cursor(Cursor.HAND_CURSOR)),
		eMM(new Cursor(Cursor.CROSSHAIR_CURSOR));
		
		private Cursor cursor;
		private EAnchor(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() {
			return this.cursor;
		}
	}	
	protected Shape shape;
	private EDrawingType eDrawingType;
	protected EAnchor eSelectedAnchor;
	private boolean bSelected;
	private Ellipse2D anchors[];
	
	public GShape(Shape shape, EDrawingType eDrawingType) {
		this.shape = shape;
		this.eDrawingType = eDrawingType;
		this.eSelectedAnchor = null;
		this.bSelected = false;
		
		this.anchors =  new Ellipse2D[EAnchor.values().length-1];
		for (int i=0; i<this.anchors.length; i++) {
			this.anchors[i] = new Ellipse2D.Double(0, 0, 0, 0);
		}
	}
	
	public Shape getShape() {
		return this.shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public EDrawingType getEDrawingType() {
		return this.eDrawingType;
	}
	public EAnchor getSelectedAnchor() {
		return this.eSelectedAnchor;
	}
	public boolean isSelected() {
		return this.bSelected;
	}
	public void setSelected(boolean bSelected) {
		this.bSelected = bSelected;
	}
	public Ellipse2D[] getAnchors() {
		return this.anchors;
	}
	
	public EAnchor contains(int x, int y) {
		this.eSelectedAnchor = null;
		for (int i=0; i<EAnchor.values().length-1; i++) {
			if (this.anchors[i].contains(x, y)) {
				this.eSelectedAnchor = EAnchor.values()[i];
				return this.eSelectedAnchor;
			}				
		}
		if (this.shape.contains(x, y)) {
			this.eSelectedAnchor = EAnchor.eMM;
			return this.eSelectedAnchor;
		}
		return this.eSelectedAnchor;
	}
	public boolean contains(Rectangle2D rectangle) {
		return this.shape.contains(rectangle);
	}
	public Rectangle2D getBounds() {
		return this.shape.getBounds2D();
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
		if (this.isSelected()) {
			this.drawAnchors(graphics2D, shape.getBounds2D());
		}
	}
	
	public void drawAnchors(Graphics2D graphics, Rectangle2D bounds) {
		double x = bounds.getX()-ANCHOR_WIDTH/2;
		double y = bounds.getY()-ANCHOR_HEIGHT/2;
		double w = bounds.getWidth();
		double h = bounds.getHeight();
		
		this.anchors[EAnchor.eEE.ordinal()].setFrame(x+w, y+h/2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eWW.ordinal()].setFrame(x, y+h/2, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eSS.ordinal()].setFrame(x+w/2, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eNN.ordinal()].setFrame(x+w/2, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eNW.ordinal()].setFrame(x, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eNE.ordinal()].setFrame(x+w, y, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eSW.ordinal()].setFrame(x, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eSE.ordinal()].setFrame(x+w, y+h, ANCHOR_WIDTH, ANCHOR_HEIGHT);
		this.anchors[EAnchor.eRR.ordinal()].setFrame(x+w/2, y-30, ANCHOR_WIDTH, ANCHOR_HEIGHT);
	
		for(Ellipse2D anchor: this.anchors) {
			Color color = graphics.getColor();
			graphics.setColor(graphics.getBackground());
			graphics.fill(anchor);
			graphics.setColor(color);
			graphics.draw(anchor);
		}
	
	}
	
//	private void test() {
//		Rectangle2D bounds = this.shape.getBounds2D();
//		for (EAnchor eAnchor: EAnchor.values()) {
//			switch(eAnchor) {
//			case EE:
//				break;
//			case WW:
//				break;
//			case SS:
//				break;
//			case NN:
//				break;
//			case NE:
//				break;
//			case SE:
//				break;
//			case NW:
//				break;
//			case SW:
//				break;
//			case RR:
//				break;
//			default:
//				break;
//			}
//		}
//	}
	
	public abstract void startDrawing(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void drawing(int x, int y);
	public abstract void startMoving(int x, int y);
	public abstract void moving(int x, int y);
	
	public abstract void add(GShape shape);
}
