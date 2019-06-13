package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterControler implements Initializable, UiLoaderCallback {

    @FXML
    private AnchorPane main_frame;

    @FXML
    private JFXButton btn_next;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_next.setOnAction(event -> {
            loadUI("/sample/layout/table_choose_activity.fxml", main_frame);
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
