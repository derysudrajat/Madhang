package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuCategoryActivity implements Initializable, UiLoaderCallback, EventHandler {
    @FXML
    private AnchorPane main_frame;
    @FXML
    private AnchorPane list_frame;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXButton btn_cart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_back.setOnAction(this::handle);
        btn_cart.setOnAction(this::handle);
        loadUI("/sample/layout/menu_activity.fxml", list_frame);
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        }catch (IOException ignored){

        }
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)){
            loadUI("/sample/layout/table_choose_activity.fxml",main_frame);
        }
    }
}
