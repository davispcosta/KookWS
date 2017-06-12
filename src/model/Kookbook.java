package model;
import java.util.List;


public class Kookbook {
	private long id;
	private long creator;
	private String name;
	private boolean privateKookbok;
	private int countViews;
	private int countFollowers;
	// nao seria melhor colocar só os ids??
	private List<Long> receipes;
	private List<Long> followers;
	private Categories categories;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCreator() {
		return creator;
	}
	public void setCreator(long creator) {
		this.creator = creator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPrivateKookbok() {
		return privateKookbok;
	}
	public void setPrivateKookbok(boolean privateKookbok) {
		this.privateKookbok = privateKookbok;
	}
	public int getCountViews() {
		return countViews;
	}
	public void setCountViews(int countViews) {
		this.countViews = countViews;
	}
	public int getCountFollowers() {
		return countFollowers;
	}
	public void setCountFollowers(int countFollowers) {
		this.countFollowers = countFollowers;
	}
	public List<Long> getReceipes() {
		return receipes;
	}
	public void setReceipes(List<Long> receipes) {
		this.receipes = receipes;
	}
	public List<Long> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Long> followers) {
		this.followers = followers;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
}
