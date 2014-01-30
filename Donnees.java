import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.*;

import javax.swing.JFrame;


public class Donnees {
	public LinkedList<Point> points;
	public Integer nbGroupes;
	public JFrame affichage;
	public LinkedList<GroupePoint> listeGroupe ;
	
	public Donnees (int k) {
		this.points = new LinkedList<Point>();
		this.nbGroupes = k;
		this.affichage = new JFrame();
		//Définit un titre pour notre fenêtre
		this.affichage.setTitle("KMeans");
	    //Définit sa taille avec une bordure de 100 pixels
		this.affichage.setSize(300, 300);
	    //Nous demandons maintenant à notre objet de se positionner au centre
		this.affichage.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
		this.affichage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.affichage.setVisible(true);
		this.listeGroupe = new  LinkedList<GroupePoint>();
		int j =0;
		
		for (j=0; j< this.nbGroupes ; j++){
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
				String[] coordonnees = ligne.split("[\t\n]", 2);
				Point newPoint = new Point();
				for (String string : coordonnees) {
					newPoint.coord.add(new Double(string));
					
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
		//affichage.add(comp);
	}
	
	public static void main(String[] args) {
		int k=2;
		Donnees data = new Donnees(k);
		data.lireFichier("exemple2.txt");
		System.out.println("Fin");
		//data.afficher();
		
		Random alea = new Random();
		for (int i = 0; i < k; i++) {
			data.listeGroupe.get(i).centre.coord = data.points.get(alea.nextInt(data.points.size())).coord;
		}
		
		
	}
	
}
