package cinemaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieDataBase {

	Statement statement;
	MovieDataBase(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinemadatabase", "root", "G7h7y7@@");
			statement = con.createStatement();

		} catch (Exception e) {

			 
		}
	}

	public ArrayList<Movie> getAllRecords(){
		ArrayList<Movie> list = new ArrayList<Movie>();

		String sql = "SELECT* FROM MOVIE;";

		try{	
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				int mvId = result.getInt("movieID");
				String movieTitle = result.getString("title");
				int rTime = result.getInt("runtime");
				int yr = result.getInt("year");
 				String rating = result.getString("rating");
				String plot = result.getString("plot");
				String pster = result .getString("poster");
				String vid = result .getString("video");
				Movie m = new Movie (mvId,movieTitle,rTime, yr,rating,plot,pster,vid);
				list.add(m);
			}

		} catch (Exception e) {

			 
		}

		return list;
	}

	public Movie getRecord(String title){
		String sql = "SELECT * FROM MOVIE WHERE title ='" + title + "'";
		
		try{
			ResultSet result = statement.executeQuery(sql);
			while (result.next()){
				int mvId = result.getInt("movieID");
				String ttl = result .getString("title");
				int rtime = result . getInt("runtime");
				int yr = result . getInt("year");
				String rting = result .getString("rating");
				String plt = result .getString("plot");
				String pster = result .getString("poster");
				String vid = result .getString("video");
				Movie m = new Movie (mvId,ttl,rtime, yr,rting,plt,pster,vid);
				return m;       

			}
		}catch(Exception e){
			 
		}

		return null;
	}

	public boolean addNewMovie(Movie mv){
		boolean success = true;

		String sql = "INSERT INTO `movie`(`movieID`, `title`, `runtime`, `year`, `rating`, `plot`, `poster`, `video`) "
				+ "VALUES ('" +mv.movieId+"','"+mv.getTitle()+"','"+mv.getRuntime()+"','"+mv.getYear()+"','"+mv.getRating()+"','"+mv.getPlot()+"','"+mv.getPoster()+"','"+mv.getVideo()+"');";
		
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}

	public boolean updateMovie(Movie mv){
		boolean success = true;
		String sql ="UPDATE `movie` SET `title`='"+mv.getTitle()+"',`runtime`="
				+mv.getRuntime()+ ",`year`= "+ mv.getYear()+ ",`rating`='"+ mv.getRating()
				+"',`plot`= '"+ mv.getPlot()+ "',`poster`= '"+ mv.getPoster()+ "',`video`= '"
				+ mv.getVideo()+ "' WHERE title='"+ mv.getTitle()+ "'"; 
	
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}

	public boolean deleteMovie(Movie mv){
		boolean success = true;
		String sql = "DELETE FROM `movie` WHERE title='"+mv.getTitle()+"'" ;
	
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			 
		}
		return success;
	}

	public static void main(String[] args) {


	}

}
