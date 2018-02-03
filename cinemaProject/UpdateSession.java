package cinemaProject;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class UpdateSession {
	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();
	ObservableList<Session> data = FXCollections.observableArrayList();
	SessionDataBase record2 = new SessionDataBase();
	public UpdateSession() {
		Stage stage = new Stage();
		stage.setTitle("Update A Session"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		table.setEditable(true);
		Label label =new Label("Session listing");
		TableColumn titleCol = new TableColumn("title");
		TableColumn timeStartCol = new TableColumn("timeStart");
		TableColumn DateCol = new TableColumn("Date");
		TableColumn typeCol = new TableColumn("type");
		TableColumn StatusCol = new TableColumn("Status");

		table.getColumns().addAll( titleCol, timeStartCol,DateCol,typeCol,StatusCol);
		table.setEditable(true);

		ArrayList <Session> list = new SessionDataBase().getAllRecords();
		for (int i =0; i<list.size();i++){
			Session session = list.get(i);
			data.add(session);}

		table.setItems(data);
		Callback<TableColumn, TableCell> cellFactory =
				new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleCol.setMinWidth(200);


		DateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));	             
		DateCol.setCellFactory(TextFieldTableCell.<Session> forTableColumn());	      
		DateCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		DateCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Session, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Session, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setDate(t.getNewValue());
					}
				});                  

		timeStartCol.setCellValueFactory(new PropertyValueFactory<Session, Integer>("timeStart"));	

		timeStartCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		timeStartCol.setMinWidth(200);  

		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));	             
		typeCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		typeCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		typeCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Session, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Session, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setType(t.getNewValue());
					}
				});

		StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));	             
		StatusCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		StatusCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		typeCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Session, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Session, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setStatus(t.getNewValue());
					}
				});

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		Button button = new Button("Update");
		button.setOnAction(event -> updateRecord());
		vbox.getChildren().addAll(label, table, button);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();		
	}

	private void updateRecord () {		
		ObservableList <Session> sList = table.getItems();
		ArrayList <Session> list = new ArrayList(sList);
                System.out.println("slist:"+sList);
		System.out.println("list:"+list);
		for (int i =0; i<list.size();i++) {

			Session session = list.get(i);
			boolean success= record2.updateSession(session);
			if (success)
				System.out.println("record updated");
			else
				System.out.println("error update record");
		}

	}
}