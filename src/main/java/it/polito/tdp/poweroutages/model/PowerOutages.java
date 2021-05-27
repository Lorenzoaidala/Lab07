package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutages implements Comparable<PowerOutages> {

	private int id;
	private Nerc nerc;
	private LocalDateTime start;
	private LocalDateTime end;
	private int affectedPeople;
	private long duration;
	private int year;
	public PowerOutages(int id, Nerc nerc, LocalDateTime start, LocalDateTime end, int affectedPeople) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.start = start;
		this.end = end;
		this.affectedPeople = affectedPeople;
			
		LocalDateTime temp = LocalDateTime.from(start);
		this.duration = temp.until(end, ChronoUnit.HOURS);
		this.year = start.getYear();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public int getAffectedPeople() {
		return affectedPeople;
	}
	public void setAffectedPeople(int affectedPeople) {
		this.affectedPeople = affectedPeople;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@Override
	public int compareTo(PowerOutages o) {	
		return this.start.compareTo(o.start);
	}
	
	
	
}
