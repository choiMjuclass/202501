package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	public enum EDrawingTool {
		eSelect("select",GRectangle.class),
		eRectnalge("rectangle",GRectangle.class),
		eEllipse("ellipse", GRectangle.class),
		eLine("line", GRectangle.class),
		ePolygon("polygon", GPolygon.class);
		
		private String name;
		private Class<?> classShape;
		private EDrawingTool(String name, Class<?> classShape) {
			this.name = name;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
		}
		public GShape newShape() {
			try {
				GShape shape = (GShape) classShape.getConstructor().newInstance();
				return shape;
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	// components

	// associations
	private GDrawingPanel drawingPanel;
	
	public GShapeToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		for (EDrawingTool eShpaeType: EDrawingTool.values()) {
			JRadioButton radioButton = new JRadioButton(eShpaeType.getName());
			ActionHandler actionHandler = new ActionHandler();
			radioButton.addActionListener(actionHandler);
			radioButton.setActionCommand(eShpaeType.toString());
			this.add(radioButton);
			buttonGroup.add(radioButton);
		}
	}

	public void initialize() {
		JRadioButton radioButton = (JRadioButton) this.getComponent(EDrawingTool.eSelect.ordinal());
		radioButton.doClick();
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String sShapeType = e.getActionCommand();
			EDrawingTool eShapeType = EDrawingTool.valueOf(sShapeType);
			drawingPanel.setEDrawingTool(eShapeType);
		}
		
	}
}
