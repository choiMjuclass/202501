package shapes;

import java.awt.Polygon;

import transformers.GTransformer.EDrawingType;

public class GPolygon extends GShape {
	private Polygon polygon;
	
	public GPolygon() {
		super(new Polygon(), EDrawingType.eNP);
		this.polygon = (Polygon) this.getShape();
	}	
	public void startDrawing(int x, int y) {
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		polygon.addPoint(x, y);		
	}
	public void drawing(int x, int y) {
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}

	@Override
	public void add(GShape shape) {
		// TODO Auto-generated method stub
		
	}	
}
