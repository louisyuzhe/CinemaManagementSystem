package cinemaProject;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class RetrieveSeat{
	Stage stage;
	ArrayList <Seat> seatlist;
	Button bt_cancel;

	RetrieveSeat() {
		stage = new Stage();
		//Creating Section Header
		Label lb_title1 = new Label("Hall to be Retrieved");
		Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
		lb_title1.setFont(headerFont);
		lb_title1.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_title1.setTextFill(Color.web("White"));
		lb_title1.setPrefSize(430, 10);
		lb_title1.setAlignment(Pos.CENTER);

		//Hall Type ComboBox
		ComboBox<String> hallType = new ComboBox<>(); 
		hallType.getItems().addAll("2D", "IMAX", "INDULGE", "LUXE", "SUPREME", "PREMIUM"); 
		hallType.setValue("Select Type of Hall"); 
		Label lb_hallType = new Label("SELECT HALL");
		hallType.setEditable(false);

		//Assigning function to button
		Button	bt_search  = new Button("Search");
		bt_search.setOnAction(event->
		retrieveSeat(hallType.getValue())
				);
		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->stage.close());

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.setPadding(new Insets(15, 0, 10, 0));
		flowPane.getChildren().add(bt_search);
		flowPane.getChildren().add(bt_cancel);
		flowPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_title1, lb_hallType, hallType, flowPane);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Update Seat");
		stage.setResizable(false);
		stage.show();		
	}

	private void retrieveSeat(String hallType){
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Select Seat");

		//Adding FlowPane
		FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
		flowPane.setPadding(new Insets (10, 20, 0, 40));
		flowPane.setVgap(5);

		//Adding GridPane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets (20, 20, 40, -10));
		grid.setVgap(10);
		grid.setHgap(10);

		Label seatChoice = new Label()	;	
		Label movieScreen = new Label("Movie Screen");
		movieScreen.setStyle("-fx-border-color:Black; -fx-background-color: Grey;");
		movieScreen.setTextFill(Color.web("White"));
		movieScreen.setPrefSize(530, 40);
		movieScreen.setWrapText(true); //Adjust Cinema Hall size

		//Invoke search method
		seatlist = new SeatDataBase().getAllRecords(hallType);
		//display seats on label
		for (int i =0; i<seatlist.size();i++) {
			Seat seat = seatlist.get(i);
			char row = seat.getRowInitial().charAt(0);
			seatChoice = new Label(seat.toString());
			seatChoice.setPrefSize(80, 40);
			seatChoice.setAlignment(Pos.CENTER);
			grid.add(seatChoice, seat.getSeatNo(), row-'A');
			seatChoice.setStyle("-fx-border-color:Grey; -fx-background-color: Grey;");

		}

		//Adding Seats to the scene
		flowPane.getChildren().add(movieScreen);	
		movieScreen.setAlignment(Pos.CENTER);
		flowPane.getChildren().add(grid);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(flowPane);
		primaryStage.setScene(scene);
		primaryStage.setWidth(610);
		primaryStage.setHeight(440);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}