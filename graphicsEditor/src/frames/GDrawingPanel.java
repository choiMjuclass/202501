package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import frames.GShapeToolBar.EDrawingTool;
import shapes.GShape;
import shapes.GShape.EAnchor;
import transformers.GDrawer;
import transformers.GMover;
import transformers.GSelector;
import transformers.GTransformer;
import transformers.GTransformer.EDrawingType;

public class GDrawingPanel extends JPanel {	
	private static final long serialVersionUID = 1L;
	
	private enum EDrawingState {
		eIdle,
		e2PDrawing,
		eNPDrawing
	}
	private Vector<GShape> shapes;
	private EDrawingTool eDrawingTool;
	private EDrawingState eDrawingState;
	private GTransformer transformer;
	private GShape currentShape;

	public void setEDrawingTool(EDrawingTool eDrawingTool) {
		this.eDrawingTool = eDrawingTool;
	}

	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.shapes = new Vector<GShape>();
		this.eDrawingTool = null;
		this.eDrawingState = EDrawingState.eIdle;
		this.transformer = null;
		this.currentShape = null;
	}
	public void initialize() {
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
	private GTransformer getTransformer(int x, int y) {
		if ( this.eDrawingTool == EDrawingTool.eSelect) {
			this.currentShape = onShape(x, y);
			if (this.currentShape == null) {
				this.currentShape = this.eDrawingTool.newShape();
				return new GSelector(currentShape);
			} else {
				if (currentShape.getSelectedAnchor() == EAnchor.eMM) {
					return new GMover(currentShape);
				} else if (currentShape.getSelectedAnchor() == EAnchor.eRR) {
					return new GRotator(currentShape);
				} else {
					
				}
			}			
		} else {
			this.currentShape = this.eDrawingTool.newShape();
			return new GDrawer(currentShape);			
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
		for (GShape shape: this.shapes) {
			shape.setSelected(false);
		}
		this.currentShape.setSelected(true);
		
		if (this.transformer instanceof GDrawer) {
			this.shapes.add(this.currentShape);
		} else if (this.transformer instanceof GSelector) {
			for (GShape shape: this.shapes) {
				shape.setSelected(this.currentShape.contains(shape));
				if (shape.isSelected()) {
					this.currentShape.add(shape);
				}
			}
		}
		this.repaint();
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
			if (eDrawingState == EDrawingState.eIdle) {
				transformer = getTransformer(e.getX(), e.getY());
				if (transformer.getEDrawingType() == EDrawingType.eNP) {
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
			if (eDrawingState == EDrawingState.eNPDrawing) {
				finishPoint(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				transformer = getTransformer(e.getX(), e.getY());
				if (transformer.getEDrawingType() == EDrawingType.e2P) {
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
