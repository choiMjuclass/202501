package transformers;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import shapes.GShape;

public class GRotator extends GTransformer {

	private double px, py;
    private double rotateCenterX, rotateCenterY;

    public GRotator(GShape shape) {
		super(shape, EDrawingType.e2P);
	}
	public void start(Graphics2D graphics, int x, int y) {
	    try {
		    Shape transformedShape = this.shape.getTransformedShape(this.shape.getShape());
		    Rectangle2D bounds = transformedShape.getBounds2D();
		    Point2D center = new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
	        // transform 이전 좌표계로 되돌림
	        AffineTransform inverse = this.shape.getTransform().createInverse();
	        Point2D localCenter = inverse.transform(center, null);
	        this.rotateCenterX = localCenter.getX();
	        this.rotateCenterY = localCenter.getY();
	    } catch (NoninvertibleTransformException e) {
	        e.printStackTrace();
	    }

	    this.px = x;
	    this.py = y;
	}
	public void drag(Graphics2D graphics, int x, int y) {
	       try {
	            // 마우스 위치도 로컬 좌표계로 변환
	            AffineTransform inverse = this.shape.getTransform().createInverse();
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
	            this.shape.getTransform().rotate(theta, rotateCenterX, rotateCenterY);

	            this.px = x;
	            this.py = y;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void add(Graphics2D graphics, int x, int y) {
	}
	
	public void startRotate(int x, int y) {
	}
	
    public void rotate(int x, int y) {
     }

}
