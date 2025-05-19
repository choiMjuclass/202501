package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import global.GConstants;

public class GMainFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private GMenuBar menuBar;
	private GShapeToolBar toolBar;
	private GDrawingPanel drawingPanel;
	// associations
	// ...
	
	public GMainFrame() {
		// attributes
		this.setLocation(GConstants.GMainFrame.X, GConstants.GMainFrame.Y);
		this.setSize(GConstants.GMainFrame.W, GConstants.GMainFrame.H);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);
		
		this.setLayout(new BorderLayout());
		this.toolBar = new GShapeToolBar();
		this.add(toolBar, BorderLayout.NORTH);		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);		
	}

	public void initialize() {
		// associate
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
		
		// associated attributes
		this.setVisible(true);

		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}
