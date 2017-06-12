package model;

import java.util.Calendar;

public class Cook {
	private long id;
	private String name;
	private byte[] picture;
	private boolean monetizable;
	private boolean premium;
	private Credential credential;
	private Calendar singUpDate;
	private Calendar premiumExpirationDate;
	private int countDailyExecutions;
	private int countFollowers;
	private Calendar lastAcess;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public boolean isMonetizable() {
		return monetizable;
	}
	public void setMonetizable(boolean monetizable) {
		this.monetizable = monetizable;
	}
	public boolean isPremium() {
		return premium;
	}
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	public Credential getCredential() {
		return credential;
	}
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	public Calendar getSingUpDate() {
		return singUpDate;
	}
	public void setSingUpDate(Calendar singUpDate) {
		this.singUpDate = singUpDate;
	}
	public Calendar getPremiumExpirationDate() {
		return premiumExpirationDate;
	}
	public void setPremiumExpirationDate(Calendar premiumExpirationDate) {
		this.premiumExpirationDate = premiumExpirationDate;
	}
	public int getCountDailyExecutions() {
		return countDailyExecutions;
	}
	public void setCountDailyExecutions(int countDailyExecutions) {
		this.countDailyExecutions = countDailyExecutions;
	}
	public int getCountFollowers() {
		return countFollowers;
	}
	public void setCountFollowers(int countFollowers) {
		this.countFollowers = countFollowers;
	}
	public Calendar getLastAcess() {
		return lastAcess;
	}
	public void setLastAcess(Calendar lastAcess) {
		this.lastAcess = lastAcess;
	}
		
}
