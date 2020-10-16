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

public class SessionMenu {

	SessionMenu(Stage primaryStage) {
		Stage stage = new Stage();
		stage.setTitle("Session Maintenanc(Admin)"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Label lbWelcome = new Label("WELCOME TO THE SESSION MAINTAINENCE SYSTEM ");
		lbWelcome.setFont(Font.font("Comic Sans",FontWeight.BOLD, FontPosture.ITALIC,30));
		Button btStart = new Button("Start");
		btStart.setFont(Font.font("Comic Sans",FontWeight.BOLD, FontPosture.ITALIC,20));
		Button btCreateS = new Button("Create a new Session");
		Button btRetrieveS = new Button("Retrive exsiting Sessions");
		Button btUpdateS = new Button("Update a new Session");
		Button btDeleteS = new Button("Delete an existing Time Session");
		Button btBack = new Button("Back to Main Menu");

		Font font1 = Font.font("Arial", FontWeight.BOLD, 17);
		btCreateS.setFont(font1);
		btRetrieveS.setFont(font1);
		btUpdateS.setFont(font1);
		btDeleteS.setFont(font1);
		btBack.setFont(font1);

		gridPane.add(btCreateS,0,0);       
		gridPane.add(btRetrieveS ,0,1);
		gridPane.add(btUpdateS ,0,2);
		gridPane.add(btDeleteS ,0,3);
		gridPane.add(btBack ,0,4);

		btCreateS.setOnAction(e->{
			new CreateSession();
		});

		btRetrieveS.setOnAction(e->{
			new RetrieveSession();	
		});

		btUpdateS .setOnAction(e->{
			new UpdateSession();	
		});

		btDeleteS.setOnAction(e->{
			new DeleteSession();		
		});

		btBack.setOnAction(e->{
			primaryStage.show();
			stage.close();
		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		Image image = new Image(getClass().getResourceAsStream("image/wallpaper.jpeg"));
		// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		// new BackgroundImage(image, repeatX, repeatY, position, size)
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
		// new Background(images...)
		Background background = new Background(backgroundImage);
		gridPane.setBackground(background);
		stage.show();
	}

}
