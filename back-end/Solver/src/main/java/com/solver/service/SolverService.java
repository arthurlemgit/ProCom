package com.solver.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.solver.model.Donnee;
import com.solver.model.Modelisation;
import com.solver.model.Request;
import com.solver.model.Unavailable;

@Service
public class SolverService {
	
	public String solver(Request request) {
		int Nb_Semaines = request.getWeeksNumber();
		
		ArrayList<Integer> Dispo = new Donnee().Traduction(request.getUnavailables());
		
		ArrayList<Integer> DispoTest = new Donnee().Traduction(request.getUnavailables() , request.getStartDate() );
		
//		ArrayList<Integer> DispoTest = new ArrayList<>(Arrays.asList(
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1,
//				 1,1,1,1,1,1));
//		
		for(com.solver.model.Module module : request.getModulesUeA()) {
			module.setDispo(DispoTest);
		}
		for(com.solver.model.Module module : request.getModulesUeB()) {
			module.setDispo(DispoTest);
		}
		for(com.solver.model.Module module : request.getModulesUeC()) {
			module.setDispo(DispoTest);
		}
		
		 /*
		 ArrayList<Integer> Dispo = new ArrayList<Integer>(Nb_Semaines*6*2);
		 for (int i = 0 ; i<Nb_Semaines*6*2;i++) {
			 int x = Math.random()>.15?1:0; 
			 Dispo.add(x);
		 }
		  * 
		  */

		System.out.println(Dispo);
		int Result = 0;
		for (int i : Dispo) {
			if (i == 0) {
				Result = Result + 1;
			}
		}
		System.out.println(Result);

		Modelisation test = new Modelisation(request.getModulesUeA(), request.getModulesUeB(), request.getModulesUeC(), Nb_Semaines, Dispo,Dispo,request.getStartDate());

		test.BuildModel();
		test.addConstraints();
		test.solve();
		return test.getSolution();
	}
	
}
