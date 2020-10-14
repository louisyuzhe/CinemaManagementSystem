/*Main class for the project
		*/
package cinemaProject;

import javafx.application.Application;
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

public class MainMenu extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane seatPane = new FlowPane(Orientation.VERTICAL);

		Button session = new Button("Session Maintenance"); 
		Button movie = new Button("Movie Maintenance "); 
		Button seat = new Button("Seating Maintenance"); 
		Button ticket = new Button("Ticketing Maintenance"); 

		Font font1 = Font.font("Arial", FontWeight.BOLD, 17);
		session.setFont(font1);
		movie.setFont(font1);
		seat.setFont(font1);
		ticket.setFont(font1);

		session.setOnAction(event-> SessionMenu());
		movie.setOnAction(event-> MovieMenu());
		seat.setOnAction(event-> SeatingMaintenance());
		ticket.setOnAction(event-> TicketingMaintenance());

		session.setMaxWidth(Double.MAX_VALUE);
		movie.setMaxWidth(Double.MAX_VALUE);
		seat.setMaxWidth(Double.MAX_VALUE);
		ticket.setMaxWidth(Double.MAX_VALUE);

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
		seatPane.setAlignment(Pos.CENTER);
		seatPane.setVgap(20); 
		seatPane.getChildren().addAll(session, movie, seat, ticket); 

		primaryStage.setTitle("Main Menu(Admin)");
		primaryStage.setWidth(900);
		primaryStage.setHeight(700);
		primaryStage.setResizable(false);
		primaryStage.show(); 		
	}
	

	private void TicketingMaintenance() {
		new TicketingMaintenance(); 
	}

	private void SeatingMaintenance() {
		new SeatingMaintenance();
	}

	private void MovieMenu() {
		new MovieMenu();
	}

	private void SessionMenu() {
		new SessionMenu();
	}

	public static void main(String[] args) {
		launch();

	}
}