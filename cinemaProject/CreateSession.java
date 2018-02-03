package cinemaProject;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateSession {
	ComboBox<String> movieBox;
	ComboBox<String> statusBox;
	ComboBox<String> timeStartBox;
	DatePicker datepicker;
	SessionDataBase record2 = new SessionDataBase();

	public CreateSession() {

		Stage stage = new Stage();
		stage.setTitle("Create a session"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Button btCreateSDone = new Button("DONE");
		Button btCreateSBack = new Button("RETURN TO MAIN MENU");
		Label lbCreateSTitle = new Label("Please Select A Movie:   ");
		Label lbCreateSDate = new Label("Please Select Movie Screening Date:   ");
		Label lbCreateSTime = new Label("Please Select Movie Screening time:   ");
		Label lbCreatesType = new Label("Please Select Hall Type:         ");
		Label lbCreateSStatus = new Label("Select Movie Status");

		movieBox= new ComboBox<>();
		MovieDataBase record1 = new MovieDataBase();
		ArrayList<Movie> movieArray = record1.getAllRecords();
		for (int i =0; i<movieArray.size();i++) {

			Movie movie = movieArray.get(i);
			String movieTitle = movie.getTitle();
			movieBox.getItems().add(movieTitle); 
		}                
		movieBox.setPromptText("Select Movie");

		datepicker = new DatePicker();
		datepicker.setPromptText("Select Date");

		timeStartBox = new ComboBox<>();
		timeStartBox.getItems().addAll("1030", "1230", "1330", "1700", "2100");


		timeStartBox.setPromptText("Select Time");
		FlowPane timeStartPane = new FlowPane(Orientation.HORIZONTAL);
		timeStartPane.getChildren().add(timeStartBox);

		RadioButton rb2D = new RadioButton("2D");
		RadioButton rbIMAX = new RadioButton("IMAX");
		RadioButton rbIND = new RadioButton("INDULGE");
		RadioButton rbLUXE = new RadioButton("LUXE");
		RadioButton rbSUP = new RadioButton("SUPREME");
		RadioButton rbPRE = new RadioButton("PREMIUM");
		ToggleGroup group = new ToggleGroup();
		rb2D.setToggleGroup(group);
		rbIMAX.setToggleGroup(group);
		rbIND.setToggleGroup(group);
		rbLUXE.setToggleGroup(group);
		rbSUP.setToggleGroup(group);
		rbPRE.setToggleGroup(group);

		// add flowpane for radio button
		FlowPane typePane = new FlowPane(Orientation.HORIZONTAL);
		typePane.getChildren().addAll(rb2D, rbIMAX, rbIND, rbLUXE, rbSUP, rbPRE);
		typePane.setHgap(15);

		statusBox = new ComboBox<>();
		statusBox.getItems().addAll("Now Screening", "Now Seating", "Selling Fast", "On sale", "Sold Out");
		statusBox.setPromptText("Set Status");
		FlowPane statusPane = new FlowPane(Orientation.HORIZONTAL);
		statusPane.getChildren().add(statusBox);

		gridPane.getChildren().clear();
		gridPane.add(lbCreateSTitle,0,0);     
		gridPane.add(lbCreateSDate ,0,1);
		gridPane.add(lbCreateSTime ,0,2);
		gridPane.add(lbCreatesType ,0,3);
		gridPane.add(lbCreateSStatus ,0,4);
		// gridPane.add(lbCreatePlot,0,4);
		gridPane.add(btCreateSDone,0,5);
		gridPane.add(btCreateSBack,0,6);
		gridPane.add(  movieBox,1,0); 
		gridPane.add(datepicker,1,1);
		gridPane.add(timeStartBox,1,2);
		gridPane.add(typePane,1,3);
		gridPane.add(statusPane,1,4);
		// gridPane.add( statusPane,1,4);


		btCreateSDone.setOnAction(e->{

			String userMovie;
			String userDate;
			int userTime;
			String userType=null;
			String userStatus;
			userMovie = movieBox.getValue();
			userDate =  datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			userTime = Integer.parseInt(timeStartBox.getValue());

			if(rb2D.isSelected()){
				userType = "2D";
			}
			else if(rbIMAX.isSelected()){ 
				userType = "IMAX";
			}
			else if(rbIND.isSelected()){
				userType = "INDULGE";
			}
			else if(rbLUXE.isSelected()){
				userType = "LUXE";
			}
			else if(rbSUP.isSelected()){
				userType = "SUPREME";
			}
			else if(rbPRE.isSelected()){
				userType = "PREMIUM";
			}
			else{new CreateMovie();}
			userStatus = statusBox.getValue();

			addSession(userMovie, userDate, userTime, userType, userStatus);

			gridPane.getChildren().clear();

			stage.close();//}
		});

		btCreateSBack.setOnAction(e->{
			stage.close();
		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		stage.show();
	}

	private void addSession(String movieTitle, String movieDate, int userTime, String hallType, String status) {
		Stage dialog = new Stage();

		//execute addNewTicket() method in SDB class
		Session sess = new Session(movieTitle, movieDate, userTime, hallType, status);
		boolean success = false;
		try {
			success = new SessionDataBase().addNewSession(sess);
		} catch (Exception e) {
			
		}

		Button bt_ok = new Button("ok");
		Label lb_replyMsg = new Label();
		if(success){
			lb_replyMsg.setText("Session added");
			bt_ok.setOnAction(event->{
				dialog.close(); 
			}
					);
		}
		else{
			lb_replyMsg.setText("Error processing session");
			bt_ok.setOnAction(event->{
				dialog.close(); 
			}
					);
		}
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);
		box.setPrefSize(400, 200);
		Scene scene= new Scene(box);
		dialog.setScene(scene);
		dialog.show();

	}

}
