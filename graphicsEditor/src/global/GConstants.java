package global;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import global.GConstants.EEditMenuItem;
import global.GConstants.EFileMenuItem;
import global.GConstants.EGraphicsMenuItem;
import global.GConstants.EMainFrame;
import global.GConstants.EMenu;
import global.GConstants.EToolBarButton;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GShape;
import shapes.GShape.EPoints;

public final class GConstants {
	
	public GConstants() {
	}
	
	public void readFromFile(String fileName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Load the input XML document, parse it and return an instance of the
			// Document class.
			File file = new File(fileName);
			Document document = builder.parse(file);
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			for (int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getNodeName().equals(EMainFrame.class.getSimpleName())) {
						EMainFrame.setValues(node);
					} else if (node.getNodeName().equals(EMenu.class.getSimpleName())) {
						EMenu.setValues(node);
					} else if (node.getNodeName().equals(EFileMenuItem.class.getSimpleName())) {
						EFileMenuItem.setValue(node);
					} else if (node.getNodeName().equals(EEditMenuItem.class.getSimpleName())) {
						EEditMenuItem.setValue(node);					
					} else if (node.getNodeName().equals(EGraphicsMenuItem.class.getSimpleName())) {
						EGraphicsMenuItem.setValue(node);					
					} else if (node.getNodeName().equals(EToolBarButton.class.getSimpleName())) {
						EToolBarButton.setValue(node);						
					} 
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public enum EMainFrame {
		eX(0),
		eY(0),
		eW(0),
		eH(0);
		
		private int value;
		private EMainFrame(int value) {
			this.value = value;
		}
		public int getValue() {
			return this.value;
		}
		public static void setValues(Node node) {
			for (EMainFrame eMainFrame: EMainFrame.values()) {
				Node attribute = node.getAttributes().getNamedItem(eMainFrame.name());
				eMainFrame.value = Integer.parseInt(attribute.getNodeValue());				
			}
			
		}
	}
	
	public enum EAnchor {
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eRR(new Cursor(Cursor.HAND_CURSOR)),
		eMM(new Cursor(Cursor.MOVE_CURSOR));		
		private Cursor cursor;
		private EAnchor(Cursor cursor) {
			this.cursor = cursor;
		}
		public Cursor getCursor() {
			return this.cursor;
		}
	}
	
	public enum EFileMenuItem {
		eNew("새파일", "newPanel"),
		eOpen("열기", "open"),
		eSave("저장", "save"),
		eSaveAs("다른이름으로", "saveAs"),
		ePrint("프린트", "print"),
		eClose("닫기", "close"),
		eQuit("종료", "quit");
		
		private String name;
		private String methodName;
		private EFileMenuItem(String name, String methodName) {
			this.name = name;
			this.methodName = methodName;
		}
		public String getName() {
			return this.name;
		}
		public String getMethodName() {
			return this.methodName;
		}
	}
	
	public enum EShapeTool {
		eSelect("select", EPoints.e2P, GRectangle.class),
		eRectnalge("rectangle", EPoints.e2P, GRectangle.class),
		eEllipse("ellipse", EPoints.e2P, GRectangle.class),
		eLine("line", EPoints.e2P, GRectangle.class),
		ePolygon("polygon", EPoints.eNP, GPolygon.class);
		
		private String name;
		private EPoints ePoints;
		private Class<?> classShape;
		private EShapeTool(String name, EPoints ePoints, Class<?> classShape) {
			this.name = name;
			this.ePoints = ePoints;
			this.classShape = classShape;
		}
		public String getName() {
			return this.name;
		}
		public EPoints getEPoints() {
			return this.ePoints;
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
	}	// components

}
