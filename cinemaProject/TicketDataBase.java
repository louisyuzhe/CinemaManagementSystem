package cinemaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TicketDataBase {

	Statement statement;
	TicketDataBase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemadatabase", "root", "G7h7y7@@");
			statement = con.createStatement();
		} catch (Exception e) {


		}
	}

	public ArrayList<Ticket> getAllRecords() {
		ArrayList <Ticket> list = new ArrayList <Ticket>();

		String sql = "SELECT * FROM `ticket`;";

		try {
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				String ticket_ID = result.getString("ticket_ID");
				String movieTitle = result.getString("movieTitle");
				String showDate = result.getString("showDate");
				int showTime = result.getInt("showTime");
				String hallType = result.getString("hallType");
				String seatLocation = result.getString("seatLocation");
				String timePurchase = result.getString("timePurchase");
				int billAmount = result.getInt("billAmount");
				Ticket tic = new Ticket(ticket_ID, movieTitle, showDate, showTime, hallType, seatLocation, timePurchase, billAmount);
				list.add(tic);
			}

		} catch (Exception e) {


		}

		return list;
	}

	public Ticket searchTicket(Ticket tic){
		String sql = "SELECT * FROM `ticket` WHERE `ticket_ID` = '"+ tic.getTicket_ID()+ "';";

		try {
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				String ticket_ID = result.getString("ticket_ID");
				String movieTitle = result.getString("movieTitle");
				String showDate = result.getString("showDate");
				int showTime = result.getInt("showTime");
				String hallType = result.getString("hallType");
				String seatLocation = result.getString("seatLocation");
				String timePurchase = result.getString("timePurchase");
				int billAmount = result.getInt("billAmount");
				tic = new Ticket(ticket_ID, movieTitle, showDate, showTime, hallType, seatLocation, timePurchase, billAmount);

			}

		} catch (Exception e) {


		}

		return tic;
	}

	public boolean addNewTicket(Ticket tic){
		boolean success = true;

		try{
			String sql = "INSERT INTO `ticket`(`ticket_ID`, `movieTitle`, `showDate`, `showTime`, "
					+ "`hallType`, `seatLocation`, `timePurchase`, `billAmount`) VALUES ('" 
					+ tic.getTicket_ID()+ "', '"+ tic.getMovieTitle()+ "', '" + tic.getMovieDate()+ "', '" 
					+ tic.getMovieTime()+ "', '"+ tic.getHallType()+ "', '"+ tic.getSeatLocation()
					+ "', '"+ tic.getTimePurchase()+ "', '"+ tic.getBillAmount()+ "')"; 

			try{
				int num = statement.executeUpdate(sql);

				if(num < 1)
					success = false;
			} catch (java.sql.SQLIntegrityConstraintViolationException ex1){
				success = false;
			}
		}catch (Exception ex2){
			ex2.printStackTrace();
		}
		return success;
	}

	public boolean updateTicket(Ticket tic) {
		boolean success = true;

		try{

			String sql = "UPDATE `ticket` SET `ticket_ID` ='"+ tic.getTicket_ID()+"',`movieTitle`= '"+ tic.getMovieTitle()
			+ "',`showDate`= '" +tic.getMovieDate()+ "',`showTime`= '" +tic.getMovieTime() 
			+ "',`hallType`= '" +tic.getHallType()+ "',`seatLocation`= '" +tic.getSeatLocation() 
			+ "'WHERE (`ticket_ID`,`movieTitle`)= ('"+ tic.getTicket_ID()+ "','"+ tic.getMovieTitle()+ "');";
			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			ex.printStackTrace();
		}
		return success;
	}

	public boolean deleteTicket(Ticket tic){
		boolean success = true;

		try{
			String sql = "DELETE FROM `ticket` WHERE `ticket_ID` = '"
					+ tic.getTicket_ID()+"'"; 

			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			ex.printStackTrace();
		}
		return success;
	}

	public boolean updateTicket(String ticID, String oldSeats, String newSeats) {
		boolean success = true;

		try{
			String sql = "UPDATE `ticket` SET `seatLocation`= '" + newSeats+ "' WHERE `ticket_ID`='"
					+ ticID+ "';";

			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			success =false;
		}
		return success;
	}

	public static void main (String [] args){

	}
}