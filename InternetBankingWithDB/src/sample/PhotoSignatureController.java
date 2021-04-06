package sample;


import Alert.AlertBox;
import MySQLConnection.CreateMySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PhotoSignatureController {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();
    File file;
    Image image;
    String getPhotoPath,getSignPath;


    @FXML private TextField accountNumber;

    @FXML private TextArea signTextArea,photoTextArea;

    @FXML private Button cancelButton,submit;

    @FXML private Label warning,warning1,warning2;

    @FXML private ImageView imageView,signView;




    public void submitOnAction(ActionEvent event) throws IOException, SQLException {

        Connection connection = createConnectionDemo.createConnection();

       if (accountNumber.getText().isEmpty()  && photoTextArea.getText().isEmpty() && signTextArea.getText().isEmpty()){
           warning.setText("Enter Your 16 Digit Account Number");
           warning1.setText("Select A Photo !!!");
           warning2.setText("Select A Signature !!!");
       }else if(accountNumber.getText().isEmpty() || accountNumber.getText().length() != 16){
           warning.setText("Enter Your 16 Digit Account Number");
           warning1.setText("");
           warning2.setText("");
       }else if(photoTextArea.getText().isEmpty()){
           warning.setText("");
           warning1.setText("Select A Photo !!!");
           warning2.setText("");
       }else if(signTextArea.getText().isEmpty()){
           warning.setText("");
           warning1.setText("");
           warning2.setText("Select A Signature !!!");
       }else if (!accountNumber.getText().isEmpty() && accountNumber.getText().length() == 16 && !photoTextArea.getText().isEmpty() && !signTextArea.getText().isEmpty()){

                if (isExecute()){

               AlertBox.display(
                               "Successful !!!",
                              "\n\t>>> Congratulation <<<\n" +
                                    "\t________________________________\n"+
                                    "\nSuccessfully uploaded your photo and sign." +
                                    "\n");


                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("Successfull.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Welcome..");
                        primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));
                        Scene scene = new Scene(root, 500, 500); //"/image/login.png"
                        scene.getStylesheets().add("/Style/style.css");
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        System.out.println("Registration  Success...");

                        Stage stage = (Stage) submit.getScene().getWindow();
                        stage.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {

                    }
                }else {
                    warning.setText("Invalid Account Number. Try Again.");
                    System.out.println("Invalid Account Number...");
                }
       }else{
            warning.setText("Enter Your 16 Digit Account Number");
            warning1.setText("Select A Photo !!!");
            warning2.setText("Select A Signature !!!");
            System.out.println("Enter Your 16 Digit Account Number");
       }
    }



    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



    public boolean isExecute(){
        Connection connection = createConnectionDemo.createConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            statement.execute("update abcd_bank.users set image = '"+getPhotoPath+"' , sign = '"+getSignPath+"' where Account_Number = '"+accountNumber.getText()+"' ;  ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void photoSelectOnAction(ActionEvent event) throws IOException {

        warning1.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                (new FileChooser.ExtensionFilter("Jpg File", "*.jpg")),
                (new FileChooser.ExtensionFilter("Png File", "*.png")),
                (new FileChooser.ExtensionFilter("Gif File", "*.gif")) );

        file = fileChooser.showOpenDialog(null);

        if (file != null){
            image = new Image(file.toURI().toString());
            imageView.setImage(image);
            System.out.println("Selected File--> " + photoTextArea.getText());
            photoTextArea.appendText("" + file.getName());
            getPhotoPath=file.getAbsolutePath();
        }
    }


    public void signSelectOnAction(ActionEvent event) {
        warning2.setText("");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                (new FileChooser.ExtensionFilter("Jpg File", "*.jpg")),
                (new FileChooser.ExtensionFilter("Png File", "*.png")),
                (new FileChooser.ExtensionFilter("Gif File", "*.gif")) );

        file = fileChooser.showOpenDialog(null);

        if (file != null){
            image = new Image(file.toURI().toString());
            System.out.println("Selected File--> " + signTextArea.getText());
            signView.setImage(image);
            signTextArea.appendText("" + file.getName());
            getSignPath=file.getAbsolutePath();
        }
    }


    public void setAccountNumberTextField(String accountNumber_text) {
        this.accountNumber.setText(accountNumber_text);
    }

}
