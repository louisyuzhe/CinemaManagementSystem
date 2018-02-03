package cinemaProject;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RetrieveTicketTable {
	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();
	ObservableList<Ticket> data = FXCollections.observableArrayList();
	MovieDataBase record1 = new MovieDataBase();
	@SuppressWarnings("rawtypes")
	public RetrieveTicketTable(){
		Stage stage = new Stage();
		stage.setTitle("Retrieve Existing Tickets"); 
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


		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table);
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();	

	}
}
