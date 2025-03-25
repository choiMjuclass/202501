package graphicsEditor;

import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {		
	}

	public void initialize() {
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.draw(graphics);
	}	
	
	public void draw(Graphics graphics) {
		graphics.drawRect(10, 10, 50, 50);
	}
}
