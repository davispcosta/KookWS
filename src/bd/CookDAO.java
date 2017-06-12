package bd;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import model.Comment;
import model.Cook;
import model.CookReport;
import model.Credential;
import model.UserPreferences;
import model.Recipe;

public class CookDAO extends BaseDAO<Cook> {
	public static final String TABLE = "cook";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PICTURE = "picture";
	public static final String MONETIZABLE = "monetizable";
	public static final String PREMIUM = "premium";
	public static final String EMAIL = "email";
	public static final String PASSWORD = "password";
	public static final String SINGUPDATE = "singupdate";
	public static final String PREMIUMEXPIRATIONDATE = "premiumexpirationdate";
	public static final String COUNTDAILYEXECUTIONS = "countdailyexecutions";
	public static final String COUNTFOLLOWERS = "countfollowers";
	public static final String LASTACESS = "lastacess";

	public CookDAO(String table) {
		super(table);
	}

	// nao leva em conta a tabela cook_kookbook, acho que pode ser feito com trigger
	public boolean createCook(Cook cook) {
		String SQL = createSimpleSqlInsert(new String[] { NAME, PICTURE,
				MONETIZABLE, PREMIUM, EMAIL, PASSWORD, SINGUPDATE,
				PREMIUMEXPIRATIONDATE, COUNTDAILYEXECUTIONS, COUNTFOLLOWERS,
				LASTACESS });

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setString(1, cook.getName());
			Blob blob = new javax.sql.rowset.serial.SerialBlob(
					cook.getPicture());
			ps.setBlob(2, blob);
			ps.setBoolean(3, cook.isMonetizable());
			ps.setBoolean(4, cook.isPremium());
			ps.setString(5, cook.getCredential().getEmail());
			ps.setString(6, cook.getCredential().getPassword());
			ps.setDate(7, new Date(cook.getSingUpDate().getTimeInMillis()));
			ps.setDate(8, new Date(cook.getPremiumExpirationDate()
					.getTimeInMillis()));
			ps.setInt(9, 0);
			ps.setInt(10, 0);
			ps.setDate(11, new Date(Calendar.getInstance().getTimeInMillis()));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean deleteCook(long cookId) {
		String SQL = createSimpleSqlDelete(ID);
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, cookId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCookName(long cookId, String name) {
		String SQL = "UPDATE kookbook SET c_name = " + name + " WHERE c_id = "
				+ cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCookPicture(long cookId, byte[] picture) {
		try {
			Blob blob = new javax.sql.rowset.serial.SerialBlob(picture);
			String SQL = "UPDATE kookbook SET c_picture = " + blob
					+ " WHERE c_id = " + cookId;
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCookMonetizable(long cookId, boolean monetizable) {
		String SQL = "UPDATE kookbook SET c_monetizable  = " + monetizable
				+ " WHERE c_id = " + cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCookPremium(long cookId, boolean premium) {
		String SQL = "UPDATE kookbook SET c_premium  = " + premium
				+ " WHERE c_id = " + cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCookPremiumExpiration(long cookId, Date newDate) {
		String SQL = "UPDATE kookbook SET c_premium_expiration_date  = "
				+ newDate + " WHERE c_id = " + cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateEmail(long cookId, String email) {
		String SQL = "UPDATE kookbook SET c_email  = " + email
				+ " WHERE c_id = " + cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updatePassword(long cookId, String password) {
		String SQL = "UPDATE kookbook SET c_password  = " + password
				+ " WHERE c_id = " + cookId;

		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateCountDailyExecutions(long cookId) {
		String SQL = "UPDATE cook SET c_count_daily_executions = c_count_daily_executions + 1 WHERE c_id = "
				+ cookId;
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateDietPreferences(long cookId, UserPreferences preferences) {
		String SQL = "UPDATE preferences SET p_vegetarian  = " + preferences.isVegetarian() + 
				",p_vegan = " + preferences.isVegan() + ",p_dairy_free = " + preferences.isDairyFree() + 
				",p_gluten_free = " + preferences.isGlutenFree() + ",p_seafood_free  = " + 
				preferences.isSeafoodFree() + ",p_peanut_free = " + preferences.isPeanutFree() + 
				" WHERE c_id = " + cookId;
		
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean reportCook(long cookId, CookReport report) {
		String SQL = "INSERT INTO cook_reports (c_id,rep_r_complaint,rep_r_motivation,rep_r_other_reason) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, cookId);
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

	public boolean followCook(long cookId, long followedCookId) {
		String SQL = "INSERT INTO follow (c_id,followed_id) VALUES (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, cookId);
			ps.setLong(2, followedCookId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean unfollowCook(long cookId, long followedCookId) {
		String SQL = "DELETE FROM follow WHERE (c_id = " + cookId
				+ " ) AND (followed_id = " + followedCookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public int getCountFollowersCook(long cookId) {
		String SQL = "SELECT c_count_followers FROM cook WHERE c_id = "
				+ cookId;
		int countFollowers = -1;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);

			countFollowers = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -countFollowers;
	}

	public boolean addUnwantedIngredient(long cookId, long ingredientId) {
		String SQL = "INSERT INTO unwanted (i_id,c_id) VALUES (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, ingredientId);
			ps.setLong(2, cookId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean removeUnwantedIngredient(long cookId, long ingredientId) {
		String SQL = "DELETE FROM unwanted WHERE (i_id = " + ingredientId
				+ " ) AND (c_id = " + cookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addWantedIngredient(long cookId, long ingredientId) {
		String SQL = "INSERT INTO wanted (i_id,c_id) VALUES (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, ingredientId);
			ps.setLong(2, cookId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean removeWantedIngredient(long cookId, long ingredientId) {
		String SQL = "DELETE FROM wanted WHERE (i_id = " + ingredientId
				+ " ) AND (c_id = " + cookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addIngredientOnShopppingList(long cookId, long ingredientId) {
		String SQL = "INSERT INTO shopping_list (i_id,c_id) VALUES (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, ingredientId);
			ps.setLong(2, cookId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean removeIngredientFromShoppingList(long cookId,
			long ingredientId) {
		String SQL = "DELETE FROM shopping_list WHERE (i_id = " + ingredientId
				+ " ) AND (c_id = " + cookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addIngredientsOnShoppingList(long cookId,
			List<Long> ingredientsIds) {
		String SQL = "INSERT INTO shopping_list (i_id,c_id) VALUES (?,?)";
		for (int i = 1; i < ingredientsIds.size(); i++) {
			SQL = SQL + ",(?,?)";
		}
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, ingredientsIds.get(0));
			ps.setLong(2, cookId);
			for (int i = 1; i < ingredientsIds.size(); i++) {
				ps.setLong(((2 * i) + 1), ingredientsIds.get(0));
				ps.setLong(((2 * i) + 2), cookId);
			}
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean removeAllIngredientsFromShoppingList(long cookId) {
		String SQL = "DELETE FROM shopping_list WHERE (c_id = " + cookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean addRecipeOnViewed(long cookId, long recipeId) {
		String SQL = "INSERT INTO viewed_recipe (r_id,c_id) VALUES (?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, recipeId);
			ps.setLong(2, cookId);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean makeComment(Comment comment) {
		String SQL = "INSERT INTO commented (comm_mensage,comm_date,r_id,c_id) VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setString(1, comment.getMensage());
			ps.setDate(2, new Date(comment.getDate().getTimeInMillis()));
			ps.setLong(3, comment.getCook());
			ps.setLong(4, comment.getRecipe());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public Cook findCookById(long cookId) {
		String SQL = "SELECT * FROM cook c_id = " + cookId;
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
			cook.setCredential(new Credential(res.getString(6), res
					.getString(7)));
			cook.getSingUpDate().setTime(res.getDate(8));
			cook.getPremiumExpirationDate().setTime(res.getDate(9));
			cook.setCountDailyExecutions(res.getInt(10));
			cook.setCountFollowers(res.getInt(11));
			cook.getLastAcess().setTime(res.getDate(12));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cook;
	}

	public List<Cook> findCookByName(String name) {
		List<Cook> cooks = new ArrayList<>();
		Cook cook = null;
		
		StringTokenizer str = new StringTokenizer(name);
		StringBuilder SQL = new StringBuilder();
		SQL.append("SELECT * FROM " + TABLE + " WHERE name LIKE '%");
		String s = str.nextElement().toString();

		if (s.length() >= 3) {
			SQL.append(s + "%'");
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
				
				cooks.add(cook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cooks;
	}

	public List<Cook> findCookByFollowers (int limit) {
		List<Cook> cooks = new ArrayList<>();
		Cook cook = null;
		
		String SQL = "SELECT * FROM cook LIMIT " + limit + " ORDER BY c_count_followers DESC";

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());

			while (res.next()) {

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
				
				cooks.add(cook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cooks;
	}
	
	public List<Cook> getFollowersCook(long cookId) {
		List<Cook> followers = null;
		String SQL = "SELECT * FROM cook INNER JOIN follow ON follow.followed_id = " + cookId;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());
			followers = new ArrayList<Cook>();
			while (res.next()) {
				Cook cook = new Cook();
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

				followers.add(cook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return followers;
	}

	public List<Cook> getFollowedCook(long cookId) {
		List<Cook> followed = null;
		String SQL = "SELECT * FROM cook INNER JOIN follow ON follow.c_id = " + cookId;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());
			followed = new ArrayList<Cook>();
			while (res.next()) {
				Cook cook = new Cook();
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

				followed.add(cook);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return followed;
	}

	public List<Recipe> getRecipesCreated(long cookId) {
		ArrayList<Recipe> recipes = new ArrayList<>();
		String SQL = "SELECT * FROM recipe WHERE c_id=" + cookId;

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
	
	public List<Recipe> getViewedRecipes (long cookId) {
		ArrayList<Recipe> recipes = new ArrayList<>();
		String SQL = "SELECT * FROM recipe INNER JOIN viewed_recipe ON recipe.r_id=viewed_recipe.r_id AND c_id=" + cookId;

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
}
