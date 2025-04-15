package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GRectangle;
import shapes.GShape;
import transformers.GDrawer;
import transformers.GMover;
import transformers.GTransformer;

public class GDrawingPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public enum EDrawingType {
		e2P,
		eNP
	}
	
	private enum EDrawingState {
		eIdle,
		e2PDrawing,
		eNPDrawing
	}
	
	public enum EShapeType {
		eRectnalge("rectangle", EDrawingType.e2P, GRectangle.class),
		eEllipse("ellipse", EDrawingType.e2P, GRectangle.class),
		eLine("line", EDrawingType.e2P, GRectangle.class),
		ePolygon("polygon", EDrawingType.eNP, GRectangle.class);
		
		private String name;
		private EDrawingType eDrawingType;
		private Class<?> classShape;
		private EShapeType(String name, EDrawingType eDrawingType, Class<?> classShape) {
			this.name = name;
			this.eDrawingType = eDrawingType;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
		}
		public EDrawingType getEDrawingType() {
			return this.eDrawingType;
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
	}

	private Vector<GShape> shapes;
	private EShapeType eShapeType;
	private EDrawingState eDrawingState;
	private GTransformer transformer;

	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eDrawingState = EDrawingState.eIdle;
		this.transformer = null;
	}

	public void initialize() {
	}	
	public void setEShapeType(EShapeType eShapeType) {
		this.eShapeType = eShapeType;
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		for (GShape shape: this.shapes) {
			shape.draw((Graphics2D)graphics);
		}
	}	
	

	private GShape onShape(int x, int y) {
		for (GShape shape: this.shapes) {
			if (shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}
	
	private void startPoint(int x, int y) {
		GShape shape = onShape(x, y);
		if (shape == null) {
			shape = eShapeType.newShape();
			transformer = new GDrawer(shape);
			if (eShapeType.getEDrawingType() == EDrawingType.e2P) {
				this.eDrawingState = EDrawingState.e2PDrawing;
			} else if (eShapeType.getEDrawingType() == EDrawingType.eNP) {
				this.eDrawingState = EDrawingState.eNPDrawing;
			}
		} else {
			transformer = new GMover(shape);				
			this.eDrawingState = EDrawingState.e2PDrawing;
		}
		transformer.start((Graphics2D)getGraphics(), x, y);	
	}
	private void dragPoint(int x, int y) {
		transformer.drag((Graphics2D)getGraphics(), x, y);
	}
	private void addPoint(int x, int y) {
		
	}
	private void finishPoint(int x, int y) {
		GShape shape = transformer.finish((Graphics2D)getGraphics(), x, y);			
		shapes.add(shape);
		this.eDrawingState = EDrawingState.eIdle;
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				startPoint(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PDrawing) {
				dragPoint(e.getX(), e.getY());
			}

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PDrawing) {
				finishPoint(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}		
		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
