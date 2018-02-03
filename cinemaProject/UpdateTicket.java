package cinemaProject;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UpdateTicket {
	ArrayList <Ticket> ticketList; 
	String ticID, temp_ticID;
	boolean success;
	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();
	ObservableList<Ticket> data = FXCollections.observableArrayList();
	TicketDataBase record1 = new TicketDataBase();
	Label lb_replyMsg = new Label();
	@SuppressWarnings("rawtypes")

	public UpdateTicket(){
		Stage stage = new Stage();
		stage.setTitle("Update Tickets"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		table.setEditable(true);
		Label label =new Label("Ticket listing");
		TableColumn ticketIdCol = new TableColumn("Ticket ID");
		TableColumn movieTitleCol = new TableColumn("Movie Title");
		TableColumn movieDateCol = new TableColumn("Show Date");
		TableColumn movieTimeCol = new TableColumn("Show Time");
		TableColumn hallTypeCol = new TableColumn("Hall Type");
		TableColumn seatLocationCol = new TableColumn("Seat Number");
		TableColumn timePurchaseCol = new TableColumn("Time of Purchase");
		TableColumn billAmountCol = new TableColumn("Bill Amount");

		table.getColumns().addAll(ticketIdCol, movieTitleCol,movieDateCol, movieTimeCol, hallTypeCol,seatLocationCol,timePurchaseCol ,billAmountCol);
		table.setEditable(true);

		ArrayList <Ticket> list = new TicketDataBase().getAllRecords();
		for (int i =0; i<list.size();i++){
			Ticket ticket = list.get(i);
			data.add(ticket);}

		table.setItems(data);
		Callback<TableColumn, TableCell> cellFactory =
				new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("ticket_ID"));
		ticketIdCol.setMinWidth(200);

		movieTitleCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
		movieTitleCol.setMinWidth(200);

		movieDateCol.setCellValueFactory(new PropertyValueFactory<>("movieDate"));
		movieDateCol.setMinWidth(170);

		movieTimeCol.setCellValueFactory(new PropertyValueFactory<>("movieTime"));
		movieTimeCol.setMinWidth(100);

		hallTypeCol.setCellValueFactory(new PropertyValueFactory<>("hallType"));
		hallTypeCol.setMinWidth(100);

		seatLocationCol.setCellValueFactory(new PropertyValueFactory<>("seatLocation"));
		seatLocationCol.setMinWidth(250);

		timePurchaseCol.setCellValueFactory(new PropertyValueFactory<>("timePurchase"));
		timePurchaseCol.setMinWidth(200);

		billAmountCol.setCellValueFactory(new PropertyValueFactory<>("billAmount"));
		billAmountCol.setMinWidth(100);

		Label lb_updateTitle = new Label("Enter ID of ticket you wish to update: ");
		Label lb_updateSeat = new Label("Enter new seats Location(same amount as the initial one e.g (A1,E6)");
		Button bt_Update = new Button("Update");

		// TicketID ComboBox
		ComboBox<String> ticBox = new ComboBox<>();
		// Display all tic ID
		for (int i = 0; i < list.size(); i++) {
			Ticket tic = list.get(i);
			ticID = tic.getTicket_ID();
			if (ticID.equals(temp_ticID)) {
				continue;
			}
			ticBox.getItems().add(ticID);
			temp_ticID = ticID;
		}
		ticBox.setValue("Select Ticket ID");

		TextField update_space = new TextField();


		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(10, 0, 0, 10));
		hbox.getChildren().addAll(lb_updateTitle, ticBox, lb_updateSeat, update_space, bt_Update);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hbox);
		bt_Update.setOnAction(e->{
			String newSeats = "["+update_space.getText()+"]";
			updateTic(ticBox.getValue(), newSeats);

		});
		Scene scene = new Scene(vbox);	
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();	

	}

	private void updateTic(String ticID, String newSeats) {
		String newSeat = newSeats;
		Ticket tic = new Ticket(ticID);
		Ticket database = new TicketDataBase().searchTicket(tic);

		String string1 = (database.getSeatLocation())
				.replace("[", "")  //remove the right bracket
				.replace("]", "") ; //remove the left bracket

		String test = string1.replaceAll(" ","");
		String test1 = test;

		int commas = 1;
		//Delete Ini. Seat
		for(int i = 0; i < test.length(); i++) {
			if(test.charAt(i) == ',') commas++;
		}

		for( int i = 0; i < commas; i++ )
		{
			String[] parts = test1.split("");
			String rowInitial = parts[0]; 
			String seatNo = parts[1]; 
			String empty = "true";

			new SeatDataBase().updateSeat(database.getHallType(), rowInitial, seatNo, empty);

		}

		String string2 = newSeats
				.replace("[", "")  //remove the right bracket
				.replace("]", "") ; //remove the left bracket

		String newStr = string2.replaceAll(" ","");
		String newStr1 = newStr;

		int commas1 = 1;
		//Insert new Seat
		for(int i = 0; i < newStr.length(); i++) {
			if(test.charAt(i) == ',') commas1++;
		}

		for( int i = 0; i < commas1; i++ )
		{ 
			String[] parts = newStr1.split("");
			String rowInitial = parts[0]; 
			String seatNo = parts[1]; 
			String taken = "false";

			new SeatDataBase().updateSeat(database.getHallType(), rowInitial, seatNo, taken);

		}

		String oldSeats = database.getSeatLocation();
		if(test.equals(newStr))
			success = false;
		else
			try {
				success = new TicketDataBase().updateTicket(ticID, oldSeats, newSeat);
			} catch (Exception e) {
				lb_replyMsg.setText("Error updating ticket");
			}
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
		}
				);

		if(success ==  true)
			lb_replyMsg.setText("Ticket with ID : " + ticID+" is updated successfully");
		else
			lb_replyMsg.setText("Error updating ticket");

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