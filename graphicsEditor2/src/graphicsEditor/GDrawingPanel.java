package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		super();
	}
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(10, 10, 50, 50);
	}

	public void initialize() {
//		this.draw(this.getGraphics());
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
	
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.draw(this.getGraphics());
	}
}
