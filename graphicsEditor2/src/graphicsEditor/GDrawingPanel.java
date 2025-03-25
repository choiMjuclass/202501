package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
<<<<<<< HEAD
		super();
		System.out.println("GDrawingPanel::GDrawingPanel");
=======
>>>>>>> branch 'master' of https://github.com/choiMjuclass/202501.git
	}


	public void initialize() {
<<<<<<< HEAD
		System.out.println("GDrawingPanel::initialize");
=======
>>>>>>> branch 'master' of https://github.com/choiMjuclass/202501.git
	}
	
<<<<<<< HEAD
	public void paint(Graphics graphics) {
		System.out.println("GDrawingPanel::paint");
		super.paint(graphics);
	}
	
	protected void paintComponent(Graphics graphics) {
		System.out.println("GDrawingPanel::paintComponent");
		super.paintComponent(graphics);		
=======
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
>>>>>>> branch 'master' of https://github.com/choiMjuclass/202501.git
		this.draw(graphics);
	}	
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(10, 10, 50, 50);
	}
	
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(100, 100, 50, 50);
	}
}
