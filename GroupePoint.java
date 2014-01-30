import java.awt.Color;
import java.util.LinkedList;

public class GroupePoint {
	
	public Centre centre;
	public Color couleur;
	public String nom;	
	
	public GroupePoint(int i){
		this.centre= new Centre();
		this.couleur=Color.black;
		this.nom="Groupe" + i;
		
	}
	
	
	
	// Methode permettant de calculer le centre d'un groupe de points
	public void calculerCentre(Donnees donne){
		int longueur = donne.points.getFirst().coord.size();
		Double[] coordonnesCentre= new Double[longueur];// Liste des points ayant pour groupe this
		int i=0;
		LinkedList<Point> pointsCentre=null;
		
		for (Point p : donne.points){
			if (p.groupe==this){
				pointsCentre.add(p);
			}
		}
		
		// calcul des coordonnes du nouveau point
		for (Point p : pointsCentre){
			i=0;
			for(i=0; i<p.coord.size(); i++){
				coordonnesCentre[i]=coordonnesCentre[i] + p.coord.get(i);
			}
		}
		
		i=0;
		for(i=0; i<longueur; i++){
			this.centre.coord.set(i, coordonnesCentre[i]/longueur);
		}
	}
}
