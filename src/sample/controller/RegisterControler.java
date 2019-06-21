package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import sample.helper.DBHelper;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class RegisterControler implements Initializable, UiLoaderCallback {

    @FXML
    private AnchorPane main_frame;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXTextField tf_nama;
    RequiredFieldValidator validator = new RequiredFieldValidator();
    private DBHelper dbHelper = new DBHelper();
    private Connection connection;

    @FXML
    void textValidate(KeyEvent event) {
        if (tf_nama.getText().equals("")) {
            validator.setMessage("Isi nama terlebih dahulu");
            tf_nama.getValidators().add(validator);
            tf_nama.validate();
        } else {
            tf_nama.resetValidation();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_next.setOnAction(event -> {
            if (tf_nama.getText().trim().isEmpty()) {
                validator.setMessage("Isi nama terlebih dahulu");
                tf_nama.getValidators().add(validator);
                tf_nama.validate();
            }else{
                tf_nama.resetValidation();
                dbHelper.setCustomerName(connection, tf_nama.getText());
                loadUI("/sample/layout/table_choose_activity.fxml", main_frame);
            }
        });
        try {
            connection = dbHelper.getConnection();
        } catch (Exception e) {
            System.out.println(RegisterControler.class.getSimpleName() + " Exc: " + e.getMessage());
        }
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        }catch (IOException ignored){

        }
    }


}
