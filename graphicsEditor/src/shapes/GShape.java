package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import global.GConstants.EAnchor;

public abstract class GShape {
	private final static int ANCHOR_W = 10;
	private final static int ANCHOR_H = 10;
	
	public enum EPoints {
		e2P,
		eNP
	}
	
	private Shape shape;
	private AffineTransform affineTransform;
	
	private Ellipse2D anchors[];
	private boolean bSelected;
	private EAnchor eSelectedAnchor;
	private int px, py;
	
	public AffineTransform getAffineTransform() {
		return this.affineTransform;
	}
	public GShape(Shape shape) {
		this.shape = shape;
		this.affineTransform = new AffineTransform();

		this.anchors = new Ellipse2D[EAnchor.values().length-1];
		for (int i=0; i<this.anchors.length; i++) {
			this.anchors[i] = new Ellipse2D.Double();
		}		
		this.bSelected = false;
		this.eSelectedAnchor = null;
	}
	// getters and setters
	protected Shape getShape() {
		return this.shape;
	}
	public Shape getTransformedShape() {
		return this.affineTransform.createTransformedShape(this.shape);
	}

	public boolean isSelected() {
		return this.bSelected;
	}
	public void setSelected(boolean bSelected) {
		this.bSelected = bSelected;
	}
	public EAnchor getESelectedAnchor() {
		return this.eSelectedAnchor;
	}
	public Rectangle getBounds() {
		return this.shape.getBounds();
	}


	// methods
	private void setAnchors() {
		Shape transformedShape = this.affineTransform.createTransformedShape(this.shape);
		Rectangle bounds = transformedShape.getBounds();
		int bx = bounds.x;
		int by = bounds.y;
		int bw = bounds.width;
		int bh = bounds.height;
		
		int cx=0;
		int cy=0;
		for (int i=0; i<this.anchors.length; i++) {
			switch(EAnchor.values()[i]) {
			case eSS: cx = bx+bw/2;	cy=by+bh; 	break;
			case eSE: cx = bx+bw; 	cy=by+bh; 	break;
			case eSW: cx = bx;		cy=by+bh; 	break;
			case eNN: cx = bx+bw/2; cy=by; 		break;
			case eNE: cx = bx+bw; 	cy=by; 		break;
			case eNW: cx = bx; 		cy=by;	 	break;
			case eEE: cx = bx+bw; 	cy=by+bh/2;	break;
			case eWW: cx = bx; 		cy=by+bh/2; break;
			case eRR: cx = bx+bw/2; cy=by-30; 	break;
			default: break;
			}
			anchors[i].setFrame(cx-ANCHOR_W/2, cy-ANCHOR_H/2, ANCHOR_W, ANCHOR_H);
		}
	}
	public void draw(Graphics2D graphics2D) {
		Shape transformedShape = this.affineTransform.createTransformedShape(shape);
		graphics2D.draw(transformedShape);
		if (bSelected) {
			this.setAnchors();
			for (int i=0; i<this.anchors.length; i++) {
				Color penColor = graphics2D.getColor();
				graphics2D.setColor(graphics2D.getBackground());
				graphics2D.fill(this.anchors[i]);
				graphics2D.setColor(penColor);
				graphics2D.draw(this.anchors[i]);
			}
		}
	}
	public boolean contains(int x, int y) {
		if (bSelected) {
			for (int i=0; i<this.anchors.length; i++) {
				Shape transformedAnchor = this.affineTransform.createTransformedShape(anchors[i]);
				if (transformedAnchor.contains(x, y)) {
					this.eSelectedAnchor = EAnchor.values()[i];
					return true;
				}
			}
		}
		Shape transformedShape = this.affineTransform.createTransformedShape(shape);
		if (transformedShape.contains(x, y)) {
			this.eSelectedAnchor = EAnchor.eMM;
			return true;
		}
		return false;
	}
	public boolean contains(GShape shape) {
		return this.shape.contains(shape.getShape().getBounds());
	}


	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void dragPoint(int x, int y);

}
