package cinemaProject;

import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class DeleteSeat {
	Stage stage;
	Button bt_delete, bt_cancel;
	ArrayList<Seat> list;
	ComboBox<String> rowInitial = new ComboBox<>(); 
	ComboBox<String> text_seatNo = new ComboBox<>(); 
	String seatInit, temp_seatInit;

	DeleteSeat() {
		stage = new Stage();

		//Creating Section Header
		Label lb_title1 = new Label("Seat to be deleted");
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
		Button	bt_select  = new Button("Select Hall");
		bt_select.setOnAction(event->{
			//Invoke search method
			list = new SeatDataBase().getAllRecords(hallType.getValue());

			//Display row initial
			for (int i =0; i<list.size();i++) {
				Seat seat = list.get(i);
				seatInit = seat.getRowInitial();
				if (seatInit.equals(temp_seatInit)){
					continue;
				}
				rowInitial.getItems().add(seatInit); 
				temp_seatInit = seatInit;

			}
			rowInitial.setValue("Select Initial of Row"); 
			rowInitial.setEditable(false);

			//Seat No. Initial ComboBox
			text_seatNo.getItems().addAll("1", "2", "3", "4", "5", "6"); 
			text_seatNo.setValue("Select a Seat Number"); 
			text_seatNo.setEditable(false); 
		}
				); 

		Label lb_rowInitial = new Label("SELECT ROW");
		Label lb_seatNo = new Label("SELECT SEAT NUMBER");

		//Assigning function to button
		bt_delete  = new Button("Delete");
		bt_delete.setOnAction(event->
		deleteSeat(hallType.getValue(), rowInitial.getValue(), text_seatNo.getValue())
				);
		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->stage.close());

		//Uniform the box size
		hallType.setMaxWidth(160);
		rowInitial.setMaxWidth(160);
		text_seatNo.setMaxWidth(160);

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.getChildren().add(bt_delete);
		flowPane.getChildren().add(bt_cancel);
		flowPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_hallType, hallType, bt_select, lb_rowInitial, rowInitial, lb_seatNo, text_seatNo);
		vbox.getChildren().add(flowPane);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Delete Seat");
		stage.setResizable(false);
		stage.show();
	}

	private void deleteSeat(String hallType, String rowInitial, String t_seatNo) {
		int seatNo = Integer.parseInt(t_seatNo);

		Seat seat = new Seat(hallType, rowInitial, seatNo);
		boolean success = new SeatDataBase().deleteSeat(seat);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
			stage.close();
		}
				);

		Label lb_replyMsg = new Label();
		if(success)
			lb_replyMsg.setText( "Seat " + rowInitial+ seatNo+" is deleted successfully");
		else
			lb_replyMsg.setText( "Error deleting seat");

		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);

		Scene scene= new Scene(box);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();
	}

}