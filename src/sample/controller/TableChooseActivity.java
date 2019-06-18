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
import javafx.scene.layout.StackPane;
import sample.helper.Popup;
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
    @FXML
    private StackPane main_Stackpane;

    String nomor_kursi = null;

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
        } catch (IOException ignored) {

        }
    }

    @FXML
    void tableSelected(ActionEvent event) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: #542d2d");
            selectedButton = (JFXButton) event.getTarget();
        } else {
            selectedButton = (JFXButton) event.getTarget();
        }
        JFXButton btn = (JFXButton) event.getTarget();
        btn.setStyle("-fx-background-color: #ffb600");
        nomor_kursi = btn.getText(); //menampung kursi yg di pilih ke variabel nomor_kursi
    }

    @Override
    public void handle(Event event) {
        Popup p = new Popup();
        if (event.getTarget().equals(btn_done)) {
            if (nomor_kursi != null) {
                System.out.println("Nomor Kursi : " + nomor_kursi);
                loadUI("/sample/layout/menu_activity.fxml", main_frame);
            } else {
                p.toast(main_Stackpane, "JANGAN KOSONG");
                System.out.println("Silahkan Pilih Kursi");
            }
        }
        if (event.getTarget().equals(btn_back)) {
            loadUI("/sample/layout/register_activity.fxml", main_frame);
        }
    }
}
