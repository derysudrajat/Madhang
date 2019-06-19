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
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.helper.Popup;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaymentActivity implements Initializable, UiLoaderCallback, EventHandler {
    @FXML
    private AnchorPane main_frame;
    @FXML
    private StackPane main_stack_pane;
    @FXML
    private JFXButton btn_back;
    @FXML
    private Text txt_table;
    @FXML
    private Text txt_customer;
    @FXML
    private Text txt_total_items;
    @FXML
    private Text txt_total_price;
    @FXML
    private Text list_items;
    @FXML
    private JFXButton btn_make_order;
    private Popup pop = new Popup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_back.setOnAction(this::handle);
        btn_make_order.setOnAction(this::handle);
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)) {
            loadUI("/sample/layout/cart_activity.fxml", main_frame);
        }
        if (et.equals(btn_make_order)) {
            pop.poup2Dialog(main_stack_pane, "Make Order",
                    "Terimakasih telah memasan", "Ok", "Cancel",
                    "Confirm", "Silahan Menunggu", "OK");
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
