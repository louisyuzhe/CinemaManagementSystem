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

public class SeatingMaintenance {

	SeatingMaintenance() {
		Stage primaryStage = new Stage();
		FlowPane seatPane = new FlowPane(Orientation.VERTICAL);

		Button create = new Button("Create New Seat"); 
		Button retrieve = new Button("View Seats' status"); 
		Button update = new Button("Update Any Seat"); 
		Button delete = new Button("Delete Any Seat"); 
		Button restore_def = new Button("Restore Default Seat"); 

		Font font1 = Font.font("Arial", FontWeight.BOLD, 17);
		create.setFont(font1);
		retrieve.setFont(font1);
		update.setFont(font1);
		delete.setFont(font1);
		restore_def.setFont(font1);

		create.setOnAction(event-> CreateSeat());
		retrieve.setOnAction(event-> RetrieveSeat());
		update.setOnAction(event-> UpdateSeat());
		delete.setOnAction(event-> DeleteSeat());
		restore_def.setOnAction(event-> RestoreSeat());

		seatPane.setAlignment(Pos.CENTER);
		seatPane.setVgap(20); 
		seatPane.getChildren().addAll(create, retrieve, update, delete, restore_def); 

		create.setMaxWidth(Double.MAX_VALUE);
		retrieve.setMaxWidth(Double.MAX_VALUE);
		update.setMaxWidth(Double.MAX_VALUE);
		delete.setMaxWidth(Double.MAX_VALUE);
		restore_def.setMaxWidth(Double.MAX_VALUE);

		Scene scene = new Scene(seatPane); 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Seating Maintenance(Admin)");
		Image image = new Image("image/wallpaper.jpeg");
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		seatPane.setBackground(background);
		primaryStage.setWidth(900);
		primaryStage.setHeight(700);
		primaryStage.setResizable(false);
		primaryStage.show(); 		
	}

	private void CreateSeat() {
		new CreateSeat(); 
	}

	private void RetrieveSeat() {
		new RetrieveSeat();
	}

	private void UpdateSeat() {
		new UpdateSeat();
	}

	private void DeleteSeat() {
		new DeleteSeat();
	}

	private void RestoreSeat() {
		new RestoreSeat();
	}

}