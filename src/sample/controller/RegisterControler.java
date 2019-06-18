package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_next.setOnAction(event -> {

            if(tf_nama.getText().toString().trim().isEmpty()){
                System.out.println("JANGAN KOSONG");
                System.out.println("erpam");

            }else{
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
