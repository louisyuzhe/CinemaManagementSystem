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


public class UpdateMovie {
	@SuppressWarnings("rawtypes")
	private TableView table = new TableView();
	ObservableList<Movie> data = FXCollections.observableArrayList();
	MovieDataBase record1 = new MovieDataBase();
	@SuppressWarnings("rawtypes")
	public UpdateMovie() {
		Stage stage = new Stage();
		stage.setTitle("Update Existing Movie"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		table.setEditable(true);
		Label label =new Label("student listing");
		TableColumn movieIDCol = new TableColumn("Movie ID");
		TableColumn titleCol = new TableColumn("Title");
		TableColumn runtimeCol = new TableColumn("Runtime");
		TableColumn yearCol = new TableColumn("Year");
		TableColumn ratingCol = new TableColumn("Rating");
		TableColumn plotCol = new TableColumn("Plot");
		TableColumn posterCol = new TableColumn("Poster URL");
		TableColumn videoCol = new TableColumn("Video URL");

		table.getColumns().addAll(movieIDCol, titleCol, runtimeCol,yearCol,ratingCol,plotCol,posterCol,videoCol);
		table.setEditable(true);

		ArrayList <Movie> list = new MovieDataBase().getAllRecords();
		for (int i =0; i<list.size();i++){
			Movie movie = list.get(i);
			data.add(movie);}

		table.setItems(data);
		Callback<TableColumn, TableCell> cellFactory =
				new Callback<TableColumn, TableCell>() {
			@Override
			public TableCell call(TableColumn p) {
				return new EditingCell();
			}
		};

		movieIDCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movieId"));	

		movieIDCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));

		movieIDCol.setMinWidth(200);


		movieIDCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, Integer>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, Integer> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setMovieId(t.getNewValue());
					}
				});

		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		titleCol.setMinWidth(200);

		runtimeCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("runtime"));	

		runtimeCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));
		runtimeCol.setMinWidth(200);

		runtimeCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, Integer>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, Integer> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setRuntime(t.getNewValue());
					}
				});

		yearCol.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));	

		yearCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {
				return Integer.parseInt(string);
			}

		}));

		yearCol.setMinWidth(200);

		yearCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, Integer>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, Integer> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setYear(t.getNewValue());
					}
				});

		ratingCol.setCellValueFactory(new PropertyValueFactory<>("Rating"));	             
		ratingCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		ratingCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		ratingCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setRating(t.getNewValue());
					}
				});

		plotCol.setCellValueFactory(new PropertyValueFactory<>("Plot"));	             
		plotCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		plotCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		plotCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setPlot(t.getNewValue());
					}
				});

		posterCol.setCellValueFactory(new PropertyValueFactory<>("Plot"));	             
		posterCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		posterCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		posterCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setPlot(t.getNewValue());
					}
				});

		videoCol.setCellValueFactory(new PropertyValueFactory<>("Plot"));	             
		videoCol.setCellFactory(TextFieldTableCell.<Movie> forTableColumn());	      
		videoCol.setMinWidth(200);

		// On Cell edit commit (for Name column)
		videoCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
					@Override public void handle(TableColumn.CellEditEvent<Movie, String> t) {
						t.getTableView().getItems().get(
								t.getTablePosition().getRow()).setPlot(t.getNewValue());
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
		stage.setResizable(false);
		stage.show();		             

	}

	private void updateRecord () {		
		ObservableList <Movie> mList = table.getItems();
		ArrayList <Movie> list = new MovieDataBase().getAllRecords();

		for (int i =0; i<list.size();i++) {
			System.out.println(list);
			Movie movie = mList.get(i);
			String movieTitle = movie.getTitle();
			boolean success= record1.updateMovie(movie);
			if (success)
				System.out.println("record updated");
			else
				System.out.println("error update record");
		}

	}  

}