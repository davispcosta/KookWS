package bd;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.Categories;
import model.Cook;
import model.Credential;
import model.Recipe;
import model.RecipeReport;

public class RecipeDAO extends BaseDAO<Recipe> {
	public static final String TABLE = "recipe";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PICTURE = "picture";
	public static final String LIKES = "likes";
	public static final String DISLIKES = "dislikes";
	public static final String DIFFICULTY = "difficulty";
	public static final String SERVING = "serving";
	public static final String PREP_TIME = "prep_time";
	public static final String COOK_TIME = "cook_time";
	public static final String CREATION_DATE = "creation_date";
	public static final String DESCRIPTION = "description";
	public static final String EXTRAS = "extras";
	public static final String ACTIVE = "active";
	public static final String COUNT_REPORT = "count_report";
	public static final String COUNT_INGREDIENTS = "count_ingredients";
	public static final String COUNT_VIEWS = "count_views";
	public static final String COUNT_EXECUTE = "count_execute";

	public RecipeDAO() {
		super(TABLE);
	}
	
	public boolean createRecipe (Recipe recipe) {
		String SQL = createSimpleSqlInsert(new String[] { NAME, PICTURE,
				LIKES, DISLIKES, DIFFICULTY, SERVING, PREP_TIME, COOK_TIME,
				CREATION_DATE, DESCRIPTION, EXTRAS, ACTIVE, COUNT_REPORT,
				COUNT_INGREDIENTS });
		System.out.println(SQL);

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setString(1, recipe.getName());
			Blob blob = new javax.sql.rowset.serial.SerialBlob(
					recipe.getPicture());
			ps.setBlob(2, blob);
			ps.setInt(3, recipe.getLikes());
			ps.setInt(4, recipe.getDislikes());
			ps.setDouble(5, recipe.getDifficulty());
			ps.setInt(6, recipe.getServings());
			ps.setInt(7, recipe.getPrepTime());
			ps.setInt(8, recipe.getCookTime());
			ps.setDate(9, new Date(recipe.getCreationDate().getTimeInMillis()));
			ps.setString(10, recipe.getDescription());
			ps.setString(11, recipe.getExtras());
			ps.setBoolean(12, recipe.isActive());
			ps.setInt(13, recipe.getCountReports());
			ps.setInt(14, recipe.getCountIngredients());
			// checar se tem problema adicionar nulos, tratar
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean deleteRecipe (long recipeId) {
		String SQL = createSimpleSqlDelete(ID);
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, recipeId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean turnOffRecipe (long recipeId) {
		String SQL = "UPDATE recipe SET r_active = " + false + " WHERE r_id = " + recipeId;
		
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean updateRecipe (Recipe recipe) {
		String SQL = createSimpleSqlUpdate(new String[] { NAME, PICTURE,
				DIFFICULTY, SERVING, PREP_TIME, COOK_TIME, DESCRIPTION, 
				EXTRAS}, ID);

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setString(1, recipe.getName());

			Blob blob = new javax.sql.rowset.serial.SerialBlob(
					recipe.getPicture());

			ps.setBlob(2, blob);
			ps.setDouble(3, recipe.getDifficulty());
			ps.setInt(4, recipe.getServings());
			ps.setInt(5, recipe.getPrepTime());
			ps.setInt(6, recipe.getCookTime());
			ps.setString(7, recipe.getDescription());
			ps.setString(8, recipe.getExtras());
			ps.setLong(9, recipe.getId());
			// checar se tem problema adicionar nulos, tratar
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateDifficulty (long recipeId, double difficulty) {
		String SQL = "UPDATE recipe SET r_difficulty = " + difficulty + " WHERE r_id = " + recipeId;
		
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean decreaseLike (long recipeId) {
		String SQL = "UPDATE recipe SET r_like = r_like - 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean increaseLike (long recipeId) {
		String SQL = "UPDATE recipe SET r_like = r_like + 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean decreaseDislike (long recipeId) {
		String SQL = "UPDATE recipe SET r_dislike = r_dislike - 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean increaseDislike (long recipeId) {
		String SQL = "UPDATE recipe SET r_dislike = r_dislike + 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean increaseExecutionsCount (long recipeId) {
		String SQL = "UPDATE recipe SET r_count_execute = r_count_execute + 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean increaseViewCount (long recipeId) {
		String SQL = "UPDATE recipe SET r_count_views = r_count_views + 1 WHERE r_id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean reportRecipe (long recipeId, RecipeReport report) {
		String SQL = "INSERT INTO recipe_reports (r_id,rep_r_complaint,rep_r_motivation,rep_r_other_reason) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, recipeId);
			ps.setString(2, report.getComplaint());
			ps.setInt(3, report.getMotivation());
			ps.setString(4, report.getOtherReason());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public Cook getRecipeCreator (long recipeId) {
		String SQL = "SELECT * FROM cook INNER JOIN recipe ON recipe.r_id = " + recipeId + " AND recipe.c_id=cook_c_id";
		Cook cook = null; 
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			
			cook = new Cook();
			cook.setId(res.getLong(1));
			cook.setName(res.getString(2));
			
			Blob blob = res.getBlob(3);
			int blobLength = (int) blob.length();
			byte[] picture = blob.getBytes(1, blobLength);
			blob.free();
			
			cook.setPicture(picture);
			cook.setMonetizable(res.getBoolean(4));
			cook.setPremium(res.getBoolean(5));
			cook.setCredential(new Credential(res.getString(6), res.getString(7)));
			cook.getSingUpDate().setTime(res.getDate(8));
			cook.getPremiumExpirationDate().setTime(res.getDate(9));
			cook.setCountDailyExecutions(res.getInt(10));
			cook.setCountFollowers(res.getInt(11));
			cook.getLastAcess().setTime(res.getDate(12));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cook;
	}
	
	public Recipe findRecipeById (long recipeId) {
		String SQL = "SELECT * FROM recipe WHERE id = " + recipeId;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setId(res.getLong(1));
				recipe.setName(res.getString(2));

				Blob blob = res.getBlob(3);
				int blobLength = (int) blob.length();
				byte[] picture = blob.getBytes(1, blobLength);
				blob.free();

				recipe.setPicture(picture);
				recipe.setLikes(res.getInt(4));
				recipe.setDislikes(res.getInt(5));
				recipe.setDifficulty(res.getDouble(6));
				recipe.setServings(res.getInt(7));
				recipe.setPrepTime(res.getInt(8));
				recipe.setCookTime(res.getInt(9));
				recipe.getCreationDate().setTime(res.getDate(10));
				recipe.setDescription(res.getString(11));
				recipe.setExtras(res.getString(12));
				recipe.setActive(res.getBoolean(13));
				recipe.setCountReports(res.getInt(14));
				recipe.setCountIngredients(res.getInt(15));
				recipe.setCountViews(res.getInt(16));
				recipe.setCountExecute(res.getInt(17));

				return recipe;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Recipe> findRecipeByName (String name) {
		List<Recipe> recipes = new ArrayList<>();

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
				Recipe recipe = new Recipe();
				recipe.setId(res.getLong(1));
				recipe.setName(res.getString(2));

				Blob blob = res.getBlob(3);
				int blobLength = (int) blob.length();
				byte[] picture = blob.getBytes(1, blobLength);
				blob.free();

				recipe.setPicture(picture);
				recipe.setLikes(res.getInt(4));
				recipe.setDislikes(res.getInt(5));
				recipe.setDifficulty(res.getDouble(6));
				recipe.setServings(res.getInt(7));
				recipe.setPrepTime(res.getInt(8));
				recipe.setCookTime(res.getInt(9));
				recipe.getCreationDate().setTime(res.getDate(10));
				recipe.setDescription(res.getString(11));
				recipe.setExtras(res.getString(12));
				recipe.setActive(res.getBoolean(13));
				recipe.setCountReports(res.getInt(14));
				recipe.setCountIngredients(res.getInt(15));
				recipe.setCountViews(res.getInt(16));
				recipe.setCountExecute(res.getInt(17));

				recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	
	public List<Recipe> findRecipeByCategory (Categories categories) {
		List<Recipe> recipes = new ArrayList<>();

		String SQL = "SELECT * FROM recipe INNER JOIN category_recipe WHERE cat_id = " + categories.getCategory(0).getId();
		for (int i = 1; i < categories.getCategoryCount(); i++) {
			SQL = SQL + " OR cat_id = " + categories.getCategory(i).getId();
		}

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());

			while (res.next()) {
				Recipe recipe = new Recipe();
				recipe.setId(res.getLong(1));
				recipe.setName(res.getString(2));

				Blob blob = res.getBlob(3);
				int blobLength = (int) blob.length();
				byte[] picture = blob.getBytes(1, blobLength);
				blob.free();

				recipe.setPicture(picture);
				recipe.setLikes(res.getInt(4));
				recipe.setDislikes(res.getInt(5));
				recipe.setDifficulty(res.getDouble(6));
				recipe.setServings(res.getInt(7));
				recipe.setPrepTime(res.getInt(8));
				recipe.setCookTime(res.getInt(9));
				recipe.getCreationDate().setTime(res.getDate(10));
				recipe.setDescription(res.getString(11));
				recipe.setExtras(res.getString(12));
				recipe.setActive(res.getBoolean(13));
				recipe.setCountReports(res.getInt(14));
				recipe.setCountIngredients(res.getInt(15));
				recipe.setCountViews(res.getInt(16));
				recipe.setCountExecute(res.getInt(17));

				recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	
	public List<Recipe> findRecipeByPopularity (long recipeId) {
		ArrayList<Recipe> recipes = new ArrayList<>();
		String SQL = "SELECT * FROM " + TABLE + " ORDER BY " + LIKES + " DESC";
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);

			while (res.next()) {

				Recipe recipe = new Recipe();
				recipe.setId(res.getLong(1));
				recipe.setName(res.getString(2));

				Blob blob = res.getBlob(3);
				int blobLength = (int) blob.length();
				byte[] picture = blob.getBytes(1, blobLength);
				blob.free();

				recipe.setPicture(picture);
				recipe.setLikes(res.getInt(4));
				recipe.setDislikes(res.getInt(5));
				recipe.setDifficulty(res.getDouble(6));
				recipe.setServings(res.getInt(7));
				recipe.setPrepTime(res.getInt(8));
				recipe.setCookTime(res.getInt(9));
				recipe.getCreationDate().setTime(res.getDate(10));
				recipe.setDescription(res.getString(11));
				recipe.setExtras(res.getString(12));
				recipe.setActive(res.getBoolean(13));
				recipe.setCountReports(res.getInt(14));
				recipe.setCountIngredients(res.getInt(15));
				recipe.setCountViews(res.getInt(16));
				recipe.setCountExecute(res.getInt(17));

				recipes.add(recipe);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipes;
	}
	
}