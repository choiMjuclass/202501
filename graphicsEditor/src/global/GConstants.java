package global;

import java.awt.Cursor;
import java.lang.reflect.InvocationTargetException;

import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;
import shapes.GShape.EPoints;

public final class GConstants {

	public final static class GMainFrame {
		public final static int X = 100;
		public final static int Y = 200;
		public final static int W = 600;
		public final static int H = 400;
	}
	
	public enum EAnchor {
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eRR(new Cursor(Cursor.HAND_CURSOR)),
		eMM(new Cursor(Cursor.MOVE_CURSOR));		
		private Cursor cursor;
		private EAnchor(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() {
			return this.cursor;
		}
	}
	
	public enum EFileMenuItem {
		eNew("새파일", "newPanel"),
		eOpen("열기", "open"),
		eSave("저장", "save"),
		eSaveAs("다른이름으로", "saveAs"),
		ePrint("프린트", "print"),
		eClose("닫기", "close"),
		eQuit("종료", "quit");
		
		private String name;
		private String methodName;
		private EFileMenuItem(String name, String methodName) {
			this.name = name;
			this.methodName = methodName;
		}
		public String getName() {
			return this.name;
		}
		public String getMethodName() {
			return this.methodName;
		}
	}
	
	public enum EShapeTool {
		eSelect("select", EPoints.e2P, GRectangle.class),
		eRectnalge("rectangle", EPoints.e2P, GRectangle.class),
		eEllipse("ellipse", EPoints.e2P, GRectangle.class),
		eLine("line", EPoints.e2P, GRectangle.class),
		ePolygon("polygon", EPoints.eNP, GPolygon.class);
		
		private String name;
		private EPoints ePoints;
		private Class<?> classShape;
		private EShapeTool(String name, EPoints ePoints, Class<?> classShape) {
			this.name = name;
			this.ePoints = ePoints;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
		}
		public EPoints getEPoints() {
			return this.ePoints;
		}
		public GShape newShape() {
			try {
				GShape shape = (GShape) classShape.getConstructor().newInstance();
				return shape;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}
	}	// components

}
