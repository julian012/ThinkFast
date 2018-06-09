package models;

import java.util.Date;

public class NotificationEmployee {
	
	private int idJob;
	private Date date;
	private StatusJobOffer jobOffer;
	
	public NotificationEmployee(int idJob, Date date, StatusJobOffer jobOffer) {
		this.idJob = idJob;
		this.date = date;
		this.jobOffer = jobOffer;
	}

	public int getIdJob() {
		return idJob;
	}

	public Date getDate() {
		return date;
	}

	public StatusJobOffer getJobOffer() {
		return jobOffer;
	}
}
