import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;

public class ProCom {
	
	public static void main(String args[]) {
		
		//1. Création du solveur et du modèle associé
		Model model = new Model();
		Solver solver = model.getSolver();
		
		//2. Création des données
		int nbCoursdifferents = 4;
		int nbSemaines = 14;
		int nbCreneaux=nbSemaines*2*6; //Uniquement les mardis/mercredis
		
		//3. Déclaration des variables
		IntVar[][] agenda = model.intVarMatrix("agenda",12,nbSemaines,0, nbCoursdifferents);
		IntVar[] planning = model.intVarArray("creneaux", nbCreneaux, 0,nbCoursdifferents);
		IntVar[] nbSeances = model.intVarArray("nbSeances",nbCoursdifferents, 0, nbCreneaux);
		
		
		//4. Déclaration et pose des contraintes
		model.arithm(nbSeances[1], "=", 22).post(); //MAPD
		model.arithm(nbSeances[2], "=", 33).post(); //IDL
		model.arithm(nbSeances[3], "=", 11).post();	//ECO
		
		for (int i=0; i<nbCoursdifferents; i++) {
				model.count(i, planning, nbSeances[i]).post(); //On compte le nombre de gardes total de chacune des pharmacies				
		 }

		
		for (int i=0; i<12; i++) {
			for (int j=0; j<nbSemaines; j++) {
				model.arithm(agenda[i][j], "=", planning[12*j+i]).post();
			}
		}
		
		// 5. Définition de la stratégie de recherche (facultatif, il en existe une par défaut)
		solver.setSearch(Search.lastConflict(Search.randomSearch(planning, 0)));
	 
		// 6. Recherche et affichage d’une solution
		 solver.showSolutions();
		 solver.findSolution();
		  
		// 7. Affichage des statistiques de la recherche
		solver.printStatistics();
		for (int i=0; i<12; i++) {
			for (int j=0; j<nbSemaines; j++) {
				System.out.println(agenda[i][j]);
			}
		}
	}
}
