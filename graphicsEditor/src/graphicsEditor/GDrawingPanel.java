package graphicsEditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Vector<GRectangle> rectangles;
	
	public GDrawingPanel() {
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.rectangles = new Vector<GRectangle>();
	}

	public void initialize() {
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
