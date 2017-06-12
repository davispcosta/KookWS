package model;
import java.util.Calendar;
import java.util.List;

public class Recipe {
	private long id;
	private String name;
	private byte[] picture;
	private int likes;
	private int dislikes;
	private double difficulty;
	private int servings;
	private int prepTime;
	private int cookTime;
	private Calendar creationDate = Calendar.getInstance();;
	private String description;
	private String extras;
	private boolean active;
	private Cook creator; // como pegar isso do banco??
	private int countReports;
	private int countIngredients;
	private int countViews;
	private int countExecute;
	private Categories categories;
	private List<RecipeStep> steps;
	private List<Ingredient> ingredients;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	public int getServings() {
		return servings;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public int getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	public int getCookTime() {
		return cookTime;
	}
	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExtras() {
		return extras;
	}
	public void setExtras(String extras) {
		this.extras = extras;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Cook getCreator() {
		return creator;
	}
	public void setCreator(Cook creator) {
		this.creator = creator;
	}
	public int getCountReports() {
		return countReports;
	}
	public void setCountReports(int countReports) {
		this.countReports = countReports;
	}
	public int getCountIngredients() {
		return countIngredients;
	}
	public void setCountIngredients(int countIngredients) {
		this.countIngredients = countIngredients;
	}
	public int getCountViews() {
		return countViews;
	}
	public void setCountViews(int countViews) {
		this.countViews = countViews;
	}
	public int getCountExecute() {
		return countExecute;
	}
	public void setCountExecute(int countExecute) {
		this.countExecute = countExecute;
	}
	public void addCategory(Category category) {
		this.categories.addCategory(category);
	}
	public Categories getCategories() {
		return categories;
	}
	public List<RecipeStep> getSteps() {
		return steps;
	}
	public void setSteps(List<RecipeStep> steps) {
		this.steps = steps;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
