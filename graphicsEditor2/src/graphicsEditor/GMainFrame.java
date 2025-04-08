package graphicsEditor;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	// associations
	// ...
	
	public GMainFrame() {
		// attributes
		this.setLocation(100, 200);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.setLayout(new BorderLayout());
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);		
	}

	public void initialize() {
		// associated attributes
		this.setVisible(true);

		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}
