package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import frames.GShapeToolBar.EShapeType;
import shapes.GShape;
import shapes.GShape.EAnchor;
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
	

	private Vector<GShape> shapes;
	private EShapeType eShapeType;
	private EDrawingState eDrawingState;
	private GTransformer transformer;
	private GShape currentShape;

	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eShapeType = null;
		this.eDrawingState = EDrawingState.eIdle;
		this.transformer = null;
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
			if (shape.contains(x, y) != null) {
				return shape;
			}
		}
		return null;
	}	
	private GShape getCurrentShape(int x, int y) {
		GShape shape = onShape(x, y);
		if (shape == null) {
			shape = eShapeType.newShape();
		} 
		return shape;
	}
	private GTransformer getTransformer(int x, int y) {
		if (currentShape.getSelectedAnchor() == null) {
			currentShape = eShapeType.newShape();
			return new GDrawer(currentShape);
		} else if (currentShape.getSelectedAnchor() == EAnchor.MM) {
			return new GMover(currentShape);
		}
		return null;
	}
	
	private void startPoint(int x, int y) {
		this.transformer.start((Graphics2D)getGraphics(), x, y);	
	}
	private void dragPoint(int x, int y) {
		this.transformer.drag((Graphics2D)getGraphics(), x, y);
	}
	private void addPoint(int x, int y) {
		this.transformer.add((Graphics2D)getGraphics(), x, y);
	}
	private void finishPoint(int x, int y) {
		this.transformer.finish((Graphics2D)getGraphics(), x, y);			
		shapes.add(this.currentShape);
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == 1 && e.getClickCount() == 1) {
				lButton1Clicked(e);
			} else if (e.getButton() == 1 && e.getClickCount() == 2) {
				lButton2Clicked(e);
			}
			
		}
		public void lButton1Clicked(MouseEvent e) {
			System.out.println("lButton1Clicked");
			if (eDrawingState == EDrawingState.eIdle) {
				currentShape = getCurrentShape(e.getX(), e.getY());
				if (currentShape.getEDrawingType() == EDrawingType.eNP) {
					transformer = getTransformer(e.getX(), e.getY());
					startPoint(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPDrawing;
				}
			} else if (eDrawingState == EDrawingState.eNPDrawing) {
				addPoint(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPDrawing) {
				dragPoint(e.getX(), e.getY());
			}
		}		
		public void lButton2Clicked(MouseEvent e) {
			System.out.println("lButton2Clicked");
			if (eDrawingState == EDrawingState.eNPDrawing) {
				finishPoint(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				currentShape = getCurrentShape(e.getX(), e.getY());
				if (currentShape.getEDrawingType() == EDrawingType.e2P) {
					transformer = getTransformer(e.getX(), e.getY());
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
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
