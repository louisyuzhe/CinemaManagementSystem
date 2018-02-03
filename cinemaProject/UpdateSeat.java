package cinemaProject;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UpdateSeat {
	Stage stage;
	Button bt_cancel;
	String seatDetail;
	RadioButton rb_PremiumSt, rb_StandardSt, rb_FirstClassSt, rb_Yes, rb_No;
	ToggleGroup tg, tg2;
	ArrayList <Seat> list; 
	String seatInit, temp_seatInit;
	ComboBox<String> rowInitial = new ComboBox<>(); 
	ComboBox<String> text_seatNo = new ComboBox<>(); 

	UpdateSeat() {
		stage = new Stage();
		//Creating Section Header
		Label lb_title1 = new Label("Seat to be updated");
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
			rowInitial.getItems().clear(); //Reset Combobox 

			//Row Initial ComboBox
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
			text_seatNo.getItems().clear(); //Reset Combobox 
			text_seatNo.getItems().addAll("1", "2", "3", "4", "5", "6"); 
			text_seatNo.setValue("Select a Seat Number"); 
			text_seatNo.setEditable(false); 
		}
				);

		Label lb_rowInitial = new Label("SELECT ROW");
		Label lb_seatNo = new Label("SELECT SEAT NUMBER");
		//Assigning function to button
		Button	bt_search  = new Button("Search");
		bt_search.setOnAction(event->
		searchSeat(hallType.getValue() ,rowInitial.getValue(), text_seatNo.getValue())
				);
		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->stage.close());

		//Uniform the box size
		hallType.setMaxWidth(160);
		rowInitial.setMaxWidth(160);
		text_seatNo.setMaxWidth(160);

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.setPadding(new Insets(15, 0, 10, 0));
		flowPane.getChildren().add(bt_search);
		flowPane.getChildren().add(bt_cancel);
		flowPane.setAlignment(Pos.CENTER);

		VBox vBox1 = new VBox(7);
		vBox1.setPadding(new Insets(7, 0, 0, 0));
		vBox1.getChildren().addAll(lb_hallType, hallType, bt_select);
		vBox1.setAlignment(Pos.CENTER);

		VBox vBox2 = new VBox(7);
		vBox2.setPadding(new Insets(7, 0, 0, 0));
		vBox2.getChildren().addAll(lb_rowInitial, rowInitial, lb_seatNo, text_seatNo);
		vBox2.setAlignment(Pos.CENTER);

		VBox mainVBox = new VBox(7);
		mainVBox.setPadding(new Insets(15, 0, 10, 0));
		mainVBox.getChildren().addAll(lb_title1, vBox1, vBox2, flowPane);
		mainVBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(mainVBox);
		stage.setScene(scene);
		stage.setTitle("Update Seat");
		stage.setResizable(false);
		stage.show();
	}

	private void searchSeat(String hallType, String rowInitial, String temp_SeatNo) {
		int seatNo = Integer.parseInt(temp_SeatNo);
		//Invoke search method
		Seat seat = new Seat(hallType, rowInitial, seatNo);
		boolean success = new SeatDataBase().getSeatRecord(seat) != null;
		SeatDataBase database = new SeatDataBase();
		Seat seatData =  database.getSeatRecord(seat);
		Stage window = new Stage();

		Button bt_update = new Button("Update Detail");
		bt_update.setOnAction(event->{
			UpdateSeatDtl_Win(hallType, rowInitial, seatNo, seatData);
			window.close();
		}
				);

		Button bt_switch = new Button("Switch Seat");
		bt_switch.setOnAction(event->{
			changeSeatWindow(hallType, rowInitial, seatNo);
			window.close();
		}
				);

		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->{
			window.close(); 
		}
				);

		Button bt_back = new Button("Back");
		bt_back.setOnAction(event->{
			window.close(); 
		}
				);

		FlowPane flowPane2 = new FlowPane(Orientation.HORIZONTAL);
		flowPane2.setHgap(20);
		Label lb_replyMsg = new Label();
		if(success){
			lb_replyMsg.setText("Seat Info\n"+ seatData.toString2());
			flowPane2.getChildren().addAll(bt_update, /*bt_switch,*/ bt_cancel);
		}
		else{
			lb_replyMsg.setText("Seat not found");
			flowPane2.getChildren().addAll(bt_back);
		}

		flowPane2.setAlignment(Pos.CENTER);

		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(lb_replyMsg, flowPane2);
		window.setResizable(false);
		window.setTitle("Search Result"); 
		window.setWidth(400);
		window.setHeight(200);

		Scene scene= new Scene(box);
		window.setScene(scene);
		window.show();
	}

	private void UpdateSeatDtl_Win(String hallType, String rowInitial, int seatNo, Seat seatData) {
		Stage window = new Stage();

		Label lb_seatDetail = new Label();
		lb_seatDetail.setText("Seat Info\n"+ seatData.toString2());

		Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
		Label lb_header1 = new Label("Seat Detail");
		lb_header1.setFont(headerFont);
		lb_header1.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header1.setTextFill(Color.web("White"));
		lb_header1.setPrefSize(580, 20);
		lb_header1.setAlignment(Pos.CENTER);

		Label lb_header2 = new Label("Seat Quality");
		lb_header2.setFont(headerFont);
		lb_header2.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header2.setTextFill(Color.web("White"));
		lb_header2.setPrefSize(580, 20);
		lb_header2.setAlignment(Pos.CENTER);

		Label lb_description1 = new Label("Standard - Highest quality Foam filled cushion"
				+ "\nPremium - Feather and fibre filled cushion"
				+ "\nFirst Class - Full aniline leathers covered + f&f filled cushion ");
		rb_StandardSt = new RadioButton("Standard Seating");
		rb_PremiumSt = new RadioButton("Premium Seating");
		rb_FirstClassSt = new RadioButton("First Class Seating");

		Label lb_header3 = new Label("Add-ons");
		lb_header3.setFont(headerFont);
		lb_header3.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header3.setTextFill(Color.web("White"));
		lb_header3.setPrefSize(580, 20);
		lb_header3.setAlignment(Pos.CENTER);
		Label lb_description2 = new Label("*All of our tailor¨Cmade superfine dining tray tables "
				+ "come with an adjustable slot for your favorite drink");
		lb_description2.setWrapText(true);
		CheckBox chkTrayTable = new CheckBox("Premium Tray Table");

		//Price ComboBox
		Label lb_header4 = new Label("Ticket Price");
		lb_header4.setFont(headerFont);
		lb_header4.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header4.setTextFill(Color.web("White"));
		lb_header4.setPrefSize(580, 20);
		lb_header4.setAlignment(Pos.CENTER);
		ComboBox<String> price = new ComboBox<>(); 
		price.getItems().addAll("12", "14", "16", "18", "22", "26"); 
		price.setValue("Select a Ticket Price"); 
		Label lb_price = new Label("SELECT PRICE");
		price.setEditable(false); 

		Label lb_header5 = new Label("Availability");
		lb_header5.setFont(headerFont);
		lb_header5.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header5.setTextFill(Color.web("White"));
		lb_header5.setPrefSize(580, 20);
		lb_header5.setAlignment(Pos.CENTER);
		Label lb_description3 = new Label("Availability of seat");
		rb_Yes = new RadioButton("Yes");
		rb_No = new RadioButton("No");

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.getChildren().addAll(rb_StandardSt, rb_PremiumSt, rb_FirstClassSt);
		flowPane.setAlignment(Pos.CENTER);	
		tg = new ToggleGroup();
		rb_StandardSt.setToggleGroup(tg);
		rb_PremiumSt.setToggleGroup(tg);
		rb_FirstClassSt.setToggleGroup(tg);

		tg2 = new ToggleGroup();
		rb_Yes.setToggleGroup(tg2);
		rb_No.setToggleGroup(tg2);
		FlowPane flowPane2 = new FlowPane(Orientation.HORIZONTAL);
		flowPane2.setHgap(15);
		flowPane2.getChildren().addAll(rb_Yes, rb_No);
		flowPane2.setAlignment(Pos.CENTER);

		Button bt_update = new Button("Update");
		bt_update.setOnAction(event->{
			UpdateSeatDetail(hallType, rowInitial, seatNo, tg, chkTrayTable, price.getValue(), tg2);;
			window.close(); 
			stage.close();
		}
				);

		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->{
			window.close(); 
			stage.close();
		}
				);
		FlowPane buttonPane = new FlowPane(Orientation.HORIZONTAL);
		buttonPane.setHgap(15);
		buttonPane.setPadding(new Insets(15, 0, 10, 0));
		buttonPane.getChildren().add(bt_update);
		buttonPane.getChildren().add(bt_cancel);
		buttonPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_header1, lb_seatDetail, lb_header2, lb_description1, flowPane, 
				lb_header3, lb_description2, chkTrayTable,lb_header4, lb_price, price, 
				lb_header5, lb_description3, flowPane2, buttonPane);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Switch Seat");
		stage.setWidth(600);
		stage.setHeight(600);
		stage.setResizable(false);
		stage.show();

	}

	private void UpdateSeatDetail(String hallType, String rowInitial, int seatNo, ToggleGroup tg, CheckBox chkTrayTable, String price, ToggleGroup tg2) {
		String seatQuality = "";
		String availability = "";
		boolean trayTable = false;

		if (rb_StandardSt.isSelected())
			seatQuality = "Standard";
		else if(rb_PremiumSt.isSelected())
			seatQuality = "Premium";
		else
			seatQuality = "First Class";

		if (chkTrayTable.isSelected())
			trayTable = true;

		if (rb_Yes.isSelected())
			availability = "true";
		else
			availability = "false";


		Seat seat = new Seat(hallType, rowInitial, seatNo, seatQuality, trayTable, Integer.parseInt(price), availability);
		//execute addNewSeat() method in SDB class
		boolean success = new SeatDataBase().updateSeat(seat);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
			stage.close();
			searchSeat(hallType, rowInitial, Integer.toString(seatNo));

		}
				);

		Label lb_replyMsg = new Label();
		if(success)
			lb_replyMsg.setText( "Seat " + rowInitial+ seatNo+" at Hall: "+ hallType+" is updated successfully");
		else
			lb_replyMsg.setText( "Error updating seat");

		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);

		Scene scene= new Scene(box);
		dialog.setScene(scene);
		dialog.show();
	}

	private void changeSeatWindow(String hallType, String rowInitial, int seatNo) {
		Stage window = new Stage();
		//Hall Type final ComboBox
		ComboBox<String> f_hallType = new ComboBox<>(); 
		f_hallType.getItems().addAll("2D", "IMAX", "INDULGE", "LUXE", "SUPREME", "PREMIUM"); 
		f_hallType.setValue("Select Type of Hall"); 
		Label lb_fhallType = new Label("SELECT HALL");
		f_hallType.setEditable(false);

		//row final ComboBox
		ComboBox<String> f_rowinitial = new ComboBox<>(); 
		f_rowinitial.getItems().addAll("A", "B", "C", "D", "E", "F"); 
		f_rowinitial.setValue("Select Initial of Row"); 
		Label lb_frowinitial = new Label("SELECT ROW");
		f_rowinitial.setEditable(false);

		//seat no. final ComboBox
		ComboBox<String> text_fseatno = new ComboBox<>(); 
		text_fseatno.getItems().addAll("1", "2", "3", "4", "5", "6"); 
		text_fseatno.setValue("Select a Seat Number"); 
		Label lb_fseatno = new Label("SELECT SEAT NUMBER");
		text_fseatno.setEditable(false); 

		Button bt_change = new Button("Change");
		bt_change.setOnAction(event->{
			changeSeat(hallType, rowInitial, seatNo, f_hallType.getValue(), f_rowinitial.getValue(), text_fseatno.getValue());;
			window.close(); 
			stage.close();
		}
				);

		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->{
			window.close(); 
			stage.close();
		}
				);
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.setPadding(new Insets(15, 0, 10, 0));
		flowPane.getChildren().add(bt_change);
		flowPane.getChildren().add(bt_cancel);
		flowPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_fhallType, f_hallType, lb_frowinitial, f_rowinitial, lb_fseatno, text_fseatno, flowPane);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Change Seat");
		stage.setResizable(false);
		stage.show();
	}

	private void changeSeat(String i_hallType, String i_rowInit, int i_seatNo, String f_hallType, String f_rowInit, String temp_fSeatNo) {

		if(f_hallType.equals("Select Type of Hall"))
			f_hallType = i_hallType;

		if(f_rowInit.equals("Select Initial of Row"))
			f_rowInit = i_rowInit;

		if(temp_fSeatNo.equals("Select a Seat Number"))
			temp_fSeatNo = Integer.toString(i_seatNo);

		int f_seatNo = Integer.parseInt(temp_fSeatNo);	

		Seat seat = new Seat(i_hallType, i_rowInit, i_seatNo, f_hallType, f_rowInit, f_seatNo);
		boolean success = new SeatDataBase().changeSeat(seat);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
			stage.close();


			searchSeat(seat.getF_hallType(), seat.getF_rowInitial(), Integer.toString(seat.getF_seatNo()));
		}
				);

		Label lb_replyMsg = new Label();
		if(success){
			lb_replyMsg.setText("Seat " + i_rowInit+ i_seatNo+ " at hall: "+ i_hallType +" is updated to Seat " 
					+ f_rowInit+ f_seatNo+ " at hall: "+ f_hallType +" successfully");
		}
		else
			lb_replyMsg.setText("Error updating seat");

		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);

		Scene scene= new Scene(box);
		dialog.setScene(scene);
		dialog.show();
	}
}