package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import frames.GShapeToolBar.EShapeType;
import shapes.GRectangle;
import shapes.GShape;
import transformers.GTransformer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public enum EDrawingType {
		e2P,
		eNP
	}
	
	public enum EDrawingState {
		eIdle,
		e2P,
		eNP
	}

	private Vector<GShape> shapes;
	private EShapeType eShapeType;
	private EDrawingState eDrawingState;
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eShapeType = null;
		this.eDrawingState = EDrawingState.eIdle;
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
	
	private void startDrawing(int x, int y) {
		// set shape
		GShape shape = eShapeType.newShape();
		GTransformer transformer = new GDrawer(shape);
		transformer.start(e.getX(), e.getY(), getGraphics());
	}
	private void keepDrawing(int x, int y) {		
	}
	private void addPoint(int x, int y) {		
	}
	private void finishDrawing(int x, int y) {		
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
		}

		private GTransformer transformer;
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				// set transformer
				if (eShapeType == EShapeType.eSelect) {					
				} else {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2P;
				}				
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2P) {
				keepDrawing(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2P) {
				finishDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
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
