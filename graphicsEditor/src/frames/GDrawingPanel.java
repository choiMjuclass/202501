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
import transformers.GTransformer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public enum EDrawingType {
		e2P,
		eNP
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
		public GShape getClassShape() {
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

	private Vector<GRectangle> rectangles;
	private EShapeType eShapeType;
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.rectangles = new Vector<GRectangle>();
	}

	public void initialize() {
	}	
	public void setEShapeType(EShapeType eShapeType) {
		this.eShapeType = eShapeType;
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		for (GRectangle rectangle: rectangles) {
			rectangle.draw((Graphics2D)graphics);
		}
	}	
	

	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
		}

		private GTransformer transformer;
		
		@Override
		public void mousePressed(MouseEvent e) {
			transformer = new GTransformer();
			Graphics2D graphics2D = (Graphics2D)getGraphics();
			graphics2D.setXORMode(getBackground());
			transformer.start(graphics2D, e.getX(), e.getY());			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			Graphics2D graphics2D = (Graphics2D)getGraphics();
			graphics2D.setXORMode(getBackground());
			transformer.drag(graphics2D, e.getX(), e.getY());			

		}
		@Override
		public void mouseReleased(MouseEvent e) {
			Graphics2D graphics2D = (Graphics2D)getGraphics();
			graphics2D.setXORMode(getBackground());
			GRectangle rectangle = transformer.finish(graphics2D, e.getX(), e.getY());			
			rectangles.add(rectangle);
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("mouseMoved");
		}		
		
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("mouseEntered");
		}
		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("mouseExited");
		}
	}
}
