package shapes;

import java.awt.Polygon;

import transformers.GTransformer.EDrawingType;

public class GPolygon extends GShape {
	private Polygon polygon;
	private int px, py;
	
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
	public void startMoving(int x, int y) {
		px = x;
		py = y;
	}
	public void moving(int x, int y) {
		int dx = x - px;
		int dy = y - py;
		for (int i=0; i<polygon.npoints; i++) {
			polygon.xpoints[i] = polygon.xpoints[i] + dx;
			polygon.ypoints[i] = polygon.ypoints[i] + dy;
		}
		polygon.invalidate();
		px = x;
		py = y;
	}
	
	@Override
	public void add(GShape shape) {
		// TODO Auto-generated method stub
		
	}	
}
