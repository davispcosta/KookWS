package model;

public class RecipeReport {
	public static int OTHER = 0;
	public static int DUPLICATE = 1;
	public static int WRONG_CATEGORY = 2;
	public static int OFFENCIVE_COMMENTS = 3;
	
	private long recipeId;
	private String complaint;
	private int motivation;
	private String otherReason;
	
	public long getRecipe() {
		return recipeId;
	}
	public void setRecipe(long recipeId) {
		this.recipeId = recipeId;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public int getMotivation() {
		return motivation;
	}
	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}
	public String getOtherReason() {
		return otherReason;
	}
	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}	
}
