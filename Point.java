import java.util.*;


public class Point {

	/**
	 * @param args
	 */
	
	public LinkedList<Double> coord;
	public GroupePoint groupe;
	
	public Point(){
		this.coord=new LinkedList<Double>();
	}
	

	
	// Methode permettant d'affecter un groupe a une point
	public void grouper(LinkedList<GroupePoint> g, int paramSim, int puissance){
		Distance sim= new Distance();
		GroupePoint groupe = g.getFirst();
		Double distanceMin=new Double(sim.distance(this, groupe.centre, paramSim, puissance));
		
		for(GroupePoint p : g){
			if(sim.distance(p.centre, this, paramSim, puissance) < distanceMin){
				distanceMin=sim.distance(p.centre, this, paramSim, puissance);
				groupe=p;
				System.out.println("Le point en (" + this.coord.get(0) + "," + this.coord.get(1) + ") est attribué au " + groupe.nom );
			}
		}
		this.groupe=groupe;
	}

}
