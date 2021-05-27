package it.polito.tdp.poweroutages.model;

import java.util.*;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<PowerOutages> partenza;
	private List<PowerOutages> result;
	private int maxPersoneCoinvolte;
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	 public List<PowerOutages> getPowerOutagesByNerc(Nerc nerc){
		 return podao.getPowerOutagesByNerc(nerc);
	 }
	 
	 public List<PowerOutages> getWorstCase(Nerc nerc, int maxYear, int maxHours){
		 
		 partenza = this.getPowerOutagesByNerc(nerc);
		 maxPersoneCoinvolte = 0;
		 Collections.sort(partenza);
		 cerca(partenza, maxYear,maxHours);
		 return result;
		 
	 }

	private void cerca(List<PowerOutages> parziale, int maxYear, int maxHours) {
		
		if(sommaPersoneCoinvolte(parziale)>maxPersoneCoinvolte) {
			maxPersoneCoinvolte = sommaPersoneCoinvolte(parziale);
			result = parziale;
		}
		for(PowerOutages po : parziale) {
			if(!result.contains(po)) {
				
			}
		}
		
	}

}
