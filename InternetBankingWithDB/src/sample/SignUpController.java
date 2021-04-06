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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpController  {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();

    @FXML private Button cancelButton,nextButton;

    @FXML private Label warning,warning1,warning2;

    @FXML private TextField accountNumber,phone;



    public void logInOnAction(ActionEvent event) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("CreateBankAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create A Bank Account");
            Scene scene = new Scene(root, 550, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/CreateBankAccount.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }
    }


    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    public void nextButtonOnAction(ActionEvent event) {

        Connection connection = createConnectionDemo.createConnection();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("  SELECT count(1) FROM abcd_bank.users where Account_Number = '"+ accountNumber.getText() +"' and Phone = '"+ phone.getText() +"';  ");

            while (resultSet.next()){

                if (accountNumber.getText().isEmpty() && phone.getText().isEmpty()){
                    warning2.setText("");
                    warning1.setText("");

                    AlertBox.display(
                            "Warning !!!",
                            "\n\t\t>>> Error <<<\n" +
                                    "\t________________________________\n"+
                                    "\nEnter Both Account Number And Phone." +
                                    "\n");

                    warning.setText("Enter Account Number And Phone");
                    System.out.println("Please Enter Username And Password");
                }else if (accountNumber.getText().isEmpty()){
                    warning.setText("");
                    warning2.setText("");
                    warning1.setText("Enter Account Number");
                    System.out.println("Please Enter Account Number");
                }else if(phone.getText().isEmpty()){
                    warning.setText("");
                    warning1.setText("");
                    warning2.setText("Enter Account Phone");
                    System.out.println("Please Enter Account Phone");
                } else {
                            if (resultSet.getInt(1) == 1){

                                String accountNumber_text = accountNumber.getText();

                                FXMLLoader Loader = new FXMLLoader();
                                Loader.setLocation(getClass().getResource("CreateInternetBankingAccount.fxml"));
                                try {
                                    Loader.load();
                                } catch (IOException e) {
                                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,e);
                                }finally {
                                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                                    stage.close();
                                }

                                CreateInternetBankingAccountController s = Loader.getController();
                                s.setAccountNumberTextField(accountNumber_text);

                                Parent p = Loader.getRoot();
                                Stage stage2 = new Stage();
//                                stage2.getIcons().add(new Image("/image/3rd.jpg"));
                                stage2.setTitle("Create A Bank Account..");
                                stage2.resizableProperty().setValue(false);
                                stage2.initModality(Modality.APPLICATION_MODAL);
                                Scene scene = new Scene(p);
                                scene.getStylesheets().add("/Style/CreateBankAccount.css");
                                stage2.setScene(scene);
                                stage2.showAndWait();



                            }else {
                                warning2.setText("");
                                warning1.setText("");

                            AlertBox.display(
                                    "Warning !!!",
                                    "\n\t\t>>> Error <<<\n" +
                                    "\t________________________________\n"+
                                    "\nNo account found." +
                                    "\n");

                                warning.setText("Invalid!Need An Account? Please-->");
                                System.out.println("Invalid UserName or Password!!!");
                            }
                }
            }
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
