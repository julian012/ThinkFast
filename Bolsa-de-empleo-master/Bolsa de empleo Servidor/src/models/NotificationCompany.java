package models;

import java.util.Date;

public class NotificationCompany {
	
	private int jobOffer;
	private int idEmployee;
	private Date date;
	private StatusOffer offer;
	
	public NotificationCompany(int jobOffer, int idEmployee, Date date, StatusOffer offer) {
		this.jobOffer = jobOffer;
		this.idEmployee = idEmployee;
		this.date = date;
		this.offer = offer;
	}

	public int getJobOffer() {
		return jobOffer;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public Date getDate() {
		return date;
	}

	public StatusOffer getOffer() {
		return offer;
	}
	
}
