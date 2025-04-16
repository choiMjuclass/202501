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
import shapes.GShape.EDrawingType;
import transformers.GDrawer;
import transformers.GMover;
import transformers.GTransformer;

public class GDrawingPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private enum EDrawingState {
		eIdle,
		e2PDrawing,
		eNPDrawing
	}
	
	public enum EShapeType {
		eRectnalge("rectangle",GRectangle.class),
		eEllipse("ellipse", GRectangle.class),
		eLine("line", GRectangle.class),
		ePolygon("polygon", GRectangle.class);
		
		private String name;
		private Class<?> classShape;
		private EShapeType(String name, Class<?> classShape) {
			this.name = name;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
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
	private GTransformer currentTransformer;
	private GShape currentShape;

	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eShapeType = null;
		this.eDrawingState = EDrawingState.eIdle;
		this.currentTransformer = null;
		this.currentShape = null;
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
	
	private GTransformer selectTransformer(int x, int y) {
		this.currentShape = onShape(x, y);
		if (currentShape == null) {
			currentShape = eShapeType.newShape();
			return new GDrawer(currentShape);
		} else {
			return new GMover(currentShape);
		}
	}
	
	private void startPoint(int x, int y) {
		this.currentTransformer.start((Graphics2D)getGraphics(), x, y);	
	}
	private void dragPoint(int x, int y) {
		this.currentTransformer.drag((Graphics2D)getGraphics(), x, y);
	}
	private void addPoint(int x, int y) {
		this.currentTransformer.add((Graphics2D)getGraphics(), x, y);
	}
	private void finishPoint(int x, int y) {
		this.currentTransformer.finish((Graphics2D)getGraphics(), x, y);			
		shapes.add(this.currentShape);
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				currentTransformer = selectTransformer(e.getX(), e.getY());
				if (currentShape.getEDrawingType() == EDrawingType.e2P) {
					startPoint(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2PDrawing;
				}
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
				eDrawingState = EDrawingState.eIdle;
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
