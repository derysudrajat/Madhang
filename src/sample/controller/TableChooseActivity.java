package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableChooseActivity implements Initializable, UiLoaderCallback, EventHandler {
    @FXML
    private AnchorPane main_frame;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXButton btn_done;
    private JFXButton selectedButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_done.setOnAction(this::handle);
        btn_back.setOnAction(this::handle);
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        }catch (IOException ignored){

        }
    }

    @FXML
    void tableSelected(ActionEvent event) {
        if (selectedButton!=null){
            selectedButton.setStyle("-fx-background-color: #542d2d");
            selectedButton =(JFXButton)event.getTarget();
        }else {
            selectedButton =(JFXButton)event.getTarget();
        }
        JFXButton btn =(JFXButton)event.getTarget();
        btn.setStyle("-fx-background-color: #ffb600");

    }

    @Override
    public void handle(Event event) {
        if (event.getTarget().equals(btn_done)){
            loadUI("/sample/layout/menu_activity.fxml", main_frame);
        }
        if (event.getTarget().equals(btn_back)){
            loadUI("/sample/layout/register_activity.fxml",main_frame);
        }
    }
}
