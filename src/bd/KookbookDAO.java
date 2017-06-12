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

import model.Categories;
import model.Cook;
import model.Credential;
import model.Kookbook;
import model.Recipe;

public class KookbookDAO extends BaseDAO<Kookbook> {
	public static final String TABLE = "kookbook";
	public static final String ID = "id";
	public static final String CREATOR = "creator";
	public static final String NAME = "name";
	public static final String PRIVATE = "privateKookbok";
	public static final String RECIPES = "receipes";
	public static final String FOLLOWERS = "followers";

	public KookbookDAO(String table) {
		super(table);
	}

	// nao leva em conta a tabela cook_kookbook, acho que pode ser feito com trigger
	public boolean createKookbook(Kookbook kookbook) {
		String SQL = createSimpleSqlInsert(new String[] { ID , NAME , CREATOR , PRIVATE });
		
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, kookbook.getId());
			ps.setString(2, kookbook.getName());
			ps.setLong(3, kookbook.getCreator());
			ps.setBoolean(4, kookbook.isPrivateKookbok());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean deleteKookbook(long kookbookId) {
		String SQL = createSimpleSqlDelete(ID);
		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, kookbookId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean updateKookbookName(long kookbookId, String newName) {
		String SQL = "UPDATE kookbook SET k_name = " + newName + " WHERE k_id = " + kookbookId;
		
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean updateKookbookPrivacy(long kookbookId, boolean privacy) {
		String SQL = "UPDATE kookbook SET k_private = " + privacy + " WHERE k_id = " + kookbookId;
		
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean addRecipeToKookbook(long kookbookId, long recipeId) {
		String SQL = "INSERT INTO kookbook_recipe (r_id,c_id,kr_date) VALUES (?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, kookbookId);
			ps.setLong(2, recipeId);
			ps.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean removeRecipeFromKookbook(long kookbookId, long recipeId) {
		String SQL = "DELETE FROM kookbook_recipe WHERE (k_id = " + kookbookId + " ) AND (r_id = " + recipeId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public long getKookbookCreator(long kookbookId) {
		String SQL = "SELECT c_id FROM cook_kookbook WHERE k_id = " + kookbookId;
		long cookId = -1;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			
			cookId = res.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cookId;
	}
	
	public boolean followKookbook(long cookId, long kookbookId){
		String SQL = "INSERT INTO cook_kookbook (k_id,c_id,ck_follow) VALUES (?,?,?)";

		try {
			PreparedStatement ps = connection.prepareStatement(SQL);
			ps.setLong(1, kookbookId);
			ps.setLong(2, cookId);
			ps.setBoolean(3, true);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public boolean unfollowKookbook(long cookId, long kookbookId){
		String SQL = "DELETE FROM cook_kookbook WHERE (k_id = " + kookbookId + " ) AND (c_id = " + cookId + " )";
		try {
			Statement st = connection.createStatement();
			st.executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Kookbook  findKookbookById (long kookbookId) {
		String SQL = "SELECT * FROM kookbook WHERE k_id = " + kookbookId;
		Kookbook kookbook = null;
		
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());
			while (res.next()) {
				kookbook = new Kookbook();
			
				kookbook.setId(res.getLong(1));
				kookbook.setCreator(res.getLong(2));
				kookbook.setName(res.getString(3));
				kookbook.setPrivateKookbok(res.getBoolean(4));
				kookbook.setCountViews(res.getInt(5));
				kookbook.setCountFollowers(res.getInt(6));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kookbook;
	}
	
	public List<Kookbook> findKookbookByName (String search) {
		ArrayList<Kookbook> kookbooks = new ArrayList<>();

		StringTokenizer str = new StringTokenizer(search);
		StringBuilder SQL = new StringBuilder();
		SQL.append("SELECT * FROM " + TABLE + " WHERE name LIKE '%");
		String s = str.nextElement().toString();

		if (s.length() >= 3) {
			SQL.append( s + "%'");
		}
		while (str.hasMoreTokens()) {
			s = str.nextElement().toString();
			SQL.append(" AND");
			if (s.length() >= 3) {
				SQL.append("'%" + s + "%'");
			}
		}

		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL.toString());
			while (res.next()) {
				Kookbook kookbook = new Kookbook();
				
				kookbook.setId(res.getLong(1));
				kookbook.setCreator(res.getLong(2));
				kookbook.setName(res.getString(3));
				kookbook.setPrivateKookbok(res.getBoolean(4));
				kookbook.setCountViews(res.getInt(5));
				kookbook.setCountFollowers(res.getInt(6));;
				
				kookbooks.add(kookbook);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kookbooks;
	}
	
	public List<Kookbook> findKookbookByCategory (Categories categories) {
		List<Kookbook> kookbooks = new ArrayList<>();
		String SQL = "SELECT * FROM kookbook INNER JOIN category_kookbook WHERE cat_id = " + categories.getCategory(0).getId();
		for (int i = 1; i < categories.getCategoryCount(); i++) {
			SQL = SQL + " OR cat_id = " + categories.getCategory(i).getId();
		}
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			while (res.next()) {
				Kookbook kookbook = new Kookbook();
				
				kookbook.setId(res.getLong(1));
				kookbook.setCreator(res.getLong(2));
				kookbook.setName(res.getString(3));
				kookbook.setPrivateKookbok(res.getBoolean(4));
				kookbook.setCountViews(res.getInt(5));
				kookbook.setCountFollowers(res.getInt(6));;
				
				if(!kookbooks.contains(kookbook)) {
					kookbooks.add(kookbook);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kookbooks;
	}

	public List<Recipe> getKookbookRecipes (long kookbookId) {
		List<Recipe> recipes = new ArrayList<>();
		String SQL = "SELECT * FROM recipe INNER JOIN kookbook_recipe ON kookbook_recipe.k_id=" + kookbookId;
		
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

	public int getCountFollowersKookbook (long kookbookId) {
		String SQL = "SELECT COUNT(c_id) FROM cook_kookbook WHERE ck_follow = true";
		int count = -1;
		try {
			Statement st = connection.createStatement();
			ResultSet res = st.executeQuery(SQL);
			count = res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Cook> getFollowersKookbook (long kookbookId) {
		List<Cook> followed = null;
		String SQL = "SELECT * FROM cook INNER JOIN cook_kookbook ON cook_kookbook.k_id = " + kookbookId;
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
}
