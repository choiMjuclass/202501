package graphicsEditor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		super();
		System.out.println("GMainFrame::GMainFrame");
		
		// attributes
		this.setLocation(100, 200);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);		
		
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);		
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);		
		
		this.drawingPanel = new GDrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void initialize() {		
		System.out.println("GMainFrame::initialize");
		
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
	
	public void paint(Graphics graphics) {
		System.out.println("GMainFrame::paint");
		super.paint(graphics);
	}
	
	protected void paintComponent(Graphics graphics) {
		System.out.println("GMainFrame::paintComponent");
	}
}
