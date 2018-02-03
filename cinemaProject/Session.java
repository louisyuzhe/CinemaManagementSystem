package cinemaProject;

public class Session {
	String title;
	int timeStart ; //In minutes form
	String type;
	String Status;
	String Date;
	final int duration = 240;
	int timeEnd = timeStart+duration;
	
	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}

	public Session(String title, String Date,int timeStart, String type, String Status) {
		this.title = title;
		this.Date = Date;
		this.timeStart = timeStart;
		this.type = type;
		this.Status = Status;

	}


	public Session(String title, String Date, String timeStart) {
		this.title = title;
		this.Date = Date;
		this.timeStart = Integer.parseInt(timeStart);
		
	}

	@Override
	public String toString() {
		return "Session{" + "title=" + title + ", timeStart=" + timeStart + ", type=" + type + ", Status=" + Status + ", Date=" + Date + '}'+"\n";
	}








}
