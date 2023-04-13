package model.Announcement;

public class Announcement {
	String title;
	String Description;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Announcement(String title, String description) {
		super();
		this.title = title;
		Description = description;
	}
	

}
