package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample.adapter.CartAdapter;
import sample.entity.Foods;
import sample.helper.Popup;
import sample.helper.UiLoaderCallback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartActivity implements Initializable, UiLoaderCallback, EventHandler {
    @FXML
    private AnchorPane main_frame;
    @FXML
    private JFXButton btn_back;
    @FXML
    private JFXListView<Foods> list_view;
    @FXML
    private StackPane main_stackpane;
    @FXML
    private JFXButton btn_menu;
    @FXML
    private JFXButton btn_pay;
    private Foods foods;
    private ObservableList<Foods> foodsObservableList;
    private Popup pop = new Popup();

    public CartActivity() {
        LoadData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshData();
        btn_back.setOnAction(this::handle);
        btn_pay.setOnAction(this::handle);
        btn_menu.setOnAction(this::handle);
    }

    void LoadData() {
        foodsObservableList = FXCollections.observableArrayList();
        foodsObservableList.addAll(
                new Foods("Jus Kambing", "soto ayam adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Telur", "soto Kambing adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Apa aja", "soto kebo adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Itu", "soto pitik adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5),
                new Foods("Jus Ini", "soto sapi adalah makanan yang paling enak di sini bro pokoknya kamu haru belui", 10, (float) 4.5)
        );
    }

    void refreshData() {
        list_view.setItems(foodsObservableList);
        list_view.setCellFactory(foodListView -> new CartAdapter(list_view, main_stackpane));
    }

    @Override
    public void loadUI(String layout, AnchorPane pane) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(layout));
            pane.getChildren().setAll(root);
        } catch (IOException ignored) {

        }
    }

    @Override
    public void handle(Event event) {
        EventTarget et = event.getTarget();
        if (et.equals(btn_back)) {
            loadUI("/sample/layout/menu_activity.fxml", main_frame);
        }
        if (et.equals(btn_menu)) {
            pop.poup2Menu(main_stackpane, btn_menu, "Delete      ", "Delete All ");
        }
        if (et.equals(btn_pay)) {
            loadUI("/sample/layout/payment_activity.fxml", main_frame);
        }
    }
}