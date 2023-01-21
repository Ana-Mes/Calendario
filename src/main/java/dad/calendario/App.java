package dad.calendario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

	Controller controller = new Controller();
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		primaryStage.setTitle("Calendario");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.getIcons().add(new Image("/images/calendar-64x64.png"));
		primaryStage.show();

	}

}
