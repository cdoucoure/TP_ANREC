import java.awt.Color;
import java.util.LinkedList;

public class GroupePoint {
	
	public Centre centre;
	public Color couleur;
	public String nom;	
	public LinkedList<Double> iks; // liste des interties intra-cluster à chaque itération
	
	public GroupePoint(int i){
		this.centre= new Centre();
		if (i==0) {
			this.couleur = Color.red;
		}
		else if (i==1) {
			this.couleur = Color.blue;
		}
		else if (i==2) {
			this.couleur = Color.green;
		}
		else if (i==3) {
			this.couleur = Color.yellow;
		}
		else {
			this.couleur = Color.white;
		}
		
		this.nom="Groupe" + i;
		System.out.println("Le " + this.nom + " a été créé.");
		iks=new LinkedList<Double>();
	}
	
	
	
	// Methode permettant de calculer le centre d'un groupe de points
	public boolean calculerCentre(Donnees donne, int parametreSimilarite, int puissance){
		Distance sim= new Distance();
		boolean convergence = true;
		int longueur = donne.points.getFirst().coord.size(); // nombre de coordonnees par point
		
		LinkedList<Double> coordonnesCentre= new LinkedList<Double>();
		LinkedList<Point> pointsCentre = new LinkedList<Point>();
		
		// on recupere les points de donne
		for (Point p : donne.points){
			if (p.groupe==this){
				pointsCentre.add(p);
			}
		}
		System.out.println(pointsCentre.size());
		
		// calcul des coordonnes du nouveau centre
		for(int i=0; i<longueur; i++){
			coordonnesCentre.add(i, 0.0);
			for (Point p : pointsCentre){
				coordonnesCentre.set(i, coordonnesCentre.get(i) + p.coord.get(i)/pointsCentre.size());
			}
		}
		
		
		
		Double ik = new Double(0);
		for(int i=0; i<longueur; i++){
			Double testConvergence = this.centre.coord.get(i);
			this.centre.coord.set(i, coordonnesCentre.get(i));
			Double testConvergence2 = this.centre.coord.get(i);
			if (Math.abs(testConvergence - testConvergence2)>0.00001) {
				convergence = false;
			}
			
		}
		this.centre.coord=coordonnesCentre;
		// Calcul de l'intertie intra-cluster (servira à cha itération en en vérifier la décroissance)
		for (Point p : pointsCentre){
				ik=ik+ sim.distance(this.centre, p, parametreSimilarite, puissance);
		}
		iks.add((double) Math.round(ik));
		
		
		System.out.println("Le " + this.nom + " a un centre de coordonnées (" + this.centre.coord.get(0) + "," + this.centre.coord.get(1) + ").");
		return convergence;
	}
	

}
