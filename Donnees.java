import java.io.*;
import java.util.*;

import javax.swing.*;



public class Donnees {
	public LinkedList<Point> points;
	public Integer nbGroupes;
	private JFrame fenetre; 
	private Affichage affichage;
	public LinkedList<GroupePoint> listeGroupe ;
	
	public Donnees (int k) {
		this.points = new LinkedList<Point>();
		this.nbGroupes = k;
		this.fenetre = new JFrame();
		this.affichage = new Affichage(this.points);
		this.fenetre.setContentPane(this.affichage);
		//DÃ©finit un titre pour notre fenÃªtre
		this.fenetre.setTitle("KMeans");
	    //DÃ©finit sa taille avec une bordure de 100 pixels
		this.fenetre.setSize(300, 300);
	    //Nous demandons maintenant Ã  notre objet de se positionner au centre
		this.fenetre.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetre.setVisible(true);

		this.listeGroupe = new  LinkedList<GroupePoint>();
		
		for (int j=0; j< this.nbGroupes ; j++){
			listeGroupe.add(new GroupePoint(j));
		}
	}
	
	private void lireFichier(String fichier) {
		
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				
				
				System.out.println(ligne);
				String[] coordonnees = ligne.split("[\t\n]", ligne.length());
				Point newPoint = new Point();
				int k=0;
				
				for (k=0; k <coordonnees.length; k++) {
					newPoint.coord.add(new Double(coordonnees[k]));
					
				}
				this.points.add(newPoint);
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
	}
	
	private void afficher() {
		this.affichage.points = this.points;
		this.affichage.repaint();
	}
	
	public static void main(String[] args) {
		
		// PARAMETRES DE DEPART
		int k=4;
		int paramSim =1 ; // permet de choisir le type de distance utilisée
		int puissance = 5; // parametre supplementaire necessaire pour la distance 2 (Minkowski)
		
		Donnees data = new Donnees(k);
		data.lireFichier("exemple.txt"); // nom du fichier à tester
		
		
		// choix de k centres aléatoires pour les k groupes	
		Random alea = new Random();
		LinkedList<Integer> listeIndex = new LinkedList<Integer>();
		
		for (int i = 0; i < k; i++) {
			int indexAlea = 0;
			boolean indexNouveau = false;
			while (!indexNouveau) {
				indexAlea = alea.nextInt(data.points.size());
				indexNouveau = true;
				for (Integer integer : listeIndex) {
					if(indexAlea == integer) {
						indexNouveau = false;
					}
				}
			}
			for (int j = 0; j < data.points.getFirst().coord.size(); j++) {
				data.listeGroupe.get(i).centre.coord.add(data.points.get(indexAlea).coord.get(j));
			}

		}
		
		// Iterations de l'algorithme
		boolean convergence = false;
		int iteration=0;
		while (!convergence) {
			convergence = true;
			iteration+=1;
			for (Point point : data.points) {
				point.grouper(data.listeGroupe, paramSim, puissance );
			}
			for (GroupePoint groupe : data.listeGroupe) {
				
				
				if(!groupe.calculerCentre(data, paramSim, puissance )) {
					convergence = false;
				}
			}
			System.out.println("Iteration    " + iteration);
			System.out.println("Affichage");
			data.afficher();

		}
		System.out.println("Fin");
		
		for (int i=0; i<k; i++){
			System.out.println(" Inertie intra-groupe du groupe :"+ i +" :" + data.listeGroupe.get(i).iks);
		}

		
	}
	
}
