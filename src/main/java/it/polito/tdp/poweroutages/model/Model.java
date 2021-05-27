package it.polito.tdp.poweroutages.model;

import java.util.*;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages> partenza;
	List<PowerOutages> result;
	
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
		 cerca(partenza, maxYear,maxHours);
		 return result;
		 
	 }

	private void cerca(List<PowerOutages> parziale, int maxYear, int maxHours) {
		
		
		
	}

}
