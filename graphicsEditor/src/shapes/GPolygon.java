package shapes;

import java.awt.Polygon;

import transformers.GTransformer.EDrawingType;

public class GPolygon extends GShape {
//	private Polygon polygon;
	private int px, py;
	
	public GPolygon() {
		super(new Polygon(), EDrawingType.eNP);
	}	
	public void startDrawing(int x, int y) {
		Polygon polygon = (Polygon) this.getShape();
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) this.getShape();
		polygon.addPoint(x, y);		
	}
	public void drawing(int x, int y) {
		Polygon polygon = (Polygon) this.getShape();
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
		Polygon polygon = (Polygon) this.getShape();
		for (int i=0; i<polygon.npoints; i++) {
			polygon.xpoints[i] += dx;
			polygon.ypoints[i] += dy;
		}
		px = x;
		py = y;
	}
}
