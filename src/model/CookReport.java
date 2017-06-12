package model;

public class CookReport {
	private long cookId;
	private String complaint;
	private int motivation;
	private String otherReason;
	
	public long getCookId() {
		return cookId;
	}
	public void setCookId(long cookId) {
		this.cookId = cookId;
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
