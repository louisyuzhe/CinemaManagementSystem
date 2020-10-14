package cinemaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SeatDataBase {

	Statement statement;
	SeatDataBase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemadatabase", "root", "");
			statement = con.createStatement();

		} catch (Exception e) {

			 
		}
	}

	public ArrayList <Seat> getAllRecords(String halltype){
		ArrayList <Seat> list = new ArrayList <Seat>();
		String sql = "SELECT `Row Initial`, `Seat Number`, `Availability` FROM `seat` WHERE `hallType` = '"+ halltype+ "';";

		try {
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				String rowInitial = result.getString("Row Initial");
				int seatNo = result.getInt("Seat Number");
				String availability = result.getString("Availability");
				Seat seat1 = new Seat(rowInitial, seatNo , availability);
				list.add(seat1);
			}

		} catch (Exception e) {

			 
		}

		return list;
	}

	public Seat getSeatRecord(Seat seat){
		Seat seat1 = null;

		String sql = "SELECT * FROM `seat` WHERE (`hallType`,`Row Initial`,`Seat Number`) = ('"+ seat.getHallType() 
		+ "','"+ seat.getRowInitial()+ "','"+ seat.getSeatNo()+ "');";

		try {
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				String hallType = result.getString("hallType");
				String rowInitial = result.getString("Row Initial");
				int seatNo = result.getInt("Seat Number");
				String seatQuality = result.getString("Seat Quality");
				String trayTable = result.getString("Tray Table");
				int price = result.getInt("Price");
				String availability = result.getString("Availability");
				seat1 = new Seat(hallType, rowInitial, seatNo, seatQuality, Boolean.parseBoolean(trayTable), price, availability);

			}

		} catch (Exception e) {

			 
		}

		return seat1;

	}

	public boolean addNewSeat(Seat seat){
		boolean success = true;

		try{
			String sql = "INSERT INTO `seat`(`hallType`, `Row Initial`, `Seat Number`, `Seat Quality`, `Tray Table`, `Price`, `Availability`) VALUES ('" 
					+seat.getHallType()+ "', '"+ seat.getRowInitial()+ "', '" + seat.getSeatNo()+ "', '" 
					+ seat.getSeatQuality()+ "', '" + seat.isTrayTable()+ "', '" + seat.getPrice()+ "', '" + seat.getAvailability()+ "')"; 

			try{
				int num = statement.executeUpdate(sql);

				if(num < 1)
					success = false;
			} catch (java.sql.SQLIntegrityConstraintViolationException ex1){
				success = false;
			}
		}catch (Exception ex2){
		
		}
		return success;
	}

	public boolean updateSeat(Seat seat) {
		boolean success = true;

		try{

			String sql = "UPDATE `seat` SET `Seat Quality` = '"
					+ seat.getSeatQuality() +"',`Tray Table` = '" +seat.isTrayTable() 
					+"',`Price` = '" +seat.getPrice()+"',`Availability` = '" +seat.getAvailability()  
					+"'	WHERE `hallType` = '"+ seat.getHallType()
					+"' and `Row Initial` = '"+ seat.getRowInitial() 
					+"' and `Seat Number` = '" +seat.getSeatNo() +"';";
			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			 
		}
		return success;
	}

	public boolean updateSeat(String hallType, String rowIni, int seatNO, String avail) {
		boolean success = true;

		try{

			String sql = "UPDATE `seat` SET `Availability` = '"
					+ avail+ "'WHERE `hallType` = '"+ hallType +"' and `Row Initial` = '"
					+ rowIni+ "' and `Seat Number` = '" +seatNO +"';";
			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			 
		}
		return success;
	}

	public boolean changeSeat(Seat seat){
		boolean success = true;

		try{
			String sql = "UPDATE `seat` SET `Row Initial`= '"
					+seat.getF_rowInitial() +"',`Seat Number`= '" +seat.getF_seatNo() 
					+"' WHERE `Row Initial` = '"+seat.getRowInitial() 
					+"' and `Seat Number` = '" +seat.getSeatNo() +"';";
			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			
		}
		return success;
	}

	public boolean deleteSeat(Seat seat){
		boolean success = true;

		try{
			String sql = "DELETE FROM `seat` WHERE (`hallType`,`Row Initial`,`Seat Number`) = ('"
					+ seat.getHallType()+ "', '"+ seat.getRowInitial()+ "', '" + seat.getSeatNo()+ "')"; 

			int num = statement.executeUpdate(sql);

			if(num < 1)
				success = false;

		}catch (Exception ex){
			 
		}
		return success;
	}

	public boolean restoreSeat(Seat seat){
		boolean success = true;

		try{
			String sql = "INSERT INTO `seat`(`hallType`, `Row Initial`, `Seat Number`) VALUES ('" 
					+seat.getHallType()+ "', '"+ seat.getRowInitial()+ "', '" + seat.getSeatNo()+ "')"; 

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

	public static void main (String [] args){

	}

	public void updateSeat(String hallType, String rowInitial, String seatNo, String avail) {

		try{

			String sql = "UPDATE `seat` SET `Availability` = '"
					+ avail+ "'WHERE `hallType` = '"+ hallType +"' and `Row Initial` = '"
					+ rowInitial+ "' and `Seat Number` = '" +seatNo +"';";
			int num = statement.executeUpdate(sql);

		
		}catch (Exception ex){
			 
		}

	}

}