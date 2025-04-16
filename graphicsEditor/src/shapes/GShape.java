package shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

public abstract class GShape {
	public enum EAnchor {
		EE,
		WW,
		SS,
		NN,
		SE,
		SW,
		NE,
		NW,
		RR,
		MM
	}
	public enum EDrawingType {
		e2P,
		eNP
	}
	
	private Shape shape;
	private EDrawingType eDrawingType;
	private EAnchor eSelectedAnchor;
	private boolean bSelected;
	private Vector<Ellipse2D> anchors;
	
	public GShape(Shape shape, EDrawingType eDrawingType) {
		this.shape = shape;
		this.eDrawingType = eDrawingType;
		this.eSelectedAnchor = null;
		this.bSelected = false;
		
		this.anchors = new Vector<Ellipse2D>();
		for (int i=0; i<EAnchor.values().length-1; i++) {
			this.anchors.add(new Ellipse2D.Double(0, 0, 20, 20));
		}
	}
	
	public Shape getShape() {
		return this.shape;
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
	public Vector<Ellipse2D> getAnchors() {
		return this.anchors;
	}
	
	public EAnchor contains(int x, int y) {
		for (int i=0; i<EAnchor.values().length-1; i++) {
			if (this.anchors.get(i).contains(x, y)) {
				this.eSelectedAnchor = EAnchor.values()[i];
				return this.eSelectedAnchor;
			}				
		}
		if (this.shape.contains(x, y)) {
			this.eSelectedAnchor = EAnchor.MM;
			return this.eSelectedAnchor;
		}
		this.eSelectedAnchor = null;
		return this.eSelectedAnchor;
	}	
	
	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(shape);
	}
	
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void resize(int x, int y);
	public abstract void move(int x, int y);
}
