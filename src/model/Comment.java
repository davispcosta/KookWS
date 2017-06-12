package model;

import java.util.Calendar;

public class Comment {
	private long cookId;
	private long recipeId;
	private String mensage;
	private Calendar date;
	
	public long getCook() {
		return cookId;
	}
	public void setCook(long cook) {
		this.cookId = cook;
	}
	public long getRecipe() {
		return recipeId;
	}
	public void setRecipe(long recipe) {
		this.recipeId = recipe;
	}
	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}

	
}
