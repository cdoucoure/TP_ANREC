import java.util.LinkedList;


public class Distance {

	/**
	 * @param args
	 */
	public Double resultat;
	
	
	
	public Distance(){
		resultat = new Double(0);
	}
	
	// Methode permettant de calculer la distance 
	// le parametre j permet de selectionner la distance utilisee
	public Double distance(Point p1, Point p2, int j, int q){
		
		int i=0;
		int longueur = p1.coord.size();
		Double sommeDistanceCarre=new Double(0);
		resultat=0.0;
		
		if (j==1){ // Distance euclidienne usuelle
			
			for(i=0; i<longueur; i++){
				sommeDistanceCarre = sommeDistanceCarre + Math.pow(p1.coord.get(i) - p2.coord.get(i), 2);
			}
			resultat=Math.sqrt(sommeDistanceCarre);
		}
		else if (j==2){ // Distance de Minkowski
			for(i=0; i<longueur; i++){
				sommeDistanceCarre = sommeDistanceCarre + Math.pow(Math.abs(p1.coord.get(i) - p2.coord.get(i)), q);
			}
			resultat=Math.pow(sommeDistanceCarre, 1/q);
			
		}
		else if (j==3){ // 2 fois le nombre de coordonnées différentes entre 2 vecteurs
			for(i=0; i<longueur; i++){
				if (p1.coord.get(i)!=p2.coord.get(i))
				resultat=resultat+1;
			}
			resultat=2*resultat;
		}
		else {
			for(i=0; i<longueur; i++){ // somme des valeurs absolues de la différece coordonnée par coordonnée
				sommeDistanceCarre = sommeDistanceCarre + Math.abs(p1.coord.get(i) - p2.coord.get(i));
			}
			resultat=sommeDistanceCarre;
			
		}
		return resultat;
	}

}
