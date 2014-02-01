import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;


public class Affichage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LinkedList<Point> points;
	
	
	public Affichage(LinkedList<Point> points) {
		super();
		this.points = points;
	}
	public void paintComponent(Graphics g){
		for (Point p : this.points) {
			g.setColor(p.groupe.couleur);
			double x = p.coord.get(0);
			int positionX = (int) x;
			double y = p.coord.get(1);
			int positionY = (int) y;

			g.drawLine(100 + positionX - 1, 100 + positionY - 1, 100 + positionX + 1, 100 + positionY + 1);
			g.drawLine(100 + positionX - 1, 100 + positionY + 1, 100 + positionX + 1, 100 + positionY - 1);
		}
				
	}  

}
