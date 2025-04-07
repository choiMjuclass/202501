package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import graphicsEditor.GDrawingPanel.Transformer;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}


	public void initialize() {
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}	
	
	private void draw(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.drawRect(x, y, 50, 50);
	}
	public abstract class Transformer {
		
		protected int x0, y0, x1, y1;
		
		public abstract void start(int x, int y, Graphics graphics);
		public abstract void transform(int x, int y, Graphics graphics);
		public abstract void finish(int x, int y, Graphics graphics);
	}
	
	public class Drawer extends Transformer {

		@Override
		public void start(int x, int y, Graphics graphics) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void transform(int x, int y, Graphics graphics) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void finish(int x, int y, Graphics graphics) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class MouseEventHandler implements MouseMotionListener, MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed");
			draw(e.getX(), e.getY());
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("mouseMoved");
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("mouseDragged");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased");
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
