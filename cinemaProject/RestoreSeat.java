package cinemaProject;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestoreSeat {
	Stage stage;
	Button bt_yes, bt_cancel;
	int alreadyExecuted;

	String[] typeArray = new String[] {"2D", "IMAX", "INDULGE", "LUXE", "SUPREME", "PREMIUM"}; 

	RestoreSeat() {
		stage = new Stage();
		Label lb_comfirmation = new Label(
				"This will restore all the data to the default values. "
						+ "Any modifcations you have made to the database will be lost."
						+ "\nDo you wish to continue?");

		//Assigning function to button
		bt_yes  = new Button("Yes");
		bt_yes.setOnAction(e -> {

			for(int i=0; i< typeArray.length; i++){
				int text_seatNo = 1;
				char rowInitial = 'A';
				for(int j=1; j<31; j++){
					if(text_seatNo > 6){
						text_seatNo = 1 ;
					}
					addSeat(typeArray[i], rowInitial, text_seatNo);
					text_seatNo++;
					if(text_seatNo % 6 == 1){
						rowInitial++;
					}
				}
			}
		}
				);

		bt_cancel = new Button("Cancel");
		bt_cancel.setOnAction(event->stage.close());

		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL);
		flowPane.setHgap(15);
		flowPane.getChildren().add(bt_yes);
		flowPane.getChildren().add(bt_cancel);
		flowPane.setAlignment(Pos.CENTER);

		VBox vbox = new VBox(7);
		vbox.getChildren().addAll(lb_comfirmation);
		vbox.getChildren().add(flowPane);

		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Add New Seat");
		stage.setResizable(false);
		stage.show();
	}

	private void addSeat(String hallType, char t_rowInitial, int t_seatNo) {
		String rowInitial = Character.toString(t_rowInitial);
		String seatNoText =  Integer.toString(t_seatNo);
		int seatNo = Integer.parseInt(seatNoText);
		Seat seat = new Seat(hallType, rowInitial, seatNo);
		boolean success = new SeatDataBase().restoreSeat(seat);
		Stage dialog = new Stage();

		Button bt_ok = new Button("ok");
		bt_ok.setOnAction(event->{
			dialog.close(); 
			stage.close();
		}
				);

		Label lb_replyMsg = new Label();
		if(success)
			lb_replyMsg.setText( "The seats were successfully restored to their original setting");
		else
			lb_replyMsg.setText( "Error restoring default seats");

		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(lb_replyMsg);
		box.getChildren().add(bt_ok);

		Scene scene= new Scene(box);
		dialog.setScene(scene);

		if(alreadyExecuted == 0){
			dialog.show();
			alreadyExecuted++;
		}
	}
}