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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class UpdatePasswordController {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();


    @FXML private TextField accountNumber,password, confirmPassword;

    @FXML private Button cancel,submit;

    @FXML private Label warning;



    public void setAccountNumberTextField(String numberTextField){
        this.accountNumber.setText(numberTextField);
    }



    public void submitOnAction(ActionEvent event) throws IOException {

        Connection connection = createConnectionDemo.createConnection();


        if (!accountNumber.getText().isEmpty() && !password.getText().isEmpty()
                && !confirmPassword.getText().isEmpty()){


            if ( password.getText().equals(confirmPassword.getText())){
                if (isExiecute()){

                            AlertBox.display(
                                    "Successful !!!",
                                    "\n\t>>> Congratulation <<<\n" +
                                    "\t________________________________\n"+
                                    "\nPassword updated success." +
                                    "\n");


                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("Successfull.fxml"));
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Upload Photo And Signature..");
                        primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));
                        Scene scene = new Scene(root, 500, 500); //"/image/login.png"
                        scene.getStylesheets().add("/Style/style.css");
                        primaryStage.resizableProperty().setValue(false);
                        primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        System.out.println("Registration  Success...");


                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {

                        Stage stage = (Stage) cancel.getScene().getWindow();
                        stage.close();

                        accountNumber.setText("");
                        password.setText("");
                        confirmPassword.setText("");
                    }

                }else {

                    AlertBox.display(
                            "Warning !!!",
                            "\n\t>>> Error <<<\n" +
                                    "\t________________________________\n"+
                                    "\nTry a Different UserName." +
                                    "\n");

                    warning.setText("Try a Different UserName...");
                }
            }else if (!password.getText().equals(confirmPassword.getText())){

                AlertBox.display(
                        "Warning !!!",
                        "\n\t>>> Error <<<\n" +
                                "\t________________________________\n"+
                                "\nPassword Must be Same." +
                                "\n");

                warning.setText("Password Must be Same...");
            }
        }else{

            AlertBox.display(
                    "Warning !!!",
                    "\n\t>>> Error <<<\n" +
                            "\t________________________________\n"+
                            "\nPlease Fill Up All Field." +
                            "\n");

            warning.setText("Please Fill Up All Field");
        }
    }



    public void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }




    public boolean isExiecute(){
        Connection connection = createConnectionDemo.createConnection();
        try{
            Statement statement;
            statement = connection.createStatement();
            statement.execute("update abcd_bank.users set  password = '"+password.getText()+"' where Account_Number = '"+accountNumber.getText()+"' ;  ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
