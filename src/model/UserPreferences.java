package model;

public class UserPreferences {
	private long cookId;
	private boolean vegetarian;
	private boolean vegan;
	private boolean dairyFree;
	private boolean glutenFree;
	private boolean seafoodFree;
	private boolean peanutFree;
	
	public long getCookId() {
		return cookId;
	}
	public void setCookId(long cookId) {
		this.cookId = cookId;
	}
	public boolean isVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}
	public boolean isVegan() {
		return vegan;
	}
	public void setVegan(boolean vegan) {
		this.vegan = vegan;
	}
	public boolean isDairyFree() {
		return dairyFree;
	}
	public void setDairyFree(boolean dairyFree) {
		this.dairyFree = dairyFree;
	}
	public boolean isGlutenFree() {
		return glutenFree;
	}
	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}
	public boolean isSeafoodFree() {
		return seafoodFree;
	}
	public void setSeafoodFree(boolean seafoodFree) {
		this.seafoodFree = seafoodFree;
	}
	public boolean isPeanutFree() {
		return peanutFree;
	}
	public void setPeanutFree(boolean peanutFree) {
		this.peanutFree = peanutFree;
	}

}
