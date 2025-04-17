package shapes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
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
    private AffineTransform transform;
	private int px, py;
	
	public GShape(Shape shape, EDrawingType eDrawingType) {
		this.shape = shape;
		this.eDrawingType = eDrawingType;
		this.eSelectedAnchor = null;
		this.bSelected = false;
		
		this.anchors =  new Ellipse2D[EAnchor.values().length-1];
		for (int i=0; i<this.anchors.length; i++) {
			this.anchors[i] = new Ellipse2D.Double(0, 0, 0, 0);
		}
		
        this.transform = new AffineTransform();
	}
	
	public Shape getShape() {
		return this.shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
    public Shape getTransformedShape(Shape shape) {
        return transform.createTransformedShape(shape);
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
			if (this.getTransformedShape(this.anchors[i]).contains(x, y)) {
				this.eSelectedAnchor = EAnchor.values()[i];
				return this.eSelectedAnchor;
			}				
		}
		if (this.getTransformedShape(this.shape).contains(x, y)) {
			this.eSelectedAnchor = EAnchor.eMM;
			return this.eSelectedAnchor;
		}
		return this.eSelectedAnchor;
	}
	public boolean contains(GShape shape) {
		return this.shape.contains(this.getTransformedShape(shape.getShape()).getBounds2D());
	}
	public Rectangle2D getBounds() {
		return this.shape.getBounds2D();
	}
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(getTransformedShape(this.shape));
		if (this.isSelected()) {
			this.drawAnchors(graphics2D, this.shape.getBounds2D());
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
			graphics.fill(this.getTransformedShape(anchor));
			graphics.setColor(color);
			graphics.draw(this.getTransformedShape(anchor));
		}	
	}
	
	public void startTranslate(int x, int y) {
		px = x;
		py = y;
	}
    public void translate(int x, int y) {
		double dx = x - px;
		double dy = y - py;
        this.transform.translate(dx, dy);
		px = x;
		py = y;
    }
    
	public Point getResizeAnchorPoint(int x, int y, Rectangle r) {
		int cx=0, cy=0;
		this.eReiszeAnchor = null;
		switch (this.eSelectedAnchor) {
			case eNW: eReiszeAnchor = EAnchors.eSE; cx=r.x+r.width; 	cy=r.y+r.height; 	break;
			case eWW: eReiszeAnchor = EAnchors.eEE; cx=r.x+r.width;		cy=r.y+r.height/2; 	break;				
			case eSW: eReiszeAnchor = EAnchors.eNE; cx=r.x+r.width;		cy=r.y; 			break;				
			case eSS: eReiszeAnchor = EAnchors.eNN; cx=r.x+r.width/2;	cy=r.y; 			break;				
			case eSE: eReiszeAnchor = EAnchors.eNW; cx=r.x; 			cy=r.y;			 	break;				
			case eEE: eReiszeAnchor = EAnchors.eWW; cx=r.x; 			cy=r.y+r.height/2; 	break;				
			case eNE: eReiszeAnchor = EAnchors.eSW; cx=r.x; 			cy=r.y+r.height; 	break;				
			case eNN: eReiszeAnchor = EAnchors.eSS; cx=r.x+r.width/2;	cy=r.y+r.height; 			break;				
			default: break;
		}
		return new Point(cx, cy);
	}
	public void startScale(int x, int y) {
		Rectangle rectangle = this.shape.getBounds();
		Point resizeAnchorPoint = this.anchors.getResizeAnchorPoint(x, y, rectangle);
		this.cx = resizeAnchorPoint.x;
		this.cy = resizeAnchorPoint.y;
	}
    public void scale(double sx, double sy, Point2D center) {
		int dx =0; int dy=0;
		EAnchors eReiszeAnchor = this.anchors.getResizeAnchor();
		switch (eReiszeAnchor) {
			case eNW: dx = (x-px); 	dy = (y-py); 	break;
			case eWW: dx = (x-px); 	dy = 0; 		break;				
			case eSW: dx = (x-px); 	dy = -(y-py);  	break;				
			case eSS: dx = 0; 		dy = -(y-py);  	break;				
			case eSE: dx = -(x-px); dy = -(y-py);  	break;				
			case eEE: dx = -(x-px); dy = 0;  		break;				
			case eNE: dx = -(x-px); dy = (y-py);  	break;				
			case eNN: dx = 0; 		dy = (y-py);  	break;				
			default: break;
		}
		Shape transformedShape = this.shape.getTransformedShape();
		double w1 = transformedShape.getBounds().width;
		double w2 = dx + w1;
		double h1 = transformedShape.getBounds().height;
		double h2 = dy + h1;
		
		double xScale = w2/w1;
		double yScale = h2/h1;
		
		this.affineTransform.translate(cx, cy);
		this.affineTransform.scale(xScale, yScale);
		this.affineTransform.translate(-cx, -cy);
    }
    
	public void startRotate(int x, int y) {
		px = x;
		py = y;
	}
    public void rotate(double theta, Point2D center) {
        this.transform.rotate(theta, center.getX(), center.getY());
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
	
	public abstract void add(GShape shape);
}
