package model;

import java.util.List;

public class Categories {
	List<Category> categories;

	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void removeCategory (Category category) {
		categories.remove(category);
	}
	
	public Category getCategory(int index) {
		return categories.get(index);
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public int	getCategoryCount()  {
		return categories.size();
	}
	
	public boolean hasCategory(Category category) {
		return categories.contains(category);
	}
}
