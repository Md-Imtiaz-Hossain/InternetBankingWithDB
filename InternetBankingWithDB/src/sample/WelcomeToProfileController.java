package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class WelcomeToProfileController  {

    @FXML private TextField firstName,lastName,email,phone,accountNumber,branch,balance,nid,userName,birthDate,country;

    @FXML private Button okButton;


    public void setUserNameTextField(String accountNumber_text) {
        this.userName.setText(accountNumber_text);
        System.out.println(accountNumber_text);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/abcd_bank?verifyServerCertificate=false&useSSL=true",
                    "root",
                    "rootDB600@660");
            System.out.println("Successfully connected with abcd_bank !!!");
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from abcd_bank.users where userName='"+accountNumber_text+"'");

            while (set.next()){
                firstName.setText(set.getString(3));
                lastName.setText(set.getString(4));
                email.setText(set.getString(5));
                phone.setText(set.getString(6));
                accountNumber.setText(set.getString(7));
                branch.setText(set.getString(9));
                balance.setText(set.getString(11));
                nid.setText(set.getString(13));
                birthDate.setText(set.getString(10));
                country.setText(set.getString(8));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public void okOnAction(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
