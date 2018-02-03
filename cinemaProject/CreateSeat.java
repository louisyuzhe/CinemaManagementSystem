package cinemaProject;

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

public class CreateSeat {
	Stage stage;
	Button bt_add, bt_cancel;
	RadioButton rb_PremiumSt, rb_StandardSt, rb_FirstClassSt, rb_Yes, rb_No;
	ToggleGroup tg, tg2;

	CreateSeat() {
		stage = new Stage();

		//Hall Type ComboBox
		ComboBox<String> hallType = new ComboBox<>(); 
		hallType.getItems().addAll("2D", "IMAX", "INDULGE", "LUXE", "SUPREME", "PREMIUM"); 
		hallType.setValue("Select Type of Hall"); 
		Label lb_hallType = new Label("SELECT HALL");
		hallType.setEditable(false);

		//Row Initial ComboBox
		ComboBox<String> rowInitial = new ComboBox<>(); 
		rowInitial.getItems().addAll("A", "B", "C", "D", "E", "F"); 
		rowInitial.setValue("Select Initial of Row"); 
		Label lb_rowInitial = new Label("SELECT ROW");
		rowInitial.setEditable(false);

		//Seat Number ComboBox
		ComboBox<String> text_seatNo = new ComboBox<>(); 
		text_seatNo.getItems().addAll("1", "2", "3", "4", "5", "6"); 
		text_seatNo.setValue("Select a seat number"); 
		Label lb_seatNo = new Label("SELECT SEAT NUMBER");
		text_seatNo.setEditable(false); 

		//Price ComboBox
		ComboBox<String> price = new ComboBox<>(); 
		price.getItems().addAll("12", "14", "16", "18", "22", "26"); 
		price.setValue("Select a Ticket Price"); 
		Label lb_price = new Label("SELECT PRICE");
		price.setEditable(false); 

		Label lb_header1 = new Label("Seat Quality");
		Font headerFont = Font.font("Arial", FontWeight.BOLD, 16);
		lb_header1.setFont(headerFont);
		lb_header1.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header1.setTextFill(Color.web("White"));
		lb_header1.setPrefSize(580, 20);
		lb_header1.setAlignment(Pos.CENTER);

		Label lb_description1 = new Label("Standard - Highest quality Foam filled cushion"
				+ "\nPremium - Feather and fibre filled cushion"
				+ "\nFirst Class - Full aniline leathers covered + f&f filled cushion ");
		rb_StandardSt = new RadioButton("Standard Seating");
		rb_PremiumSt = new RadioButton("Premium Seating");
		rb_FirstClassSt = new RadioButton("First Class Seating");

		tg = new ToggleGroup();
		rb_StandardSt.setToggleGroup(tg);
		rb_PremiumSt.setToggleGroup(tg);
		rb_FirstClassSt.setToggleGroup(tg);

		Label lb_header2 = new Label("Add-ons");
		lb_header2.setFont(headerFont);
		lb_header2.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header2.setTextFill(Color.web("White"));
		lb_header2.setPrefSize(580, 20);
		lb_header2.setAlignment(Pos.CENTER);
		Label lb_description2 = new Label("*All of our tailor¨Cmade superfine dining tray tables "
				+ "come with an adjustable slot for your favorite drink");
		lb_description2.setWrapText(true);
		CheckBox chkTrayTable = new CheckBox("Premium Tray Table");

		Label lb_header3 = new Label("Availability");
		lb_header3.setFont(headerFont);
		lb_header3.setStyle("-fx-border-color:Black; -fx-background-color: gray;");
		lb_header3.setTextFill(Color.web("White"));
		lb_header3.setPrefSize(580, 20);
		lb_header3.setAlignment(Pos.CENTER);
		Label lb_description3 = new Label("Availability of seat");
		rb_Yes = new RadioButton("Yes");
		rb_No = new RadioButton("No");
		tg2 = new ToggleGroup();
		rb_Yes.setToggleGroup(tg2);
		rb_No.setToggleGroup(tg2);

		//Assigning function to button
		bt_add  = new Button("Add");
		bt_add.setOnAction(event->
		addSeat(hallType.getValue(), rowInitial.getValue(), text_seatNo.getValue(), tg, chkTrayTable, price.getValue(), tg2)
				);
		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->stage.close());

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.getChildren().addAll(rb_StandardSt, rb_PremiumSt, rb_FirstClassSt);
		flowPane.setAlignment(Pos.CENTER);
		FlowPane flowPane2 = new FlowPane(Orientation.HORIZONTAL);
		flowPane2.setHgap(15);
		flowPane2.getChildren().addAll(rb_Yes, rb_No);
		flowPane2.setAlignment(Pos.CENTER);
		FlowPane flowPane3 = new FlowPane(Orientation.HORIZONTAL);
		flowPane3.setHgap(20);
		flowPane3.setPadding(new Insets(15, 0, 10, 0));
		flowPane3.getChildren().add(bt_add);
		flowPane3.getChildren().add(bt_cancel);
		flowPane3.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_hallType, hallType, lb_rowInitial, rowInitial, lb_seatNo, text_seatNo, lb_price, price, lb_header1, 
				lb_description1, flowPane, lb_header2, lb_description2, chkTrayTable,lb_header3, lb_description3, flowPane2, flowPane3);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Add New Seat");
		stage.setResizable(false);
		stage.show();
	}

	private void addSeat(String hallType, String t_rowInitial, String t_seatNo, ToggleGroup tg, CheckBox chkTrayTable, String text_Price, ToggleGroup tg2) {
		String rowInitial = t_rowInitial;
		String seatNoText = t_seatNo;
		int seatNo = Integer.parseInt(seatNoText);
		String seatQuality = "";
		String availability = "";
		boolean trayTable = false;
		int price = Integer.parseInt(text_Price);

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

		Seat seat = new Seat(hallType, rowInitial, seatNo, seatQuality, trayTable, price, availability);
		//execute addNewSeat() method in SDB class
		boolean success = new SeatDataBase().addNewSeat(seat);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");

		Label lb_replyMsg = new Label();
		if(success){
			lb_replyMsg.setText( "Seat " + rowInitial+ seatNo+" at Hall: "+ hallType +" is added successfully");
			bt_ok.setOnAction(event->{
				dialog.close(); 
				stage.close();
			}
					);
		}
		else{
			lb_replyMsg.setText("Seat exists");
			bt_ok.setOnAction(event->{
				dialog.close(); 
			}
					);
		}
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);

		Scene scene= new Scene(box);
		dialog.setScene(scene);
		dialog.show();
	}

}