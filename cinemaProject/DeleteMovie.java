package cinemaProject;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteMovie {
	MovieDataBase record1 = new MovieDataBase();
	String movieTitle, temp_movieTitle;
	Movie userMovie;
	ArrayList<Movie> movieList;

	public DeleteMovie() {
		Stage stage = new Stage();
		stage.setTitle("Delete an Existing Movie"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);


		// Movie ComboBox
		ComboBox<String> movieBox = new ComboBox<>();
		// Display movie title
		movieList = new MovieDataBase().getAllRecords();
		for (int i = 0; i < movieList.size(); i++) {
			Movie movie = movieList.get(i);
			movieTitle = movie.getTitle();
			if (movieTitle.equals(temp_movieTitle)) {
				continue;
			}
			movieBox.getItems().add(movieTitle);
			temp_movieTitle = movieTitle;
		}
		movieBox.setValue("Select a Movie");
		Button btDeleteDelete = new Button("Delete");
		Button btDeleteYes = new Button("Yes");
		Button btDeleteNo = new Button("No");

		Label lbDeleteTitle = new Label("Please Enter Movie Title:   ");
		Label lbDeleteConfirm = new Label("Are you sure you want to delete this movie?  ");
		Label lbDeleteYes = new Label("Movie successfully deleted");

		gridPane.getChildren().clear();
		gridPane.add(lbDeleteTitle,0,0); 
		gridPane.add(movieBox ,1,0);
		gridPane.add(btDeleteDelete,0,1);  

		btDeleteDelete.setOnAction(e->{

			userMovie= record1.getRecord(movieBox.getValue());

			gridPane.getChildren().clear();
			gridPane.add(lbDeleteConfirm,0,0); 
			gridPane.add(btDeleteYes ,0,1);
			gridPane.add(btDeleteNo ,1,1);


		});
		btDeleteYes.setOnAction(e->{
			
		boolean success =new MovieDataBase().deleteMovie(userMovie);
		if(success==true){
		gridPane.getChildren().clear();
		gridPane.add(lbDeleteYes,0,0);}
		});

		btDeleteNo.setOnAction(e->{
			stage.close();
		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		stage.show();
	}

}
