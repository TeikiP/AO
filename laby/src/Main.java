import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Labyrinth;

public class Main extends Application {

	static Controller controller;

	public static void main(String args[]) {		
		launch();		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Controller.makeInstance().start(primaryStage);
	}

	@Override
	public void stop(){
	    System.exit(0);
    }

}
