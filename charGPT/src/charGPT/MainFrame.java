package charGPT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import charGPT.CircleShape;
import charGPT.LineShape;
import charGPT.RectangleShape;
import charGPT.TextShape;

public class MainFrame extends JFrame {
    private JPanel canvas;
    private CShape selectedShape = null;
    private int startX, startY;
    
    public MainFrame() {
        setTitle("Graphic Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel toolbar = new JPanel();
        JButton rectButton = new JButton("Rectangle");
        JButton circleButton = new JButton("Circle");
        JButton lineButton = new JButton("Line");
        JButton textButton = new JButton("Text");
        
        toolbar.add(rectButton);
        toolbar.add(circleButton);
        toolbar.add(lineButton);
        toolbar.add(textButton);
        
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (selectedShape != null) {
                    ((RectangleShape) selectedShape).draw(g);
                }
            }
        };
        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }
        });
        
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selectedShape != null) {
                    ((CShape) selectedShape).move(e.getX() - startX, e.getY() - startY);
                    startX = e.getX();
                    startY = e.getY();
                    canvas.repaint();
                }
            }
        });
        
        rectButton.addActionListener(e -> addShape(new RectangleShape(50, 50, 100, 70, Color.BLUE)));
        circleButton.addActionListener(e -> addShape(new CircleShape(100, 100, 50, Color.GREEN)));
        lineButton.addActionListener(e -> addShape(new LineShape(50, 50, 150, 150, Color.BLACK)));
        textButton.addActionListener(e -> addShape(new TextShape(100, 100, "Sample Text", Color.BLACK)));
        
        add(toolbar, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }
    
    private void addShape(CShape shape) {
        this.selectedShape = shape;
        canvas.repaint();
    }

}
