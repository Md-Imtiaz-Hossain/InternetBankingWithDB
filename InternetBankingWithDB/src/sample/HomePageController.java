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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePageController {

    CreateMySQLConnection createConnectionDemo = new CreateMySQLConnection();
    Statement statement;

    @FXML private Button signIn,signUpForBankAccount,signUp,showPasswordButton,forgotPassword;

    @FXML private PasswordField passwordField;

    @FXML private TextField usernameTextField;

    @FXML private Label showPass,l1,l2,l3;




    public void showPasswordButtonOnAction(ActionEvent event){
        String password = passwordField.getText();
        showPass.setText("Password- " + password);
    }




    public void signInOnAction(ActionEvent event) {

        if (passwordField.getText().isEmpty() && usernameTextField.getText().isEmpty()) {
            l3.setText("");

            AlertBox.display(
                    "Warning  !!!",
                    "\n\t\t>>> Error <<<\n" +
                            "\t________________________________\n"+
                            "\nMust enter both of your \n-->UserName and \n-->Password." +
                            "\n");

            l2.setText("Must Enter Your Password");
            l1.setText("Must Enter Your UserName");
        } else if (usernameTextField.getText().isEmpty()) {
            l1.setText("Must Enter Your UserName");
            l3.setText("");
            l2.setText("");
        } else if (passwordField.getText().isEmpty()) {
            l3.setText("");
            l2.setText("Must Enter Your Password");
            l1.setText("");
        }else if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
            Connection connection = createConnectionDemo.createConnection();


            try{
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("  SELECT count(1) FROM abcd_bank.users where userName = '"+ usernameTextField.getText() +"' and password = '"+ passwordField.getText() +"';  ");

                while (resultSet.next()){

                        if (resultSet.getInt(1) == 1){
                            System.out.println("Congratulation !! Successfully LogIn !!");


                            AlertBox.display(
                                    "Successful !!!",
                                    "\n\t>>> Congratulation <<<\n" +
                                            "\t________________________________\n"+
                                            "\nSuccessfully logged into your account." +
                                            "\n");


                            String accountNumber_text = usernameTextField.getText();

                            FXMLLoader Loader = new FXMLLoader();
                            Loader.setLocation(getClass().getResource("welcomeToProfile.fxml"));
                            try {
                                Loader.load();
                            } catch (IOException e) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,null,e);
                            }finally {
                                Stage stage = (Stage) signIn.getScene().getWindow();
                                stage.close();
                            }

                            WelcomeToProfileController s = Loader.getController();
                            s.setUserNameTextField(accountNumber_text);

                            Parent p = Loader.getRoot();
                            Stage stage2 = new Stage();
                            stage2.getIcons().add(new Image("/sample/3rd.jpg"));
                            stage2.setTitle("Welcome to Your Profile...");
                            stage2.resizableProperty().setValue(false);
                            stage2.initModality(Modality.APPLICATION_MODAL);
                            Scene scene = new Scene(p);
                            scene.getStylesheets().add("/Style/style.css");
                            stage2.setScene(scene);
                            stage2.showAndWait();


                            usernameTextField.setText("");passwordField.setText("");
                            l1.setText("");
                            l2.setText("");
                            l3.setText("");
                            showPass.setText("");




                        }else {
                            l1.setText("");
                            l2.setText("");

                            AlertBox.display(
                                    "Warning  !!!",
                                    "\n\t\t>>> Error <<<\n" +
                                            "\t________________________________\n"+
                                            "\nInvalid UserName or Password." +
                                            "\n");

                            l3.setText("Invalid UserName or Password!!!");
                            System.out.println("Invalid UserName or Password!!!");
                        }
                    }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }




    public void signUpForBankAccountOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("CreateBankAccount.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Create A Bank Account");
            primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));

            Scene scene = new Scene(root, 550, 900); //"/image/login.png"
            scene.getStylesheets().add("/Style/CreateBankAccount.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }


    public void signUpOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("SignUp");
            primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));

            Scene scene = new Scene(root, 355, 392); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }



    public void forgotPasswordOnAction(ActionEvent event) {

        try{
            Parent root = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Recover Account");
            primaryStage.getIcons().add(new Image("/sample/3rd.jpg"));
            Scene scene = new Scene(root, 355, 392); //"/image/login.png"
            scene.getStylesheets().add("/Style/style.css");
            primaryStage.initModality(Modality.APPLICATION_MODAL); // Disable Others all Window
            primaryStage.resizableProperty().setValue(false); // Disable Mazimuise Button
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            showPass.setText("");
        }
    }

    public void refreshImage(MouseEvent mouseEvent) {
        l1.setText("");
        l2.setText("");
        l3.setText("");
        showPass.setText("");
        usernameTextField.setText("");
        passwordField.setText("");
    }

    public void deleteAnAccountAccountOnAction(ActionEvent event) {

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("DeleteAnAccount.fxml"));
        try {
            Loader.load();
        } catch (IOException e) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE,null,e);
        }

        Parent p = Loader.getRoot();
        Stage stage2 = new Stage();
        stage2.setTitle("Delete an Account");
        stage2.getIcons().add(new Image("/sample/3rd.jpg"));
        stage2.resizableProperty().setValue(false);
        stage2.initModality(Modality.APPLICATION_MODAL);
        Scene scene2 = new Scene(p);
        stage2.setScene(scene2);
        stage2.showAndWait();

    }
}
