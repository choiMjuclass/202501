package shapes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import transformers.GTransformer.EDrawingType;

public abstract class GShape {
	private final static int ANCHOR_WIDTH = 8;
	private final static int ANCHOR_HEIGHT = 8;

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
			Rectangle2D tBounds = this.getTransformedShape(anchor).getBounds2D();
			double cx = tBounds.getCenterX()-ANCHOR_WIDTH/2;
			double cy = tBounds.getCenterY()-ANCHOR_HEIGHT/2;
			Ellipse2D tAnchor = new Ellipse2D.Double(cx, cy, ANCHOR_WIDTH,ANCHOR_HEIGHT );
			
			Color color = graphics.getColor();
			graphics.setColor(graphics.getBackground());
			graphics.fill(tAnchor);
			graphics.setColor(color);
			graphics.draw(tAnchor);
		}	
	}
	
	public void startTranslate(int x, int y) {
		px = x;
		py = y;
	}
    public void translate(int x, int y) {
 		try {
 	       // 현재 마우스 좌표 (화면 기준)를 도형 좌표계로 변환
 	        Point2D p1 = new Point2D.Double(px, py);
 	        Point2D p2 = new Point2D.Double(x, y);
 	        AffineTransform inverse = this.transform.createInverse();
 	        
			Point2D localP1 = inverse.transform(p1, null);
	        Point2D localP2 = inverse.transform(p2, null);

	        double dx = localP2.getX() - localP1.getX();
	        double dy = localP2.getY() - localP1.getY();
	        
	        this.transform.translate(dx, dy);
	        
			px = x;
			py = y;
			
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     }
    
    private EAnchor eReiszeAnchor;
    private int cx, cy;
	public Point getResizeCenter(int x, int y) {
		int cx=0, cy=0;
		this.eReiszeAnchor = null;
		Rectangle r = this.shape.getBounds();
		switch (this.eSelectedAnchor) {
			case eNW: eReiszeAnchor = EAnchor.eSE; cx=r.x+r.width; 		cy=r.y+r.height; 	break;
			case eWW: eReiszeAnchor = EAnchor.eEE; cx=r.x+r.width;		cy=r.y+r.height/2; 	break;				
			case eSW: eReiszeAnchor = EAnchor.eNE; cx=r.x+r.width;		cy=r.y; 			break;				
			case eSS: eReiszeAnchor = EAnchor.eNN; cx=r.x+r.width/2;	cy=r.y; 			break;				
			case eSE: eReiszeAnchor = EAnchor.eNW; cx=r.x; 				cy=r.y;			 	break;				
			case eEE: eReiszeAnchor = EAnchor.eWW; cx=r.x; 				cy=r.y+r.height/2; 	break;				
			case eNE: eReiszeAnchor = EAnchor.eSW; cx=r.x; 				cy=r.y+r.height; 	break;				
			case eNN: eReiszeAnchor = EAnchor.eSS; cx=r.x+r.width/2;	cy=r.y+r.height; 	break;				
			default: break;
		}
		return new Point(cx, cy);
	}
	public void startScale(int x, int y) {
		Point center = this.getResizeCenter(x, y);
		this.cx = center.x;
		this.cy = center.y;
		px = x;
		py = y;
	}
    public void scale(int x, int y) {
		int dx =0; int dy=0;
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
		Shape transformedShape = this.getTransformedShape(this.shape);
		double w1 = transformedShape.getBounds().width;
		double w2 = dx + w1;
		double h1 = transformedShape.getBounds().height;
		double h2 = dy + h1;
		
		double xScale = w2/w1;
		double yScale = h2/h1;
		
		this.transform.translate(cx, cy);
		this.transform.scale(xScale, yScale);
		this.transform.translate(-cx, -cy);
		
		px = x;
		py = y;

    }
    
    private double rotateCenterX;
    private double rotateCenterY;
	public void startRotate(int x, int y) {
	    Shape transformedShape = getTransformedShape(this.shape);
	    Rectangle2D bounds = transformedShape.getBounds2D();
	    Point2D center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());

	    try {
	        // transform 이전 좌표계로 되돌림
	        AffineTransform inverse = this.transform.createInverse();
	        Point2D localCenter = inverse.transform(center, null);
	        this.rotateCenterX = localCenter.getX();
	        this.rotateCenterY = localCenter.getY();
	    } catch (NoninvertibleTransformException e) {
	        e.printStackTrace();
	    }

	    this.px = x;
	    this.py = y;
	}
	
    public void rotate(int x, int y) {
        try {
            // 마우스 위치도 로컬 좌표계로 변환
            AffineTransform inverse = this.transform.createInverse();
            Point2D pPrev = inverse.transform(new Point2D.Double(px, py), null);
            Point2D pCurr = inverse.transform(new Point2D.Double(x, y), null);

            double dx1 = pPrev.getX() - rotateCenterX;
            double dy1 = pPrev.getY() - rotateCenterY;
            double dx2 = pCurr.getX() - rotateCenterX;
            double dy2 = pCurr.getY() - rotateCenterY;

            double angle1 = Math.atan2(dy1, dx1);
            double angle2 = Math.atan2(dy2, dx2);
            double theta = angle2 - angle1;

            // 로컬 중심 기준 회전 적용
            this.transform.rotate(theta, rotateCenterX, rotateCenterY);

            this.px = x;
            this.py = y;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public abstract void startDrawing(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void drawing(int x, int y);
	
	public abstract void add(GShape shape);
}
