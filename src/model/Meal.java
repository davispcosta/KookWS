package model;

public class Meal extends Category{
	public static int MEAL_BREAKFAST = 31;
	public static int MEAL_SNACK = 32;
	public static int MEAL_LUNCH = 33;
	public static int MEAL_DINNER = 34;
	public static int MEAL_NIGHT_SNACK = 35;
	
	public Meal(int id) {
		super(id);
		if (id == 31) {
			this.name = "Breakfast";
			//this.icon = ;
		} else if (id == 32) {
			this.name = "Snack";
			//this.icon = ;
		} else if (id == 33) {
			this.name = "Lunch";
			//this.icon = ;
		} else if (id == 34) {
			this.name = "Dinner";
			//this.icon = ;
		} else if (id == 35) {
			this.name = "Night Snack";
			//this.icon = ;
		}
	}

}
