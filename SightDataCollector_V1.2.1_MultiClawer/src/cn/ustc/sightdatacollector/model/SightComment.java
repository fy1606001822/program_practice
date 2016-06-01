package cn.ustc.sightdatacollector.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SightComment {
	private int id;
	private String sighturl;
	private String comment;
	

	
	public SightComment(){
		
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSighturl() {
		return sighturl;
	}

	public void setSighturl(String sighturl) {
		this.sighturl = sighturl;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	

	
	
	
}
