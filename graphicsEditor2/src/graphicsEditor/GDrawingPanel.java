package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		super();
		System.out.println("GDrawingPanel::GDrawingPanel");
	}

	public void initialize() {
		System.out.println("GDrawingPanel::initialize");
	}
	
	public void paint(Graphics graphics) {
		System.out.println("GDrawingPanel::paint");
		super.paint(graphics);
	}
	
	protected void paintComponent(Graphics graphics) {
		System.out.println("GDrawingPanel::paintComponent");
		super.paintComponent(graphics);		
		this.draw(graphics);
	}
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(100, 100, 50, 50);
	}
}
