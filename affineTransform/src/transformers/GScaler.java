package transformers;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import shapes.GShape;
import shapes.GShape.EAnchor;

public class GScaler extends GTransformer {
	
	private double px, py;
	private double cx, cy;	
    private EAnchor eReiszeAnchor;

	public GScaler(GShape shape) {
		super(shape, EDrawingType.e2P);
	}
	
	public void start(Graphics2D graphics, int x, int y) {
		
		this.cx=0;
		this.cy=0;
		this.eReiszeAnchor = null;
		
		Rectangle r = this.shape.getBounds();
		switch (this.shape.getSelectedAnchor()) {
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
		px = x;
		py = y;
	}
	
	public void drag(Graphics2D graphics, int x, int y) {
		double dx =0; double dy=0;
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
		Shape transformedShape = this.shape.getTransformedShape(this.shape.getShape());
		double w1 = transformedShape.getBounds().width;
		double w2 = dx + w1;
		double h1 = transformedShape.getBounds().height;
		double h2 = dy + h1;
		
		double xScale = w2/w1;
		double yScale = h2/h1;
		
		this.shape.getTransform().translate(cx, cy);
		this.shape.getTransform().scale(xScale, yScale);
		this.shape.getTransform().translate(-cx, -cy);
		
		px = x;
		py = y;
	}
	
	public void finish(Graphics2D graphics, int x, int y) {
	}
	
	@Override
	public void add(Graphics2D graphics, int x, int y) {
	}
}
