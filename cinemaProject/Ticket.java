package cinemaProject;

public class Ticket {
	private String ticket_ID;
	private String movieTitle;	
	private String movieDate;
	private int movieTime;
	private String hallType;
	private String seatLocation;
	private String timePurchase;
	private int billAmount;
	private String newSeatLocation;

	public Ticket(String ticket_ID, String movieTitle, String movieDate, String movieTime, String hallType, Object seatSelected,
			String timePurchase, int billAmount) {
		this.setTicket_ID(ticket_ID);
		this.setMovieTitle(movieTitle);
		this.setMovieDate(movieDate);
		this.setMovieTime(Integer.parseInt(movieTime));
		this.setHallType(hallType);
		this.setSeatLocation(seatSelected+"");
		this.setTimePurchase(timePurchase);
		this.setBillAmount(billAmount);

	}

	public Ticket(String ticket_ID, String movieTitle, String showDate, int showTime, String hallType, String seatLocation,
			String timePurchase, int billAmount) {
		this.setTicket_ID(ticket_ID);
		this.setMovieTitle(movieTitle);
		this.setMovieDate(showDate);
		this.setMovieTime(showTime);
		this.setHallType(hallType);
		this.setSeatLocation(seatLocation);
		this.setTimePurchase(timePurchase);
		this.setBillAmount(billAmount);

	}

	public Ticket(String ticket_ID) {
		this.setTicket_ID(ticket_ID);
		
	}

	public Ticket(String ticID, String oldSeats, String newSeats) {
		this.setTicket_ID(ticket_ID);
		this.setSeatLocation(oldSeats);
		this.setNewSeatLocation(newSeats);
	}

	public String getTicket_ID() {
		return ticket_ID;
	}

	public void setTicket_ID(String ticket_ID2) {
		this.ticket_ID = ticket_ID2;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}


	public String getMovieDate() {
		return movieDate;
	}


	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}


	public int getMovieTime() {
		return movieTime;
	}


	public void setMovieTime(int movieTime) {
		this.movieTime = movieTime;
	}


	public String getHallType() {
		return hallType;
	}


	public void setHallType(String hallType) {
		this.hallType = hallType;
	}


	public String getSeatLocation() {
		return seatLocation;
	}


	public void setSeatLocation(String seatSelected) {
		this.seatLocation = seatSelected;
	}


	public String getTimePurchase() {
		return timePurchase;
	}


	public void setTimePurchase(String timePurchase) {
		this.timePurchase = timePurchase;
	}


	public int getBillAmount() {
		return billAmount;
	}


	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}

	public String getNewSeatLocation() {
		return newSeatLocation;
	}

	public void setNewSeatLocation(String newSeatLocation) {
		this.newSeatLocation = newSeatLocation;
	}
	
	@Override
	public String toString() {
		return "Ticket Receipt\nTicket ID\t: "+ ticket_ID+ "\nMovie Title\t: " + movieTitle + "\nMovie Date\t: " + movieDate + "\nShow Time\t: " + movieTime
				+ "\nHall Type\t\t: " + hallType + "\nSeat No.\t\t: " + seatLocation + "\nPurchase Time\t: " + timePurchase
				+ "\nTotal Amount\t: RM " + billAmount;
	}

}
