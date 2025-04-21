package frames;

public class GMain {

	public static void main(String[] args) {
		// create aggregation hierarchy
		GMainFrame mainFrame = new GMainFrame();
		// tree traverse (DFS)
		mainFrame.initialize();
		
//		void processEvent() {
//			while(true) {
//				Event e = getEvent();
//				for (JComponent component: this.components) {
//					if (component.contains(e.getPoint)) {
//						component.processEvent(e);
//					}
//				}
//			}
//		}
	}
}
