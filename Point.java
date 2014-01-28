import java.util.*;


public class Point {

	/**
	 * @param args
	 */
	public LinkedList<Double> coord;
	public GroupePoint groupe;
	
	
	// Methode permettant de calculer la distace a un point
	public Double distance(Point p){
		
		Double distance=null;
		int i=0;
		int longueur = coord.size();
		Double sommeDistanceCarre=new Double(0);
		
		for(i=0; i<longueur; i++){
			sommeDistanceCarre = sommeDistanceCarre + Math.pow(this.coord.get(i) - p.coord.get(i), 2);
		}
		distance=Math.sqrt(sommeDistanceCarre);
		return distance;
	}
	
	
	// Methode permettant d'affecter un groupe a une point
	public GroupePoint grouper(LinkedList<GroupePoint> g){
		
		GroupePoint groupe=g.getFirst();
		Double distanceMin=new Double(g.getFirst().centre.distance(this));
		
		for(GroupePoint p : g){
			if(p.centre.distance(this)<distanceMin){
				distanceMin=p.centre.distance(this);
				groupe=p;
			}
		}
		
		return groupe;
	}

}
