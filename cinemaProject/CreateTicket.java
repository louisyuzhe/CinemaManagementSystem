package cinemaProject;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CreateTicket {
	Stage primaryStage = new Stage();

	ArrayList<Seat> seatList;
	Movie movieList;
	ArrayList<Session> sessionList, sessionList1, sessionList2;
	ArrayList<Object> selectedList = new ArrayList<Object>();
	ComboBox<String> dateBox = new ComboBox<>();
	ComboBox<String> timeBox = new ComboBox<>();
	String showDate, showTime, movieTitle, temp_movieTitle, choosenMovie;
	Session database;
	Seat seat;
	Ticket tic;
	FlowPane moviePane = new FlowPane();
	FlowPane imgPane = new FlowPane();

	CreateTicket() {
		FlowPane mainPane = new FlowPane(Orientation.VERTICAL);

		Label lb_Main = new Label("* QUICK TICKETS *");
		Font headerFont = Font.font("Arial", FontWeight.BOLD, 20);
		lb_Main.setFont(headerFont);

		// Movie ComboBox
		ComboBox<String> movieBox = new ComboBox<>();
		// Display movie title
		sessionList = new SessionDataBase().getAllRecords();
		for (int i = 0; i < sessionList.size(); i++) {
			Session session = sessionList.get(i);
			movieTitle = session.getTitle();
			if (movieTitle.equals(temp_movieTitle)) {
				continue;
			}
			movieBox.getItems().add(movieTitle);
			temp_movieTitle = movieTitle;
		}
		movieBox.setValue("Select a Movie");

		Label lb_movieBox = new Label("SELECT A MOVIE");
		Label lb_session = new Label("SELECT A SESSION");

		// Assigning function to button
		Button bt_selectM = new Button("Select Movie");
		bt_selectM.setOnAction(event -> {

			moviePane.getChildren().clear();
			imgPane.getChildren().clear();
			movieList = new MovieDataBase().getRecord(movieBox.getValue());

			// Invoke search method
			dateBox.getItems().clear(); //Reset Combobox 
			timeBox.getItems().clear(); 
			sessionList1 = new SessionDataBase().getAllRecords(movieBox.getValue());
			String temp_showDate = "";

			// Display show date
			for (int i = 0; i < sessionList1.size(); i++) {
				Session session = sessionList1.get(i);
				showDate = session.getDate();
				if (showDate.equals(temp_showDate)) {
					continue;
				}
				dateBox.getItems().add(showDate);
				temp_showDate = showDate;

			}	
			dateBox.setValue("Select a date");

			//Display detail
			Label lb_runtime = new Label("Runtime :\t"+movieList.getRuntime()+" minutes");
			Label lb_year = new Label("Year :\t"+movieList.getYear());
			Label lb_rating = new Label("Rating :\t"+movieList.getRating());

			//Display movie poster
			Image img = new Image(getClass().getResourceAsStream(movieList.getPoster()+""));
			ImageView poster = new ImageView(img);
			poster.setFitWidth(230);
			poster.setFitHeight(320);
			VBox imgbox = new VBox();
			imgbox.getChildren().addAll(poster, lb_runtime, lb_year, lb_rating);
			imgPane.getChildren().add(imgbox);
			imgPane.setAlignment(Pos.CENTER_LEFT);

			//Display Plot
			Label lb_plotTitle = new Label("Plot");
			Label lb_plot = new Label(movieList.getPlot());
			Font font1 = Font.font("Arial", FontWeight.BOLD, 15);
			lb_plotTitle.setFont(font1);

			//Playing Video
			URL resource = getClass().getResource(movieList.getVideo());
			Media media;
			try {
				media = new Media(resource.toString());
				MediaPlayer mediaPlayer = new MediaPlayer(media);
				MediaView mediaView = new MediaView(mediaPlayer);

				Button playButton = new Button(">");
				playButton.setOnAction(e -> {
					if (playButton.getText().equals(">")) {
						mediaPlayer.play();
						playButton.setText("||");
					} else {
						mediaPlayer.pause();
						playButton.setText(">");
					}
				});
				mediaView.setFitWidth(380);
				VBox videoBox1 = new VBox();
				videoBox1.setAlignment(Pos.CENTER);
				videoBox1.getChildren().addAll(mediaView, playButton, lb_plotTitle, lb_plot);
				moviePane.getChildren().addAll(videoBox1);
				moviePane.setAlignment(Pos.CENTER_RIGHT);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1);
			}

		}
				);

		// Assigning function to button
		Button bt_selectD = new Button("Select Date");
		bt_selectD.setOnAction(event -> {
			// Invoke search method
			timeBox.getItems().clear(); //Reset Combobox 
			sessionList2 = new SessionDataBase().getAllRecords2(dateBox.getValue());
			String temp_showTime = "";
			// Time ComboBox
			for (int i = 0; i < sessionList2.size(); i++) {
				Session session = sessionList2.get(i);
				showTime =  String.format("%04d", session.getTimeStart());
				if (showTime.equals(temp_showTime)) {
					continue;
				}
				timeBox.getItems().add(showTime);
				temp_showTime = showTime;

			}	
			timeBox.setValue("Select a Time");
		}

				);

		// Uniform the box size
		bt_selectM.setPrefWidth(85);
		bt_selectD.setPrefWidth(85);
		movieBox.setPrefWidth(175);
		dateBox.setPrefWidth(175);
		timeBox.setPrefWidth(175);

		// Assigning function to button
		Button bt_continue = new Button("Continue to purchase ticket");
		bt_continue.setOnAction(event -> {
			checkTicSelected(movieBox.getValue(), (dateBox.getValue()), timeBox.getValue());
		});

		Button bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event -> primaryStage.close());

		VBox vbox = new VBox();
		vbox.getChildren().add(lb_Main);
		vbox.setAlignment(Pos.CENTER);
		HBox hbox1 = new HBox(20);
		hbox1.getChildren().addAll(movieBox, bt_selectM);
		hbox1.setAlignment(Pos.CENTER);
		VBox vbox1 = new VBox(7);
		vbox1.getChildren().addAll(lb_movieBox, hbox1);
		vbox1.setAlignment(Pos.CENTER);
		HBox hbox2 = new HBox(20);
		hbox2.getChildren().addAll(dateBox, bt_selectD);
		hbox2.setAlignment(Pos.CENTER);
		VBox vbox2 = new VBox(7);
		vbox2.getChildren().addAll(lb_session, hbox2);
		vbox2.setAlignment(Pos.CENTER);
		HBox hbox3 = new HBox(20);
		hbox3.getChildren().addAll(bt_continue, bt_cancel);
		hbox3.setAlignment(Pos.CENTER);

		mainPane.setAlignment(Pos.CENTER);
		mainPane.setVgap(20);
		mainPane.getChildren().addAll(vbox, vbox1, vbox2, timeBox, hbox3);
		mainPane.setPadding(new Insets(0, 0, 0, -180));
		moviePane.setPadding(new Insets(-20, 15, 0, -20));
		imgPane.setPadding(new Insets(0, 0, 0, 15));
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(mainPane);
		borderPane.setRight(moviePane);
		borderPane.setLeft(imgPane);

		Scene scene = new Scene(borderPane);
		primaryStage.setTitle("Sunway Cinema");
		primaryStage.setWidth(1000);
		primaryStage.setHeight(600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void checkTicSelected(String movieTitle, String movieDate, String movieTime) {
		Stage dialog = new Stage();
		Label lb_errorMsg = new Label();

		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);

		Button bt_cancel = new Button("Back");
		bt_cancel.setOnAction(event -> dialog.close());

		if (movieTime.equals("Select a Time")) {
			lb_errorMsg.setText("Time is not chosen");
			vBox.getChildren().addAll(lb_errorMsg, bt_cancel);
			dialog.show();
		} else {
			SelectSeat(movieTitle, movieDate, movieTime);
		}

		Scene scene = new Scene(vBox);
		dialog.setScene(scene);
	}


	private void SelectSeat(String movieTitle, String movieDate, String movieTime) {
		Stage seatWindow = new Stage();
		seatWindow.setTitle("Select Seat");

		// Adding FlowPane
		FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
		flowPane.setPadding(new Insets(10, 20, 0, 40));
		flowPane.setVgap(5);

		// Adding GridPane
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 20, 20, -10));
		grid.setVgap(10);
		grid.setHgap(10);

		ToggleButton seatChoice = new ToggleButton();
		Label movieScreen = new Label("Movie Screen");
		movieScreen.setStyle("-fx-border-color:Black; -fx-background-color: Grey;");
		movieScreen.setTextFill(Color.web("White"));
		movieScreen.setPrefSize(530, 40);
		movieScreen.setAlignment(Pos.CENTER);

		Session session = new Session(movieTitle, movieDate, movieTime);
		database = new SessionDataBase().getAllRecords(session);
		seatList = new SeatDataBase().getAllRecords(database.getType());

		// display seats on Button
		ToggleGroup group = new ToggleGroup();

		for (int i = 0; i < seatList.size(); i++) {
			Seat seat = seatList.get(i);
			char row = seat.getRowInitial().charAt(0);
			seatChoice = new ToggleButton(seat.getRowInitial() + seat.getSeatNo());
			if((seat.getAvailability()).equals("false")){
				seatChoice.setDisable(true);
			}
			else {
				seatChoice.setDisable(false);
			}
			seatChoice.setOnAction(new SeatButtonHandler(database.getType(), seat.getRowInitial(), seat.getSeatNo()));
			seatChoice.setPrefSize(80, 40);
			seatChoice.setAlignment(Pos.CENTER);
			seatChoice.setToggleGroup(group);
			grid.add(seatChoice, seat.getSeatNo(), row - 'A');

		}

		// Assigning function to button
		Button bt_next = new Button("Continue to payment");
		bt_next.setOnAction(event -> {

			int billAmount = 0;
			for (int i = 0; i < selectedList.size(); i++) {

				//Split string
				String[] parts = ((String) selectedList.get(i)).split("");
				String rowInitial = parts[0]; 
				String seatNo = parts[1]; 
				Seat seat = new Seat(database.getType(), rowInitial, Integer.parseInt(seatNo));
				SeatDataBase database = new SeatDataBase();
				Seat seatData =  database.getSeatRecord(seat);
				int price = seatData.getPrice();

				billAmount = billAmount+ price;
			}
			// get current date time with Calendar()
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String timePurchase = dateFormat.format(cal.getTime());
			String ticket_ID = timePurchase.replaceAll("[// ::]","");
			addTicket(ticket_ID, movieTitle, movieDate, movieTime, database.getType(), selectedList, timePurchase, billAmount);
			seatWindow.close();
		}
				);
		Button bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event -> seatWindow.close());

		// Add button
		FlowPane controlPane = new FlowPane();
		// controlPane.getChildren().add(displayField) ;
		controlPane.getChildren().add(bt_next);
		controlPane.getChildren().add(bt_cancel);
		controlPane.setAlignment(Pos.CENTER);
		controlPane.setPadding(new Insets(0, 20, 0, -5));
		controlPane.setHgap(10);

		BorderPane bPane = new BorderPane();
		bPane.setCenter(controlPane);

		// Adding Pane to the scene
		flowPane.getChildren().add(movieScreen);
		flowPane.getChildren().add(grid);
		flowPane.getChildren().add(bPane);

		Scene scene = new Scene(flowPane);
		seatWindow.setScene(scene);
		seatWindow.setWidth(610);
		seatWindow.setHeight(400);
		seatWindow.setResizable(false);
		seatWindow.show();
	}


	private void addTicket(String ticket_ID, String movieTitle, String movieDate, String movieTime, String hallType, Object seatSelected, String timePurchase, int billAmount) {
		Stage dialog = new Stage();

		//execute addNewTicket() method in SDB class
		Ticket tic = new Ticket(ticket_ID, movieTitle, movieDate, movieTime, hallType, seatSelected, timePurchase, billAmount);
		boolean success = new TicketDataBase().addNewTicket(tic);

		Button bt_ok = new Button("ok");
		Label lb_replyMsg = new Label();
		if(success){
			lb_replyMsg.setText(tic.toString());
			bt_ok.setOnAction(event->{
				dialog.close(); 
				primaryStage.close();
			}
					);
		}
		else{
			lb_replyMsg.setText( "Error processing ticket");
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

	// Record down button selected
	class SeatButtonHandler implements EventHandler<ActionEvent> {
		private String hallType;
		private String rowInitial;
		private int seatNo;
		private String taken = "false";

		SeatButtonHandler(String hallType, String rowInitial, int seatNo) {

			this.hallType = hallType;
			this.rowInitial = rowInitial;
			this.seatNo = seatNo;
		}

		@Override
		public void handle(ActionEvent event) {

			new SeatDataBase().updateSeat(hallType, rowInitial, seatNo, taken);
			selectedList.add(rowInitial + seatNo);
		}
	}

}