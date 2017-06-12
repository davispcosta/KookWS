package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.Ingredient;

public class IngredientDAO extends BaseDAO<Ingredient>{
	public static final String TABLE = "ingredient";
	public static final String ID = "id";
	public static final String CAT_ID = "cat_id";
	public static final String NAME = "name";
	public static final String KCAL = "kcal";
	public static final String COUNT = "count_searched";

	public IngredientDAO() {
		super(TABLE);
	}
	
	public Ingredient findIngredientById (long ingredientId) {
		String SQL = "SELECT * FROM ingredient WHERE id = " + ingredientId;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(res.getLong(1));
				ingredient.setCat_id(res.getLong(2));
				ingredient.setName(res.getString(3));
				ingredient.setKcal(res.getInt(4));
				ingredient.setCount_searched(res.getInt(5));
				
				return ingredient;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Ingredient> findIngredientByName (String name) {
		List<Ingredient> ingredients = new ArrayList<>();

		StringTokenizer str = new StringTokenizer(name);
		StringBuilder SQL = new StringBuilder();
		SQL.append("SELECT * FROM " + TABLE + " WHERE name LIKE '%");
		String s = str.nextElement().toString();

		if (s.length() >= 3) {
			SQL.append( s + "%'");
		}
		while (str.hasMoreTokens()) {
			s = str.nextElement().toString();
			if (s.length() >= 3) {
				SQL.append(" AND '%" + s + "%'");
			}
		}

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());

			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(res.getLong(1));
				ingredient.setCat_id(res.getLong(2));
				ingredient.setName(res.getString(3));
				ingredient.setKcal(res.getInt(4));
				ingredient.setCount_searched(res.getInt(5));

				ingredients.add(ingredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
	
	public boolean updateIngredient (long ingredient_id) {
		String SQL = createSimpleSqlUpdate(new String[] { NAME, KCAL }, ID);

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			
			Ingredient ingredient = findIngredientById(ingredient_id);
			ps.setString(1, ingredient.getName());
			ps.setInt(2, ingredient.getKcal());
			ps.setLong(3, ingredient.getId());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean createIngredient (Ingredient ingredient) {
		String SQL = createSimpleSqlInsert(new String[] { CAT_ID, NAME, KCAL, COUNT });
		System.out.println(SQL);

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, ingredient.getCat_id());
			ps.setString(2, ingredient.getName());
			ps.setInt(3, ingredient.getKcal());
			ps.setInt(4, ingredient.getCount_searched());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean updateCountSearchedIngredient(long ingredient_id) {
		String SQL = "UPDATE ingredient SET i_count_searched  = i_count_searched  + 1 WHERE i_id  = "
				+ ingredient_id;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public List<Ingredient> getRecipesCreated(long ingredient_id) {
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		String SQL = "SELECT * FROM ingredient ORDER BY " + NAME;

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());

			while (res.next()) {
				Ingredient ingredient = new Ingredient();
				ingredient.setId(res.getLong(1));
				ingredient.setCat_id(res.getLong(2));
				ingredient.setName(res.getString(3));
				ingredient.setKcal(res.getInt(4));
				ingredient.setCount_searched(res.getInt(5));

				ingredients.add(ingredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}

}
