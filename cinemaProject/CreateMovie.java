package cinemaProject;
import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CreateMovie {
	public CreateMovie() {

		Stage stage = new Stage();
		stage.setTitle("New Movie"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Button btCreateDone = new Button("DONE");
		Button btCreateBack = new Button("RETURN TO MAIN MENU");
		Label lbCreateTitle = new Label("Please Enter New Movie Title\t: ");
		Label lbCreateRuntime = new Label("Please Enter Movie runtime\t: ");
		Label lbCreateYear = new Label("Please Enter Movie Year\t: ");
		Label lbCreateRating = new Label("Movie Rating\t: ");
		Label lbCreatePlot = new Label("Plot\t: ");
		Label lbPoster = new Label("Please enter Poster location");
		Label lbVideo = new Label("Please enter video location");

		RadioButton rbG = new RadioButton("G");
		RadioButton rbPG = new RadioButton("PG");
		RadioButton rbPG13 = new RadioButton("PG13");
		RadioButton rbR = new RadioButton("R");
		RadioButton rbNC17 = new RadioButton("NC17");
		ToggleGroup group = new ToggleGroup();
		rbG.setToggleGroup(group);
		rbPG.setToggleGroup(group);
		rbPG13.setToggleGroup(group);
		rbR.setToggleGroup(group);
		rbNC17.setToggleGroup(group);

		TextField tfTitle = new TextField();
		TextField tfRuntime = new TextField();
		TextField tfYear = new TextField();
		TextArea taPlot= new TextArea();
		TextField tfPoster = new TextField();
		TextField tfVid = new TextField();


		FlowPane ratingPane = new FlowPane(Orientation.HORIZONTAL);
		ratingPane.getChildren().add(rbG);
		ratingPane.getChildren().add(rbPG);
		ratingPane.getChildren().add(rbPG13);
		ratingPane.getChildren().add(rbR);
		ratingPane.getChildren().add(rbNC17);
		ratingPane.setHgap(25);
		// add flowpane for radio button

		gridPane.getChildren().clear();
		gridPane.add(lbCreateTitle,0,0);       
		gridPane.add(lbCreateRuntime ,0,1);
		gridPane.add(lbCreateYear ,0,2);
		gridPane.add(lbCreateRating ,0,3);
		gridPane.add(lbCreatePlot,0,4);
		gridPane.add(lbPoster,0,5);
		gridPane.add(lbVideo,0,6);
		gridPane.add(btCreateDone,0,7);
		gridPane.add(btCreateBack,0,8);
		gridPane.add(tfTitle,1,0); 
		gridPane.add(tfRuntime,1,1);
		gridPane.add(tfYear,1,2);
		gridPane.add(ratingPane,1,3);
		gridPane.add(taPlot,1,4);
		gridPane.add(tfPoster,1,5);
		gridPane.add(tfVid,1,6);

		btCreateDone.setOnAction(e->{

			MovieMenu.movieCount++;
			int count = 100+ MovieMenu.movieCount++;
			String userTitle = tfTitle.getText();
			int userRuntime = Integer.parseInt(tfRuntime.getText());
			int userYear = Integer.parseInt(tfYear.getText());
			String userPlot = taPlot.getText();
			String userRating = "Null";

			if(rbG.isSelected()){
				userRating = "G";
			}
			else if(rbPG.isSelected()){ 
				userRating = "PG";
			}
			else if(rbPG13.isSelected()){
				userRating = "PG13";
			}
			else if(rbR.isSelected()){
				userRating = "R";
			}
			else if(rbNC17.isSelected()){
				userRating = "NC17";
			}
			else{new CreateMovie();}

			String	posterAddress = tfPoster.getText();
			String	vidAddress= tfVid.getText();
			Movie userMovie = new Movie( count,userTitle, userRuntime, userYear, userRating, userPlot,posterAddress,vidAddress);

			userMovie.setPoster(posterAddress);
			userMovie.setVideo(vidAddress);

			ArrayList<Movie> record1 = new MovieDataBase().getAllRecords();
			boolean success = new MovieDataBase().addNewMovie(userMovie);
			Label lbUserTitle = new Label("Movie is created successfully");

			gridPane.getChildren().clear();
			gridPane.add(lbUserTitle,0,0); 

		});
		btCreateBack.setOnAction(e->{

			stage.close();

		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		stage.show();

	}

}
