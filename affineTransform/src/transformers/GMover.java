package transformers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import shapes.GShape;

public class GMover extends GTransformer {
	
	private double px, py;
	
	public GMover(GShape shape) {
		super(shape, EDrawingType.e2P);
	}
	public void start(Graphics2D graphics, int x, int y) {
		px = x;
		py = y;
	}
	public void drag(Graphics2D graphics, int x, int y) {
 		try {
  	       // 현재 마우스 좌표 (화면 기준)를 도형 좌표계로 변환
  	        Point2D p1 = new Point2D.Double(px, py);
  	        Point2D p2 = new Point2D.Double(x, y);
  	        AffineTransform inverse = shape.getTransform().createInverse();
  	        
 			Point2D localP1 = inverse.transform(p1, null);
 	        Point2D localP2 = inverse.transform(p2, null);

 	        double dx = localP2.getX() - localP1.getX();
 	        double dy = localP2.getY() - localP1.getY();
 	        
 	       shape.getTransform().translate(dx, dy);
 	        
 			px = x;
 			py = y;
 			
 		} catch (NoninvertibleTransformException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}
	public void finish(Graphics2D graphics, int x, int y) {
	}
	@Override
	public void add(Graphics2D graphics, int x, int y) {
	}
}
