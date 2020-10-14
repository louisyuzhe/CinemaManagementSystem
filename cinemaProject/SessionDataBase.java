package cinemaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionDataBase {
	Statement statement;
	SessionDataBase(){
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cinemadatabase", "root", "G7h7y7@@");
			statement = con.createStatement();

		} catch (Exception e) {


		}
	}
	public ArrayList<Session> getAllRecords(){
		ArrayList<Session> list = new ArrayList<Session>();

		String sql = "SELECT DISTINCT *FROM SESSION";
		try{
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				String sTtl = result .getString("title");
				String sDate = result.getString("Date");
				int stime = result . getInt("timeStart");
				String sType = result .getString("Type");
				String sStatus = result .getString("Status");
				Session session = new Session (sTtl,sDate,stime,sType,sStatus);
				list.add(session);
			}
		}catch(Exception e){
			 
		}

		return list;
	}

	public ArrayList<Session> getAllRecords(String movieTitle){

		ArrayList<Session> list = new ArrayList<Session>();

		String sql = "SELECT DISTINCT * FROM `session` WHERE `Title` = '"+ movieTitle+ "';";
		try{
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				String title = result.getString("title");
				String sDate = result.getString("Date");
				int sTime = result.getInt("timeStart");
				String sType = result.getString("Type");
				String sStatus = result.getString("Status");
				Session session = new Session (title,sDate,sTime,sType,sStatus);
				list.add(session);
			}
		}catch(Exception e){
			 
		}

		return list;
	}

	public Session getAllRecords(Session session) {
		ArrayList<Session> list = new ArrayList<Session>();

		String sql = "SELECT * FROM `session` WHERE (`Title`,`Date`,`timeStart`) = ('"
				+ session.getTitle()+ "','"+ session.getDate()+ "','"+ session.getTimeStart()+"');";
		try{
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				String title = result.getString("title");
				String sDate = result.getString("Date");
				int sTime = result.getInt("timeStart");
				String sType = result.getString("Type");
				String sStatus = result.getString("Status");
				Session session1 = new Session (title,sDate,sTime,sType,sStatus);
				session = session1;
			}
		}catch(Exception e){
		
		}
		return session;

	}

	public ArrayList<Session> getAllRecords2(String movieDate){
		ArrayList<Session> list = new ArrayList<Session>();

		String sql = "SELECT * FROM `session` WHERE `Date` = '"+ movieDate+ "';";
		try{
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				String title = result.getString("title");
				String sDate = result.getString("Date");
				int sTime = result.getInt("timeStart");
				String sType = result.getString("Type");
				String sStatus = result.getString("Status");
				Session session = new Session (title,sDate,sTime,sType,sStatus);
				list.add(session);
			}
		}catch(Exception e){
			 
		}

		return list;
	}

	public Session getSRecord(String title){
		String sql = "SELECT * FROM SESSION WHERE title ='" + title + "'";
		
		try{
			ResultSet result = statement.executeQuery(sql);
			String temp="";
			while (result.next()){
				String sttl = result .getString("title");
				String sDate = result.getString("Date");
				int stime = result . getInt("TimeStart");
				String rType = result .getString("Type");
				String rStatus = result .getString("Status");
				Session s = new Session (sttl,sDate,stime, rType,rStatus);
				return s;       

			}
		}catch(Exception e){
			 
		}

		return null;
	}

	public boolean addNewSession(Session session){
		boolean success = true;

		String sql = "INSERT INTO `Session`(`Title`,`Date`, `TimeStart`, `Type`, `Status`)VALUES('"+session.getTitle()+"','"+session.getDate()+"','"+session.getTimeStart()+"','"
				+session.getType()+"','"+session.getStatus()+"')";
	
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			
		}
		return success;
	}

	public boolean updateSession(Session session){
		boolean success = true;
		String sql ="UPDATE `session` SET `title`='"+session.getTitle()+"',`Date`='"
				+ session.getDate()+"',`timeStart`= '"+session.getTimeStart()+"',`Type`='"
				+ session.getType()+"',`Status`= '"+session.getStatus()+"' WHERE title='"+session.getTitle()+"'"; 

		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}

	public boolean deleteSession(Session session){
		boolean success = true;
		String sql = "DELETE FROM `session` WHERE title='"+session.getTitle()+"'" ;
	
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}


	public boolean checkTime(String userDate , int userTime){
		boolean success = true;
		String sql = "SELECT * FROM SESSION WHERE Date ='" +userDate+ "', timeStart='"+userTime+"'";
		try{
			boolean num = statement.execute(sql);
			if(num==true)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}


	public static void main(String[] args) {

	}

}