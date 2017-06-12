package model;

public class Ingredient {
	private long id;
	private long cat_id;
	private String name;
	private int kcal;
	private int count_searched;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCat_id() {
		return cat_id;
	}
	public void setCat_id(long cat_id) {
		this.cat_id = cat_id;
	}
	public int getCount_searched() {
		return count_searched;
	}
	public void setCount_searched(int count_searched) {
		this.count_searched = count_searched;
	}
}
