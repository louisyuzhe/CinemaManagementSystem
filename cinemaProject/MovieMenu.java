package cinemaProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MovieMenu {
	public static int movieCount = 0;

	MovieMenu() {
		Stage stage = new Stage();
		stage.setTitle("Movie Maintenance (Admin)"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Label lbWelcome = new Label("WELCOME TO THE MOVIE TICKET REGISTRATION ");
		lbWelcome.setFont(Font.font("Comic Sans",FontWeight.BOLD, FontPosture.ITALIC,30));
		Button btStart = new Button("Start");
		btStart.setFont(Font.font("Comic Sans",FontWeight.BOLD, FontPosture.ITALIC,20));
		Button btCreate = new Button("Create a new Movie");
		Button btRetrieve = new Button("Retrive an exsiting Movie");
		Button btUpdate = new Button("Update a new Movie");
		Button btDelete = new Button("Delete a existing Movie");

		Font font = Font.font("Arial", FontWeight.BOLD, 17);
		btCreate.setFont(font);
		btRetrieve.setFont(font);
		btUpdate.setFont(font);
		btDelete.setFont(font);

		gridPane.add(btCreate,0,0);       
		gridPane.add(btRetrieve ,0,1);
		gridPane.add(btUpdate ,0,2);
		gridPane.add(btDelete ,0,3);

		btCreate.setOnAction(e->{
			new CreateMovie();
		});

		btRetrieve.setOnAction(e->{
			new RetrieveMovie();	
		});

		btUpdate .setOnAction(e->{
			new UpdateMovie();	
		});

		btDelete.setOnAction(e->{
			new DeleteMovie();		
		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		Image image = new Image("image/wallpaper.jpeg");
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		gridPane.setBackground(background);
		stage.setResizable(false);
		stage.show();
	}

}