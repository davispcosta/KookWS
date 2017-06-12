package model;

public class Portion {
	private String measure;
	private double amount;
	private Ingredient ingredient;
	
	public Portion(String measure, double amount, Ingredient ingredient) {
		this.measure = measure;
		this.amount = amount;
		this.ingredient = ingredient;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	
}
