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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DeleteTicket {
	Stage stage;
	ArrayList <Ticket> ticketList; 
	String ticID, temp_ticID;

	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();
	ObservableList<Ticket> data = FXCollections.observableArrayList();
	MovieDataBase record1 = new MovieDataBase();
	@SuppressWarnings("rawtypes")

	public DeleteTicket(){
		Stage stage = new Stage();
		stage.setTitle("Delete Existing Tickets"); 
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
		table.setEditable(false);

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

		Label lb_deleteTitle = new Label("Enter ID of ticket you wish to delete: ");
		Button bt_Delete = new Button("Delete");

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

		bt_Delete.setOnAction(e->{
			deleteTic(ticBox.getValue());
			stage.close();
		});
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(10, 0, 0, 10));
		hbox.getChildren().addAll(lb_deleteTitle, ticBox, bt_Delete);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hbox);
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();	

	}
	private void deleteTic(String ticID) {

		Ticket tic = new Ticket(ticID);
		Ticket database = new TicketDataBase().searchTicket(tic);


		String string = (database.getSeatLocation())
				.replace("[", "")  //remove the right bracket
				.replace("]", "") ; //remove the left bracket

		String test = string.replaceAll(" ","");
		String test1 = test;

		int commas = 0;
		for(int i = 0; i < test.length(); i++) {
			if(test.charAt(i) == ',') commas++;
		}

		for( int i = 0; i < commas; i++ )
		{ 
			String [] a = test1.split(",", 2);
			String tempo = a[0];

			String[] parts = tempo.split("");
			String rowInitial = parts[0]; 
			String seatNo = parts[1]; 
			String empty = "true";

			new SeatDataBase().updateSeat(database.getHallType(), rowInitial, seatNo, empty);
			if(a[1].equals(null))
				break;
			else
				test1 = a[1];
		}

		boolean success = new TicketDataBase().deleteTicket(tic);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
		}
				);

		Label lb_replyMsg = new Label();
		if(success)
			lb_replyMsg.setText("Ticket with ID : " + ticID+" is deleted successfully");
		else
			lb_replyMsg.setText("Error deleting ticket");

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