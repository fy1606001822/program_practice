package cn.ustc.sightdatacollector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Columns;

@Entity
public class SightData {
	private int id;
	private String sightName;
	private String sightScore;
	private String sightIntruduction;
	
	public SightData(){
		
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSightName() {
		return sightName;
	}

	public void setSightName(String sightName) {
		this.sightName = sightName;
	}

	public String getSightScore() {
		return sightScore;
	}

	public void setSightScore(String sightScore) {
		this.sightScore = sightScore;
	}
	@Column(length=32767)
	public String getSightIntruduction() {
		return sightIntruduction;
	}

	public void setSightIntruduction(String sightIntruduction) {
		this.sightIntruduction = sightIntruduction;
	}
	
	

}
