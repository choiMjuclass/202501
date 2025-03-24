package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
	}


	public void initialize() {
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		this.draw(graphics);
	}	
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(10, 10, 50, 50);
	}
}
