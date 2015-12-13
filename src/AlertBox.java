import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
public class AlertBox implements EventHandler<ActionEvent> {
	 Button closeButton;
	  Stage window = new Stage();
	
	 public void display(String title, String message) {

	        //Block events to other windows
	        window.initModality(Modality.APPLICATION_MODAL);
	        window.setTitle(title);
	        window.setMinWidth(250);

	        Label label = new Label();
	        label.setText(message);
	        closeButton = new Button("Close Window");
	        closeButton.setOnAction(this);

	        VBox layout = new VBox(10);
	        layout.getChildren().addAll(label, closeButton);
	        layout.setAlignment(Pos.CENTER);

	        //Display window and wait for it to be closed before returning
	        Scene scene = new Scene(layout);
	        window.setScene(scene);
	        window.showAndWait();
	    }
	  //When button is clicked, handle() gets called
	    //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
	   public void handle(ActionEvent event) {
		   if (event.getSource() == closeButton) 
			   window.close();
	   }
}
