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
		 cerca(new ArrayList<PowerOutages>(), maxYear,maxHours);
		 return result;
		 
	 }

	private void cerca(List<PowerOutages> parziale, int maxYear, int maxHours) {
		
		if(sommaPersoneCoinvolte(parziale)>maxPersoneCoinvolte) {
			maxPersoneCoinvolte = sommaPersoneCoinvolte(parziale);
			result = new ArrayList<PowerOutages>(parziale);
		}
		for(PowerOutages po : partenza) {
			if(!parziale.contains(po)) {
				parziale.add(po);
				
				if(checkMaxYears(parziale)<maxYear && checkMaxHours(parziale)<maxHours) {
					cerca(parziale, maxYear,maxHours);
				}
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

	private int checkMaxYears(List<PowerOutages> parziale) {
		
		return 0;
	}

	private int sommaPersoneCoinvolte(List<PowerOutages> parziale) {
		int somma = 0;
		for(PowerOutages po : parziale) {
			somma = somma + po.getAffectedPeople();
		}
		return somma;
	}
	

}
