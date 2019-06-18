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
import javafx.scene.layout.StackPane;
import sample.helper.Popup;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterControler implements Initializable, UiLoaderCallback {

    @FXML
    private AnchorPane main_frame;

    @FXML
    private JFXButton btn_next;

    @FXML
    private JFXTextField tf_nama;
    @FXML
    private StackPane main_stackpane;
    RequiredFieldValidator validator = new RequiredFieldValidator();

    @FXML
    void textValidate(KeyEvent event) {
        if (tf_nama.getText().equals("")){
            validator.setMessage("Name Can't be Empty");
            tf_nama.getValidators().add(validator);
            tf_nama.validate();
        }else{
            tf_nama.resetValidation();
        }

    }

    //pop up
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Popup p = new Popup();
        btn_next.setOnAction(event -> {

            if(tf_nama.getText().toString().trim().isEmpty()){
                p.toast(main_stackpane, "JANGAN KOSONG");
                System.out.println("JANGAN KOSONG");

            }else{
                tf_nama.resetValidation();
                System.out.println(tf_nama.getText().toString().trim());
                loadUI("/sample/layout/table_choose_activity.fxml", main_frame);
            }

        });
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
