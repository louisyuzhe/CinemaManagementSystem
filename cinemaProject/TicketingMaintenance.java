package cinemaProject;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TicketingMaintenance {

	TicketingMaintenance() {
		Stage primaryStage = new Stage();
		FlowPane seatPane = new FlowPane(Orientation.VERTICAL);

		Button create = new Button("Create New Ticket"); 
		Button retrieve = new Button("View Ticket Status"); 
		Button update = new Button("Update Any Ticket"); 
		Button delete = new Button("Delete Any Ticket"); 

		Font font1 = Font.font("Arial", FontWeight.BOLD, 17);
		create.setFont(font1);
		retrieve.setFont(font1);
		update.setFont(font1);
		delete.setFont(font1);

		create.setOnAction(event-> CreateTicket());
		retrieve.setOnAction(event-> RetrieveTicket());
		update.setOnAction(event-> UpdateTicket());
		delete.setOnAction(event-> DeleteTicket());

		seatPane.setAlignment(Pos.CENTER);
		seatPane.setVgap(20); 
		seatPane.getChildren().addAll(create, retrieve, update, delete); 

		create.setMaxWidth(Double.MAX_VALUE);
		retrieve.setMaxWidth(Double.MAX_VALUE);
		update.setMaxWidth(Double.MAX_VALUE);
		delete.setMaxWidth(Double.MAX_VALUE);

		Scene scene = new Scene(seatPane); 
		primaryStage.setScene(scene);
		Image image = new Image(getClass().getResourceAsStream("image/wallpaper.jpeg"));
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		seatPane.setBackground(background);
		primaryStage.setTitle("Ticketing Maintenance(Admin)");
		primaryStage.setWidth(900);
		primaryStage.setHeight(700);
		primaryStage.setResizable(false);
		primaryStage.show(); 		
	}

	private void CreateTicket() {
		new CreateTicket(); 
	}

	private void RetrieveTicket() {
		new RetrieveTicketTable();
	}

	private void UpdateTicket() {
		new UpdateTicket();
	}

	private void DeleteTicket() {
		new DeleteTicket();
	}


}