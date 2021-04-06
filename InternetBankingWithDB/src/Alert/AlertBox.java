package Alert;

import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void display(String title, String message){

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
//        window.setHeight(300);
        window.resizableProperty().set(false);


        Label label = new Label();
        label.setText(message);
        label.wrapTextProperty().set(true);
//        label.setFont(Font.font(java.awt.Font.SERIF, 25));
        Button closeButton = new Button("Close");


        ToggleButton toggle = new ToggleButton("Toggle color");
        closeButton.setOnAction(e -> window.close());

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label,closeButton);
        vBox.setAlignment(Pos.CENTER);

        vBox.backgroundProperty().bind(Bindings.when(toggle.selectedProperty())
                .then(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)))
                .otherwise(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY))));


        Scene s = new Scene(vBox,Color.BEIGE);

        window.setScene(s);
        window.showAndWait();

    }

}

//import Alert.AlertBox;


//
//                            AlertBox.display(
//                                    "Successful !!!",
//                                    "\n\t>>> Congratulation <<<\n" +
//                                    "\t________________________________\n"+
//                                    "\nSuccessfully logged into your account." +
//                                    "\n");

