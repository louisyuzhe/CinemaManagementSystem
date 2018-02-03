package cinemaProject;

public class Seat {
	private String hallType;
	private String rowInitial;
	private int seatNo;
	private String seatQuality;
	private boolean trayTable;
	private int price;
	private String f_hallType;
	private String f_rowInitial;
	private int f_seatNo;
	private String availability;
	public static String temp_rowInitial = "A";
	public static int temp_seatNo = 1;

	public Seat(String rowInitial, int seatNo, String availability){
		this.setRowInitial(rowInitial);
		this.setSeatNo(seatNo);
		this.setAvailability(availability);

	}
	public Seat(String hallType, String rowInitial, int seatNo){
		this.setHallType(hallType);
		this.setRowInitial(rowInitial);
		this.setSeatNo(seatNo);

	}

	public Seat(String hallType, String rowInitial, int seatNo, String seatQuality, boolean trayTable, int price, String availability){
		this.setHallType(hallType);
		this.setRowInitial(rowInitial);
		this.setSeatNo(seatNo);
		this.setSeatQuality(seatQuality);
		this.setTrayTable(trayTable);
		this.setPrice(price);
		this.setAvailability(availability);

	}

	public Seat(String hallType, String rowInitial, int seatNo, String f_hallType, String f_rowInitial, int f_seatNo) {
		this.setHallType(hallType);
		this.setRowInitial(rowInitial);
		this.setSeatNo(seatNo);
		this.setF_hallType(f_hallType);
		this.setF_rowInitial(f_rowInitial);
		this.setF_seatNo(f_seatNo);

	}

	public String getHallType() {

		return hallType;
	}

	public void setHallType(String hallType) {
		this.hallType = hallType;
	}

	public String getRowInitial() {
		return rowInitial;
	}

	public void setRowInitial(String rowInitial) {
		this.rowInitial = rowInitial;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getF_hallType() {
		return f_hallType;
	}

	public void setF_hallType(String f_hallType) {
		this.f_hallType = f_hallType;
	}

	public String getF_rowInitial() {
		return f_rowInitial;
	}

	public void setF_rowInitial(String f_rowInitial) {
		this.f_rowInitial = f_rowInitial;
	}

	public int getF_seatNo() {
		return f_seatNo;
	}

	public void setF_seatNo(int f_seatNo) {
		this.f_seatNo = f_seatNo;
	}

	public String getSeatQuality() {
		return seatQuality;
	}

	public void setSeatQuality(String seatQuality) {
		this.seatQuality = seatQuality;
	}

	public boolean isTrayTable() {
		return trayTable;
	}

	public void setTrayTable(boolean trayTable) {
		this.trayTable = trayTable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAvailability() {
		return availability;

	}
	public void setAvailability(String availability) {
		this.availability = availability;

	}

	@Override
	public String toString() {

		temp_rowInitial = rowInitial;
		temp_seatNo++;
		return rowInitial+ seatNo ;
	}

	public String toString2() {
		return "Hall Type\t\t\t: "+ hallType+ "\nSeat\t\t\t\t: "+rowInitial +seatNo 
				+"\nSeating type\t\t: " + seatQuality+ "\nTray Table needed\t: " 
				+trayTable+ "\nPrice\t\t\t\t: "+ price+ "\nAvailability\t\t: "+ availability;
	}


}