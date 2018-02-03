package cinemaProject;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class DeleteSession {
	ComboBox<String> sessionBox;
	SessionDataBase record2 = new SessionDataBase();
	String userTitle;
	Session userSession;
	public DeleteSession() {
		Stage stage = new Stage();
		stage.setTitle("Delete an Existing Session"); 
		GridPane gridPane = new GridPane();
		gridPane.setMinWidth(900);
		gridPane.setMinHeight(700);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		Button btDeleteSDelete = new Button("Delete");
		Button btDeleteSYes = new Button("Yes");
		Button btDeleteSNo = new Button("No");

		Label lbDeleteSTitle = new Label("Please Select Movie Title:   ");
		Label lbDeleteSConfirm = new Label("Are you sure you want to delete this Session?  ");
		Label lbDeleteSYes = new Label("Session successfully deleted");
		Label lbDeleteSNo = new Label("Session not found");
		sessionBox= new ComboBox<>();
		SessionDataBase record2 = new SessionDataBase();
		ArrayList<Session> sessionArray = record2.getAllRecords();
		for (int i =0; i<sessionArray.size();i++) {

			Session session = sessionArray.get(i);
			String sessionTitle = session.getTitle();
			sessionBox.getItems().add(sessionTitle); 
		}

		sessionBox.setPromptText("Select Movie");

		//st.compareToIgnoreCase(str2);       st compare to st2 , return 1 or 0 if t or f
		gridPane.getChildren().clear();
		gridPane.add(lbDeleteSTitle,0,0); 
		gridPane.add(sessionBox,1,0);
		gridPane.add(btDeleteSDelete,0,1);  


		btDeleteSDelete.setOnAction(e->{
			userTitle= sessionBox.getValue();
			System.out.println("user title"+ userTitle);
			userSession = record2.getSRecord(userTitle);

			if(userSession==null){
				gridPane.getChildren().clear();
				gridPane.add(lbDeleteSNo,0,0); 
			}
			else{
				gridPane.getChildren().clear();
				gridPane.add(lbDeleteSConfirm,0,0); 
				gridPane.add(btDeleteSYes ,0,1);
				gridPane.add(btDeleteSNo ,1,1);

			}
		});
		btDeleteSYes.setOnAction(e->{
			boolean success = record2.deleteSession(userSession);
			gridPane.getChildren().clear();
			gridPane.add(lbDeleteSYes,0,0);
		});

		btDeleteSNo.setOnAction(e->{
			stage.close();
		});

		Scene scene = new Scene(gridPane);
		stage.setScene(scene); 
		stage.show();
	}

}
